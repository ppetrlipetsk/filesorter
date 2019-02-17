package FilesTools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public  class FileTools {

    // Возвращает имя файла без расширения, включая путь, и расширение файла.

    public static FilePathStr splitFileNameNE(String fn){
        FilePathStr f=new FilePathStr();
        int i = fn.lastIndexOf('\\');
        if (i>-1) {
            //i = fn.lastIndexOf('\\');
            f.setBasePath(fn.substring(0, i));
            f.setFilename(fn.substring(i + 1));
        }
        else{
            f.setFilename(fn);
        }
        i = f.getFilename().lastIndexOf('.');
        f.setFilenameNE(f.getFilename().substring(0,i));
        f.setExtension(f.getFilename().substring(i+1));
        i = fn.lastIndexOf('.');
        if (i>0){
            f.setPathNonExt(fn.substring(0,i));
        }
        return f;
    }

    public  static void renFile(File fx, String filepath) {
        File f=new File(filepath);
        try {
            fx.renameTo(f);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Ошибка переноса файла! Путь "+filepath+". Код ошибки:"+e.getMessage());
        }
    }

    public static void renFile(String fs, String filepath) {
        File fx=new File(fs);
        renFile(fx,filepath);
    }

//    public static void renFile(File fx, String filepath) {
//        //File fx=new File(fs);
//        renFile(fx,filepath);
//    }

    public static  BasicFileAttributes getFileAttr(String  fs){
        File f=new File(fs);
        return getFileAttr(f);
    }

    public static  BasicFileAttributes getFileAttr(File f){
        BasicFileAttributes attr=getFileAttr(f.toPath());;
        return attr;
    }

    public static  BasicFileAttributes getFileAttr(Path filepath){
        BasicFileAttributes attr=null;
        try{
            attr= Files.readAttributes(filepath,BasicFileAttributes.class);
        }
        catch (IOException exception)
        {
            System.out.println("Exception handled when trying to get file " +
                    "attributes: " + exception.getMessage());
        }
        return attr;
    }



    public static  Date getFileDate(File f,FileDateTypes tf){
        Path filepath=f.toPath();
        return getFileDate(filepath,tf);
    }

    public static  Date getFileDate(String fn,FileDateTypes tf){
        File f=new File(fn);
        return getFileDate(f,tf);
    }

    public static  Date getFileDate(Path filepath,FileDateTypes tf){
        Date creationDate=getFileDateByAttr(getFileAttr(filepath),tf);

        return creationDate;
    }

    public static Date getFileDateByAttr(BasicFileAttributes attr,FileDateTypes tf) {
        if (tf==FileDateTypes.LASTMODIFIED) return new Date((attr.lastModifiedTime()).to(TimeUnit.MILLISECONDS));
        if (tf==FileDateTypes.CREATION) return new Date((attr.creationTime()).to(TimeUnit.MILLISECONDS));
        else
            return null;
    }

    public static boolean isPathExists(String s){
        File f_path=new File(s);
        return f_path.exists();
    }

    public  static boolean createDirIfNeed(String s){
        if (!isPathExists(s)){
            if (!createDir(s)){
                throw new IllegalArgumentException("Ошибка при создании папки:"+s);
            }
        }
        return true;
    }

    public  static boolean createDir(String s){
        File f_path=new File(s);
        boolean b=false;

        try {
            f_path.mkdirs();
            b=true;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Ошибка при создании папки:"+s+" с кодом: "+e.getMessage());
        }
        return b;
    }


/*
    // Возвращает уникальное имя файла для заданного начального имени.
    public static String getUniqueFileName(String filename, String filepath) {
        filepath = getIndexedFileName(filename, filepath);
        return filepath;
    }
*/
// Возвращает уникальное имя файла для папки, в которой уже имеется файл с таким именем.
// Тогда к имени файла перед расширением добавляется индекс.
// Параметры:
// filepath-полный путь к файлу.
// destiationPath- папка, в которую будет записан файл с новым именем, если в папке-источнике он уже есть.
    private static String getIndexedFileName(String filename, String filepath) {
        return getIndexedFileName( filename, filepath, "_");
    }
    // Возвращает уникальное имя файла для папки, в которой уже имеется файл с таким именем.
    // Тогда к имени файла перед расширением добавляется индекс.
    // Параметры:
    // filepath-полный путь к файлу.
    // destiationPath- папка, в которую будет записан файл с новым именем, если в папке-источнике он уже есть.
    private static String getIndexedFileName(String filename, String filepath,String spl) {
        boolean b=false;
        int i=0;
        String fn=filename;
        if (spl.length()==0) spl="_";
        FilePathStr f=FileTools.splitFileNameNE(filename);
        while (!b){
            if (f!=null) {
                fn=f.getPathNonExt() +spl+ i + "." + f.getExtension();
                String filepath2 =filepath+"//"+ fn;
                File fw = new File(filepath2);
                if (!fw.exists()) b = true;
                i++;
            }
            else
                throw new IllegalArgumentException("Ошибка при получении расширения файла: с кодом: ");
        }
        return fn;
    }

    // Возвращает уникальное имя файла для папки, в которой уже имеется файл с таким именем.
    // Тогда к имени файла перед расширением добавляется индекс.
    // Параметры:
    // filepath-полный путь к файлу.
    // destiationPath- папка, в которую будет записан файл с новым именем, если в папке-источнике он уже есть.

    public static String getUniqueFileName(String filename, String filepath) {
        if ((new File(filepath+"//"+filename)).exists()) {
            filename = getIndexedFileName(filename, filepath);
            //if (destinationPath.length()>0) filepath=destinationPath+"//"+filepath;
        }
        return filename;
    }

    // Проверяет, есть ли аналоги файла для заданного пути.
    // Допустим, что надо переместить файл из каталога c:\files в каталог c:\files\1.
    // Если в пути назначения уже есть файл с таким именем или именем с маской: {имя файла}{разделитель}{индекс}.расширение
    // и размеры их совпадают, то считается, что файлы одинаковые и возвращается true;
    public static boolean isFileAnalogExist(String filename, String fpathsrc, String fpathdst, String spl){
        boolean b=false;
        FilePathStr fp=FileTools.splitFileNameNE(filename);
        long flength=(new File(fpathsrc+"//"+filename)).length();
        File[] fl=(new File(fpathdst)).listFiles();
        for (File fx:fl) {
            if (fx.isFile()) {
                FilePathStr fs = FileTools.splitFileNameNE(fx.getName());
                if (fp.getExtension().equals(fs.getExtension())) {
                    if (fs.getFilenameNE().length() >= fp.getFilenameNE().length()) {
                        if (fp.getFilenameNE().equals(fs.getFilenameNE().substring(0, fp.getFilenameNE().length()))) {
                            if (flength == fx.length()) {
                                b = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return b;
    }

    //Возвращает расширение файла
    public static String getExtension(String filename){
        return splitFileNameNE(filename).getExtension();
    }

}
