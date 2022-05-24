package com.javachallenge.fileapi.business;

import com.javachallenge.fileapi.dataAccess.FileRepository;
import com.javachallenge.fileapi.dto.FileDataConverter;
import com.javachallenge.fileapi.dto.FileDataResponse;
import com.javachallenge.fileapi.dto.FileInformationResponse;
import com.javachallenge.fileapi.entities.FileData;
import com.javachallenge.fileapi.exceptions.FileProcessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.*;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileDataConverter fileDataConverter;
    private final String FOLDER_PATH = System.getProperty("user.home") + "/Desktop/fileapi/src/main/resources/UploadedFiles";
    private final String DELETED_FILES_PATH = System.getProperty("user.home") + "/Desktop/fileapi/src/main/resources/DeletedFiles";

    @Transactional
    public void fileUpload(MultipartFile file) {
        String formattedDateTimeNow = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));

        // save the file to particular folder
        addFileToFolder(file, formattedDateTimeNow);

        // save file information to db
        String fileName = FilenameUtils.getName(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        fileRepository.save(new FileData(
                FOLDER_PATH,
                file.getSize(),
                (formattedDateTimeNow + fileName),
                fileExtension
        ));
    }

    @Transactional(readOnly = true, propagation = SUPPORTS)
    public List<FileInformationResponse> getAllFileInformation(Pageable pageable) {
        return fileDataConverter
                .convertToFileInformationResponseList(fileRepository.findAll(pageable).getContent());
    }

    public FileDataResponse getFileById(Long id) {
        FileData fileData = getFileData(id);

        String filePath = fileData.getFilePath() + "/" + fileData.getFileName();
        File file = getFile(filePath);

        return fileDataConverter.convertToFileDataResponse(fileData, file);
    }


    @Transactional
    public void deleteFileById(Long fileId) {
        FileData fileData = getFileData(fileId);

        // delete from db
        fileRepository.deleteById(fileId);

        // delete from folder (or move to another folder named DeletedFiles)
        deleteFromFolder(fileData);
    }

    @Transactional
    @Modifying
    public void updateFileById(MultipartFile file, Long fileId) {
        FileData fileData = getFileData(fileId);
        String formattedDateTimeNow = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));

        // delete old file from folder
        deleteFromFolder(fileData);

        // upload new file to the folder
        addFileToFolder(file, formattedDateTimeNow);

        // update and save to db
        String fileName = FilenameUtils.getName(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        long fileSize = file.getSize();

        fileData.setFileName(fileName);
        fileData.setFileExtension(fileExtension);
        fileData.setSize(fileSize);
        fileRepository.save(fileData);
    }

    private File getFile(String filePath) {
        try {
            return ResourceUtils.getFile(filePath);
        } catch (FileNotFoundException e) {
            throw new FileProcessException("An exception occurred during downloading file! Please try again.");
        }
    }

    private FileData getFileData(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new com.javachallenge.fileapi.exceptions.FileNotFoundException("File not found!"));
    }

    private void deleteFromFolder(FileData fileData){
        String filePath = fileData.getFilePath() + "/" + fileData.getFileName();
        try {
            FileUtils.moveFileToDirectory(
                    FileUtils.getFile(filePath),
                    FileUtils.getFile(DELETED_FILES_PATH),
                    true
            );
        } catch (IOException e) {
            throw new FileProcessException("An exception occurred during deleting file! Please try again.");
        }
    }

    private void addFileToFolder(MultipartFile file, String formattedDateTimeNow){
        try {
            file.transferTo(new File(FOLDER_PATH + "/" + formattedDateTimeNow + file.getOriginalFilename()));
        } catch (IOException e) {
            throw new FileProcessException("An exception occurred during uploading file! Please try again.");
        }
    }



//    private boolean doesFileDataExist(Long id) {
//        return fileRepository.existsById(id);
//    }


}
