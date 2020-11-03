package com.tawe.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @ClassName ExceptionUtil
 * @Description 异常信息打印工具
 * @Author davidt
 * @Date 11/3/2020 5:02 PM
 * @Version 1.0
 **/
public class ExceptionUtil {
    public static String getMessage(Exception e) {
        try(
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw)) {

            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();

        } catch (IOException ioException) {
            ioException.printStackTrace();
            return ioException.getMessage();
        }
    }
    private ExceptionUtil() {}
}
