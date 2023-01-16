package com.example.pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class PDF5 {


    private static final String PDFTYPEFACE = "c:\\windows\\fonts\\simkai.ttf";


    /**
     * 生成一个带有目录(可跳转)的pdf文件(支持中文).
     * 如果目录两页,请为pdf多留出一页空白内容.
     * 此方法不能生成书签.
     *
     * @param sourceFile      源pdf文件
     * @param targetFile      带有目录(可跳转)的pdf文件
     * @param catalogs        目录数据map<标题,页码>
     * @param catalogTitle    目录上方第一行文字
     * @param catalogPageSize 目录占页大小(为锚点偏移做参数)
     * @return targetFile
     * @throws IOException
     */
    public static String createPdfWithCatalog(String sourceFile, String targetFile, String catalogTitle,
                                              Map<String, Integer> catalogs, int catalogPageSize) throws IOException {
        Map<String, Integer> catalogsTemp = new TreeMap<>();
        catalogsTemp.putAll(catalogs);
        //实际页码索引偏移量为 目录占页数-1
        int offset = catalogPageSize - 1;
        for (Map.Entry<String, Integer> entry : catalogsTemp.entrySet()) {
            entry.setValue(entry.getValue() + offset);
        }

        File file = new File(targetFile);
        file.getParentFile().mkdirs();
        PdfWriter pdfWriter = new PdfWriter(targetFile);
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDoc);
        PdfFont font0 = PdfFontFactory.createFont(PDFTYPEFACE, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        if (catalogTitle == null) {
            catalogTitle = " ";
        }
        document.add(new Paragraph(new Text(catalogTitle))
                .setTextAlignment(TextAlignment.CENTER).setFont(font0));
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(sourceFile));
        int numberOfPages = firstSourcePdf.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfPage page = firstSourcePdf.getPage(i).copyTo(pdfDoc);
            pdfDoc.addPage(page);
            for (Map.Entry<String, Integer> entry : catalogsTemp.entrySet()) {
                if (i == entry.getValue()) {
                    String destinationKey = "p" + (pdfDoc.getNumberOfPages() - 1);
                    PdfArray destinationArray = new PdfArray();
                    destinationArray.add(page.getPdfObject());
                    destinationArray.add(PdfName.XYZ);
                    destinationArray.add(new PdfNumber(0));
                    destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
                    destinationArray.add(new PdfNumber(1));
                    try {
                        pdfDoc.addNamedDestination(destinationKey, destinationArray);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    Paragraph p = new Paragraph();
                    PdfFont font = PdfFontFactory.createFont(PDFTYPEFACE, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                    p.setFont(font);
                    p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
                    p.add(entry.getKey());
                    p.add(new Tab());
                    p.add(String.valueOf(pdfDoc.getNumberOfPages() - 1));
                    p.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey));
                    document.add(p);
                }
            }
        }
        firstSourcePdf.close();
        document.close();
        return targetFile;
    }

    int catalogPdfPageSize = 2;

    public static void main(String[] args) throws IOException {
        Map<String, Integer> catalogs =  new HashMap<>();
        catalogs.put("目录test",0);
        catalogs.put("asdasdas",1);
        catalogs.put("爱生气翁",2);
        catalogs.put("删除已",3);
        catalogs.put("asdasdasd",4);
        catalogs.put("佛号频繁更换",5);
        catalogs.put("而我二翁群翁",6);
        catalogs.put("VB每年现场v",7);



        createPdfWithCatalog("E:\\liuce\\g1671528549338.pdf", "E:\\liuce\\转换后带有目录的pdf文件4.pdf", "目录"
                ,catalogs,2);
    }



}
