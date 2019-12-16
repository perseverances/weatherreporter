package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

public class Focus extends DataSupport {
    private String weatherId;

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
