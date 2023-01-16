package com.example.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PDF7 {


    public void insertObject(HttpServletResponse response) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        String content = "You can learn Java programming on the " +
                "following website: ";
        Paragraph country = new Paragraph();

        // 创建一个链接到外部网站的新锚点
        // 并将此锚点添加到段落中。
        Anchor anchor = new Anchor("Learn Java by Examples");
        anchor.setReference("https://nhooo.org");
        country.add(anchor);
        document.add(country);

        Chapter chapter = new Chapter(0);
        Paragraph paragraph = new Paragraph();
        Anchor anchor2 = new Anchor("this is a point");
        anchor2.setReference("#ceshi");
        paragraph.add(anchor2);
        Chunk chunk = new Chunk(new DottedLineSeparator());
        Chunk pageno = new Chunk(2+"");
        paragraph.add(chunk);
        paragraph.add(pageno);
        chapter.addSection(paragraph,1);
        document.add(chapter);


        Anchor anchor3 = new Anchor("这是个锚点",getChineseFont());
        anchor3.setName("ceshi");
        Paragraph paragraph2 = new Paragraph();
        paragraph2.add(anchor3);
        document.add(paragraph2);


        document.close();
    }

    private Font getChineseFont() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 10f,Font.NORMAL,BaseColor.BLACK);
        return font;
    }

}
