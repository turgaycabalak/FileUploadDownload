package com.javachallenge.fileapi.dto;

public record FileInformationResponse(Long id,
                                      String filePath,
                                      long size,
                                      String fileName,
                                      String fileExtension) {

}
