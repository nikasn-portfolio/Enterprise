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
        InputStream fileStream = findFileStream("EmployeeData.xlsx");
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(fileStream);
        } catch (Exception e) {
            log.error("Error while initializing reference to file", e);
        }
        byte[] bytesOfExcelFile = createByteOutputStreamFromWorkbook(wb).toByteArray();
        BinaryData data = BinaryData.builder().title("test")
                .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .bytes(bytesOfExcelFile)
                .size(Long.valueOf(bytesOfExcelFile.length)).build();
        binaryDataRepository.save(data);
    }

}
