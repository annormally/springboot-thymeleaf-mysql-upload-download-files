package upload.download.entity;

public class FileInfo {

    private String fileInfoName;
    private String fileURL;

    public FileInfo() {
    }

    public FileInfo(String fileInfoName, String fileURL) {
        this.fileInfoName = fileInfoName;
        this.fileURL = fileURL;
    }

    public String getFileInfoName() {
        return fileInfoName;
    }

    public void setFileInfoName(String fileInfoName) {
        this.fileInfoName = fileInfoName;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }
}
