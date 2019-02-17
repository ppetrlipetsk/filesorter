package VisualForms;

import baseform.FormPanelView;
import baseform.baseformtypes.DefineBottomButtons;

import javax.swing.*;
import java.awt.*;

public class FormDoneProgressView extends FormPanelView implements IFormDoneProgress{
    JLabel totalLabel;
    JLabel doneLabel;
    JLabel remainLabel;
    JLabel dupLabel;
    JLabel uniqLabel;
    JLabel notExtLabel;
    JProgressBar pb;

    public FormDoneProgressView() {
        super();
        setDefaultFormSize();
    }

    public FormDoneProgressView(DefineBottomButtons b) {
        super(b);
        setDefaultFormSize();
    }

    public FormDoneProgressView(String title) {
        super(title);
        setDefaultFormSize();
    }

    public FormDoneProgressView(JDialog p, String title, boolean modal){
        super(p,title,modal);
        initForm();
    }

    public FormDoneProgressView(JDialog p, String t, Dialog.ModalityType m){
        super(p,t,m);
    }
    protected void setDefaultFormSize(){
        //setFormSize(new Dimension(200,250));
        setMaxSize(new Dimension(200,250));
        setMinSize(new Dimension(200,250));
    }


    public void setTotal(int total) {
        totalLabel.setText(Integer.toString(total));
        if (pb!=null) pb.setMaximum(total);
    }

    public void setDone(int done) {
        doneLabel.setText(Integer.toString(done));
        if (pb!=null) pb.setValue(done);
    }


    public void setRemain(int remain) {
        remainLabel.setText(Integer.toString(remain));
    }

    public void setDup(int dup) {
        dupLabel.setText(Integer.toString(dup));
    }

    public void setUniq(int uniq) {
        uniqLabel.setText(Integer.toString(uniq));
    }

    public void setNotExt(int n){
        notExtLabel.setText(Integer.toString(n));
    }

    protected void prepareElements(GroupLayout layout) {

        JPanel dlgPanel=new JPanel();
        JLabel label1=new JLabel("Всего:");
        JLabel label2=new JLabel("Выполнено:");
        JLabel label3=new JLabel("Осталось:");
        JLabel label4=new JLabel("Дубликатов:");
        JLabel label5=new JLabel("Уникальных:");
        JLabel label6=new JLabel("Не обработано:");

        totalLabel=new JLabel("0");
        doneLabel=new JLabel("0");
        remainLabel=new JLabel("0");
        dupLabel=new JLabel("0");
        uniqLabel=new JLabel("0");
        notExtLabel=new JLabel("0");

        pb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
        pb.setMinimum(0);
        //pb.setValue(50);
        //pb.setIndeterminate(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        dlgPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        //hGroup.addComponent(label5);
        GroupLayout.SequentialGroup sg1=layout.createSequentialGroup();

        sg1.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label2).addComponent(label3).addComponent(label4).addComponent(label5).addComponent(label6));
        sg1.addGroup(layout.createParallelGroup().addComponent(totalLabel).addComponent(doneLabel).addComponent(remainLabel).addComponent(dupLabel).addComponent(uniqLabel).addComponent(notExtLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(pb).addGroup(sg1));

        layout.setHorizontalGroup(hGroup);

        vGroup.addComponent(pb);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1).addComponent(totalLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2).addComponent(doneLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label3).addComponent(remainLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label4).addComponent(dupLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label5).addComponent(uniqLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label6).addComponent(notExtLabel));
        layout.setVerticalGroup(vGroup);
        }
    }


