package mobile.enums;

public enum MainMenu implements IMenu{
    GOOGLE("Google services", 0),
    NETWORK_INTERNET("Network & internet", 1),
    DISPLAY_TOUCH("Display & touch", 7),
    SYSTEM("System", 11);

    String title;
    int index;

    MainMenu(String t, int i){
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
