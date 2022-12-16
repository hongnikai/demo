package com.example.controller;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class CreateImgController {

    /**
     * 1.获取echart图的Base64码
     * <input type="hidden" name="img" id="img" />
     * $('#img').val(myChart.getDataURL("png"));
     */


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void createImg(HttpServletRequest request) {
        String uplodapath = "D:/temp/upload";
        //生成图片begin
        String data = request.getParameter("img");
        String imgname = "test.png";
        String filedir = "D:/temp";
        File file = new File(filedir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] url = data.split(",");
        String u = url[1];
        BASE64Decoder decoder = new BASE64Decoder();
        // 生成图片
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(u);
            OutputStream out = new FileOutputStream(new File(filedir + "\'" + imgname));
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 读取图片到excel
     */
    public void readFileToExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            //下载图片到excel
            //begin
            BufferedImage bufferImg = null;
            try {
                //创建作图sheet
                XSSFSheet sheet1 = null;
                if (wb.getSheet("echarts") == null) {
                    sheet1 = wb.createSheet("echarts");
                } else {
                    sheet1 = wb.getSheet("echarts");
                }
                //循环读取图片插入到excel
                String filedir = "D:/temp";
                File file = new File(filedir);
                if (file.isDirectory()) {
                    String[] files = file.list();
                    //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
                    XSSFDrawing patriarch = sheet1.createDrawingPatriarch();
                    int i = 0;
                    int rowbegin = 1;
                    int rowend = 8;
                    for (String _file : files) {
                        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                        bufferImg = ImageIO.read(new File(filedir + "\'" + _file));
                                ImageIO.write(bufferImg, "png", byteArrayOut);
                        //anchor主要用于设置图片的属性
                        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 1, rowbegin, (short) 5, rowend);
                        //插入图片
                        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
                        i++;
                        rowbegin = i * 8 + 1;
                        rowend = rowbegin + 8;
                    }
                }
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("test.xls", "GBK"));
                OutputStream out = response.getOutputStream();
                // 写入excel文件
                wb.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
