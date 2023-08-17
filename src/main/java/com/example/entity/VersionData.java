package com.example.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class VersionData {

    private String osVersion;

    private Long num;

    public static void main(String[] args) {
        List<VersionData> versionDataList = new ArrayList<>();
        versionDataList.add(new VersionData("1.2.1",20L));
        versionDataList.add(new VersionData("13.222.1",220L));
        versionDataList.add(new VersionData("2.1.2",210L));
        versionDataList.add(new VersionData("111.0.0",230L));
        versionDataList.sort(Comparator.comparing(item -> getOsVersionValue(item.osVersion)));
        System.out.println(JSON.toJSONString(versionDataList));
        Collections.reverse(versionDataList);
        System.out.println(JSON.toJSONString(versionDataList));
    }

    /**
     * 版本号转为一个虚拟的值，用于排序
     * 目前版本号支持最多三位，如1.2.3
     * 如后期增加位数，将算法中的j的初始值增大即可
     * 核心思想：以.为分隔符，分割出来的几块做10进制汇总处理
     * （1.2.3 = 1*10^2 + 2*10^1 + 3*10^0）
     * （10.3.0 = 10*10^2 + 3*10^1 + 0*10^0）
     *
     * @param osVersion
     * @return
     */
    private static Integer getOsVersionValue(String osVersion) {
        if (ObjectUtils.isEmpty(osVersion)) {
            return 0;
        }
        String[] arr = osVersion.split("\\.");
        Integer value = 0;
        for (int i = 0, j = 2; i < arr.length; i++, j--) {
            Double val = Math.pow(10, j);
            value += Integer.parseInt(arr[i]) * val.intValue();
        }
        return value;
    }


}
