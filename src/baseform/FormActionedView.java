package baseform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class FormActionedView extends FormBaseView implements IActionedForm{

    public ActionListener ButtonListener=null;

    public FormActionedView(){
        super();
    }

    public FormActionedView(String title){
        super(title);
    }

    public FormActionedView(JDialog p, String title, boolean modal){
        super(p,title,modal);
        //initForm();
    }

    public FormActionedView(JDialog p, String t, Dialog.ModalityType m){
        super(p,t,m);
    }

    public void setButtonListener(ActionListener bl){
        ArrayList<Component> cl=getAllComponents((Container) form);
        for (int i=0;i<cl.size();i++){
            if (cl.get(i) instanceof JButton)  ((JButton)cl.get(i)).addActionListener(bl);
        }
    }

    public void setKeyListener(KeyListener l){
        if (form!=null) form.addKeyListener(l);
    }

    public void setTotalKeyListener(KeyListener l){
        ArrayList<Component> cl=getAllComponents((Container) form);
        for (int i=0;i<cl.size();i++){
            if (cl.get(i) instanceof JComponent)  ((JComponent)cl.get(i)).addKeyListener(l);
        }
    }

    public static ArrayList<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        ArrayList<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }


}
