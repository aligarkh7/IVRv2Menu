package org.example;

import com.google.gson.JsonObject;

public class Client {
    private final String CLIENT = "client";
    private final String NAME = "name";
    private final String BALANCE = "balance";
    private final String INTERNETTRAFFIC = "internetTraffic";

    private String name;
    private int balance;
    private long internetTraffic;

    public Client(JsonObject jsonObject) {
        this.name = jsonObject.getAsJsonObject(CLIENT).get(NAME).getAsString();
        this.balance = jsonObject.getAsJsonObject(CLIENT).get(BALANCE).getAsInt();
        this.internetTraffic = jsonObject.getAsJsonObject(CLIENT).get(INTERNETTRAFFIC).getAsLong();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public String toStringInternetTraffic() {
        long bytes = internetTraffic;
        long gb = bytes / (1024 * 1024 * 1024);
        long mb = (bytes / (1024 * 1024)) % 1024;
        long kb = (bytes / 1024) % 1024;
        return gb + " ГБ " + mb + " МБ " + kb + " КБ";
    }
}
