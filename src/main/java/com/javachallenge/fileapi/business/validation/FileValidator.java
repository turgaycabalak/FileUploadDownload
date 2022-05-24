package com.javachallenge.fileapi.business.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

import static com.javachallenge.fileapi.business.validation.FileDataValidator.*;
import static com.javachallenge.fileapi.business.validation.FileDataValidator.ValidationResult.SUCCESS;
import static com.javachallenge.fileapi.business.validation.FileDataValidator.isFileDataExtensionValid;

@RequiredArgsConstructor
public class FileValidator implements ConstraintValidator<ValidateFile, MultipartFile> {


    @Value("#{'${application.file.extensions}'.split(',')}")
    private final Set<String> extensions;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        ValidationResult result = isFileDataExtensionValid()
                .and(isFileDataEmpty())
                .apply(extensions, file);

        return result == SUCCESS;
    }

}
