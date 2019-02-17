package BuisnessCode;

import FilesTools.FileTools;
import VisualForms.IJobCounters;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FilesToOneDir extends PathClass{

    protected boolean stoped=false;

    public boolean isStoped() {
        return stoped;
    }

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }


    // Constructor
    public FilesToOneDir(){
    }

    // Methods
    public void route(String sP, IJobCounters j) {

        File f = new File(sP);
        File[] l = f.listFiles();

       // if (l!=null)
         //j.setTotal(l.length-1);

        int cnt=0;
        if (l != null) {
            for (File fx : l) {
                askForActions(j);
                if (j.isStopPressed())
                    break;
/*
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
*/
                if (fx.isDirectory()) {
                    route(sP + "//" + fx.getName(),j);
                    String[] l1=fx.list();
                    if ((l1!=null)&&(l1.length == 0))
                        fx.delete();
                } else {
                    String ext = FileTools.getExtension(fx.getName()).toLowerCase();
                    if ((ext != null) && this.getExtensions().contains(ext)) {
                        String filepath = getDestination() + "//" + fx.getName();
                        File file_new = new File(filepath);
                        if (file_new.exists()) {
                            if (!FileTools.isFileAnalogExist(fx.getName(), sP, getDestination(), "_")) {
                                filepath = getDestination() + "//" + FileTools.getUniqueFileName(fx.getName(), getDestination());
                            } else {
                                String dp = getDestination() + "//res";
                                filepath = dp + "//" + FileTools.getUniqueFileName(fx.getName(), dp);
                            }
                            j.incrementDup();
                        }
                        else{
                            j.incrementUnic();
                        }
                        try {
                            FileTools.renFile(fx, filepath);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Ошибка при переименовании файла:" + fx.getName() + " в " + filepath + " с кодом:" + e.getMessage());
                        }
                    }
                    else
                        j.incrementNotExt();
                    j.incrementDone();
                }
            }
            cnt++;
        }
    }




}
