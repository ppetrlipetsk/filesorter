package baseform;

import baseform.baseformtypes.*;
import defines.StrCaptions;

import javax.swing.*;
import java.awt.*;



// Класс контроллер формы. Просто выводит форму и задает визуальное оформление.

public class FCBase {
    public IBaseForm _View;

    // Событие, генериуемое при закрытии формы. Так родительская форма может получить событие закрытия дочерней формы, если она не модальная.
    protected IFormCloseEvent closeEvent=null;
    // обратный вызов обработчика некоего события в форме. так родительская форма может получить сигнал и обработать его.
    protected IFormEvent formEvent=null;
    // Флаг закрытия формы. Если true, то при закрытии формы будет произведен выход из приложения. Если false, то форма просто будет закрыта.
    protected boolean exitOnClose=false;

    protected FormSizeType formSizeType;
    protected FormPositionTypes formPosition;
    protected Dimension formSize;
    // Флаг присвоения размера форме. Если true, то размер formSize устанавливается и для setMinimumSize() и для setMaximumSize().
    protected boolean totalSize=false;
    // Флаг закрытия формы. Если true, то перед закрытием формы будет выведен диалог, в котором требуется подтверждение закрытия формы.
    // Иначе форма закроется без подтверждения.
    protected boolean askForClose=false;
    // Флаг признака закрытия формы. Если true, то форма закрыта. Если false, то форма открыта.
    protected boolean formClosed=false;
    // Строка диалога подтверждения закрытия формы.
    protected String askString=StrCaptions.closeFormConfirmMessage;
    // Код возврата из формы. 0 - форма закрыта без сохранения данных. 1- форма закрыта с сохранением данных.
    protected int exitCode=0;
    // Результат возврата из формы. Например resultCode=="okPressed".
    protected Object resultCode=null; //


    public IFormEvent getFormEvent() {
        return formEvent;
    }

    public void setFormEvent(IFormEvent formEvent) {
        this.formEvent = formEvent;
    }

    public IFormCloseEvent getCloseEvent() {
        return closeEvent;
    }

    public void setCloseEvent(IFormCloseEvent closeEvent) {
        this.closeEvent = closeEvent;
    }

    public Object getResultCode() {
        return resultCode;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    public String getAskString() {
        return askString;
    }

    public void setAskString(String askString) {
        this.askString = askString;
    }

    public boolean isFormClosed() {
        return formClosed;
    }

    public void setFormClosed(boolean formClosed) {
        this.formClosed = formClosed;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exit_code) {
        this.exitCode = exit_code;
    }

    public boolean isAskForClose() {
        return askForClose;
    }

    public void setAskForClose(boolean askForClose) {
        this.askForClose = askForClose;
    }

    // Возвращает флаг признака одинакового размера для min, msx и текущего размера формы.
    public boolean isTotalSize() {
        return totalSize;
    }

    // Устанавливает флаг признака одинакового размера для min, msx и текущего размера формы.
    public void setTotalSize(boolean totalSize) {
        this.totalSize = totalSize;
    }


    public void setExitOnClose(boolean b) {
        exitOnClose=b;
    }

    public boolean getExitOnClose() {
        return exitOnClose;
    }

    public Dimension getFormSize() {
        return formSize;
    }

    public void setFormSize(Dimension formSize) {
        this.formSize = formSize;
    }

    public FormPositionTypes getFormPosition() {
        return formPosition;
    }

    public void setFormPosition(FormPositionTypes formPosition) {
        this.formPosition = formPosition;
    }

    public FormSizeType getFormSizeType() {
        return formSizeType;
    }

    public void setFormSizeType(FormSizeType formSizeType) {
        this.formSizeType = formSizeType;
    }

    protected void setViewSize(Dimension d){
        if (isTotalSize()) _View.setTotalSize(d);
        else
            _View.setSize(d);
    }


    public  FCBase(){
    }

    public FCBase(IBaseForm _view){
        //_View=_view;
        this.InitController(_view);
    }

    public void InitController(IBaseForm _view){
        _View=_view;
        setDefaultParameters();
    }

    protected void setDefaultParameters(){
        askForClose=false;
        setExitOnClose(false);
    }

    protected void resetFormKeys(){
        formClosed=false;
        exitCode=0;
        resultCode=null;
    }

    protected void initFormPropertyes(FormSizeType fst, FormPositionTypes fpt){
        if (fst!=null)
            setFormSizeType(fst);
        if (fpt!=null)
            setFormPosition(fpt);
        prepareFormLayout();
    }

    public int getForm(){
        resetFormKeys();
        prepareFormLayout();
        _View.showForm();
        return exitCode;
    }

    public int getForm(FormSizeType fst, FormPositionTypes fpt){
        resetFormKeys();
        initFormPropertyes(fst,fpt);
        _View.showForm();
        return exitCode;
    }

    public int getForm(FormSizeType fst, FormPositionTypes fpt, boolean f){
        resetFormKeys();
        initFormPropertyes(fst,fpt);
        _View.showForm(f);
        return exitCode;
    }

    public int getForm(FormSizeType fst, FormPositionTypes fpt, Dimension d){
        resetFormKeys();
        if (d!=null)
            setFormSize(d);
        exitCode=getForm(fst,fpt);
        return exitCode;
    }

    public int getForm(FormSizeType fst, FormPositionTypes fpt, Dimension d, boolean f){
        resetFormKeys();
        if (d!=null)
            setFormSize(d);
        exitCode=getForm(fst,fpt,f);
        return exitCode;
    }

// _View settings
    protected void prepareFormLayout(){
        if (_View.isFormCreated()) {
            if ((this.formSizeType == FormSizeType.MAXIMIZED)) {
                Rectangle r=_View.getScreenBounds();
                //_View.setSize(r.getSize());
                setViewSize(r.getSize());
                _View.setLocation(r.x,r.y);
            } else
            if ((this.formSizeType == FormSizeType.MAXIMIZEDVERTICAL)) {
                Rectangle r=_View.getScreenBounds();
                int w;
                if (formSize!=null) w=formSize.width;
                else
                    w=_View.getWidth();
                //_View.setSize(new Dimension(w,r.height));
                setViewSize(new Dimension(w,r.height));
                _View.setLocation(r.x+((r.width-_View.getWidth())/2),0);
            } else
            if ((this.formSizeType == FormSizeType.NOTSIZED)) {
                _View.setLocationByPlatform(true);
                if (this.formSize!=null){
                    //_View.setSize(this.formSize);
                    setViewSize(this.formSize);
                }
                if (this.formPosition == FormPositionTypes.CENTERED) {
                    Point p=_View.getFormCenteredPosition();
                    _View.setLocation(p.x,p.y);
                }
                else
                    _View.setLocation(0,0);
            }
        }
    }

// EOF View settings
    protected void prepareForm(){
        prepareFormLayout();
    }

    // Выход из формы без сохранения данных. Возвращает resultCode и закрывает форму.
    protected void cancelForm(){
        setResultCode("cancel");
        setExitCode(0);
        simpleCloseForm();
    }

    // Выход из формы без сохранения данных. Возвращает resultCode и закрывает форму.
    protected void saveAndExitForm(){
        setResultCode("savedForm");
        setExitCode(1);
        prepareCloseForm();
        resultedCloseForm();
    }

    // В этом методе будут методы сохранения данных при выходе из формы
    protected void prepareCloseForm() {
    }

    protected void closeForm(){
        _View.closeForm();
        formClosed=true;

        if (closeEvent!=null) closeEvent.closeAction();

        if (getExitOnClose())
            System.exit(0);
    }

    protected void resultedCloseForm() {
        //_View.closeForm();
        exitCode=1;
        closeForm();
    }

    protected void simpleCloseForm() {
        //_View.closeForm();
        exitCode=0;
        closeForm();
    }

    protected int closePerformance(){
        int i=0;
        if (isAskForClose()){
            i=_View.showConfirm(StrCaptions.closeFormConfirmMessage, StrCaptions.confirmString,JOptionPane.YES_NO_OPTION);
            if (i==JOptionPane.YES_OPTION){
                cancelCloseAction();
            }
        }
        return i;
    }

    protected void savedCloseAction(){
        setResultCode("okPressed");
        resultedCloseForm();
    }

    protected void cancelCloseAction(){
        setResultCode("cancelPressed");
        simpleCloseForm();
    }

}
