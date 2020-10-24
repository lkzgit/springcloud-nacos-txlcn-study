package com.lcn.order.utils;

import javax.servlet.http.HttpServletRequest;

public class ServerUtil {

    //从请求中获取状态码
    public static int getStatusCode(HttpServletRequest request)
    {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null)
        {
            return (int) statusCode;
        }
        else
        {
            return 500;
        }
    }

    //获取指定截取的字符串
    public static String cutString(String s, String splitKey, int index)
    {
        String[] array = s.split(splitKey);
        if (array.length > index)
        {
            return array[index];
        }

        return "";
    }
}
