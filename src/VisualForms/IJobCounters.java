package VisualForms;

public interface IJobCounters extends IFormDoneProgressC{
    void incrementDone();
    void incrementUnic();
    void incrementDup();
    void incrementNotExt();
    void setStopPressed(boolean b);
    boolean isStopPressed();
    boolean isPaused();
    void setPaused(boolean b);
}
