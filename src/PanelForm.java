import baseform.FormPanelC;
import baseform.*;
import baseform.baseformtypes.FormPositionTypes;
import baseform.baseformtypes.FormSizeType;
import baseform.baseformtypes.IPanelForm;

import java.awt.*;

public class PanelForm {
    public static void main(String[] args) {
        //FormPanelView f=new FormPanelView(DefineBottomButtons.DEFAULT);
        FormPanelView f=new FormPanelView();
       ButtonsList l=new ButtonsList();
        l.add(new ButtonItem("okButton","Ok"));
        l.add(new ButtonItem("cancelButton","Cancel"));
        l.add(new ButtonItem("printButton","Print"));
        f.setBottomButtons(l);

        FormPanelC fc=new FormPanelC((IPanelForm) f);
        fc.getForm(FormSizeType.MAXIMIZEDVERTICAL,FormPositionTypes.CENTERED,new Dimension(300,300));

    }
}
