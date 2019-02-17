import VisualForms.FormDoneProgressBarC;
import VisualForms.FormDoneProgressView;
import baseform.FormPanelC;
import baseform.*;
import baseform.baseformtypes.FormPositionTypes;
import baseform.baseformtypes.FormSizeType;
import baseform.baseformtypes.IPanelForm;
import defines.StrCaptions;

import java.awt.*;

public class ProgressForm {
    public static void main(String[] args) throws AWTException {
        TrayIcon icon = null;
        if (SystemTray.isSupported()){
            SystemTray tray = SystemTray.getSystemTray();
            Image im = Toolkit.getDefaultToolkit().getImage("myicon.gif");
            icon = new TrayIcon(im);
            tray.add(icon);
        }

        FormDoneProgressView f=new FormDoneProgressView();

        f.setMaxSize(new Dimension(200,250));
        f.setMinSize(new Dimension(200,250));
        ButtonsList l=new ButtonsList();
        l.add(new ButtonItem(StrCaptions.okButtonName,StrCaptions.okButtonCaption));
        f.setBottomButtons(l);
        FormDoneProgressBarC fc=new FormDoneProgressBarC(f);
        fc.setFormSize(new Dimension(200,250));
        fc.getForm(FormSizeType.NOTSIZED,FormPositionTypes.CENTERED,new Dimension(300,150));

    }
}
