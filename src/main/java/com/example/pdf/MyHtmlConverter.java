package com.example.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.attach.Attacher;
import com.itextpdf.html2pdf.exception.Html2PdfException;
import com.itextpdf.html2pdf.util.ReflectionUtils;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.styledxmlparser.IXmlParser;
import com.itextpdf.styledxmlparser.node.IDocumentNode;
import com.itextpdf.styledxmlparser.node.impl.jsoup.JsoupHtmlParser;

public class MyHtmlConverter {
    public static Document convertToPdf(String html, PdfDocument pdfDocument, ConverterProperties converterProperties) {
        Document document = convertToDocument(html, pdfDocument, converterProperties);
        return document;
    }

    public static Document convertToDocument(String html, PdfDocument pdfDocument, ConverterProperties converterProperties) {
        ReflectionUtils.scheduledLicenseCheck();
        if (pdfDocument.getReader() != null) {
            throw new Html2PdfException("PdfDocument should be created in writing mode. Reading and stamping is not allowed");
        } else {
            IXmlParser parser = new JsoupHtmlParser();
            IDocumentNode doc = parser.parse(html);
            return Attacher.attach(doc, pdfDocument, converterProperties);
        }
    }
}
