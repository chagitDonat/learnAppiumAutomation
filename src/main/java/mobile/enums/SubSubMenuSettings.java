package mobile.enums;

import static mobile.enums.MainMenu.SYSTEM;
import static mobile.enums.SubMenuSettings.LANGUAGE;

public enum SubSubMenuSettings {

    SYSTEM_LANGUAGES(SYSTEM,  LANGUAGE, "System Languages"),
    APP_LANGUAGES(SYSTEM, LANGUAGE, "App languages"),
    FIRST_DAY_OFWEEK(SYSTEM, LANGUAGE, "First day of week");


    public MainMenu mainMenu;
    public SubMenuSettings subMenuSettings;
    public String title;

    SubSubMenuSettings(MainMenu mainMenu, SubMenuSettings subMenuSettings, String title){
        this.mainMenu = mainMenu;
        this.subMenuSettings = subMenuSettings;
        this.title = title;
    }
}
