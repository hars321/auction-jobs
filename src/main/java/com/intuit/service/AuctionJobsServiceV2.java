package com.intuit.service;

import com.intuit.client.AuctionClient;
import com.intuit.client.SchedulerFeignClient;
import com.intuit.core.constants.ServiceConstants;
import com.intuit.external.auction.core.entity.Auction;
import com.intuit.external.auction.core.enums.AuctionStatus;
import com.intuit.external.auction.core.request.AuctionSearchRequest;
import com.intuit.external.scheduler.core.entity.ScheduledJob;
import com.intuit.util.TimeUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.intuit.util.TimeUtils.calculateDifferenceInHours;

@Service
public class AuctionJobsServiceV2 {

    @Autowired
    private AuctionClient auctionClient;
    @Autowired
    private SchedulerFeignClient schedulerClient;

    public List<Auction> getAuctionsListedInLastHour() {
        AuctionSearchRequest auctionSearchRequest = new AuctionSearchRequest();
        auctionSearchRequest.setAuctionStatus(AuctionStatus.LISTED);
        return filterByHour(auctionClient.searchAuctions(auctionSearchRequest).getData());
    }

    private List<Auction> filterByHour(List<Auction> auctionList) {
        return auctionList.stream()
                .filter(auction -> Objects.nonNull(auction.getListingTimestamp())
                        && calculateDifferenceInHours(auction.getListingTimestamp(), TimeUtils.getCurrentTimeStamp()) <= 1L)
                .collect(Collectors.toList());
    }



    public void scheduleBiddingStart() {
        List<Auction> auctions = getAuctionsListedInLastHour();
        for (Auction auction : auctions) {
            Map<String, String> requestParam = new HashMap<>();
            requestParam.put("auctionId", auction.getId());
            requestParam.put("auctionStatus", AuctionStatus.IN_PROGRESS.toString());
            ScheduledJob scheduledJob = new ScheduledJob();
            scheduledJob.setRequestBody("");
            schedulerClient.scheduleJob(new ScheduledJob(auction.getAuctionStartTimestamp(), ServiceConstants.AUCTION_UPDATE_STATUS_API_PATH,"", ServiceConstants.AUCTION_UPDATE_STATUS_API_METHOD,requestParam));
        }
    }

    public void scheduleBiddingClosing() {
        List<Auction> auctions = getAuctionsListedInLastHour();
        for (Auction auction : auctions) {
            Map<String, String> requestParam = new HashMap<>();
            requestParam.put("auctionId", auction.getId());
            requestParam.put("auctionStatus", AuctionStatus.BIDDING_CLOSED.toString());
            ScheduledJob scheduledJob = new ScheduledJob();
            scheduledJob.setRequestBody("");
            schedulerClient.scheduleJob(new ScheduledJob(auction.getAuctionEndTimeStamp(), ServiceConstants.AUCTION_UPDATE_STATUS_API_PATH,"", ServiceConstants.AUCTION_UPDATE_STATUS_API_METHOD,requestParam));
        }
    }
}


