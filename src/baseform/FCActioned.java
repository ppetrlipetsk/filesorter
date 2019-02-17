package baseform;

import baseform.baseformtypes.FormPositionTypes;
import baseform.baseformtypes.FormSizeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static defines.StrCaptions.closeFormConfirmMessage;
import static defines.StrCaptions.confirmString;

public class FCActioned extends FCBase {
    protected IActionedForm _View;
    protected boolean keyListenerActive=true;

    public boolean isKeyListenerActive() {
        return keyListenerActive;
    }

    public void setKeyListenerActive(boolean keyListenerActive) {
        this.keyListenerActive = keyListenerActive;
    }

    public FCActioned(){
     super();
 }

    public FCActioned(IActionedForm _view){
        super(_view);
        _View=_view;
        //super();
        initController();
    }

    public void initController(){
        super.InitController(_View);
        addListeners(_View);
    }

    protected void addListeners(IActionedForm _view){
        //buttonListener bl=new buttonListener();
        _View.setButtonListener(new buttonListener());
        //keyListener k=new keyListener();
        _View.setKeyListener(new keyListener());
        _View.setWindowListener(new windowListener());
    }

    protected class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
                buttonsManager(e);
        }
    }

    protected class windowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            windowOpenedManager(e);
        }

        @Override
        public void windowClosing(WindowEvent e) {
            windowClosingManager(e);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            windowClosedManager(e);
        }

        @Override
        public void windowIconified(WindowEvent e) {
            windowIconifiedManager(e);
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            windowDeiconifiedManager(e);
        }

        @Override
        public void windowActivated(WindowEvent e) {
            windowActivatedManager(e);
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            windowDeactivatedManager(e);
        }
    }

    //======================================================

    public void windowOpenedManager(WindowEvent e) {

    }

    // Событие, которое вызывается, если нажата кнопка "close" в углу формы.

    public void windowClosingManager(WindowEvent e) {
        closePerformance();
    }

    public void windowClosedManager(WindowEvent e) {
    }

    public void windowIconifiedManager(WindowEvent e) {

    }

    public void windowDeiconifiedManager(WindowEvent e) {

    }

    public void windowActivatedManager(WindowEvent e) {


    }

    public void windowDeactivatedManager(WindowEvent e) {

    }

    //======================================================
    protected class keyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            keyTypedManager(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressedManager(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {

                    keyReleasedManager(e);
        }
    }

    protected void keyPressedManager(KeyEvent e) {
      //  _View.showMessage("KeyPressedAction","title",JOptionPane.YES_OPTION);
    }

    protected void keyTypedManager(KeyEvent e) {
     //   _View.showMessage("KeyTypedAction","title",JOptionPane.YES_OPTION);
    }

//    protected void okButtonAction(){
//        setResultCode("okPressed");
//        resultedCloseForm();
//    }
//
//    protected void cancelButtonAction(){
//        setResultCode("cancelPressed");
//        simpleCloseForm();
//    }

    
    protected void buttonsManager(ActionEvent e){
        if (e.getSource() instanceof JButton){
            String s=((JButton)e.getSource()).getName();
            if (s!=null) {
                switch (s) {
                    case "cancelButton":
                        cancelButtonAction();
                        break;
                    case "okButton":
                        okButtonAction();
                        break;
                    default:
                        break;
                }
            }
            //_View.showMessage(s,"Сообщение",JOptionPane.YES_OPTION);
        }
    }

    protected void cancelButtonAction() {

    }

    protected void okButtonAction(){

    }

    protected void keyReleasedManager(KeyEvent e)    {
        if (keyListenerActive) {
            int code = e.getKeyCode();
            switch (code) {
                case 27:
                    cancelButtonAction();
                    break;
                case 10:
                    // resultedClose();
                    break;
            }
        }
    }
}
