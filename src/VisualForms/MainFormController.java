package VisualForms;

import BuisnessCode.FilesToOneDir;
import BuisnessCode.ParseFiles;
import baseform.ButtonItem;
import baseform.ButtonsList;
import baseform.FCActioned;
import baseform.baseformtypes.*;
import defines.StrCaptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static runtimetools.RunTimeTools.delay;

public class MainFormController extends FCActioned {
    protected IMainForm _View;
    protected String sourcePath;
    protected String destinationPath;
    boolean stopPressed=false;
    boolean paused=false;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean pausePressed) {
        this.paused = pausePressed;
    }

    public boolean isStopPressed() {
        return stopPressed;
    }

    public void setStopPressed(boolean stopPressed) {
        this.stopPressed = stopPressed;
    }

    public String getSourcePath() {
        if (sourcePath==null)
            return "";
        else
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        _View.setSourceDirTxt(sourcePath);
    }

    public String getDestinationPath() {
        if (destinationPath==null)
            return "";
        else
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
        _View.setDestinationDirTxt(destinationPath);
    }

    /*
     Theads
     */

    class Thread1 extends Thread{
        public void run(){
            try{
                System.out.println("Thread started");
                routeFilesToOneDir();
                System.out.println("Thread finished");
                }
            catch(Exception e){
            }
        }
    }

    class Thread2 extends Thread{
        public void run(){
            try{
                System.out.println("Thread started");
                parseFiles();
                System.out.println("Thread finished");
            }
            catch(Exception e){
            }
        }
    }

        /*
     EOF Theads
     */

//    class PBKeyAction implements IFormKeyBackActions, IFormEvent {
//        @Override
//        public void closeEvent() {
//        }
//
//        @Override
//        public void formAction(Object e) {
//        }
//    }


    class PBFormActions implements IFormEvent, IFormCloseEvent {
        @Override
        public void closeAction() {
            pbCloseManager();
        }

        @Override
        public void formAction(Object e) {
            if (e instanceof String) {
                if (((String) e).equalsIgnoreCase(StrCaptions.pausedThread)) {
                    setPaused(true);
                }
                else
                if (((String) e).equalsIgnoreCase(StrCaptions.resumeThread)) {
                    setPaused(false);
                }
                else
                if (((String) e).equalsIgnoreCase(StrCaptions.stopThread)) {
                    setStopPressed(true);
                }
            }
        }
    }

    private void pbCloseManager() {
        setStopPressed(true);
    }

    class JobCounter implements IJobCounters{
        IFormDoneProgressC formController;
        int total =0;
        int done =0;
        int remain=0;
        int uniq=0;
        int dup=0;
        int notExt=0;


        public JobCounter(IFormDoneProgressC f){
            formController=f;
            reset();
        }

        public void incrementCounter(int c) {
            setDone(done +=c);
            calcValues();
        }

        private void calcValues() {
            setRemain(total- done);
        }

        @Override
        public void setTotal(int t) {
            total=t;
            formController.setTotal(t);
        }

        @Override
        public void setDone(int c)
        {
            done =c;
            formController.setDone(c);
            remain=total-done;
            formController.setRemain(remain);
        }

        @Override
        public void setRemain(int i) {
            remain=i;
            formController.setRemain(remain);
        }

        @Override
        public void setUniq(int c) {
            uniq=c;
            formController.setUniq(c);
        }

        @Override
        public void setNotExt(int n) {
            notExt=n;
            formController.setNotExt(n);
        }

        @Override
        public void setDup(int c) {
            dup=c;
            formController.setDup(c);
        }

        public void reset() {
            total = 0;
            done = 0;
            remain = 0;
            uniq=0;
            dup=0;
            notExt=0;
            stopPressed=false;
            paused=false;
        }

        @Override
        public void incrementDone() {
            setDone(done+1);
        }

        @Override
        public void incrementUnic() {
            setUniq(uniq+1);
        }

        @Override
        public void incrementDup() {
            setDup(dup+1);
        }

        @Override
        public void incrementNotExt() {
            setNotExt(notExt+1);
        }

        @Override
        public void setStopPressed(boolean b) {
            stopPressed=b;
        }

        @Override
        public boolean isStopPressed() {
            return stopPressed;
        }

        @Override
        public boolean isPaused() {
            return paused;
        }

        @Override
        public void setPaused(boolean b) {
                paused=b;
        }
        }


    public MainFormController(IMainForm _view){
        super(_view);
        _View=_view;
}

    @Override

protected void buttonsManager(ActionEvent e){
    if (e.getSource() instanceof JButton){
        String s=((JButton)e.getSource()).getName();
        switch(s) {
            case "sourceBtn":
                sourcePathBtn();
                break;
            case "destBtn":
                destPathBtn();
                break;
            case "okButton":
                //routeFilesToOneDir();
                startToOne();
                break;
            case "startButton":
                //parseFiles();
                startParse();
                break;
            case "stopButton":
                stopButton();
                break;

            default:
                break;
        }
        //_View.showMessage(s,"Сообщение",JOptionPane.YES_OPTION);
    }
}

private void stopButton()  {
    _View.showProgressBar(_View.getFormContainer(),"Идет обработка...","Выполнено:",0,100);
    Thread2 t2=new Thread2();
    t2.start();
/*
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    */
}
    private void parseFiles() {
        String s1=getSourcePath();
        String s2=getDestinationPath();
        if ((s1.length()>0)&&(s2.length()>0)) {
            try {
                File sp = new File(getSourcePath());
                File dp = new File(getDestinationPath());
                if (sp.isDirectory() && dp.isDirectory()) {
                    FormDoneProgressBarC fc=getProgressFormC();
                    JobCounter j=new JobCounter(fc);
                    j.reset();
                    fc.getForm(FormSizeType.NOTSIZED,FormPositionTypes.CENTERED,true);

                    ParseFiles f = new ParseFiles();
                    f.setDestination(getDestinationPath());
                    f.setExtensions(getExtensions());
                    synchronized (f) {
                        try{
                            int c=f.getFilesCount(getSourcePath());
                            j.setTotal(c);
                            f.route(getSourcePath(),j);
                        }
                        finally {
                            while(j.isPaused()) { // Пока открыт прогрессбар и в нем открыт диалог закрытия формы
                                delay(500);
                            }
                            fc.close();
                        }
                        if (isStopPressed())
                            _View.showMessage("Обработка прервана!","Сообщение",JOptionPane.CANCEL_OPTION);
                        else
                            _View.showMessage("Файлы обработаны!","Сообщение",JOptionPane.CANCEL_OPTION);
                    }
                    if ((fc!=null)&&(!fc.isFormClosed())) fc.close();


//                    f.route(getSourcePath());
//                    _View.showMessage("Файлы обработаны!","Сообщение",JOptionPane.CANCEL_OPTION);
                } else {
                    _View.showMessage("Пути заполнены неправильно!","Сообщение",JOptionPane.CANCEL_OPTION);
                }
            }
            catch(Exception e) {
                _View.showMessage(e.getMessage(),"Сообщение",JOptionPane.CANCEL_OPTION);
            }
        }
        else {
            _View.showMessage("Пути заполнены неправильно!","Сообщение",JOptionPane.CANCEL_OPTION);
        }
    }

    private FormDoneProgressBarC getProgressFormC(){
        FormDoneProgressView f=new FormDoneProgressView(_View.getFormOwn(),"Done",true);
        ButtonsList l=new ButtonsList();
        l.add(new ButtonItem(StrCaptions.okButtonName,StrCaptions.okButtonCaption));
        f.setBottomButtons(l);
        f.setMaxSize(new Dimension(200,250));
        f.setMinSize(new Dimension(200,250));
        f.setResizable(false);
        PBFormActions p=new PBFormActions();
        FormDoneProgressBarC fc=new FormDoneProgressBarC(f, null);
        fc.setFormEvent(p);
        fc.setFormSize(new Dimension(200,250));
        fc.setAskForClose(true);
        return fc;
    }

    private void routeFilesToOneDir() {
        String s1=getSourcePath();
        String s2=getDestinationPath();

        if ((s1.length()>0)&&(s2.length()>0)) {
            try
            {
                File sp = new File(getSourcePath());
                File dp = new File(getDestinationPath());
                if (sp.isDirectory() && dp.isDirectory())
                {
                    FormDoneProgressBarC fc=getProgressFormC();
                    JobCounter j=new JobCounter(fc);
                    j.reset();

                    fc.getForm(FormSizeType.NOTSIZED,FormPositionTypes.CENTERED,true);
                    FilesToOneDir f = new FilesToOneDir();
                    f.setDestination(getDestinationPath());
                    f.setExtensions(getExtensions());
                    synchronized (f) {
                        try{
                            int c=f.getFilesCount(getSourcePath());
                            j.setTotal(c);
                            f.route(getSourcePath(),j);
                        }
                        finally {
                            fc.close();
                        }
                        if (isStopPressed())
                            _View.showMessage("Обработка прервана!","Сообщение",JOptionPane.CANCEL_OPTION);
                        else
                            _View.showMessage("Файлы обработаны!","Сообщение",JOptionPane.CANCEL_OPTION);
                    }
                    while(j.isPaused()) { // Пока открыт прогрессбар и в нем открыт диалог закрытия формы
                        delay(500);
                    }

                    if ((fc!=null)&&(!fc.isFormClosed())) fc.close();
                } else {
                    _View.showMessage("Пути заполнены неправильно!","Сообщение",JOptionPane.CANCEL_OPTION);
                }
            }
            catch(Exception e) {
                _View.showMessage("Ошибка! "+e.getMessage(),"Сообщение",JOptionPane.CANCEL_OPTION);
                }
        }
        else {
            _View.showMessage("Пути заполнены неправильно!","Сообщение",JOptionPane.CANCEL_OPTION);
        }
    }

    private String getExtensions(){
        return _View.getExtensions();
    }

    private void destPathBtn() {
    File f=getFolder();
    if (f!=null) {
        setDestinationPath(f.getAbsolutePath());
        //setDestinationPath("c://files//destination");
    }

}

    private void sourcePathBtn() {
        File f=getFolder();
        if (f!=null)
            setSourcePath(f.getAbsolutePath());
           // setSourcePath("C://files//foto");
    }

    protected File getFolder(){
        setKeyListenerActive(false);
        File p =_View.getFolder();
        if (p!=null){
        if (p.isDirectory())
            return p;
        else
        {
            _View.showMessage("Выбранный путь не является папкой.","Сообщение",JOptionPane.YES_OPTION);
           setKeyListenerActive(true);
            return null;
        }
        }
        else {

           setKeyListenerActive(true);
            return null;
        }
    }

    private void startToOne(){
            Thread1 t1=new Thread1();
            t1.start();
    }

    private void startParse(){
        Thread2 t2=new Thread2();
        t2.start();
    }

}
