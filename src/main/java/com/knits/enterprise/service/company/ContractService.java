package com.knits.enterprise.service.company;

import com.knits.enterprise.model.company.Contract;
import com.knits.enterprise.repository.common.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public List<Long> findContractsIds() {
        return contractRepository.findAllIds();
    }
    @Transactional(readOnly = true)
    public byte[] makeContractsZipFileByIds(List<Long> ids) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        for (int i = 0; i < ids.size(); i++) {
            Contract contract = contractRepository.findById(ids.get(i)).orElse(null);
            if (contract != null) {
                ZipEntry entry = new ZipEntry("contract-" + ids.get(i) + "#id.pdf");
                entry.setSize(contract.getMediaFile().getSize());
                try {
                    zos.putNextEntry(entry);
                    zos.write(contract.getMediaFile().getBytes());
                    zos.closeEntry();
                } catch (Exception e) {
                    throw new RuntimeException("Error while creating zip file", e);
                }
            } else {
                String reportFileGeneratedPath = generateFileOfContractFailedReport(ids.get(i));
                byte[] dataForWriting = readFileToByteArray(reportFileGeneratedPath);
                ZipEntry entry = new ZipEntry("contract-" + ids.get(i) + "#id.txt");
                entry.setSize(Long.valueOf(dataForWriting.length));
                try {
                    zos.putNextEntry(entry);
                    zos.write(dataForWriting);
                    zos.closeEntry();
                } catch (Exception e) {
                    throw new RuntimeException("Error while creating zip file", e);
                }
            }
        }
        try {
            zos.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing zip file", e);
        }
        return baos.toByteArray();
    }

    public byte[] readFileToByteArray(String reportFileGeneratedPath) {
        try (FileInputStream fileInputStream = new FileInputStream(reportFileGeneratedPath)) {
            byte[] buffer = new byte[fileInputStream.available()];
            int bytesRead = fileInputStream.read(buffer);
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateFileOfContractFailedReport(Long id) {
        String path = System.getProperty("user.home") + "/Desktop/contractreports";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File outputFile = new File(folder, "contract-" + id + "#id.txt");
        String defaultMessage = "Contract with id " + id + " not found";
        byte[] dataForWriting = defaultMessage.getBytes();
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(dataForWriting);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path + "/" + outputFile.getName();
    }
}
