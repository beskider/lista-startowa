package com.company.listastartowa.service;

import com.company.listastartowa.controller.UserController;
import com.company.listastartowa.model.User;
import com.company.listastartowa.repository.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.debug("Wywolano metode getAllUsers()");
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        logger.debug("Wywolano metode getUser({})", id);
        return userRepository.getOne(id);
    }

    public void deleteUser(Long id) {
        logger.debug("Wywolano metode deleteUser()");
        logger.debug("Uzytkownik do usuniecia: {}", userRepository.findById(id));
        userRepository.deleteById(id);
        logger.info("Usunieto uzytkownika o id {}.", id);
    }

    public void saveUser(User user) {
        logger.debug("Wywolano metode saveUser()");
        logger.debug("Dane uzytkownika do zapisania: {}", user);
        userRepository.save(user);
        logger.info("Zapisano uzytkownika o id {}", user.getId());
    }

    public boolean createPdf(List<User> users, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45, 30);
        try {
            String filePath = context.getRealPath("/resources/reports");
            File file = new File(filePath);
            boolean exists = new File(filePath).exists();
            if(!exists) {
                new File(filePath).mkdirs();
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"uczestnicy"+".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

            Paragraph paragraph = new Paragraph("Uczestnicy", mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

            float[] columnWidths = {2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell name = new PdfPCell(new Paragraph("Nazwa", tableHeader));
            name.setBorderColor(BaseColor.BLACK);
            name.setPaddingLeft(10);
            name.setHorizontalAlignment(Element.ALIGN_CENTER);
            name.setVerticalAlignment(Element.ALIGN_CENTER);
            name.setBackgroundColor(BaseColor.GRAY);
            name.setExtraParagraphSpace(5f);
            table.addCell(name);

            PdfPCell email = new PdfPCell(new Paragraph("E-mail", tableHeader));
            email.setBorderColor(BaseColor.BLACK);
            email.setPaddingLeft(10);
            email.setHorizontalAlignment(Element.ALIGN_CENTER);
            email.setVerticalAlignment(Element.ALIGN_CENTER);
            email.setBackgroundColor(BaseColor.GRAY);
            email.setExtraParagraphSpace(5f);
            table.addCell(email);

            for(User user : users) {

                PdfPCell nameValue = new PdfPCell(new Paragraph(user.getName(), tableBody));
                nameValue.setBorderColor(BaseColor.BLACK);
                nameValue.setPaddingLeft(10);
                nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
                nameValue.setBackgroundColor(BaseColor.WHITE);
                nameValue.setExtraParagraphSpace(5f);
                table.addCell(nameValue);

                PdfPCell emailValue = new PdfPCell(new Paragraph(user.getEmail(), tableBody));
                emailValue.setBorderColor(BaseColor.BLACK);
                emailValue.setPaddingLeft(10);
                emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
                emailValue.setBackgroundColor(BaseColor.WHITE);
                emailValue.setExtraParagraphSpace(5f);
                table.addCell(emailValue);

            }

            document.add(table);
            document.close();
            writer.close();
            return true;

        }
        catch(Exception e) {
            return false;
        }

    }

    public boolean createExcel(List<User> users, ServletContext context, HttpServletRequest request, HttpServletResponse response) {

        String filePath = context.getRealPath("/resources/reports");
        File file = new File(filePath);
        boolean exists = new File(filePath).exists();
        if(!exists) {
            new File(filePath).mkdirs();
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file + "/" + "uczestnicy" + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("Uczestnicy");
            workSheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFRow headerRow = workSheet.createRow(0);

            HSSFCell name = headerRow.createCell(0);
            name.setCellValue("Nazwa");
            name.setCellStyle(headerCellStyle);

            HSSFCell email = headerRow.createCell(1);
            email.setCellValue("E-mail");
            email.setCellStyle(headerCellStyle);

            int i = 1;

            for(User user : users) {

                HSSFRow bodyRow = workSheet.createRow(i);

                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
                bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

                HSSFCell nameValue = bodyRow.createCell(0);
                nameValue.setCellValue(user.getName());
                nameValue.setCellStyle(bodyCellStyle);

                HSSFCell emailValue = bodyRow.createCell(1);
                emailValue.setCellValue(user.getEmail());
                emailValue.setCellStyle(bodyCellStyle);

                i++;

            }

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            return true;

        }
        catch(Exception e) {
            return false;
        }

    }

}
