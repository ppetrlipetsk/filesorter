package VisualForms;

import baseform.FormActionedView;
import baseform.FormPanelView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.File;

public class MainFormView extends FormActionedView implements IMainForm {
    JButton okButton;
    JButton cancelButton;
    JButton startButton;
    JButton stopButton;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JPanel dialogPanel;
    JPanel bottomPanel;
    JPanel mainPanel;
    ProgressMonitor pm;

    @Override

    protected void initForm(){
        super.initForm();
        BorderLayout mainLayout=new BorderLayout();
        this.getFormContainer().setLayout(mainLayout);

        mainPanel=createMainForm();
        dialogPanel=(JPanel) initDialogPanel();
        dialogPanel.setBorder(new EtchedBorder());
        bottomPanel=(JPanel)initBottomPanel2();
        bottomPanel.setBorder(new EtchedBorder());

        this.getFormContainer().add(mainPanel,BorderLayout.CENTER);
        mainPanel.add(dialogPanel,BorderLayout.CENTER);
        mainPanel.add(bottomPanel,BorderLayout.PAGE_END);

        //pm=new ProgressMonitor((Component)this.form,"Идет обработка...","Выполнено:",0,100);
        setTitle("Поиск");
    }

    private JPanel createMainForm(){
        JPanel mainPanel=new JPanel();
        BorderLayout mailLayout=new BorderLayout();
        mainPanel.setLayout(mailLayout);
        return mainPanel;
    }

    private  Container initBottomPanel2() {
        JPanel bPanel=new JPanel();
        JPanel bottomPanel=new JPanel();

        GroupLayout layout = new GroupLayout(bottomPanel);
        BorderLayout layout2=new BorderLayout();

        bPanel.setLayout(layout2);
        bPanel.add(bottomPanel,BorderLayout.CENTER);
        bottomPanel.setLayout(layout);

        //bPanel.setBackground(Color.CYAN);
       // bottomPanel.setBackground(Color.BLUE);

        okButton=new JButton("toOne");
        cancelButton=new JButton("Cancel");
        startButton=new JButton("Sort");
        stopButton=new JButton("Stop");

        Dimension buttonsDim=new Dimension(80,25);

        okButton.setPreferredSize(buttonsDim);
        okButton.setMinimumSize(buttonsDim);
        cancelButton.setPreferredSize(buttonsDim);
        cancelButton.setMinimumSize(buttonsDim);
        startButton.setPreferredSize(buttonsDim);
        startButton.setMinimumSize(buttonsDim);
        stopButton.setPreferredSize(buttonsDim);
        stopButton.setMinimumSize(buttonsDim);

        okButton.setName("okButton");
        cancelButton.setName("cancelButton");
        startButton.setName("startButton");
        stopButton.setName("stopButton");

        // =============================
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        hGroup.addGroup(layout.createParallelGroup().addComponent(okButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hGroup.addGroup(layout.createParallelGroup().addComponent(startButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hGroup.addGroup(layout.createParallelGroup().addComponent(stopButton)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hGroup.addGroup(layout.createParallelGroup().addComponent(cancelButton));
        layout.setHorizontalGroup(hGroup);

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(okButton).addComponent(startButton).addComponent(stopButton).addComponent(cancelButton));


        layout.setVerticalGroup(vGroup);
        //==============================

        // Events Listeners
        //dlg3.ButtonListener bl=new dlg3.ButtonListener();

        okButton.addActionListener(ButtonListener);
        cancelButton.addActionListener(ButtonListener);
        startButton.addActionListener(ButtonListener);
        stopButton.addActionListener(ButtonListener);

        //return bottomPanel;
        return bPanel;
    }

    protected   Container initDialogPanel()
    {
        JPanel dlgPanel=new JPanel();
        JLabel label1=new JLabel("Source path:");
        JLabel label2=new JLabel("Destination path:");
        JLabel label3=new JLabel("Extensions (| - splitter):");

        tf1=new JTextField("",15);
        tf2=new JTextField("",15);
        tf3=new JTextField("",15);
        tf3.setText("jpg png");
        JButton button1=new JButton("...");
        JButton button2=new JButton("...");
        button1.setName("sourceBtn");
        button2.setName("destBtn");

        GroupLayout layout = new GroupLayout(dlgPanel);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        // Elements settings block

        dlgPanel.setLayout(layout);
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(true);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label2).addComponent(label3));
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2).addComponent(tf3));
        hGroup.addGroup(layout.createParallelGroup().addComponent(button1).addComponent(button2)) ;
        layout.setHorizontalGroup(hGroup);

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label1).addComponent(tf1).addComponent(button1));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label2).addComponent(tf2).addComponent(button2));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(label3).addComponent(tf3));
        layout.setVerticalGroup(vGroup);
        return dlgPanel;
    }

    public MainFormView(String title){
        super(title);
    }

    public File getFolder(){
        JFileChooser dialog = new JFileChooser();
        dialog.resetKeyboardActions();
        dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialog.showOpenDialog(this.getFormContainer());
        File f=dialog.getSelectedFile();

        return f;
    }

    public void setSourceDirTxt(String s){
        tf1.setText(s);
    }

    public void setDestinationDirTxt(String s){
        tf2.setText(s);
    }

    public String getExtensions(){
        return tf3.getText();
    }
    public Container getDialogContainer(){
        return dialogPanel;
    }

    @Override
    public void showProgressBar(Component parent, String ms, String nt, int min, int max  ) {
        pm=new ProgressMonitor((Component)parent,ms,nt,min,max);
    }
    //pm=new ProgressMonitor((Component)parent,"Идет обработка...","Выполнено:",0,100);

    @Override
    public void hideProgressBar() {
        pm.close();
    }

    @Override
    public void setProgressBarMin(int min) {
        pm.setMinimum(min);
    }

    @Override
    public void setProgressBarMax(int max) {
        pm.setMaximum(max);
    }
    @Override
    public void setProgress(int p){
        pm.setProgress(p);
    }
}
