package com.knits.enterprise.service.common;

import com.knits.enterprise.model.common.MediaFile;
import com.knits.enterprise.repository.common.MediaFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

import static com.knits.enterprise.util.excel.company.EmployeeExelUtil.findFileStream;

@Service
@AllArgsConstructor
@Slf4j
public class MediaFileService {
    private final MediaFileRepository mediaFileRepository;
    @Transactional
    public void saveTestPdfFile() {
        InputStream fileStream = findFileStream("Employment-Contract-Agreement.pdf");
        try {
            byte[] buffer = new byte[fileStream.available()];
            int bytesRead = fileStream.read(buffer);
            MediaFile data = MediaFile.builder().title("test")
                    .contentType("application/pdf")
                    .bytes(buffer)
                    .size(Long.valueOf(buffer.length)).build();
            mediaFileRepository.save(data);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
