package BuisnessCode;


import VisualForms.IJobCounters;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PathClass {
    protected String Destination;
    protected Set<String> extensions;


    public Set<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<String> extensions) {
        this.extensions = extensions;
    }

    public void setExtensions(String extensions) {
        Set<String> s=new HashSet<String>();
        for(String s1:extensions.toLowerCase().split(" ")){
            s.add(s1);
        }
        this.extensions=s;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDestination() {
        return Destination;
    }

    // Constructor
    public PathClass(){
    }

    // Methods
    public void routeDir(String sP){
    }

    public int getFilesCount(String sP) {
        File f = new File(sP);
        File[] l = f.listFiles();
        int cnt=0;
        if (l != null) {
            for (File fx : l) {
                if (fx.isDirectory()) {
                    cnt+=getFilesCount(sP + "//" + fx.getName());
                } else {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    protected static void askForActions(IJobCounters j) {
        if (j.isPaused()){
            while(j.isPaused()&&(!j.isStopPressed())){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
