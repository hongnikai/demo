package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.entity.DemoData;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.MultipartDef;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class ImportController {

    @PostMapping("/testImport")
    public void testImport(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();


    }


}
