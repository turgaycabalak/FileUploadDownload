package com.javachallenge.fileapi.dto;

public record FileDataResponse(Long id,
                               long size,
                               String fileName,
                               String fileExtension,
                               byte[] data) {
}
