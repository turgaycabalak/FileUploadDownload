package com.javachallenge.fileapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long size;
    private String fileName;
    private String fileExtension;


    public FileData(String filePath,
                    long size,
                    String fileName,
                    String fileExtension) {

        this.filePath = filePath;
        this.size = size;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }
}
