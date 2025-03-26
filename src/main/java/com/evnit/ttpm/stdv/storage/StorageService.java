package com.evnit.ttpm.stdv.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.evnit.ttpm.stdv.util.Util;

@Service
public class StorageService {

    @Value("${xdocreport.template}")
    private String path;
    @Value("${app.file.upload.url}")
    private String urlFileService;

    public static final int EXCEL_HTML = 44;
    public static final int EXCEL_PDF = 57;
    public static final String FILE_MAU_DKCT = "FILE_MAU_DKCT";
    public static final String FILE_MAU_DKNP = "FILE_MAU_DKNP";

    public Resource downloadMau(String loaiphieu) {
        try {
            String filename = "";
            if (loaiphieu.equals("DKCT")) {
                filename = "TEMPLATE_HCVP_DKCT_2MAU.rar";

            } else if (loaiphieu.equals("DKNP")) {
                filename = "TEMPLATE_HCVP_DKNP_2MAU.rar";

            } else if (loaiphieu.equals("FILE_GIAYDD")) {
                filename = "TEMPLATE_HCVP_GIAYDD_MAU.docx";

            } else if (loaiphieu.equals("FILE_BANGTT")) {
                filename = "TEMPLATE_HCVP_THANHTOAN_MAU.docx";

            } else if (loaiphieu.equals("HDLD")) {
                filename = "HDLD_MAU_EVN_2022.docx";

            }
            Path file = Paths.get(path + "/Template_mau").resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String saveFile(MultipartFile file, String module, String donviId) {
        String fileName = file.getOriginalFilename();
        // String path_id = "/smartevn/" + module + "/" + donviId + "/" + strDate;
        String path_id = Util.folderDvName(module, donviId, null);

        String nPath = path + path_id;
        File dirFile = new File(nPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path filepath = Paths.get(nPath + "/" + fileName);
            Files.write(filepath, bytes);
        } catch (IOException e) {
            String msg = String.format("Failed to store file %f", file.getName());
            throw new StorageException(msg, e);
        }
        return path_id + "/" + fileName;
    }

    public String saveFile(String fileName, byte[] bytes, String donviId) {
        String uriFile = "";
        // String nPath = path + donviId + "\\" + strDate + "\\";
        String nPath = path + Util.folderDvName("utilities", donviId, null);
        File dirFile = new File(nPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        if (bytes == null) {
            throw new StorageException("Failed to store empty file");
        }
        try {
            Path filepath = Paths.get(nPath + "/" + fileName);
            Files.write(filepath, bytes);
            uriFile = nPath + fileName;
        } catch (IOException e) {
            String msg = String.format("Failed to store file %f", fileName);
            throw new StorageException(msg, e);
        }
        return uriFile;
    }


//    public String convertDocxToPDF(String pdfFile, String docxFilePath) {
//        return this.convertToPDF(pdfFile, docxFilePath);
//    }

//    public String convertDocx2PdfBase64(byte[] fileContent) {
//        String result = "";
//        try {
//            // Initialize Docx4j
//            InputStream templateInputStream = new ByteArrayInputStream(fileContent);
//
//            // Convert
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            Docx4J.toPDF(wordMLPackage, os);
//
//            result = Base64.getEncoder().encodeToString(os.toByteArray());
//            os.flush();
//            os.close();
//        } catch (Throwable e) {
//
//            e.printStackTrace();
//        }
//        return result;
//    }

//    private Mapper registerFonts(String path) throws Exception {
//        // Load the font from the .ttf file
//        try (Stream<Path> paths = Files.walk(Paths.get(path, "fonts"))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(path1 -> {
//                        try {
//                            PhysicalFonts.addPhysicalFont(path1.toUri().toURL());
//                        } catch (MalformedURLException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//        }
//        // Add all forms of a Font: Regular, Bold, Italic, Bold Italic
//        Map<String, PhysicalFont> physicalFontMap = PhysicalFonts.getPhysicalFonts();
//        Mapper fontMapper = new IdentityPlusMapper();
//        for (Map.Entry<String, PhysicalFont> pairs : physicalFontMap.entrySet()) {
//            String fontName = pairs.getKey();
//            PhysicalFont pf = pairs.getValue();
//            if (pf != null) fontMapper.put(fontName, pf);
//        }
//
//        return fontMapper;
//    }
}
