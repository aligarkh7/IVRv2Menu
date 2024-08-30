package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Menu {

    private final String ID = "id";
    private final String TYPE = "type";
    private final String NAME = "name";
    private final String MESSAGE = "message";
    private final String OPTIONS = "options";
    private final String MENU = "menu";

    private JsonObject jsonObject;
    private String id;
    private String type;
    private String name;
    private String message;
    private JsonArray options;

    public Menu(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.id = jsonObject.get(ID).getAsString();
        this.type = jsonObject.get(TYPE).getAsString();
        this.name = jsonObject.get(NAME).getAsString();
        this.message = jsonObject.get(MESSAGE).getAsString();
        if (type.equals(MENU)) {
            this.options = jsonObject.getAsJsonArray(OPTIONS);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getOptionAsObject(String command) {
        for (JsonElement line : options) {
            if (line.getAsJsonObject().get(ID).getAsString().equals(command)) {
                return line.getAsJsonObject();
            }
        }
        return jsonObject;
    }

    public void printOptions() {
        if (type.equals(MENU)) {
            for (JsonElement line : options) {
                System.out.println(line.getAsJsonObject().get(ID).getAsString() + " " + line.getAsJsonObject().get(NAME).getAsString());
            }
        }
    }

    public boolean containsOptions(String command) {
        if (type.equals(MENU)) {
            for (JsonElement line : options) {
                if (line.getAsJsonObject().get(ID).getAsString().equals(command)) {
                    return true;
                }
            }
        }
        System.out.println("Неверный ввод. Попробуйте снова.");
        return false;
    }
}
