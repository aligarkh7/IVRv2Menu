package org.example;

import com.google.gson.JsonObject;

public class Commands {

    private final String MENU = "menu";
    private final String COMMANDS = "commands";
    private final String DAY = "day";
    private final String MESSAGE = "message";
    private final String CLIENTNAME = "clientName";
    private final String BALANCE = "balance";
    private final String INTERNETTRAFFIC = "internetTraffic";
    private final String UNLIMITEDINTERNET = "unlimitedInternet";
    private final String DISCOUNT50 = "discount50";

    private JsonObject clientName;
    private JsonObject balance;
    private JsonObject internetTraffic;
    private JsonObject unlimitedInternet;
    private JsonObject discount50;
    private JsonObject day;
    private String message;

    public Commands(JsonObject jsonObject) {
        this.clientName = jsonObject.getAsJsonObject(MENU).getAsJsonObject(COMMANDS).getAsJsonObject(CLIENTNAME);
        this.balance = jsonObject.getAsJsonObject(MENU).getAsJsonObject(COMMANDS).getAsJsonObject(BALANCE);
        this.internetTraffic = jsonObject.getAsJsonObject(MENU).getAsJsonObject(COMMANDS).getAsJsonObject(INTERNETTRAFFIC);
        this.unlimitedInternet = jsonObject.getAsJsonObject(MENU).getAsJsonObject(COMMANDS).getAsJsonObject(UNLIMITEDINTERNET);
        this.discount50 = jsonObject.getAsJsonObject(MENU).getAsJsonObject(COMMANDS).getAsJsonObject(DISCOUNT50);
        this.day = jsonObject.getAsJsonObject(MENU).getAsJsonObject(DAY);
    }

    public void containsCommand(Menu object, Client client) {
        message = object.getMessage();
        if (object.getMessage().contains("{clientName}")) {
            printClientName(client);
        } else if (object.getMessage().contains("{balance}")) {
            printBalance(client);
        } else if (object.getMessage().contains("{internetTraffic}")) {
            printInternetTraffic(client);
        } else if (object.getMessage().contains("{unlimitedInternet}")) {
            printUnlimitedInternet();
        } else if (object.getMessage().contains("{discount50}")) {
            printDiscount50();
        }
    }

    private void printClientName(Client client){
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        int hour = currentDateTime.getHour();
        if (hour > 3 && hour < 7) {
            message = day.get("goodNight").getAsString() + "\n" + message;
        } else if (hour > 6 && hour < 12) {
            message = day.get("goodMorning").getAsString() + "\n" + message;
        } else if (hour > 11 && hour < 17) {
            message = day.get("goodAfternoon").getAsString() + "\n" + message;
        } else if (hour > 16 && hour < 24) {
            message = day.get("goodEvening").getAsString() + "\n" + message;
        } else if (hour > 23 || hour < 4) {
            message = day.get("goodNight").getAsString() + "\n" + message;
        }

        message = message.replace("{clientName}", clientName.get(MESSAGE).getAsString());
        message = message.replace("{clientName}", client.getName());
        System.out.println(message);
    }

    private void printBalance(Client client){
        if (client.getBalance() < balance.get("threshold").getAsInt()) {
            message = message.replace("{balance}", balance.getAsJsonObject("conditions").get("lessThan").getAsString());
        } else {
            message = message.replace("{balance}", balance.getAsJsonObject("conditions").get("default").getAsString());
            message = message.replace("{balance}", String.valueOf(client.getBalance()));
        }
        System.out.println(message);
    }

    private void printInternetTraffic(Client client){
        message = message.replace("{internetTraffic}", internetTraffic.get(MESSAGE).getAsString());
        message = message.replace("{internetTraffic}", client.toStringInternetTraffic());
        System.out.println(message);
    }

    private void printUnlimitedInternet(){
        message = message.replace("{unlimitedInternet}", unlimitedInternet.get(MESSAGE).getAsString());
        System.out.println(message);
    }

    private void printDiscount50(){
        message = message.replace("{discount50}", discount50.get(MESSAGE).getAsString());
        System.out.println(message);
    }
}

