package com.cse406.cloud.entity;

public class FileEntity {
    private int id;
    private String fileName;
    private double fileSize;
    private String file_ext_name;
    private String filePath;
    private String fileUUID;
    private int userId;
    private String fileFatherDirectoryForView;

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileFatherDirectoryForView() {
        return fileFatherDirectoryForView;
    }

    public void setFileFatherDirectoryForView(String fileFatherDirectoryForView) {
        this.fileFatherDirectoryForView = fileFatherDirectoryForView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFile_ext_name() {
        return file_ext_name;
    }

    public void setFile_ext_name(String file_ext_name) {
        this.file_ext_name = file_ext_name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", file_ext_name='" + file_ext_name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileUUID='" + fileUUID + '\'' +
                ", userId=" + userId +
                ", fileFatherDirectoryForView='" + fileFatherDirectoryForView + '\'' +
                '}';
    }
}
