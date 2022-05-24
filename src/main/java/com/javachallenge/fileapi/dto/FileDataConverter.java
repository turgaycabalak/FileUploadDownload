package com.javachallenge.fileapi.dto;

import com.javachallenge.fileapi.entities.FileData;
import com.javachallenge.fileapi.exceptions.FileProcessException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class FileDataConverter {

    public FileInformationResponse convertToFileInformationResponse(FileData fileData) {
        return new FileInformationResponse(
                fileData.getId(),
                fileData.getFilePath(),
                fileData.getSize(),
                fileData.getFileName(),
                fileData.getFileExtension()
        );
    }

    public List<FileInformationResponse> convertToFileInformationResponseList(List<FileData> fileDataList) {
        return fileDataList.stream()
                .map(this::convertToFileInformationResponse)
                .toList();
    }

    public FileDataResponse convertToFileDataResponse(FileData fileData, File file){
        try {
            return new FileDataResponse(
                    fileData.getId(),
                    fileData.getSize(),
                    fileData.getFileName(),
                    fileData.getFileExtension(),
                    FileUtils.readFileToByteArray(file)
            );
        } catch (IOException e) {
            throw new FileProcessException("An exception occurred during downloading file! Please try again.");
        }
    }

}
