package com.example.pdf;

import com.example.conf.Html2PdfUtil;
import com.example.service.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PdfController {

    @PostMapping("/exportPdfHtml2")
    public void exportPdfHtml2(HttpServletResponse response) throws Exception {
        pdfGenerator.generatePdf(response);

    }
    @Autowired
    private PdfGenerator pdfGenerator;

    /**
     * 访问接口
     *
     * @param response --响应
     * @throws Exception
     */
    @PostMapping("/exportPdfHtml")
    public void demo(HttpServletResponse response) throws Exception {
        html = html.replace("&nbsp;", " ");
        html = html.replace("&ndash;", "–");
        html = html.replace("&mdash;", "—");
        html = html.replace("&lsquo;", "‘");
        html = html.replace("&rsquo;", "’");
        html = html.replace("&sbquo;", "‚");
        html = html.replace("&ldquo;", "“");
        html = html.replace("&rdquo;", "”");
        html = html.replace("&bdquo;", "„");
        html = html.replace("&prime;", "′");
        html = html.replace("&Prime;", "″");
        html = html.replace("&lsaquo;", "‹");
        html = html.replace("&rsaquo;", "›");
        html = html.replace("&oline;", "‾");
        new Html2PdfUtil().htmlToPdf(html,response);



    }

    public static String html = "<div style=\"position: relative;width: 100mm;height: 100mm;font-family: 黑体;\">\n" +
            "    <div style=\"position: absolute;top: 1mm;right: 34mm;width: 8mm;height: 7mm;line-height: 7mm;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border: 1px solid #000000;text-align: center;font-size: 10.5pt;font-weight: bold;font-family: 黑体;\">\n" +
            "        1/1\n" +
            "    </div>\n" +
            "    <div style=\"position: absolute;top: 4mm;right: 2mm;width: 32mm;height: 4mm;line-height: 4mm;text-align: left;font-size: 8pt;font-family: 黑体;overflow: hidden;white-space: nowrap;\">\n" +
            "        单号<span style=\"font-weight: bold;\">20220710833314</span>\n" +
            "    </div>\n" +
            "    <div id=\"cysDiv\" style=\"position: absolute;top: 8mm;left: 3mm;width: 29mm;height: 7mm;line-height: 7mm;font-size: 15pt;font-family: 黑体;\">\n" +
            "        跑得快快递\n" +
            "    </div>\n" +
            "    <div id=\"ydDiv\" style=\"position: absolute;top: 15mm;left: 3mm;width: 8mm;height: 5mm;line-height: 5mm;font-size: 9pt;font-family: 黑体;\">\n" +
            "        运单\n" +
            "    </div>\n" +
            "    <div id=\"ydhDiv\" style=\"position: absolute;top: 15mm;left: 11mm;width: 47mm;height: 5mm;line-height: 5mm;font-size: 9pt;font-family: 黑体;\">\n" +
            "        TEST1884552126\n" +
            "    </div>\n" +
            "    <div id=\"khyqDiv\" style=\"position: absolute;top: 8mm;left: 32mm;width: 26mm;height: 7mm;font-size: 8pt;font-family: 黑体;\">\n" +
            "        送货时间不限\n" +
            "    </div>\n" +
            "    <div id=\"ddhtmDiv\" style=\"position: absolute;top: 9mm;right: 2mm;width: 40mm;height: 7mm;text-align: center;\">\n" +
            "        <img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHEAAAAcAQAAAAB+b235AAAACXBIWXMAAE69AABOvQFzamgUAAAAEnRFWHRTb2Z0d2FyZQBCYXJjb2RlNEryjnYuAAAAHklEQVR42mPQSTt6dvPmsrbr1gaThLsYGEb5Q4oPAG8dwwVvdZr5AAAAAElFTkSuQmCC\"/>\n" +
            "    </div>\n" +
            "    <div id=\"ddhDiv\" style=\"position: absolute;top: 16mm;right: 2mm;width: 40mm;height: 4mm;line-height: 4mm;font-size: 9pt;text-align: center;\">\n" +
            "        20220710833314\n" +
            "    </div>\n" +
            "    <div id=\"sDiv\" style=\"position: absolute;top: 20mm;left: 0mm;width: 10mm;height: 15mm;line-height: 15mm;text-align: center;font-size: 14pt;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-bottom: 1px solid #000000;border-right: 1px solid #000000;\">\n" +
            "        收\n" +
            "    </div>\n" +
            "    <div id=\"sjrxxDiv\" style=\"position: absolute;top: 20mm;left: 10mm;width: 90mm;height: 5mm;padding-left: 2mm;font-size: 12pt;font-weight: bold;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;\">\n" +
            "        米奇&nbsp;888*****390，888*****390\n" +
            "    </div>\n" +
            "    <div id=\"sjrdzDiv\" style=\"position: absolute;top: 25mm;left: 10mm;width: 90mm;height: 10mm;padding-left: 2mm;padding-right: 2mm;font-family: 黑体;overflow: hidden;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-bottom: 1px solid #000000;\">\n" +
            "        <span style=\"font-size: 12pt;font-weight: bold;\">江苏省.南通市.启东市</span><span style=\"font-size: 10pt;\">.米奇妙妙屋</span>\n" +
            "    </div>\n" +
            "    <div id=\"destRouteDiv\" style=\"position: absolute;top: 35mm;left: 3mm;width: 70mm;height: 20mm;line-height: 20mm;white-space:nowrap;overflow: hidden;font-size: 23pt;font-weight: bold;font-family: 黑体;\">\n" +
            "        513TA-T77-013\n" +
            "    </div>\n" +
            "    <div id=\"ydhtmDiv\" style=\"position: absolute;top: 55mm;left: 1mm;width: 70mm;height: 15mm;\">\n" +
            "        <img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOoAAAA4AQAAAADbGVnHAAAACXBIWXMAAE69AABOvQFzamgUAAAAEnRFWHRTb2Z0d2FyZQBCYXJjb2RlNEryjnYuAAAAOElEQVR42mOQnn9E+vnxmwePHWRsPOdh+LH5bR9/vnyzfHq7xQ81BoZR6VHpUelR6VHpUenBLA0AFgsz1nDCSaIAAAAASUVORK5CYII=\"/>\n" +
            "    </div>\n" +
            "    <div id=\"ydhtmwzDiv\" style=\"position: absolute;top: 70mm;left: 20mm;width: 40mm;height: 8mm;font-size: 9pt;font-family: 黑体;\">\n" +
            "        <div style=\"line-height: 4mm;\">运单 TEST1884552126</div>\n" +
            "    </div>\n" +
            "    <div id=\"rgysmDiv\" style=\"position: absolute;top: 70mm;left: 3mm;width: 15mm;height: 8mm;line-height: 8mm;font-size: 27pt;font-family: 黑体;overflow: hidden;\">\n" +
            "        \n" +
            "    </div>\n" +
            "    <div id=\"jitxLabelDiv\" style=\"position: absolute;top: 36mm;left: 61mm;width: 8mm;height: 4mm;line-height: 4mm;font-size: 9pt;overflow: hidden;font-family: 黑体;font-weight: bold;\">\n" +
            "        JITX\n" +
            "    </div>\n" +
            "    <div id=\"qrCodeDiv\" style=\"position: absolute;top: 36mm;left: 70mm;width: 28mm;height: 28mm;\">\n" +
            "        <img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHAAAABwCAIAAABJgmMcAAAClElEQVR42u3dXW4DIQxG0ex/09MFRGrB3zUx6eUxmjbMobLwj9TXK1vP21p55v3hrU9++a6Vh1c+KWu8BBX0EtBnZ61sJVSrHefWfrYOb1FDUEGvA115gTBU1VYYTMOYvvibBRX0n4CGAY5CD0OwoIIKml8vtl4A/3H8D0VQQb8MNMyUwjgblmbwpO7zqaeggvaAhvXQL/vk8wVmQQXtAX36F5UX9YVp8mUFFXQ2aBgx8UYF1SsNt1puxwoq6HBQMElAGh5UvSMsc5RjsaCCDgcNI12tWBlegKgxrI6bmaCCDgelwiL+bh0tyQP5nqCC3gtKxbUD5RL8yMtJlKCCDgfti3Qnyxzh5ATYxBVU0FtA8U5DXxQLzxXPyv5IPQUVdBLogdtSX800rNrgSZSggl4EemBWgApV1JQSvlVBBZ0PWouY+UBAUmntK47kGxNU0HtB+6oSlCOVjIEFX0EFvRe0r/54ssOKZ3erLRBBBb0WFM9n+vKi2nhEzZq8hwoq6ClQqs8R3nLwK1EtKwsnqwQV9EZQfEAhHDXo63pSMXS76ymooJ8DDQMc3nvoOwb821czJUEFnQ3aV3EAQxVSf+nojAoq6HWgHfkD0lyhijVUVXf72iSooCNBwwZD33xD7fqFnxBQYBZU0JGgeDDFacJZCiqJ2p4cEVTQkaDUVQacHGIf7sgJBRV0OOgDrb4yB3VpCxOk1XqooILOBu2LLOEwxIHGCd7jEVTQi0CpFmBfHoL3XMMazWqmJKigs0FrW9naLt6xOFn0FFRQQZ/O/wEWngc+HkFOjggq6P2gYcWBOk6qJrIb0wUV9DrQA9MDVKYUdj2pTkxL6imooM2gVD00bJyE1x1q9KG8Z0EFHQ76uND1A8r/5w17MeJxAAAAAElFTkSuQmCC\" width=\"100%\" height=\"100%\"/>\n" +
            "    </div>\n" +
            "    <div id=\"proCodeDiv\" style=\"position: absolute;top: 64mm;left: 75mm;width: 20mm;height: 20mm;\">\n" +
            "        <img src=\"data:image/png;base64,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\"/>\n" +
            "    </div>\n" +
            "    <div id=\"ybzPicDiv\" style=\"position: absolute;top: 72mm;left: 60mm;width: 7mm;height: 7mm;line-height: 7mm;font-size: 10.5pt;font-weight: bold;text-align: center;display: none;\">\n" +
            "        <img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAACGElEQVRIS73WS6hNcRTH8c/1yGNg4pW8JkwoBsiIMqAMUJRQysQjpSgxRDeU58DEQFEm5JEYeUwUeSQmGKCQMpAYYeLVuv3/t223z2nfc/fxn5z67/3/f89ae63f+vX4z6tngLwhmIgp+ImP+IQ/de+pAxyO1ViD6XiH94j9AMfea1zCDfxqB28HjGi2Yieu4BxetbhsLjZhGY6mdytfbQUckyARyR58qZmySTiJkdiA7+VzVcDReITzOFITVH7tMJZiEX4UH1YBL+I5ejuE5WP7MB+r8DtvloFrsRErBgnLx8/gPs5WAaPqnmIJPjcEHIu7mJOjLEa4GRNwsCFYvibq4DEux0YReAtbUp81yVyIXVhXBI7CE8xukpTuiqBCLEIg+iOciWOporrAdA8ro59zSqNf1mN7N2iIVjuAlxm4OCnDti4BQ2f340UGzsDxLqc0evtrBob2RQ/O6kKElUUTnJuIlL5tGLogtUWI+T99GI0/DiG8Ta4YVw9wtQyMXnyGefjWEHEqriUR73MFZfHejfHY2wBwKO4kqYzfvlUGhoDfxgWcHiT0BMI1hGPoX1XzMKb9wzRSIv+drENp6oSghNlqC4yH2WKEBkZ661qMmDanMGIgFiP/m0hHSN2OmiYqBvfyTk1UMQvxXcMihlUMW/gh2cRhiCqchjfJJl4v2omqb1HHlxbPZSM8OfnPrhjhToqm5Zm//v5hHb53H40AAAAASUVORK5CYII=\"/>\n" +
            "    </div>\n" +
            "    <div id=\"ybzDiv\" style=\"position: absolute;top: 72mm;left: 60.5mm;width: 7mm;height: 7mm;line-height: 7mm;font-size: 10.5pt;font-weight: bold;text-align: center;display: none;\">\n" +
            "        预\n" +
            "    </div>\n" +
            "    <div id=\"hhPicDiv\" style=\"position: absolute;top: 72mm;left: 68mm;width: 7mm;height: 7mm;line-height: 7mm;font-size: 10.5pt;font-weight: bold;text-align: center;display: none;\">\n" +
            "        <img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAACGElEQVRIS73WS6hNcRTH8c/1yGNg4pW8JkwoBsiIMqAMUJRQysQjpSgxRDeU58DEQFEm5JEYeUwUeSQmGKCQMpAYYeLVuv3/t223z2nfc/fxn5z67/3/f89ae63f+vX4z6tngLwhmIgp+ImP+IQ/de+pAxyO1ViD6XiH94j9AMfea1zCDfxqB28HjGi2Yieu4BxetbhsLjZhGY6mdytfbQUckyARyR58qZmySTiJkdiA7+VzVcDReITzOFITVH7tMJZiEX4UH1YBL+I5ejuE5WP7MB+r8DtvloFrsRErBgnLx8/gPs5WAaPqnmIJPjcEHIu7mJOjLEa4GRNwsCFYvibq4DEux0YReAtbUp81yVyIXVhXBI7CE8xukpTuiqBCLEIg+iOciWOporrAdA8ro59zSqNf1mN7N2iIVjuAlxm4OCnDti4BQ2f340UGzsDxLqc0evtrBob2RQ/O6kKElUUTnJuIlL5tGLogtUWI+T99GI0/DiG8Ta4YVw9wtQyMXnyGefjWEHEqriUR73MFZfHejfHY2wBwKO4kqYzfvlUGhoDfxgWcHiT0BMI1hGPoX1XzMKb9wzRSIv+drENp6oSghNlqC4yH2WKEBkZ661qMmDanMGIgFiP/m0hHSN2OmiYqBvfyTk1UMQvxXcMihlUMW/gh2cRhiCqchjfJJl4v2omqb1HHlxbPZSM8OfnPrhjhToqm5Zm//v5hHb53H40AAAAASUVORK5CYII=\"/>\n" +
            "    </div>\n" +
            "    <div id=\"hhDiv\" style=\"position: absolute;top: 72mm;left: 68.5mm;width: 7mm;height: 7mm;line-height: 7mm;font-size: 10.5pt;font-weight: bold;text-align: center;display: none;\">\n" +
            "        换\n" +
            "    </div>\n" +
            "    <div id=\"goodsDiv\" style=\"position: absolute;top: 80mm;left: 0mm;width: 66mm;height: 20mm;font-size: 7.5pt;padding-left: 2mm;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;border-right: 1px solid #000000;\">\n" +
            "        <div>88888160-BLK12*黑色*12*1</div>\n" +
            "    </div>\n" +
            "    <div id=\"dyDiv\" style=\"position: absolute;top: 80mm;left: 66mm;width: 6mm;height: 6mm;line-height: 3mm;text-align: center;font-size: 7pt;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;\">\n" +
            "        打印时间\n" +
            "    </div>\n" +
            "    <div id=\"dyrqDiv\" style=\"position: absolute;top: 80mm;left: 72mm;width: 15mm;height: 3mm;line-height: 3mm;text-align: center;font-size: 7pt;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;\">\n" +
            "        2020-08-07\n" +
            "    </div>\n" +
            "    <div id=\"dysjDiv\" style=\"position: absolute;top: 83mm;left: 72mm;width: 15mm;height: 3mm;line-height: 3mm;text-align: center;font-size: 7pt;font-weight: bold;overflow: hidden;font-family: 黑体;\">\n" +
            "        20:17:31\n" +
            "    </div>\n" +
            "    <div id=\"yajDiv\" style=\"position: absolute;top: 80mm;left: 87mm;width: 13mm;height: 3mm;line-height: 3mm;padding-left: 1mm;font-size: 7pt;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;border-left: 1px solid #000000;\">\n" +
            "        已安检\n" +
            "    </div>\n" +
            "    <div id=\"yysDiv\" style=\"position: absolute;top: 83mm;left: 87mm;width: 13mm;height: 3mm;line-height: 3mm;padding-left: 1mm;font-size: 7pt;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-left: 1px solid #000000;\">\n" +
            "        已验视\n" +
            "    </div>\n" +
            "    <div id=\"qsDiv\" style=\"position: absolute;top: 86mm;left: 66mm;width: 6mm;height: 6mm;line-height: 6mm;text-align: center;font-size: 7pt;overflow: hidden;font-family: 黑体;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;border-bottom: 1px solid #000000;\">\n" +
            "        签收\n" +
            "    </div>\n" +
            "    <div id=\"qskkDiv\" style=\"position: absolute;top: 86mm;left: 72mm;width: 28mm;height: 6mm;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;border-top: 1px solid #000000;border-bottom: 1px solid #000000;\">\n" +
            "\n" +
            "    </div>\n" +
            "    <div id=\"dhDiv\" style=\"position: absolute;top: 92mm;left: 66mm;width: 6mm;height: 4mm;line-height: 4mm;text-align: center;font-size: 7pt;overflow: hidden;font-family: 黑体;\">\n" +
            "        单号\n" +
            "    </div>\n" +
            "    <div id=\"dhzDiv\" style=\"position: absolute;top: 92mm;left: 72mm;width: 26mm;height: 4mm;line-height: 4mm;font-size: 7.5pt;overflow: hidden;font-family: 黑体;\">\n" +
            "        20220710833314\n" +
            "    </div>\n" +
            "    <div id=\"cywlxDiv\" style=\"position: absolute;top: 96mm;left: 66mm;width: 32mm;height: 4mm;line-height: 4mm;font-size: 9pt;overflow: hidden;font-weight: bold;font-family: 黑体;\">\n" +
            "        \n" +
            "    </div>\n" +
            "</div>\n";


    @PostMapping("/testPdf6")
    public void testPdf6(HttpServletResponse response) throws Exception {
        PDF7 pdf7 = new PDF7();
        pdf7.insertObject(response);
    }

}
