package com.example.pdf;

import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class PDF3 {

    private final static int FONT_NORMAL = Font.NORMAL;
    public static void main(String[] args) {
        String value = "<p>图片导出</p>\n<p>&nbsp;</p>\n<p>啦啦</p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p><img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2F20170105%2Fb01b-fxzkfuk2266724.png&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623917122&t=0eef227b3c5f25073c174523fd5410c7\" alt=\"\" width=\"640\" height=\"640\" /></p>\n<p>&nbsp;</p>\n<p>发放</p>\n<p>&nbsp;</p>\n<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n<tbody>\n<tr>\n<td style=\"width: 16.6667%;\">大丰收</td>\n<td style=\"width: 16.6667%;\">阿凡达</td>\n<td style=\"width: 16.6667%;\">执行</td>\n<td style=\"width: 16.6667%;\">打算</td>\n<td style=\"width: 16.6667%;\">阿道夫</td>\n<td style=\"width: 16.6667%;\">展旭</td>\n</tr>\n<tr>\n<td style=\"width: 16.6667%;\">安抚</td>\n<td style=\"width: 16.6667%;\">啊</td>\n<td style=\"width: 16.6667%;\">的</td>\n<td style=\"width: 16.6667%;\">大</td>\n<td style=\"width: 16.6667%;\">&nbsp;发</td>\n<td style=\"width: 16.6667%;\">发送到</td>\n</tr>\n<tr>\n<td style=\"width: 16.6667%;\">阿道夫</td>\n<td style=\"width: 16.6667%;\">ad</td>\n<td style=\"width: 16.6667%;\">答复</td>\n<td style=\"width: 16.6667%;\">阿斯蒂芬</td>\n<td style=\"width: 16.6667%;\">&nbsp;发多少</td>\n<td style=\"width: 16.6667%;\">发的</td>\n</tr>\n<tr>\n<td style=\"width: 16.6667%;\">啊</td>\n<td style=\"width: 16.6667%;\">的</td>\n<td style=\"width: 16.6667%;\">更舒服的</td>\n<td style=\"width: 16.6667%;\">国防生的</td>\n<td style=\"width: 16.6667%;\">&nbsp;萨芬的</td>\n<td style=\"width: 16.6667%;\">阿斯蒂芬</td>\n</tr>\n<tr>\n<td style=\"width: 16.6667%;\">啊</td>\n<td style=\"width: 16.6667%;\">是大法官</td>\n<td style=\"width: 16.6667%;\">十多个</td>\n<td style=\"width: 16.6667%;\">杀伐果断</td>\n<td style=\"width: 16.6667%;\">杀伐果断</td>\n<td style=\"width: 16.6667%;\">用复合弓的</td>\n</tr>\n</tbody>\n</table>\n<p>&nbsp;</p>\n<p><img src=\"E://liuce//微信图片_20220505153725.png\" alt=\"\" width=\"300\" height=\"108\" /></p>\n<p>第三方</p>\n<p>&nbsp;</p>";

        htmlToPdf(value, "E://liuce//g"+System.currentTimeMillis()+".pdf");
    }

    public static void htmlToPdf(String content, String pdfPath) {
        Document document = new Document();
        try {
//            BaseFont bfChinese = BaseFont.createFont(ResourceUtils.getFile("classpath:static/Fonts/simfang.ttf").getAbsolutePath(),
//                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont(ResourceUtils.getFile("c:\\windows\\fonts\\simkai.ttf").getAbsolutePath(),
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);


            StyleSheet styleSheet = new StyleSheet();
            styleSheet.loadTagStyle("body", "leading", "16,0");

            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();

            List htmlList = HTMLWorker.parseToList(new StringReader(content), styleSheet);
            Paragraph context = new Paragraph();

            context.setKeepTogether(false);
            Font FontChinese = new Font(bfChinese, 10, FONT_NORMAL);
            for (Object aHtmlList : htmlList) {
                Element e = (Element) aHtmlList;
                System.out.println(e.type() + e.getChunks().toString() + ": " + e.toString());
                int type = e.type();
                if (type == 12) { // 文本和图片
                    List chunks = e.getChunks();
                    if (CollectionUtils.isEmpty(chunks)) {
                        continue;
                    }

                    for (Object object : chunks) {
                        Chunk chunk = (Chunk) object;
                        HashMap attributes = chunk.getAttributes();
                        if (MapUtils.isEmpty(attributes)) {
                            context = new Paragraph(chunk.getContent());
                            context.setIndentationLeft(50);
                            context.setIndentationRight(50);
                        } else {
                            context = new Paragraph("");
                            context.setIndentationLeft(50);
                            Image image = chunk.getImage();
                            context.add(image);
                        }
                        document.add(context);
                    }

                } else if (type == 23) { // 表格
                    PdfPTable table = (PdfPTable) aHtmlList;
                    document.add(table);
                }

            }
            document.close();
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
