package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil{

    private static Workbook workbook;

    public static void loadExcel(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
    }

    public static String getCellValue(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row r = sheet.getRow(row);
        Cell c = r.getCell(col);
        return c.getStringCellValue();
    }
}
