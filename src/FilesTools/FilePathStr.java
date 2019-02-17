package FilesTools;

public class FilePathStr {
    private String Filename; // Имя файла с расширением
    private String FilenameNE; // Имя файла с расширением
    private String Extension; //Расширение
    private String BasePath; // Путь к файлу без имени файла
    private String PathNonExt; // путь к файлу без расширения

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getBasePath() {
        return BasePath;
    }

    public void setBasePath(String basePath) {
        BasePath = basePath;
    }

    public String getPathNonExt() {
        return PathNonExt;
    }

    public void setPathNonExt(String pathNonExt) {
        PathNonExt = pathNonExt;
    }
    public String getFilenameNE() {
        return FilenameNE;
    }
    public void setFilenameNE(String filenameNE) {
        FilenameNE = filenameNE;
    }

}
