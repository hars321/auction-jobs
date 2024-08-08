package com.intuit.client;

import com.intuit.core.entity.ApiResponse;

import com.intuit.external.scheduler.core.entity.ScheduledJob;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "scheduler-service", url = "http://localhost:9010/")
public interface SchedulerFeignClient {

    @PostMapping("/api/v1/scheduler/new")
    ApiResponse<Object> scheduleJob(@RequestBody ScheduledJob scheduledJob);

    @GetMapping("/api/v1/scheduler")
    ApiResponse<List<ScheduledJob>> getActiveJobs();
}
