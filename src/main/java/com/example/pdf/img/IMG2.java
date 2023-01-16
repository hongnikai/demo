package com.example.pdf.img;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMG2 {

    public static void main(String[] args) throws Exception {
        List<String> imgs = new ArrayList<String>();
        String sql = "select ff result from aaa";
//        PreparedStatement ps = DataBase.getPreparedStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            String htmlcontent = rs.getString("result")==null?"":rs.getString("result");
//            if("".equals(htmlcontent)){
//                continue;
//            }
//            imgs.addAll(getImgSrc(htmlcontent));
//        }
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < imgs.size(); i++) {
            if(!temp.contains(imgs.get(i))){
                temp.add(imgs.get(i));
            }
        }
        System.out.println(temp.size());
        for (int i = 0; i < imgs.size(); i++) {
            downloadimg(imgs.get(i));
        }
        System.out.println(imgs.size());
    }
    /**
     *   @description 截取字符串中的img标签中的src地址
     *   @createTime 创建时间：
     */
    public static List<String> getImgSrc(String htmlStr) {

        if( htmlStr == null ){
            return null;
        }

        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Matcher m =
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);

            while (m.find()) {
                if(m.group(1).contains("https://image.135editor.com/files/users/")){
                    pics.add(m.group(1));
                }
            }
        }
        return pics;
    }
    public static void downloadimg(String imgurl) throws Exception {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL(imgurl);
        String mainpath = "E:/htmlcontent";

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            String realpath = mainpath+"/"+imgurl.split("https://image.135editor.com")[1];
            String path = realpath.substring(0,realpath.lastIndexOf("/"));
            //System.out.println(path);
            //创建与135图片服务器相同的目录
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fs = new FileOutputStream(mainpath+"/"+imgurl.split("https://image.135editor.com")[1]);

            byte[] buffer = new byte[51200];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
                System.out.println("下载成功："+imgurl);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    static class DataBase{
//        private static final String className = "com.mysql.jdbc.Driver";
//        private static final String url = "";
//        private static final String username = "";
//        private static final String password = "";
//
//
//        private static Connection conn = null;
//        static{
//            try{
//                Class.forName(className);
//                //2.获得数据库的连接
//                conn= DriverManager.getConnection(url, username, password);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        public static Connection getConnection() throws ClassNotFoundException, SQLException {
//            return conn;
//        }
//
//        public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
//            return getConnection().prepareStatement(sql);
//        }
//
//    }



}
