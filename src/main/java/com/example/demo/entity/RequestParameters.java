package com.example.demo.entity;

public class RequestParameters {
    private String period_id = "1sec";
    private String time_start;
    private int limit = 1;

    public RequestParameters(String time_start) {
        this.time_start = time_start;
    }

    public String getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "RequestParameters{" +
                "period_id='" + period_id + '\'' +
                ", time_start='" + time_start + '\'' +
                ", limit=" + limit +
                '}';
    }
}
