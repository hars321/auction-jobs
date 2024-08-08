package com.intuit.service;

import com.intuit.client.AuctionClient;
import com.intuit.external.auction.core.entity.Auction;
import com.intuit.external.auction.core.enums.AuctionStatus;
import com.intuit.external.auction.core.request.AuctionSearchRequest;
import com.intuit.util.TimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AuctionJobsService {

    @Autowired
    private AuctionClient auctionClient;

    public void closeBiddingJob() {
        List<Auction> listedAuctions = getInProgressAuctions();
        if (CollectionUtils.isEmpty(listedAuctions)) {
            return;
        }
        listedAuctions.stream()
                .filter(auction -> !auction.getAuctionEndTimeStamp().equals(TimeUtils.getCurrentTimeStamp()))
                .map(auction -> auctionClient.updateAuctionStatus(auction.getId(), AuctionStatus.BIDDING_CLOSED.toString()));
    }

    public void startAuctionJob() {
        List<Auction> listedAuctions = getListedAuctions();
        if (CollectionUtils.isEmpty(listedAuctions)) {
            return;
        }
        listedAuctions.stream()
                .filter(auction -> !auction.getAuctionStartTimestamp().equals(TimeUtils.getCurrentTimeStamp()))
                .map(auction -> auctionClient.updateAuctionStatus(auction.getId(), AuctionStatus.IN_PROGRESS.toString()));
    }

    public void completeAuctionJob(List<Auction> auctions) {
        List<Auction> listedAuctions = auctions;
        if (CollectionUtils.isEmpty(listedAuctions)) {
            return;
        }
        listedAuctions.stream()
                .filter(auction -> !auction.getAuctionStartTimestamp().equals(TimeUtils.getCurrentTimeStamp()))
                .map(auction -> auctionClient.updateAuctionStatus(auction.getId(), AuctionStatus.AUCTION_COMPLETED.toString()));
    }

    private List<Auction> getListedAuctions() {
        AuctionSearchRequest auctionSearchRequest = new AuctionSearchRequest();
        auctionSearchRequest.setAuctionStatus(AuctionStatus.LISTED);
        return auctionClient.searchAuctions(auctionSearchRequest).getData();
    }

    private List<Auction> getInProgressAuctions() {
        AuctionSearchRequest auctionSearchRequest = new AuctionSearchRequest();
        auctionSearchRequest.setAuctionStatus(AuctionStatus.IN_PROGRESS);
        return auctionClient.searchAuctions(auctionSearchRequest).getData();
    }


}


