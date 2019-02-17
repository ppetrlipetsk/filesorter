package baseform.baseformtypes;

import baseform.baseformtypes.FormPositionTypes;
import baseform.baseformtypes.FormSizeType;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public interface IBaseFrameForm {

    void setMinSize(Dimension d);
    void setMaxSize(Dimension d);
    void setResizable(boolean b);
    void setSize(Dimension d);
    void setTotalSize(Dimension d);
    Dimension getSize();
    void setTitle(String s);
    void setFormName(String name);
    String getFormName();
    void setDefaultCloseOperation(int c);

    void showForm();
    void showForm(boolean f);
    void closeForm();

    Container getFormContainer();
    JDialog getFormOwn();

    void showMessage(String msg, String title, int opt,Component pc);
    void showMessage(String msg, String title, int opt);
    int optionDialog(Container c, String s, String title, int opttype, int messtype, Icon icon, Object[] options);
    int showConfirm(String msg, String title, int opt);

    int getWidth();
    int getHeight();
    Point getFormCenteredPosition();
    void setLocationByPlatform(boolean b);
    //FormSizeType getFormSizeType();
    //void setFormSizeType(FormSizeType formSizeType);
    //FormPositionTypes getFormPosition();
    //void setFormPosition(FormPositionTypes t);
//    void setExitOnClose(boolean b);
//    boolean getExitOnClose();
    void setLocation(int x, int y);
    void setDefaultLookAndFeelDecorated(boolean b);
    void setWindowListener(WindowListener l);
    boolean isFormCreated();
    Rectangle getScreenBounds();
    boolean isFormVisible();
    void setFormVisible(boolean v);
   // JDialog getForm();
   // void setForm(JDialog d);


}
