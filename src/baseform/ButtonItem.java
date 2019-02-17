package baseform;

public class ButtonItem {
    protected String name;
    protected String caption;

    public ButtonItem(String name, String caption) {
        this.name = name;
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String c) {
        this.caption = c;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
