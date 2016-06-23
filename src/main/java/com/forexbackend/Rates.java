package com.forexbackend;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by worri on 6/23/2016.
 */
public class Rates {

    private String name;
    private float ask;
    private float bid;
    private Date time;

    public Rates(String name, float ask, float bid, Date time) {
        this.name = name;
        this.ask = ask;
        this.bid = bid;
        this.time = time;
    }

    public Rates() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
