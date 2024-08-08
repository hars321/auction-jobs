package com.intuit.api;

import com.intuit.core.entity.ApiResponse;
import com.intuit.service.BiddingJobsService;
import com.intuit.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/bidding-jobs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BiddingJobsAPI {
    @Autowired
    private BiddingJobsService biddingJobsService;

    @PostMapping("/update-winning-bid")
    public ResponseEntity<ApiResponse<Object>> updateWinningBid() {
        try {
            biddingJobsService.updateWinningBidForAuction();
            return ResponseUtil.successResponse();
        } catch (Exception e) {
            return ResponseUtil.errorResponse("Failed to update winning bid", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
