package com.example.conf;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.font.FontProvider;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class Html2PdfUtil {

    private static final String SHIPPED_FONT_RESOURCE_PATH = "C:\\Windows\\Fonts\\simsun.ttc";

    public static void htmlToPdf(String html, HttpServletResponse response) throws Exception {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(System.currentTimeMillis() + "", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".pdf");
        OutputStream os = response.getOutputStream();
//        os.write(html.getBytes(StandardCharsets.UTF_8));
        PdfDocument pdf = new PdfDocument(new PdfWriter(os));
        Document document = new Document(pdf, PageSize.A2);
        InputStream   htmlStream   =   new ByteArrayInputStream(html.getBytes());


        ConverterProperties converterProperties = new ConverterProperties();
        //解决中文无法显示
        FontProvider fontProvider = new DefaultFontProvider();
        System.out.println("SHIPPED_FONT_RESOURCE_PATH:  "+SHIPPED_FONT_RESOURCE_PATH);
        FontProgram fontProgram = FontProgramFactory.createFont(SHIPPED_FONT_RESOURCE_PATH+",0");
        fontProvider.addFont(fontProgram);
        converterProperties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(htmlStream, pdf, converterProperties);
        document.close();
    }

}
