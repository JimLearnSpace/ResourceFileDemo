package com.jim.tempdemo.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jim
 * @Description HttpServlet工具
 * @createTime 2024年05月06日
 */
public class HttpServletUtils {

    public static void View(HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
    }
}
