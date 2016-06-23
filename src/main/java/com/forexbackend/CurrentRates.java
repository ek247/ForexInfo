package com.forexbackend;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Entity class for rates. Just has all the info needed + getters and setters
  */
@Entity
@Table(name="current_rates")
public class CurrentRates{


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    public CurrentRates()
    {
        super();
    }

    public CurrentRates(ArrayList<Float> ask, ArrayList<Float> bid, ArrayList<Integer> direction, Timestamp date)
    {
        this.date = date;

        EURUSDAsk = ask.get(0);
        EURUSDBid = bid.get(0);
        EURUSDDirection = direction.get(0);

        USDJPYAsk = ask.get(1);
        USDJPYBid = bid.get(1);
        USDJPYDirection = direction.get(1);

        GBPUSDAsk = ask.get(2);
        GBPUSDBid = bid.get(2);
        GBPUSDDirection = direction.get(2);

        USDCHFAsk = ask.get(3);
        USDCHFBid = bid.get(3);
        USDCHFDirection = direction.get(3);

        EURCHFAsk = ask.get(4);
        EURCHFBid = bid.get(4);
        EURCHFDirection = direction.get(4);

        AUDUSDAsk = ask.get(5);
        AUDUSDBid = bid.get(5);
        AUDUSDDirection = direction.get(5);

        USDCADAsk = ask.get(6);
        USDCADBid = bid.get(6);
        USDCADDirection = direction.get(6);

        NZDUSDAsk = ask.get(7);
        NZDUSDBid = bid.get(7);
        NZDUSDDirection = direction.get(7);

        EURJPYAsk = ask.get(8);
        EURJPYBid = bid.get(8);
        EURJPYDirection = direction.get(8);

        GBPJPYAsk = ask.get(9);
        GBPJPYBid = bid.get(9);
        GBPJPYDirection = direction.get(9);

        EURGBPAsk = ask.get(10);
        EURGBPBid = bid.get(10);
        EURGBPDirection = direction.get(10);

        CHFJPYAsk = ask.get(11);
        CHFJPYBid = bid.get(11);
        CHFJPYDirection = direction.get(11);

    }

    @Column(name = "DatePosted")
    private Timestamp date;

    @Column(name = "EURUSDBid")
    private float EURUSDBid;

    @Column(name = "EURUSDAsk")
    private float EURUSDAsk;

    @Column(name = "EURUSDDirection")
    private float EURUSDDirection;

    @Column(name = "USDJPYBid")
    private float USDJPYBid;

    @Column(name = "USDJPYAsk")
    private float USDJPYAsk;

    @Column(name = "USDJPYDirection")
    private float USDJPYDirection;

    @Column(name = "GBPUSDBid")
    private float GBPUSDBid;

    @Column(name = "GBPUSDAsk")
    private float GBPUSDAsk;

    @Column(name = "GBPUSDDirection")
    private float GBPUSDDirection;

    @Column(name = "USDCHFBid")
    private float USDCHFBid;

    @Column(name = "USDCHFAsk")
    private float USDCHFAsk;

    @Column(name = "USDCHFDirection")
    private float USDCHFDirection;

    @Column(name = "EURCHFBid")
    private float EURCHFBid;

    @Column(name = "EURCHFAsk")
    private float EURCHFAsk;

    @Column(name = "EURCHFDirection")
    private float EURCHFDirection;

    @Column(name = "AUDUSDBid")
    private float AUDUSDBid;

    @Column(name = "AUDUSDAsk")
    private float AUDUSDAsk;

    @Column(name = "AUDUSDDirection")
    private float AUDUSDDirection;

    @Column(name = "USDCADBid")
    private float USDCADBid;

    @Column(name = "USDCADAsk")
    private float USDCADAsk;

    @Column(name = "USDCADDirection")
    private float USDCADDirection;

    @Column(name = "NZDUSDBid")
    private float NZDUSDBid;

    @Column(name = "NZDUSDAsk")
    private float NZDUSDAsk;

    @Column(name = "NZDUSDDirection")
    private float NZDUSDDirection;

    @Column(name = "EURJPYBid")
    private float EURJPYBid;

    @Column(name = "EURJPYAsk")
    private float EURJPYAsk;

    @Column(name = "EURJPYDirection")
    private float EURJPYDirection;

    @Column(name = "GBPJPYBid")
    private float GBPJPYBid;

    @Column(name = "GBPJPYAsk")
    private float GBPJPYAsk;

    @Column(name = "GBPJPYDirection")
    private float GBPJPYDirection;

    @Column(name = "EURGBPBid")
    private float EURGBPBid;

    @Column(name = "EURGBPAsk")
    private float EURGBPAsk;

    @Column(name = "EURGBPDirection")
    private float EURGBPDirection;

    @Column(name = "CHFJPYBid")
    private float CHFJPYBid;

    @Column(name = "CHFJPYAsk")
    private float CHFJPYAsk;

    @Column(name = "CHFJPYDirection")
    private float CHFJPYDirection;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public float getEURUSDBid() {
        return EURUSDBid;
    }

    public void setEURUSDBid(float EURUSDBid) {
        this.EURUSDBid = EURUSDBid;
    }

    public float getEURUSDAsk() {
        return EURUSDAsk;
    }

    public void setEURUSDAsk(float EURUSDAsk) {
        this.EURUSDAsk = EURUSDAsk;
    }

    public float getEURUSDDirection() {
        return EURUSDDirection;
    }

    public void setEURUSDDirection(float EURUSDDirection) {
        this.EURUSDDirection = EURUSDDirection;
    }

    public float getUSDJPYBid() {
        return USDJPYBid;
    }

    public void setUSDJPYBid(float USDJPYBid) {
        this.USDJPYBid = USDJPYBid;
    }

    public float getUSDJPYAsk() {
        return USDJPYAsk;
    }

    public void setUSDJPYAsk(float USDJPYAsk) {
        this.USDJPYAsk = USDJPYAsk;
    }

    public float getUSDJPYDirection() {
        return USDJPYDirection;
    }

    public void setUSDJPYDirection(float USDJPYDirection) {
        this.USDJPYDirection = USDJPYDirection;
    }

    public float getGBPUSDBid() {
        return GBPUSDBid;
    }

    public void setGBPUSDBid(float GBPUSDBid) {
        this.GBPUSDBid = GBPUSDBid;
    }

    public float getGBPUSDAsk() {
        return GBPUSDAsk;
    }

    public void setGBPUSDAsk(float GBPUSDAsk) {
        this.GBPUSDAsk = GBPUSDAsk;
    }

    public float getGBPUSDDirection() {
        return GBPUSDDirection;
    }

    public void setGBPUSDDirection(float GBPUSDDirection) {
        this.GBPUSDDirection = GBPUSDDirection;
    }

    public float getUSDCHFBid() {
        return USDCHFBid;
    }

    public void setUSDCHFBid(float USDCHFBid) {
        this.USDCHFBid = USDCHFBid;
    }

    public float getUSDCHFAsk() {
        return USDCHFAsk;
    }

    public void setUSDCHFAsk(float USDCHFAsk) {
        this.USDCHFAsk = USDCHFAsk;
    }

    public float getUSDCHFDirection() {
        return USDCHFDirection;
    }

    public void setUSDCHFDirection(float USDCHFDirection) {
        this.USDCHFDirection = USDCHFDirection;
    }

    public float getEURCHFBid() {
        return EURCHFBid;
    }

    public void setEURCHFBid(float EURCHFBid) {
        this.EURCHFBid = EURCHFBid;
    }

    public float getEURCHFAsk() {
        return EURCHFAsk;
    }

    public void setEURCHFAsk(float EURCHFAsk) {
        this.EURCHFAsk = EURCHFAsk;
    }

    public float getEURCHFDirection() {
        return EURCHFDirection;
    }

    public void setEURCHFDirection(float EURCHFDirection) {
        this.EURCHFDirection = EURCHFDirection;
    }

    public float getAUDUSDBid() {
        return AUDUSDBid;
    }

    public void setAUDUSDBid(float AUDUSDBid) {
        this.AUDUSDBid = AUDUSDBid;
    }

    public float getAUDUSDAsk() {
        return AUDUSDAsk;
    }

    public void setAUDUSDAsk(float AUDUSDAsk) {
        this.AUDUSDAsk = AUDUSDAsk;
    }

    public float getAUDUSDDirection() {
        return AUDUSDDirection;
    }

    public void setAUDUSDDirection(float AUDUSDDirection) {
        this.AUDUSDDirection = AUDUSDDirection;
    }

    public float getUSDCADBid() {
        return USDCADBid;
    }

    public void setUSDCADBid(float USDCADBid) {
        this.USDCADBid = USDCADBid;
    }

    public float getUSDCADAsk() {
        return USDCADAsk;
    }

    public void setUSDCADAsk(float USDCADAsk) {
        this.USDCADAsk = USDCADAsk;
    }

    public float getUSDCADDirection() {
        return USDCADDirection;
    }

    public void setUSDCADDirection(float USDCADDirection) {
        this.USDCADDirection = USDCADDirection;
    }

    public float getNZDUSDBid() {
        return NZDUSDBid;
    }

    public void setNZDUSDBid(float NZDUSDBid) {
        this.NZDUSDBid = NZDUSDBid;
    }

    public float getNZDUSDAsk() {
        return NZDUSDAsk;
    }

    public void setNZDUSDAsk(float NZDUSDAsk) {
        this.NZDUSDAsk = NZDUSDAsk;
    }

    public float getNZDUSDDirection() {
        return NZDUSDDirection;
    }

    public void setNZDUSDDirection(float NZDUSDDirection) {
        this.NZDUSDDirection = NZDUSDDirection;
    }

    public float getEURJPYBid() {
        return EURJPYBid;
    }

    public void setEURJPYBid(float EURJPYBid) {
        this.EURJPYBid = EURJPYBid;
    }

    public float getEURJPYAsk() {
        return EURJPYAsk;
    }

    public void setEURJPYAsk(float EURJPYAsk) {
        this.EURJPYAsk = EURJPYAsk;
    }

    public float getEURJPYDirection() {
        return EURJPYDirection;
    }

    public void setEURJPYDirection(float EURJPYDirection) {
        this.EURJPYDirection = EURJPYDirection;
    }

    public float getGBPJPYBid() {
        return GBPJPYBid;
    }

    public void setGBPJPYBid(float GBPJPYBid) {
        this.GBPJPYBid = GBPJPYBid;
    }

    public float getGBPJPYAsk() {
        return GBPJPYAsk;
    }

    public void setGBPJPYAsk(float GBPJPYAsk) {
        this.GBPJPYAsk = GBPJPYAsk;
    }

    public float getGBPJPYDirection() {
        return GBPJPYDirection;
    }

    public void setGBPJPYDirection(float GBPJPYDirection) {
        this.GBPJPYDirection = GBPJPYDirection;
    }

    public float getEURGBPBid() {
        return EURGBPBid;
    }

    public void setEURGBPBid(float EURGBPBid) {
        this.EURGBPBid = EURGBPBid;
    }

    public float getEURGBPAsk() {
        return EURGBPAsk;
    }

    public void setEURGBPAsk(float EURGBPAsk) {
        this.EURGBPAsk = EURGBPAsk;
    }

    public float getEURGBPDirection() {
        return EURGBPDirection;
    }

    public void setEURGBPDirection(float EURGBPDirection) {
        this.EURGBPDirection = EURGBPDirection;
    }

    public float getCHFJPYBid() {
        return CHFJPYBid;
    }

    public void setCHFJPYBid(float CHFJPYBid) {
        this.CHFJPYBid = CHFJPYBid;
    }

    public float getCHFJPYAsk() {
        return CHFJPYAsk;
    }

    public void setCHFJPYAsk(float CHFJPYAsk) {
        this.CHFJPYAsk = CHFJPYAsk;
    }

    public float getCHFJPYDirection() {
        return CHFJPYDirection;
    }

    public void setCHFJPYDirection(float CHFJPYDirection) {
        this.CHFJPYDirection = CHFJPYDirection;
    }



    @Override
    public String toString() {
        return "CurrentRates{" +
                "id=" + id +
                ", date=" + date +
                ", EURUSDBid=" + EURUSDBid +
                ", EURUSDAsk=" + EURUSDAsk +
                ", EURUSDDirection=" + EURUSDDirection +
                ", USDJPYBid=" + USDJPYBid +
                ", USDJPYAsk=" + USDJPYAsk +
                ", USDJPYDirection=" + USDJPYDirection +
                ", GBPUSDBid=" + GBPUSDBid +
                ", GBPUSDAsk=" + GBPUSDAsk +
                ", GBPUSDDirection=" + GBPUSDDirection +
                ", USDCHFBid=" + USDCHFBid +
                ", USDCHFAsk=" + USDCHFAsk +
                ", USDCHFDirection=" + USDCHFDirection +
                ", EURCHFBid=" + EURCHFBid +
                ", EURCHFAsk=" + EURCHFAsk +
                ", EURCHFDirection=" + EURCHFDirection +
                ", AUDUSDBid=" + AUDUSDBid +
                ", AUDUSDAsk=" + AUDUSDAsk +
                ", AUDUSDDirection=" + AUDUSDDirection +
                ", USDCADBid=" + USDCADBid +
                ", USDCADAsk=" + USDCADAsk +
                ", USDCADDirection=" + USDCADDirection +
                ", NZDUSDBid=" + NZDUSDBid +
                ", NZDUSDAsk=" + NZDUSDAsk +
                ", NZDUSDDirection=" + NZDUSDDirection +
                ", EURJPYBid=" + EURJPYBid +
                ", EURJPYAsk=" + EURJPYAsk +
                ", EURJPYDirection=" + EURJPYDirection +
                ", GBPJPYBid=" + GBPJPYBid +
                ", GBPJPYAsk=" + GBPJPYAsk +
                ", GBPJPYDirection=" + GBPJPYDirection +
                ", EURGBPBid=" + EURGBPBid +
                ", EURGBPAsk=" + EURGBPAsk +
                ", EURGBPDirection=" + EURGBPDirection +
                '}';
    }
}
