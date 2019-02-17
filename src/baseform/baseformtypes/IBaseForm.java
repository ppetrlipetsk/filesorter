package baseform.baseformtypes;


import java.awt.*;
import java.awt.event.WindowListener;

public interface IBaseForm extends IBaseFrameForm {
    Container getParentForm();
    void setModal(boolean b);
    void setModalityType(Dialog.ModalityType t);

}
