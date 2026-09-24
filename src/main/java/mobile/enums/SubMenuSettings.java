package mobile.enums;

import lombok.Getter;

import static mobile.enums.MainMenu.SYSTEM;

@Getter
public enum SubMenuSettings  {
    LANGUAGE(SYSTEM, "Language & region"),
    KEYBOARD(SYSTEM, "Keyboard"),
    USERS(SYSTEM, "Users");


    public MainMenu mainMenu;
    public String title;

    SubMenuSettings(MainMenu mainMenu, String title){
        this.mainMenu = mainMenu;
        this.title = title;
    }
}
