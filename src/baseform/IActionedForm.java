package baseform;


import baseform.baseformtypes.IBaseForm;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface IActionedForm extends IBaseForm {
        void setButtonListener(ActionListener bl);
        void setKeyListener(KeyListener k);
        void setTotalKeyListener(KeyListener l);
    }


