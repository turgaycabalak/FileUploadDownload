package com.javachallenge.fileapi.business;

import com.javachallenge.fileapi.TestUtil;
import com.javachallenge.fileapi.dataAccess.FileRepository;
import com.javachallenge.fileapi.dto.FileDataConverter;
import com.javachallenge.fileapi.dto.FileDataResponse;
import com.javachallenge.fileapi.dto.FileInformationResponse;
import com.javachallenge.fileapi.entities.FileData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileServiceTest extends TestUtil {

    private FileRepository fileRepository;
    private FileDataConverter fileDataConverter;


    private FileService fileService;

    @BeforeEach
    public void setUp() {
        fileRepository = mock(FileRepository.class);
        fileDataConverter = mock(FileDataConverter.class);

        fileService = new FileService(fileRepository, fileDataConverter);
    }

    @Test
    public void testGetAllFileInformation_itShouldReturnFileInformationResponseList() {
        PageRequest pageableRequest = PageRequest.of(0, 5);
        List<FileInformationResponse> fileInformationResponses = generateFileInformationResponseList();
        Page<FileData> fileDataPage = mock(Page.class);
        List<FileData> fileDataList = fileDataPage.getContent();

        when(fileRepository.findAll(pageableRequest)).thenReturn(fileDataPage);
        when(fileDataConverter.convertToFileInformationResponseList(fileDataList)).thenReturn(fileInformationResponses);

        List<FileInformationResponse> result = fileService.getAllFileInformation(pageableRequest);
        assertEquals(fileInformationResponses, result);

        verify(fileRepository).findAll(pageableRequest);
        verify(fileDataConverter).convertToFileInformationResponseList(fileDataList);
    }

    @Test
    public void testGetFileById_whenIdExists_itShouldReturnFileDataResponse() {
        FileData fileData = generateFileData();
        fileData.setId(ID);
        FileDataResponse fileDataResponse = generateFileDataResponse();
        File file = new File(FOLDER_PATH + "/" + fileData.getFileName());

        when(fileRepository.findById(ID)).thenReturn(Optional.of(fileData));
        when(fileDataConverter.convertToFileDataResponse(fileData, file)).thenReturn(fileDataResponse);

        FileDataResponse result = fileService.getFileById(ID);
        assertEquals(fileDataResponse, result);

        verify(fileRepository).findById(ID);
        verify(fileDataConverter).convertToFileDataResponse(fileData, file);
    }

    @Test
    public void testGetFileById_whenIdDoesNotExists_itShouldThrowFileNotFoundException() {
        when(fileRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(com.javachallenge.fileapi.exceptions.FileNotFoundException.class, () ->
                fileService.getFileById(ID)
        );

        verify(fileRepository).findById(ID);
        verifyNoInteractions(fileDataConverter);
    }

//    @Test
//    public void testGetFileById_whenIdExistsButFileDoesNotExistInFolder_itShouldThrowFileNotFoundException() throws FileNotFoundException {
//        FileData fileData = generateFileData();
//        fileData.setId(ID);
////        FileDataResponse fileDataResponse = generateFileDataResponse();
////        ResourceUtils resourceUtils = mock(ResourceUtils.class);
////        String filePath = fileData.getFilePath() + "/" + fileData.getFileName();
////        File file = null;
//
//        when(fileRepository.findById(ID)).thenReturn(Optional.of(fileData));
//
//        assertThrows(com.javachallenge.fileapi.exceptions.FileNotFoundException.class, () ->
//                fileService.getFileById(ID)
//        );
//
//        verify(fileRepository).findById(ID);
//        verifyNoInteractions(fileDataConverter);
//    }

    @Test
    public void testFileUpload() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        File file = generateFile();
        FileData fileData = generateFileData();

        doNothing().when(multipartFile).transferTo(file);
        when(fileRepository.save(fileData)).thenReturn(fileData);

        fileService.fileUpload(multipartFile);

//        verify(multipartFile).transferTo(file);
//        verify(fileRepository).save(fileData);
    }

//    @Test
//    public void testDeleteFileById_whenIdExists() throws IOException {
//        FileData fileData = generateFileData();
//        FileUtils fileUtils = mock(FileUtils.class);
//        File file = generateFile();
//        String filePath = fileData.getFilePath() + "/" + fileData.getFileName();
//
//        when(fileRepository.findById(ID)).thenReturn(Optional.of(fileData));
//        doNothing().when(fileRepository).deleteById(ID);
//        doNothing().when(fileUtils).moveFileToDirectory(
//                fileUtils.getFile(filePath),
//                fileUtils.getFile(DELETED_FILES_PATH),
//                true
//        );
//
//        fileService.deleteFileById(ID);
//    }


}