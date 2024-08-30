package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    private static Client client;
    private static Menu mainMenu;
    private static Controls controls;
    private static Commands commands;
    private static JsonObject menuConfig;
    private static Scanner sc = new Scanner(System.in);
    private static final String MENU = "menu";

    public static void main(String[] args) {
        loadMenuConfig();
        loadClientData();
        displayMainMenu(mainMenu);
    }

    private static void loadClientData() {
        client = new Client(menuConfig);
    }

    private static void loadMenuConfig() {
        try (FileReader reader = new FileReader("menu.json")) {
            menuConfig = new Gson().fromJson(reader, JsonObject.class);
            mainMenu = new Menu(menuConfig.getAsJsonObject(MENU));
            controls = new Controls(menuConfig);
            commands = new Commands(menuConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayMainMenu(Menu menu) {
        String name = menu.getName();
        System.out.println(name);

        commands.containsCommand(menu, client);

        menu.printOptions();

        commandMenu(menu);
    }

    private static void commandMenu(Menu menu) {
        String command = sc.nextLine();

        if (controls.getMainMenuID().equals(command)) {
            System.out.println(controls.getMainMenuNAME());
            displayMainMenu(mainMenu);
            return;
        }

        if (controls.getPreviousMenuID().equals(command)) {
            if (mainMenu.getId().equals(menu.getId())) {
                displayMainMenu(mainMenu);
                return;
            } else {
                System.out.println(controls.getPreviousMenuNAME());
                return;
            }
        }

        if (controls.getRepeatMessageID().equals(command)) {
            System.out.println(controls.getRepeatMessageNAME());
            displayMainMenu(menu);
            return;
        }

        if (menu.containsOptions(command)) {
            displayMainMenu(new Menu(menu.getOptionAsObject(command)));
            displayMainMenu(menu);
            return;
        } else {
            displayMainMenu(menu);
        }
    }
}
