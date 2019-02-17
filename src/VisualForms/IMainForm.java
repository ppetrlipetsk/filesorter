package VisualForms;

import baseform.IActionedForm;

import java.awt.*;
import java.io.File;

public interface IMainForm extends IActionedForm {
    File getFolder();
    void setSourceDirTxt(String s);
    void setDestinationDirTxt(String s);
    String getExtensions();
    Container getDialogContainer();
    void showProgressBar(Component parent, String ms, String nt, int min, int max  );
    void hideProgressBar();
    void setProgressBarMin(int min);
    void setProgressBarMax(int max);
    void setProgress(int p);
}
