package baseform;

import baseform.baseformtypes.IBaseForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

public class FormBaseView implements IBaseForm {

    protected JDialog form=null;

// Constructors

    public FormBaseView(){
        createForm();
        initForm();
    }

    public FormBaseView(String title){
        this();
        form.setTitle(title);
    }

    public FormBaseView(JDialog p, String title, boolean modal){
        createForm(p,title,modal);
        initForm();
    }

    public FormBaseView(JDialog p, String t, Dialog.ModalityType m){
        createForm(p,t,m);
        initForm();
    }

    public void setTitle(String s){
        if (form!=null)
            form.setTitle(s);
    }

    @Override
    public void setFormName(String name) {
        if (this.form!=null) this.form.setName(name);
    }

    @Override
    public String getFormName() {
        if (this.form!=null) return this.form.getName();
        else
            return "";
    }

    @Override
    public Container getParentForm() {
        if (form!=null)
           return form.getParent();
        else
        return null;
    }

    @Override
    public void setModal(boolean b){
        if (form!=null)
            form.setModal(b);
    }

    @Override
    public void setModalityType(Dialog.ModalityType t){
        if (form!=null) form.setModalityType(t);
    }

    @Override
    public Container getFormContainer(){
        return form;
    }

    @Override
    public JDialog getFormOwn() {
        return form;
    }

    protected void createForm(){
        form=new JDialog();
    }
    protected void createForm(JDialog p, String t, Dialog.ModalityType m){
        form=new JDialog(p,t,m);
    }

    // Метод, вызываемый при создании формы.
    // Установка других параметров, будет вызываться далее из метода prepareForm().
    protected void initForm(){
        setDefaultParameters();
    }
    public void setDefaultCloseOperation(int c){
        form.setDefaultCloseOperation(c);
    }

    protected void setDefaultParameters(){
        form.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setDefaultLookAndFeelDecorated(false);
    }

    protected void initFormParameters(){}

    protected void createForm(JDialog p, String t, boolean m){
        form=new JDialog(p,t,m);
    }

    public void setSize(Dimension d){if (form!=null) form.setSize(d);}

    public Dimension getSize(){
        if (form!=null)
            return  form.getSize();
        else
            return new Dimension(0,0);
    }

    public int getWidth(){if (form!=null) return form.getWidth(); else return -1;}

    public int getHeight(){if (form!=null) return form.getHeight(); else return -1;}

    public void setResizable(boolean b){
        if (form!=null) form.setResizable(b);
    }

    public void setMinSize(Dimension d){
        if (form!=null) form.setMinimumSize(d);
    }

    public void setMaxSize(Dimension d){
        if (form!=null) form.setMaximumSize(d);
    }

    // Устанавливает один размер для максимального размера, минимального размера и текущего размера формы
    public void setTotalSize(Dimension d){
        setMinSize(d);
        setMaxSize(d);
        setSize(d);
    }

    public void setDefaultLookAndFeelDecorated(boolean b){
        if (form!=null) form.setDefaultLookAndFeelDecorated(b);
    }


    public Dimension getScreenSize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.getScreenSize();
    }

    public Rectangle getScreenBounds(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    }

    @Override
    public boolean isFormVisible() {
        return form.isVisible();
    }

    @Override
    public void setFormVisible(boolean v) {
        form.setVisible(v);
    }

    public Point getFormCenteredPosition(){
        int x=0;
        int y=0;
        if (this.form!=null){
            Rectangle r=getScreenBounds();
            x=r.width/2+r.x-form.getWidth()/2;
            y=r.y+(r.height-form.getHeight())/2;
        }
        return new Point(x,y);
    }


    protected void prepareForm(){
        initFormParameters();
        //prepareFormLayout();
    }

    public boolean isFormCreated(){
        return form!=null;
    }

    private Dimension getFormVMaximizedDimensions() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        return new Dimension(kit.getScreenSize().width,kit.getScreenSize().height);
    }

    protected Dimension getFormMaximizedDimensions(){
        Dimension screenSize = getScreenSize();
        return new Dimension(screenSize.width,screenSize.height);
    }

    public void setLocation(int x, int y){
        if (form!=null) form.setLocation(x,y);
    }

    public void setLocationByPlatform(boolean b){form.setLocationByPlatform(b);}
    // Listeners
    public void setWindowListener(WindowListener l){
        form.addWindowListener(l);
    }

    public void showMessage(String msg, String title, int opt){
        JOptionPane.showMessageDialog(null,msg,title,opt);
    }

    public void showMessage(String msg, String title, int opt, Component pc){
        JOptionPane.showMessageDialog(pc,msg,title,opt);
    }

    public int optionDialog(Container c, String s, String title, int opttype,  int messtype, Icon icon, Object[] options){
        if (options==null)
            options = new Object[]{"Да", "Нет!"};
        int n = JOptionPane
                .showOptionDialog(c, s,
                        title, opttype,
                        messtype, icon, options,
                        options[0]);
        return n;
    }

    public int showConfirm(String msg, String title, int opt){
        return JOptionPane.showConfirmDialog(this.getParentForm(),msg,title,opt);
        //Dialog(null,msg,title,opt);
    }

    public void showForm(){
        prepareForm();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                form.setVisible(true);
            }
        });
    }

    public void showForm(boolean invoked){
        prepareForm();
        if (invoked) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    form.setVisible(true);
                }
            });
        }
        else{
            form.setVisible(true);
        }
    }

    @Override
    public void closeForm() {
        if (this.form!=null) this.form.setVisible(false);
    }


}
