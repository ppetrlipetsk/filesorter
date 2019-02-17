import VisualForms.MainFormController;
import VisualForms.MainFormView;
//import baseform.IActionedForm;
import VisualForms.IMainForm;
import baseform.baseformtypes.FormPositionTypes;
import baseform.baseformtypes.FormSizeType;

import javax.swing.*;
import java.awt.*;

public class MainForm {

    public static void main(String[] args) {
        MainFormView f=new MainFormView("FileSorter");
        f.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
//        f.setMaxSize(new Dimension(450,250));
//        f.setMinSize(new Dimension(450,250));
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setResizable(false);

        MainFormController fc=new MainFormController(f);
        fc.setExitOnClose(true);
        fc.setFormSize(new Dimension(450,250));
        fc.setTotalSize(true);
        fc.getForm(FormSizeType.NOTSIZED,FormPositionTypes.CENTERED,new Dimension(450,250));
        //System.exit(0);

    }
}
