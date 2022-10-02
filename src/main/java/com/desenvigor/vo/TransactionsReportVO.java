package com.desenvigor.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionsReportVO {
    private LocalDate time;
    private BigDecimal value;
    private String client;
    private String account;

    public TransactionsReportVO(LocalDate time, BigDecimal value, String client, String account) {
        this.time = time;
        this.value = value;
        this.client = client;
        this.account = account;
    }

    @Override
    public String toString() {
        return "TransactionsReportVO{" +
                "time=" + time +
                ", value=" + value +
                ", client='" + client + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

    public LocalDate getTime() {
        return time;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getClient() {
        return client;
    }

    public String getAccount() {
        return account;
    }
}
