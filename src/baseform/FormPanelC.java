package baseform;

import baseform.FCActioned;
import baseform.baseformtypes.IPanelForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class FormPanelC extends FCActioned {
    protected IPanelForm _View;

    public FormPanelC(IPanelForm _view){
        super(_view);
        _View=_view;
    }

}

