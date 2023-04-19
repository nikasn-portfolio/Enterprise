package com.knits.enterprise.validation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FileValidation {

    public boolean isSupportedExtension(String extension) {
        return extension != null && (
                        extension.equals("zip")
                        || extension.equals("docx")
                        || extension.equals("pdf"));
    }
}
