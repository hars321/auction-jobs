package com.intuit.api;

import com.intuit.core.entity.ApiResponse;
import com.intuit.service.AuctionJobsService;
import com.intuit.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auction-jobs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuctionJobsAPI {

    @Autowired
    private AuctionJobsService auctionJobsService;

    @PostMapping("/start-bidding")
    public ResponseEntity<ApiResponse<Object>> startBidding() {
        try {
            auctionJobsService.startAuctionJob();
            return ResponseUtil.successResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Failed to update status of listed auctions", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/close-bidding")
    public ResponseEntity<ApiResponse<Object>> closeBidding() {
        try {
            auctionJobsService.closeBiddingJob();
            return ResponseUtil.successResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Failed to update status of auctions in progress", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
