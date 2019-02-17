package baseform;

import javax.swing.*;
import java.awt.*;

public class FormProgressView extends FormPanelView {
    private JPanel progressPanel;
    private JProgressBar progressBar;


    protected void getProgressBar(){
        progressBar =new JProgressBar();
        progressBar.setMaximum(100);
        progressBar.setMinimum(0);
        progressBar.setValue(50);
        progressBar.setOrientation(JProgressBar.HORIZONTAL);
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(true);
    }

    public FormProgressView(JDialog p, String title, boolean modal){
        super(p,title,modal);
        initForm();
    }

    public FormProgressView(JDialog p, String t, Dialog.ModalityType m){
        super(p,t,m);
    }

    protected void prepareElements(GroupLayout layout){
        getProgressBar();
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        GroupLayout.Group gh = layout.createParallelGroup().addComponent(this.progressBar);
        hGroup.addGroup(gh);

        GroupLayout.Group gv = layout.createParallelGroup().addComponent(this.progressBar);
        vGroup.addGroup(gv);
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
    }
}
