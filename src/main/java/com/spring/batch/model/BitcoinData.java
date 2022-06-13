package com.spring.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "bitcoin_batch")
public class BitcoinData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "unix_timestamp")
    private String unix_timestamp;
    @Column(name = "date_time")
    private String date_time;
    @Column(name = "open")
    private String open;
    @Column(name = "high")
    private String high;
    @Column(name = "low")
    private String low;
    @Column(name = "close")
    private String close;
    @Column(name = "volume_btc")
    private String volume_btc;
    @Column(name = "volume_currency")
    private String volume_currency;
    @Column(name = "weighted_price")
    private String weighted_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnix_timestamp() {
        return unix_timestamp;
    }

    public void setUnix_timestamp(String unix_timestamp) {
        this.unix_timestamp = unix_timestamp;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume_btc() {
        return volume_btc;
    }

    public void setVolume_btc(String volume_btc) {
        this.volume_btc = volume_btc;
    }

    public String getVolume_currency() {
        return volume_currency;
    }

    public void setVolume_currency(String volume_currency) {
        this.volume_currency = volume_currency;
    }

    public String getWeighted_price() {
        return weighted_price;
    }

    public void setWeighted_price(String weighted_price) {
        this.weighted_price = weighted_price;
    }
}
