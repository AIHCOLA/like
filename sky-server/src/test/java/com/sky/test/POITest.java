package com.sky.test;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 受用POI来操作Excel文件
 */
public class POITest {


    /**
     * 通过POI创建一个新的文件并且写入文件内容
     * @throws IOException
     */
    public static void write() throws IOException {
        //在内存中创建一个excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //在excel中创建一个新的sheet页
        XSSFSheet sheet = excel.createSheet("info");
        //在sheet页中创建一个行对象,row num代表的是从0开始
        XSSFRow row = sheet.createRow(1);
        //创建单元格并写入文件内容
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("城市");

        //创建一个新行
        row = sheet.createRow(2);
        row.createCell(1).setCellValue("张三");
        row.createCell(2).setCellValue("北京");

        //创建一个新行
        row = sheet.createRow(3);
        row.createCell(1).setCellValue("李四");
        row.createCell(2).setCellValue("南京");

        //通过输出流将内存中的文件写入磁盘
        FileOutputStream out = new FileOutputStream(new File("D:\\info.xlsx"));
        excel.write(out);

        //关闭资源
        out.close();
        excel.close();

    }

    /**
     * 通过POI读取excel文件中的内容
     * @throws IOException
     */
    public static void read() throws IOException{

        FileInputStream inputStream = new FileInputStream(new File("D:\\info.xlsx"));
        //读取磁盘已经存在的excel文件
        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        //读取excel文件的sheet页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取sheet页中最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++){
            //获得某一行
            XSSFRow row = sheet.getRow(i);
            //获得单元格
            String cellValue1 = row.getCell(1).getStringCellValue();
            String cellValue2 = row.getCell(2).getStringCellValue();
            System.out.println(cellValue1+" "+cellValue2);
        }

        //关闭资源
        excel.close();
        inputStream.close();
    }

    public static void main(String[] args) throws IOException {
//        write();
//        read();
    }


}
