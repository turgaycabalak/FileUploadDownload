package com.javachallenge.fileapi.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@RequiredArgsConstructor
public class FileValidator implements ConstraintValidator<ValidateFile, MultipartFile> {

    @Value("#{'${application.file.extensions}'.split(',')}")
    private Set<String> extensions;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        FileDataValidator.ValidationResult result = FileDataValidator.isFileDataExtensionValid()
                .and(FileDataValidator.isFileDataEmpty())
                .apply(extensions, file);

        return result == FileDataValidator.ValidationResult.SUCCESS;
    }

}
