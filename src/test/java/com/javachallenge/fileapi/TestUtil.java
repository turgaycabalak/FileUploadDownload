package com.javachallenge.fileapi;

import com.javachallenge.fileapi.dto.FileDataResponse;
import com.javachallenge.fileapi.dto.FileInformationResponse;
import com.javachallenge.fileapi.entities.FileData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;


public class TestUtil {

    public static Long ID = 5L;
    public static final String FOLDER_PATH = System.getProperty("user.home") + "/Desktop/fileapi/src/main/resources/UploadedFiles";
    public static final String DELETED_FILES_PATH = System.getProperty("user.home") + "/Desktop/fileapi/src/main/resources/DeletedFiles";
    public static String FILE_NAME = "mock-file-name.pdf";
    public static String FILE_EXTENSION = "pdf";
    public static Long FILE_SIZE = 30000L;
    public static String str = "byte array size example";
    public static byte[] BYTE_ARRAY = str.getBytes();
    public static String FORMATTED_DATETIME_NOW = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-"));


    public static List<FileInformationResponse> generateFileInformationResponseList() {
        return IntStream.range(0, 5).mapToObj(i ->
                new FileInformationResponse(
                        (long) i,
                        FOLDER_PATH,
                        FILE_SIZE,
                        i + FILE_NAME,
                        FILE_EXTENSION
                )
        ).toList();
    }

    public static List<FileData> generateFileDataList() {
        return IntStream.range(0, 5).mapToObj(i ->
                new FileData(
                        FOLDER_PATH,
                        FILE_SIZE,
                        FORMATTED_DATETIME_NOW + FILE_NAME,
                        FILE_EXTENSION
                )
        ).toList();
    }
    public static FileData generateFileData() {
        return new FileData(
                FOLDER_PATH,
                FILE_SIZE,
                FORMATTED_DATETIME_NOW + FILE_NAME,
                FILE_EXTENSION
        );
    }

    public static FileDataResponse generateFileDataResponse() {
        return new FileDataResponse(
                ID,
                FILE_SIZE,
                FORMATTED_DATETIME_NOW + FILE_NAME,
                FILE_EXTENSION,
                BYTE_ARRAY
        );
    }


    public static File generateFile() {
        return new File(FOLDER_PATH + "/" + FORMATTED_DATETIME_NOW + FILE_NAME);
    }


    public static Pageable generatePageable() {
        return PageRequest.of(0, 5);
    }


}
