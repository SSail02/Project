package com.innovatus.studentrecords.model;
import java.time.LocalDateTime;
public record UploadHistory(int id,int userId,String fileName,String department,String batch,String year,int totalRecords,LocalDateTime uploadDate) {}
