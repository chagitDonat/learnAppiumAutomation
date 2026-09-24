package mobile.enums;

public enum DisplayMenu implements IMenu{

    BRIGHTNESS( "Brightness level", 0),
    SCREEN_TIMEOUT( "Screen timeout", 2),
    DISPLAY_SIZE_TEXT( "Display size and text", 5);


    public String title;
    int index;

    DisplayMenu(String t, int i){
        this.title = t;
        this.index = i;
    }

    public String getTitle(){
        return title;
    }

    public int getIndex(){
        return index;
    }
}
