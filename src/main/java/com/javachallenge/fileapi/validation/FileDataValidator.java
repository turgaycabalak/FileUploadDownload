package com.javachallenge.fileapi.validation;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.function.BiFunction;

import static com.javachallenge.fileapi.validation.FileDataValidator.*;
import static com.javachallenge.fileapi.validation.FileDataValidator.ValidationResult.*;

public interface FileDataValidator extends BiFunction<Set<String>, MultipartFile, ValidationResult> {

    static FileDataValidator isFileDataExtensionValid() {
        return (extensionList, file) -> extensionList.stream()
                .anyMatch(s -> s.equals(FilenameUtils.getExtension(file.getOriginalFilename()))) ?
                SUCCESS : EXTENSION_IS_NOT_COMPATIBLE;
    }

    static FileDataValidator isFileDataEmpty() {
        return (extensionList, file) -> !file.isEmpty() ?
                SUCCESS : FILE_IS_EMPTY;
    }

    default FileDataValidator and(FileDataValidator other) {
        return (extensionList, file) -> {
            ValidationResult result = this.apply(extensionList, file);
            return result.equals(SUCCESS) ? other.apply(extensionList, file) : result;
        };
    }

    enum ValidationResult {
        SUCCESS,
        EXTENSION_IS_NOT_COMPATIBLE,
        FILE_IS_EMPTY
    }
}
