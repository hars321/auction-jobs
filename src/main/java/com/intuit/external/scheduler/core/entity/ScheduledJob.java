package com.intuit.external.scheduler.core.entity;


import com.intuit.external.scheduler.core.enums.JobStatus;
import lombok.Data;

import java.util.Map;


@Data
public class ScheduledJob {

    public ScheduledJob() {
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    private Long id;

    private Long triggerTime;

    private String url;

    private String requestBody;

    private JobStatus status;

    private String requestMethod;

    private Map<String, String> requestParams;
    // Getters and Setters


    public ScheduledJob(Long triggerTime, String url, String requestBody, String requestMethod, Map<String, String> requestParams) {
        this.triggerTime = triggerTime;
        this.url = url;
        this.requestBody = requestBody;
        this.requestMethod = requestMethod;
        this.requestParams = requestParams;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }
}
