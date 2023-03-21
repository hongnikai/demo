package com.example.controller;

import com.example.util.BatchDownFilesUtils;
import com.example.util.FilePath;
import com.example.util.FileUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@RestController
public class ExportHtmlController {

    @PostMapping("/exportHtml2")
    public void exportHtml2(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<File> fileList = Lists.newArrayList();
        File file1 = new File("C:\\downloadFile\\model\\type\\test.txt");
        File file2 = new File("C:\\downloadFile\\model\\type\\test2.txt");
        fileList.add(file1);
        fileList.add(file2);
        //随机名称临时存放zip的目录，导出后删除
        String destDir = "/var/"+ File.separator + UUID.randomUUID().toString();
        File destDirFile = new File(destDir);
        if(!destDirFile.exists()){
            destDirFile.mkdirs();
        }
        try {
            BatchDownFilesUtils.downLoadFiles(fileList,"C:\\downloadFile\\model\\type"+ ".zip",request,response);
        } finally {
            delFile(destDirFile);
        }
    }

    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
            return file.delete();
        }
    }


    @PostMapping("/exportHtml")
    public void exportHtml(HttpServletResponse response, HttpServletRequest request){
        createDir();
//        文件创建
        String fileName = "test2.txt";
        String path = FileUtil.getByPath(FilePath.UPLOAD_MODEL_TYPE_PATH + File.separator + fileName);
        File newFile =new File(path);
        File parentFile = new File(newFile.getParent());//获得父级文件夹
        if (!parentFile.exists()) {
            parentFile.mkdirs();// 如果不存在则创建目录
        }
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        List<String> data = new ArrayList<>();
        data.add("<html>");
        data.add("萨达哈沙硕大的");
        data.add("</html>");
        writeFile(path,data);
    }

    //传入一个带绝对路径的文件名，和数据组。
    public static Boolean writeFile(String path, List<String> data) {
        Boolean flag = false;
        File newFile = new File(path);
        File parentFile = newFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();// 创建目录,带s多级，mkdir单个
        }
        BufferedWriter bw = null;
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileWriter fw = new FileWriter(newFile.getAbsoluteFile(), true);  //true表示可以追加新内容
            bw = new BufferedWriter(fw);
            Integer size = data.size();
            if (data != null && size > 0) {
                for (Integer i = 0; i < size; i++) {
                    String content = data.get(i);
                    if (i < (size - 1)) {
                        content += "\n";
                    }
                    bw.write(content);
                }
            }
            flag = true;
        } catch (IOException e) {
            flag = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    private void createDir(){
        String path = FileUtil.getByPath(FilePath.UPLOAD_MODEL_TYPE_PATH);//通过工具类拼接合适目录
        File parentFile = new File(path);
        if (!parentFile.exists()) {
            parentFile.mkdirs();// 创建目录
        }
    }


    /**
     * 将批复函压缩成zip(导出pdf)
     * @param srcFiles
     * @param out
     */
    public static void toPDFZip(List<Map<String, Object>> srcFiles, OutputStream out) {
        try (ZipOutputStream zos = new ZipOutputStream(out);) {
            // 最终压缩文件
            for (Map<String, Object> mapFile : srcFiles) {
                String fileName = mapFile.get("fileName").toString();
                byte[] buffer = (byte[]) mapFile.get("pdf");
                ZipEntry entry = new ZipEntry(fileName);
                zos.putNextEntry(entry);
                zos.write(buffer);
            }
            zos.flush();
            zos.close();
        } catch (Exception e) {
            throw new RuntimeException("zip error", e);
        }
    }

}
