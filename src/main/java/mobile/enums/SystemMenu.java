package mobile.enums;

import static mobile.enums.MainMenu.SYSTEM;

public enum SystemMenu implements IMenu {

    LANGUAGE( "Language & region", 0),
    KEYBOARD( "Keyboard", 1),
    USERS( "Users", 9),
    DATE_TIME("Date & time", 5);


    public String title;
    public int index;

    SystemMenu(String t, int i){
        this.title = t;
        this.index = i;
    }

    public String getTitle(){
        return title;
    }

    public int getIndex() {
        return index;
    }
}
