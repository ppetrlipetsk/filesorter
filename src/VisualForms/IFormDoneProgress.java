package VisualForms;

import baseform.baseformtypes.IPanelForm;

public interface IFormDoneProgress extends IPanelForm {
    void setTotal(int total);
    void setDone(int done);
    void setRemain(int remain);
    void setDup(int dup);
    void setUniq(int uniq);
    void setNotExt(int n);
}
