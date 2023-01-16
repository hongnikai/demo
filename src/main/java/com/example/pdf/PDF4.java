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

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PDF4 {

    /**
     * 生成一个带有目录(可跳转)的pdf文件(支持中文).
     * <p>
     * 如果目录两页,请为pdf多留出一页空白内容.
     * <p>
     * 此方法不能生成书签.
     *
     * @param sourceFile      源pdf文件
     * @param targetFile      带有目录(可跳转)的pdf文件
     * @param catalogs        目录数据map
     * @param catalogTitle    目录上方第一行文字
     * @param catalogPageSize 目录占页大小(为锚点偏移做参数)
     * @return targetFile
     */

    public static String createPdfWithCatalog(String sourceFile,

                                              String targetFile,

                                              String catalogTitle

                                             /* Map catalogs,

                                              int catalogPageSize*/) throws IOException {

//        Map catalogsTemp = new TreeMap<>();
//
//        catalogsTemp.putAll(catalogs);
//
////实际页码索引偏移量为 目录占页数-1
//
//        int offset = catalogPageSize - 1;
//
//        for (Map.Entry entry : catalogsTemp.entrySet()) {
//
//            entry.setValue(entry.getValue() + offset);
//
//        }

        File file = new File(targetFile);

        file.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(targetFile));

        Document document = new Document(pdfDoc);

        PdfFont font0 = PdfFontFactory.createFont("c:\\windows\\fonts\\simkai.ttf",  false);

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

            for (int k = 0; k < 3; k++) {


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

                    PdfFont font = PdfFontFactory.createFont("c:\\windows\\fonts\\simkai.ttf",  false);

                    p.setFont(font);

                    p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));

                    p.add("第 " + k + " 页");

                    p.add(new Tab());
                int ofPages = pdfDoc.getNumberOfPages();
                p.add(String.valueOf( ofPages- 1));

                    p.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey));

                    document.add(p);


            }

        }

        firstSourcePdf.close();

        document.close();

        return targetFile;

    }


//catalogPdfPageSize,预测目录会有两页,此参数影响锚点的偏移和目录是否会与主要内容重叠(计算目录页数的方法在文章最后贴出,见分割线)

    int catalogPdfPageSize = 2;

    public static void main(String[] args) throws IOException {
        createPdfWithCatalog("E:\\liuce\\g1671528549338.pdf", "E:\\liuce\\转换后带有目录的pdf文件3.pdf", "目录第一行标题随意啦");
//        createCatalogPdfOnly("E:\\liuce\\g1671528549338.pdf","E:\\liuce\\转换后带有目录的pdf文件2.pdf",new HashMap<>());
//        使用示例:

//一个只有一页的pdf文件,必须,该文件必须只有一页!!!
        String blankPdf = "d://一个只有一页的pdf文件.pdf";
//生成的只有目录的pdf文件
        String catalogPagesTempFile = "随便七个名字把.pdf";
//catalog 即上文中的catalogs数据 ,形式为 Map map ....
//完成 ,catalogPdfPageSize即目录所占总页数 ,可将此参数用作上边方法得偏移参数
//        int catalogPdfPageSize = createCatalogPdfOnly(blankPdf, catalogPagesTempFile, catalog);
    }

///*************一个特别难看的分割线**************************一个特别难看的分割线*************************


    /**
     * 新改的方法 ,生成目录pdf ,只是为了方便计算页数.
     * <p>
     * 基于createPdfWithCatalog()代码改编,该方法进保留生成目录功能.
     *
     * @param sourceFile sourceFile
     * @param targetFile targetFile
     * @param catalogs   catalogs
     * @return 目录页数 page size
     * @throws IOException IOException
     */

    public static int createCatalogPdfOnly(String sourceFile, String targetFile, Map catalogs) throws IOException {

        File file = new File(targetFile);

        file.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(targetFile));

        Document document = new Document(pdfDoc);

        document.add(new Paragraph(new Text("The Revenant nominations list"))

                .setTextAlignment(TextAlignment.CENTER));

        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(sourceFile));

        for (int j=0;j<=3;j++) {

            Paragraph p = new Paragraph();

            p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));

            p.add("0"+j);

            p.add(new Tab());

            p.add(String.valueOf(pdfDoc.getNumberOfPages() - 1));

            document.add(p);

        }

        firstSourcePdf.close();

        int pages = pdfDoc.getNumberOfPages();

        document.close();

        return pages;

    }

}
