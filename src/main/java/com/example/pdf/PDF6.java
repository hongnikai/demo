package com.example.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PDF6 {

    public void insertObject(HttpServletResponse response) throws Exception {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // Anchor超链接和锚点对象: internal and external links
        Paragraph country = new Paragraph();
        Anchor dest = new Anchor("我是锚点，也是超链接", getChineseFont());
        dest.setName("CN"); // 设置锚点的名字
        dest.setReference("http://www.china.com");// 连接
        country.add(dest);
        country.add(String.format(": %d sites", 10000));
        document.add(country);

        Anchor toUS = new Anchor("连接到设置的CN锚点。", getChineseFont());
        toUS.setReference("#CN");// 取到锚点
        document.add(toUS);


        // 图片Image对象
        Image img = Image.getInstance("E:\\liuce\\微信图片_20220505153725.png");
        img.setAlignment(Image.LEFT);
        img.setBorder(Image.BOX);
        img.setBorderWidth(10);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1000, 72);// 大小
        img.setRotationDegrees(-30);// 旋转
        document.add(img);

        //设置一个坐标点，用于goTop定位
        Anchor topLine = new Anchor("This name is US");
        topLine.setName("US");
        document.add(topLine);

        // Chapter, Section对象（目录对象）
//        Paragraph title = new Paragraph("", getChineseFont());
        Chapter chapter = new Chapter(0);


        Anchor toUS2 = new Anchor("二级标题1", getChineseFont());
        toUS.setReference("#ceshi");// 取到锚点
        Paragraph title2 = new Paragraph(null, getChineseFont());
        title2.add(toUS2);

        Section section = chapter.addSection(title2,1);
        section.setBookmarkTitle("bmk");// 左边目录显示的名字，不写就默认名
        section.setIndentation(30);
        section.setIndentationLeft(5);
        section.setBookmarkOpen(false);
        section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section section2 = chapter.addSection(new Paragraph("二级标题2", getChineseFont()),1);
        section2.setIndentation(30);
        section2.setIndentationLeft(5);
        section2.setBookmarkOpen(false);
        section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);

        Section subsection = section.addSection(new Paragraph("三级标题1", getChineseFont()),2);
        subsection.setIndentationLeft(10);
        // subsection.setNumberDepth(1);
        subsection.setNumberStyle(Section.NUMBERSTYLE_DOTTED);

        Section subsection2 = section2.addSection(new Paragraph("三级标题2", getChineseFont()),2);
        subsection2.setIndentationLeft(10);
        subsection2.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
        document.add(chapter);


        Anchor goTop = new Anchor("Go Top");
        goTop.setReference("#US");
        document.add(goTop);

        document.close();

    }

    private Font getChineseFont() throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 10f,Font.NORMAL,BaseColor.BLACK);
        return font;
    }


}
