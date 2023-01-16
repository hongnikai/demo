package com.example.service;

import javax.servlet.http.HttpServletResponse;

public interface PdfGenerator {

    /**
     * 获取字体文件我的是在resources下面fonts包里
     * @return
     */
    default String fontpath() {
        return "/fonts/Alibaba-PuHuiTi-Regular.otf";
    }

    void generatePdf(HttpServletResponse response) throws Exception;

}
