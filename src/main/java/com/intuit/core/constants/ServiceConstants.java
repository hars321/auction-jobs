package com.intuit.core.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceConstants {
    public static final String AUCTION_UPDATE_STATUS_API_PATH = "http://localhost:9091/api/v1/auctions/update";
    public static final String AUCTION_UPDATE_STATUS_API_METHOD = "PUT";

    public static final String BIDDING_FINAL_BID_UPDATE_STATUS_API_PATH = "http://localhost:9092/api/v1/bidding/accepted-bid";
    public static final String BIDDING_FINAL_BID_UPDATE_STATUS_API_METHOD = "GET";
}
