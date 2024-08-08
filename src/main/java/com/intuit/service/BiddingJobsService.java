package com.intuit.service;

import com.intuit.client.BiddingClient;
import com.intuit.client.SchedulerFeignClient;
import com.intuit.core.constants.ServiceConstants;
import com.intuit.external.auction.core.entity.Auction;
import com.intuit.external.auction.core.enums.AuctionStatus;
import com.intuit.external.scheduler.core.entity.ScheduledJob;
import com.intuit.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BiddingJobsService {

    @Autowired
    private AuctionJobsServiceV2 auctionJobsServiceV2;

    @Autowired
    private SchedulerFeignClient schedulerClient;


    public void updateWinningBidForAuction() {
        List<Auction> auctionList = auctionJobsServiceV2.getAuctionsListedInLastHour();
        for (Auction auction : auctionList) {
            Map<String, String> requestParamMap = new HashMap<>();
            requestParamMap.put("auctionId", auction.getId());
            schedulerClient.scheduleJob(new ScheduledJob(TimeUtils.addFifteenMinutes(auction.getAuctionEndTimeStamp()), ServiceConstants.BIDDING_FINAL_BID_UPDATE_STATUS_API_PATH,"", ServiceConstants.BIDDING_FINAL_BID_UPDATE_STATUS_API_METHOD,requestParamMap));
        }
    }
}
