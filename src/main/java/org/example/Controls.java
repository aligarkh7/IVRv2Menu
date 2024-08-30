package org.example;

import com.google.gson.JsonObject;

public class Controls {
    private final String CONTROLS = "controls";
    private final String MAINMENU = "mainMenu";
    private final String PREVIOUSMENU = "previousMenu";
    private final String REPEATMESSAGE = "repeatMessage";

    private final String ID = "id";
    private final String TYPE = "type";
    private final String NAME = "name";

    private String mainMenuID;
    private String mainMenuNAME;

    private String previousMenuID;
    private String previousMenuNAME;

    private String repeatMessageID;
    private String repeatMessageNAME;

    public Controls(JsonObject jsonObject) {
        this.mainMenuID = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(MAINMENU).get(ID).getAsString();
        this.mainMenuNAME = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(MAINMENU).get(NAME).getAsString();
        this.previousMenuID = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(PREVIOUSMENU).get(ID).getAsString();
        this.previousMenuNAME = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(PREVIOUSMENU).get(NAME).getAsString();
        this.repeatMessageID = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(REPEATMESSAGE).get(ID).getAsString();
        this.repeatMessageNAME = jsonObject.getAsJsonObject(CONTROLS).getAsJsonObject(REPEATMESSAGE).get(NAME).getAsString();
    }

    public String getMainMenuID() {
        return mainMenuID;
    }

    public String getMainMenuNAME() {
        return mainMenuNAME;
    }

    public String getPreviousMenuID() {
        return previousMenuID;
    }

    public String getPreviousMenuNAME() {
        return previousMenuNAME;
    }

    public String getRepeatMessageID() {
        return repeatMessageID;
    }

    public String getRepeatMessageNAME() {
        return repeatMessageNAME;
    }
}

