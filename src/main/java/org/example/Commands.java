package org.example;

import com.google.gson.JsonObject;

public class Commands {

    private final String MENU = "menu";
    private final String DAY = "day";
    private final String BALANCE = "balance";

    private JsonObject balance;
    private JsonObject day;
    private String message;

    public Commands(JsonObject jsonObject) {
        this.balance = jsonObject.getAsJsonObject(MENU).getAsJsonObject(BALANCE);
        this.day = jsonObject.getAsJsonObject(MENU).getAsJsonObject(DAY);
    }

    public void containsCommand(Menu object, Client client) {
        message = object.getMessage();

        if (message.contains("{day}")) {
            message = message.replace("{day}", getDay());
        }

        if (message.contains("{clientName}")) {
            message = message.replace("{clientName}", client.getName());
        }

        if (message.contains("{balance}")) {
            message = message.replace("{balance}", getBalance(client));
        }

        if (message.contains("{internetTraffic}")) {
            message = message.replace("{internetTraffic}", client.toStringInternetTraffic());
        }

        if (!message.isEmpty()) {
            System.out.println(message);
        }
    }

    private String getDay() {
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        int hour = currentDateTime.getHour();
        if (hour > 3 && hour < 7) {
            return day.get("goodNight").getAsString();
        } else if (hour > 6 && hour < 12) {
            return day.get("goodMorning").getAsString();
        } else if (hour > 11 && hour < 17) {
            return day.get("goodAfternoon").getAsString();
        } else if (hour > 16 && hour < 24) {
            return day.get("goodEvening").getAsString();
        } else if (hour > 23 || hour < 4) {
            return day.get("goodNight").getAsString();
        }
        return "";
    }

    private String getBalance(Client client) {
        if (balance.get("threshold").getAsInt() > client.getBalance()) {
            return balance.get("lessThan").getAsString().replace("{threshold}", balance.get("threshold").getAsString());
        }
        return balance.get("default").getAsString().replace("{balance}", String.valueOf(client.getBalance()));
    }
}
