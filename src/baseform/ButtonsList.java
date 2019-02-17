package baseform;

import javax.swing.*;
import java.util.ArrayList;

public class ButtonsList {
    ArrayList<JButton>  buttons;

    public ButtonsList(){
        buttons=new ArrayList<>();
    }

    public ButtonsList(ArrayList<JButton> buttons){
        this.buttons = buttons;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<ButtonItem> buttons) {
        for(ButtonItem b:buttons){
            add(b);
        }
    }

    public void add(ButtonItem btn){
        buttons.add(createButton(btn));
    }

    protected JButton createButton(ButtonItem btn){
        JButton b=new JButton(btn.caption);
        b.setName(btn.name);
        return b;
    }

    public JButton getButton(String name){
        int indx=getIndex(name);
        if (indx>=0)
        return buttons.get(indx);
        else
            return null;
    }

    public int getIndex(String s){
        int i=0;
        boolean f=false;
        while(i<buttons.size()){
            if ((buttons.get(i).getName()).equals(s)) {
                f=true;
                break;
            }
            }
            if (f)  return i;
        else return -1;
    }

    public void remove(String s){
        int indx=getIndex(s);
        if (indx>=0) buttons.remove(indx);
    }

    public int getSize(){
        if (this.buttons!=null)
        return buttons.size();
        else
            return 0;
    }

    public JButton get(int indx){
        if ((buttons!=null)&&(buttons.size()>=indx)) return buttons.get(indx);
        else
            return null;
    }

    public void set(int indx, ButtonItem b){
        if (buttons!=null) buttons.set(indx, createButton(b));
    }


}
