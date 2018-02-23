package com.mitbbs.sdk.utils;

/**
 * data:2017/12/27.
 *
 * @author:jc
 * 资源工具类-加载资源文件
 */
public class ResourcesUtils {
    /**
     * 获取strings.xml资源文件字符串
     *
     * @param id 资源文件id
     * @return 资源文件对应字符串
     */
    public static String getString(int id){
        return AppUtils.getContext().getResources().getString(id);
    }

    /**
     * 获取strings.xml资源文件字符串数组
     *
     * @param id 资源文件id
     * @return 资源文件对应字符串数组
     */
    public static String[] getStringArray(int id){
        return AppUtils.getContext().getResources().getStringArray(id);
    }
}
