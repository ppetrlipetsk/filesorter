package baseform.baseformtypes;

import baseform.ButtonsList;
import baseform.IActionedForm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public interface IPanelForm extends IActionedForm {
    ArrayList<JButton> setBottomButtons(ButtonsList bl);
    void setButtonsSize(Dimension buttonsSize);
    Dimension getButtonsSize();
}
