package com.ita.provapp.server.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Order {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
