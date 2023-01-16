package com.example.word;

import org.docx4j.org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.docx4j.org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

public class Word1 {

    public static void main(String[] args) {

        String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Strict//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<html lang=\"zh\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<title>HTML转DOCX测试示例</title>\n" +
                "<style type=\"text/css\">\n" +
                "h1 {\n" +
                "\ttext-align: center;\n" +
                "}\n" +
                "table {\n" +
                "\tborder-collapse: collapse;\n" +
                "\twidth: 100%;\n" +
                "}\n" +
                "table th {\n" +
                "\ttext-align: center;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<div>\n" +
                "\t\t<h1>HTML转DOCX测试示例</h1>\n" +
                "\t</div>\n" +
                "\t<div>\n" +
                "\t\t<table border=\"1\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th width=\"20%\">编号：</th>\n" +
                "\t\t\t\t<td>E125800</td>\n" +
                "\t\t\t\t<th width=\"20%\">日期：</th>\n" +
                "\t\t\t\t<td>2020-01-10</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th width=\"20%\">审核：</th>\n" +
                "\t\t\t\t<td>审核人</td>\n" +
                "\t\t\t\t<th width=\"20%\">部门：</th>\n" +
                "\t\t\t\t<td>技术部</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                "\t</div>\n" +
                "\t<div>\n" +
                "\t\t<p>以下为示例内容。</p>\n" +
                "\t\t<h2>醉花阴·薄雾浓云愁永昼</h2>\n" +
                "\t\t<p><em>李清照</em></p>\n" +
                "\t\t<p>薄雾浓云愁永昼，瑞脑销金兽。佳节又重阳，玉枕纱厨，半夜凉初透。<br />东篱把酒黄昏后，有暗香盈袖。莫道不销魂，帘卷西风，人比黄花瘦。</p>\n" +
                "\t\t<h2>念奴娇·赤壁怀古</h2>\n" +
                "\t\t<p><em>苏轼</em></p>\n" +
                "\t\t<p>大江东去，浪淘尽，千古风流人物。<br />故垒西边，人道是，三国周郎赤壁。<br />乱石穿空，惊涛拍岸，卷起千堆雪。<br />江山如画，一时多少豪杰。<br />遥想公瑾当年，小乔初嫁了，雄姿英发。<br />羽扇纶巾，谈笑间，樯橹灰飞烟灭。<br />故国神游，多情应笑我，早生华发。<br />人生如梦，一尊还酹江月。</p>\n" +
                "\t\t<h2>登飞来峰</h2>\n" +
                "\t\t<p><em>王安石</em></p>\n" +
                "\t\t<p>飞来山上千寻塔，闻说鸡鸣见日升。<br />不畏浮云遮望眼，只缘身在最高层。</p>\n" +
                "\t</div>\n" +
                "\t<div>\n" +
                "\t\t<p>网络图片1</p>\n" +
                "\t\t<img src=\"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2F20170105%2Fb01b-fxzkfuk2266724.png&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1623917122&t=0eef227b3c5f25073c174523fd5410c7\" alt=\"W3cLOGO\" />\n" +
                "\t</div>\n" +
                "\t<div>\n" +
                "\t\t<p>网络图片2</p>\n" +
                "\t\t<img src=\"https://mmbiz.qpic.cn/mmbiz_jpg/yWXmuSFeCk1X8myzCEicobIGLZCnFdSWEYe8et6zaGK1VsWlIa8ubyhMH5KIpsmMtQdkQ3kibKfK9ruD0vp578Vw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1\" alt=\"海边\" />\n" +
                "\t</div>\n" +
                "\t<div>\n" +
                "\t\t<p>以下为列表。</p>\n" +
                "\t\t<p><strong>无序列表：</strong></p>\n" +
                "\t\t<ul>\n" +
                "\t\t\t<li>无序列表项一</li>\n" +
                "\t\t\t<li>无序列表项二</li>\n" +
                "\t\t\t<li>无序列表项三</li>\n" +
                "\t\t\t<li>无序列表项四</li>\n" +
                "\t\t\t<li>无序列表项五</li>\n" +
                "\t\t</ul>\n" +
                "\t\t<p><strong>有序列表：</strong></p>\n" +
                "\t\t<ol>\n" +
                "\t\t\t<li>有序列表项一</li>\n" +
                "\t\t\t<li>有序列表项二</li>\n" +
                "\t\t\t<li>有序列表项三</li>\n" +
                "\t\t\t<li>有序列表项四</li>\n" +
                "\t\t\t<li>有序列表项五</li>\n" +
                "\t\t</ol>\n" +
                "\t\t<p><strong>嵌套列表：</strong></p>\n" +
                "\t\t<ol>\n" +
                "\t\t\t<li>有序列表项一</li>\n" +
                "\t\t\t<li>有序列表项二</li>\n" +
                "\t\t\t<li>有序列表项三\n" +
                "\t\t\t\t<ol style=\"list-style: lower-alpha;\">\n" +
                "\t\t\t\t\t<li>列表项三的子项1</li>\n" +
                "\t\t\t\t\t<li>列表项三的子项2</li>\n" +
                "\t\t\t\t\t<li>列表项三的子项3</li>\n" +
                "\t\t\t\t\t<li>列表项三的子项4</li>\n" +
                "\t\t\t\t\t<li>列表项三的子项5</li>\n" +
                "\t\t\t\t</ol>\n" +
                "\t\t\t</li>\n" +
                "\t\t\t<li>有序列表项四</li>\n" +
                "\t\t\t<li>有序列表项五</li>\n" +
                "\t\t</ol>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>";
        POIFSFileSystem fs = new POIFSFileSystem();
        DirectoryEntry root = fs.getRoot();
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes());
        try {
            root.createDocument("WordDocument", bais);
            fs.writeFilesystem(new FileOutputStream("E:\\liuce\\导出带表格的html到doc.doc"));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }




}
