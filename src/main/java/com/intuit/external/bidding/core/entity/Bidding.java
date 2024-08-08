package com.intuit.external.bidding.core.entity;

import com.intuit.bidding.core.enums.BidStatus;

import lombok.Data;

@Data
public class Bidding {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public Bidding() {
    }

    public Bidding(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getBidPrice() {
        return bidPrice;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public Bidding(String id, String auctionId, String userId, Integer bidPrice, BidStatus bidStatus, Long timeStamp) {
        this.id = id;
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidPrice = bidPrice;
        this.bidStatus = bidStatus;
        this.timeStamp = timeStamp;
    }

    private String id;
    private String auctionId;
    private String userId;
    private Integer bidPrice;
    private BidStatus bidStatus;
    private Long timeStamp;
}
