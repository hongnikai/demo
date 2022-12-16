package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.entity.UserPdfExport;
import com.example.util.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.*;

@RestController
public class ExportController {



    @GetMapping("/test/export")
    public String export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            //新建一个临时的file，指定名称和路径
            File excelFile;
            excelFile = new File(System.getProperty("java.io.tmpdir") + File.separator + System.currentTimeMillis() + ".xls");
            excelFile.createNewFile();

            //这里是用于显示我们想要的列名，不需要可以忽略
            List<List<String>> headList = new ArrayList();
            List<String> head1 = new ArrayList();
            head1.add("序列号");
            List<String> head2 = new ArrayList();
            head2.add("姓名");
            List<String> head3 = new ArrayList();
            head3.add("年龄");
            List<String> head4 = new ArrayList();
            head4.add("性别");
            headList.add(head1);
            headList.add(head2);
            headList.add(head3);
            headList.add(head4);

            //com.demo.Student权限定名，可以前端动态传递过来，这里先写成定值了
            Class entityClass = Class.forName("com.example.controller.Student");

            List<String> excluteNameList = new ArrayList<>();
            excluteNameList.add("name");
            ExcelWriter excelWriter = EasyExcel.write(excelFile)//传参是File，可以有两个参（File，class）指定了class类型
                    .head(headList)//指定每列的标题
                    .excludeColumnFiledNames(excluteNameList)//指定不想显示的列名称
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet(excelFile.getName()).build();

            List<Student> studentList = new ArrayList();
            //这里循环加4条数据
            for (int i = 0;i<4;i++){
                Student stu = new Student();
                stu.setId(i);
                stu.setName("张三"+i);
                stu.setAge(i);
                stu.setSex("女");
                studentList.add(stu);
            }
            //这里是模仿了后端调用http请求，返回json串，所以进行了一个转换，可以忽略下面两步，直接传studentList
            //实际是前端传递一个url查询的路径，可以拼接查询条件
            //我们通过http调用去请求，如果有分页，可以传回分页参数，后端去循环给excel拼接数据
            String s = toJSONString(studentList);
            List list = parseArray(s,entityClass);

            excelWriter.write(list,writeSheet);
            excelWriter.finish();//finish代表我的excel写入完成了
            //下面是用response将excel刷到页面上
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + URLEncoder.encode(excelFile.getName(), "UTF-8"));
            response.flushBuffer();
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(new FileInputStream(excelFile));
                OutputStream os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int j = bis.read(buffer);
                while (j != -1) {
                    os.write(buffer, 0, j);
                    j = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (bis != null) {
                    try {
                        bis.close();
                        excelFile.delete();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "导出成功";
        } catch (Exception busExcep) {
            return busExcep.toString();
        }
    }

    @PostMapping("/PDFTest")
    public void exportPDFTest(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(System.currentTimeMillis()+"", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".pdf");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();// 构建字节输出流
            // 实例化文档对象
            Document document = new Document(PageSize.A4, 10, 10, 10, 10);
            // 创建 PdfWriter 对象 文件的输出路径+文件的实际名称
            PdfWriter writer = PdfWriter.getInstance(document, baos);// 设置分页
            // writer.setPageEvent(new PDFMaker());
            document.open();// 打开文档
            // 创建一个段落
            document.add(PDFUtil.getPDFParagraph("测试PDF标题", 0, 20, Element.ALIGN_CENTER, 1));
            // 标题
            String[] title = {"ID", "姓名", "年龄", "生日", "出生地", "身份证", "住址"};
            // 列宽
            int tableWidth[] = {10, 15, 15, 15, 15, 15, 15};
            Map<String, Object> map = new HashMap<>();
            //自定义数据列
            List<UserPdfExport> userPdfExports = new ArrayList<>();
            UserPdfExport userPdfExport = new UserPdfExport();
            userPdfExport.setId(1);
            userPdfExport.setName("zhang");
            userPdfExport.setAge(12);
            userPdfExport.setBirthday("232");
            userPdfExport.setCsd("住址住址住址住址住址住址住址住址");
            userPdfExport.setIdCard("住址住址住址");
            userPdfExport.setAddress("住址住址住址住址阿斯顿发送到发送到发送到发送到发送到发送到阿斯顿发送到阿斯顿发水电费阿斯顿发送到阿斯蒂芬阿萨德");
            userPdfExports.add(userPdfExport);
            map.put("ksList", userPdfExports);
            if (map != null) {

                if (map.get("ksList") != null) {
                    List list = (List<UserPdfExport>) map.get("ksList");
                    // 获取PDFTable
                    PdfPTable table = PDFUtil.getPDFTable(tableWidth, title, list, 0);
                    // 添加进文档
                    document.add(table);
                }

            }
            // 关闭文档
            if (document != null) {
                document.close();
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            OutputStream os = response.getOutputStream();
            // 设置文件大小
            // response.setContentLength((int) f.length());
            os.write(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
