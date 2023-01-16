package com.example.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class DocTest {

    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\liuce\\test3.pdf"));
        document.open();
        BaseFont baseFont = BaseFont.createFont("c:\\windows\\fonts\\simkai.ttf",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 10f,Font.NORMAL, BaseColor.BLACK);
        document.add(new Paragraph("创建pdf文件.支持中文......", font));
        //显示中文必须设置font-family。这里为宋体(simsun)。中文可为汉字，也可为汉字的unicode
        String str = "123\u6d4b\u8bd5abc测试<img src ='https://csdnimg.cn/medal/qixiebiaobing4@240.png'/>";
        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
        ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
        worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));





        String s = "123\u6d4b\u8bd5abc测试<img src ='https://csdnimg.cn/medal/qixiebiaobing4@240.png'/>";
        ByteArrayInputStream bin = new ByteArrayInputStream(s.getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, bin, Charset.forName("UTF-8"), new ChinaFontProvide());

        document.close();
        writer.close();

    }

    /**
     *
     * 提供中文
     *
     */
    public static final class ChinaFontProvide implements FontProvider {

        @Override
        public Font getFont(String arg0, String arg1, boolean arg2, float arg3,
                            int arg4, BaseColor arg5) {
            BaseFont bfChinese = null;
            try {
                //bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                //直接引用 下载好的字体文件
                bfChinese = BaseFont.createFont("D:\\simsun.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
            return FontChinese;
        }

        @Override
        public boolean isRegistered(String arg0) {
            return false;
        }
    }


}
