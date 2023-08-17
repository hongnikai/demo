package com.example.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class PDFUtil {


    static BaseFont baseFont = null;

    static {
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * PDF大标题字体
     */
    public static Font PDFTITLEFONT = new Font(baseFont, 16, Font.BOLD);

    /**
     * PDF小标题字体
     */
    public static Font PDFTITLEFONT1 = new Font(baseFont, 13, Font.NORMAL);

    /**
     * 表格宽度百分比
     */
    public static Integer WIDTHPERCENTAGE = 98;

    /**
     * 表格标题字体
     */
    public static Font TITLEFONT = new Font(baseFont, 12, Font.NORMAL);

    /**
     * 翻页加载表头
     */
    public static Integer HEADERROWS = 1;

    /**
     * 翻页不加载表头
     */
    public static Integer NOHEADERROWS = 0;

    /**
     * 表格内容字体
     */
    public static Font CONTENTFONT = new Font(baseFont, 9, Font.NORMAL);

    /**
     * PDF表格样式
     */
    private static PdfPCell cell = new PdfPCell();

    /**
     * 获取表格
     */
    public static PdfPCell getCell() {
        // 水平居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        // 边距
        cell.setPadding(1);
        // 行高
        cell.setMinimumHeight(22);
        cell.setBorderColor(BaseColor.BLACK);
        // 不换行
        // cell.setNoWrap(true);
        // 颜色淡化
        // cell.setBorderColor(Color.decode("#EBEEF5"));
        cell.setBorderColor(new BaseColor(1));
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setPadding(3);
        return cell;
    }

    /**
     * 获取表格并赋值
     */
    public static PdfPCell getCell(Paragraph content) {
        cell = getCell();
        // 设置内容
        cell.setPhrase(content);
        return cell;
    }

    /**
     * @param titleNum   列数
     * @param tableWidth 列宽
     * @param titles     标题集合
     * @param contents   内容集合
     * @param headerRows 是否再次加载表头
     * @return
     * @throws Exception
     * @Description 生成PDF表格
     */
    public static void setTableStyle(PdfPTable table, PdfPCell cell) {
//			设置表格样式
        table.setLockedWidth(true);
        table.setTotalWidth(500);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
//			设置单元格样式
        cell.setMinimumHeight(35);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorLeft(BaseColor.BLACK);
        cell.setBorderColorRight(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setPadding(3);
    }
    public static PdfPTable getPDFTable(int[] tableWidth, String[] titles, List<Object> contents, int headerRows) throws Exception {
        // 创建表格对象
        // 列数
        PdfPTable table = new PdfPTable(titles.length);

        // 表格宽度百分比
        table.setWidthPercentage(WIDTHPERCENTAGE);

        table.setSpacingBefore(20);

        table.setSpacingAfter(20);

        // 列宽百分比
        if (tableWidth != null) table.setWidths(tableWidth);

        // 翻页加载表头
        if (headerRows == HEADERROWS) table.setHeaderRows(HEADERROWS);

        // 标题集合
        String[] pdfTitles = titles;
        if (pdfTitles != null && pdfTitles.length > 0) {
            // 标题
            for (String pdfTitle : pdfTitles) {
                PdfPCell title = getCell(new Paragraph(pdfTitle, TITLEFONT));
                table.addCell(title);
            }
        }
        // 内容集合
        if (contents != null && contents.size() > 0) {
            Field[] fields = contents.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
        }

        // 内容集合
        if (contents != null && contents.size() > 0) {
            for (Object obj : contents) {
                Field[] fields = obj.getClass().getDeclaredFields();
                String[] fieldNames = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fieldNames[i] = fields[i].getName();
                    String firstLetter = fieldNames[i].substring(0, 1).toUpperCase();
                    String getter = "get" + firstLetter + fieldNames[i].substring(1);
                    Method method = obj.getClass().getMethod(getter, new Class[]{});
                    Object value = method.invoke(obj, new Object[]{});
                    PdfPCell content = getCell(new Paragraph(String.valueOf(value), CONTENTFONT));
                    table.addCell(content);
                }
                // 撑行数，否则最后一行会消失
                table.addCell("");
                table.completeRow();
            }
        }
        return table;
    }

    /**
     * @param content 段落内容
     * @param top     上边距
     * @param bottom  下边距
     * @param align   居中
     * @param type    标题类别
     * @return
     * @throws Exception
     */
    public static Paragraph getPDFParagraph(String content, int top, int bottom, int align, int type) throws Exception {
        Paragraph paragraph = null;
        if (type == 1) {
            paragraph = new Paragraph(content, PDFTITLEFONT);
        } else {
            paragraph = new Paragraph(content, PDFTITLEFONT1);
        }
        paragraph.setSpacingAfter(top);
        paragraph.setSpacingBefore(bottom);
        paragraph.setAlignment(align);
        return paragraph;
    }


}
