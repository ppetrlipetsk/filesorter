package VisualForms;


import baseform.FormPanelC;
import baseform.baseformtypes.IFormCloseEvent;
import baseform.baseformtypes.IFormEvent;
import baseform.baseformtypes.IFormKeyBackActions;
import baseform.baseformtypes.IPanelForm;
import defines.StrCaptions;

import javax.swing.*;

public class FormDoneProgressBarC extends FormPanelC implements IFormDoneProgressC{
    protected IFormDoneProgress _View;
    //IFormCloseEvent okAction=null;
    IFormEvent formEvent=null;

    @Override
    public IFormEvent getFormEvent() {
        return formEvent;
    }

    @Override
    public void setFormEvent(IFormEvent formEvent) {
        this.formEvent = formEvent;
    }

    public FormDoneProgressBarC(IFormDoneProgress _View) {
        super((IPanelForm)_View);
        this._View = _View;
    }

    public FormDoneProgressBarC(IFormDoneProgress _View, IFormCloseEvent action) {
        this(_View);
        if (action!=null) closeEvent=action;
    }

    public void setTotal(int c){_View.setTotal(c);}
    public void setDone(int c){_View.setDone(c);}
    public void setDup(int c){_View.setDup(c);}
    public void setUniq(int c){_View.setUniq(c);}
    public void setRemain(int remain) {_View.setRemain(remain);}
    public void setNotExt(int n){_View.setNotExt(n);}
    public void close(){
        //_View.closeForm();
        closeForm();
    }

    protected void okButtonAction(){
        //setResultCode("okPressed");
        //resultedCloseForm();
       // if (okAction!=null) okAction.closeEvent();
        int i=closePerformance();
        if (i==JOptionPane.YES_OPTION)
            if (formEvent!=null) formEvent.formAction(StrCaptions.stopThread);
    }

    protected int closePerformance(){
        if (isAskForClose()) {
            if (formEvent != null) {
                formEvent.formAction(StrCaptions.pausedThread);
            }
        }
        int i=super.closePerformance();
        if (!formClosed){
            formEvent.formAction(StrCaptions.resumeThread);
        }
        else
        if (formEvent!=null) formEvent.formAction(StrCaptions.stopThread);
        return i;
    }

}
