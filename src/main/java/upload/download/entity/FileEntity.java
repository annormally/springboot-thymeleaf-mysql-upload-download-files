package upload.download.entity;

import javax.persistence.*;

@Entity
@Table(name = "file_table")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;
    @Lob
    @Column(name = "file_byte")
    private byte[] fileByte;

    public FileEntity() {
    }

    public FileEntity(String fileName, String fileType, byte[] fileByte) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileByte = fileByte;
    }

    public Long getId() {
        return fileId;
    }

    public void setId(Long id) {
        this.fileId = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }
}
