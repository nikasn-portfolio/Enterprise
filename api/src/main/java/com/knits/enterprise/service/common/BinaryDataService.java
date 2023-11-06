package com.knits.enterprise.service.common;

import com.knits.enterprise.model.common.BinaryData;
import com.knits.enterprise.repository.common.BinaryDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

import static com.knits.enterprise.utils.EmployeeExelUtils.createByteOutputStreamFromWorkbook;
import static com.knits.enterprise.utils.EmployeeExelUtils.findFileStream;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class BinaryDataService {

    private final BinaryDataRepository binaryDataRepository;


    public void saveTestPdfFile() {
        InputStream fileStream = findFileStream("Employment-Contract-Agreement.pdf");
        try {
            byte[] buffer = new byte[fileStream.available()];
            int bytesRead = fileStream.read(buffer);
            BinaryData data = BinaryData.builder().title("test")
                    .contentType("application/pdf")
                    .bytes(buffer)
                    .size(Long.valueOf(buffer.length)).build();
            binaryDataRepository.save(data);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
