package com.intuit.client;

import com.intuit.core.entity.ApiResponse;
import com.intuit.external.auction.core.entity.Auction;
import com.intuit.external.auction.core.request.AuctionSearchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "auction-service-client", url = "http://localhost:9091/")
public interface AuctionClient {

    @PostMapping("/api/v1/auctions/new")
    ApiResponse<Auction> createAuction(@RequestBody Auction auction);

    @PutMapping("/api/v1/auctions/update")
    ApiResponse<Auction> updateAuctionStatus(
            @RequestParam("auctionId") String auctionId,
            @RequestParam("auctionStatus") String auctionStatus);

    @PostMapping("/api/v1/auctions/search")
    ApiResponse<List<Auction>> searchAuctions(@RequestBody AuctionSearchRequest auctionSearchRequest);

    @GetMapping("/api/v1/auctions/id")
    ApiResponse<Auction> getAuctionById(@RequestParam("auctionId") String auctionId);
}
