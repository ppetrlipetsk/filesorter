package BuisnessCode;

import DateTools.DateConvert;
import FilesTools.FileDateTypes;
import FilesTools.FilePathStr;
import FilesTools.FileTools;
import FilesTools.IPTCTools;
import VisualForms.IJobCounters;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static DateTools.DateConvert.getYear;


public class ParseFiles extends PathClass{

    public ParseFiles(){ }

    // Methods
    public void route(String sP, IJobCounters j){
        File f = new File(sP);
        File[] l=f.listFiles();

        for (File fx:l) {
            /*try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
            FilesToOneDir.askForActions(j);
            if (j.isStopPressed())
                break;

            if (fx.isDirectory()) {
                route(sP+"//"+fx.getName(),j);
                fx.delete();
            } else {
                String ext = FileTools.getExtension(fx.getName()).toLowerCase();
                if ((ext != null) && this.getExtensions().contains(ext)) {
                    fileToPath(fx,j);
                }
                else
                    j.incrementNotExt();
                j.incrementDone();
            }
        }
    }


    private void fileToPath(File fx, IJobCounters j) {
        Date d= FileTools.getFileDate(fx,FileDateTypes.LASTMODIFIED);
        Date d1=IPTCTools.getCreationDate(fx);
        if (d.after(d1)) d=d1;
        int year=getYear(d);
        String month=DateConvert.getMonthString(d);
        String file_dir=(getDestination()+"//"+year+" год")+"//"+month;
        String filepath=file_dir+"//"+fx.getName();
        FileTools.createDirIfNeed(file_dir);
        File file_new=new File(filepath);
        if (file_new.exists()) {
            FilePathStr fps=FileTools.splitFileNameNE(fx.getPath());
            if (!FileTools.isFileAnalogExist(fx.getName(),fps.getBasePath(),file_dir,"_")){
                filepath =file_dir+"//"+ FileTools.getUniqueFileName(fx.getName(),  file_dir);
            }
            else{
                String dp=getDestination() + "//res";
                filepath =dp+"//"+ FileTools.getUniqueFileName(fx.getName(),  dp);
            }
            j.incrementDup();
        }
        else
            j.incrementUnic();
        try {
            FileTools.renFile(fx, filepath);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Ошибка при переименовании файла:"+fx.getName()+" в "+filepath+" с кодом:"+e.getMessage());
        }
        }


}

