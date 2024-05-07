package com.jim.tempdemo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jim
 * @Description
 * @createTime 2024年05月06日
 */
public interface ReadResourceFile {

    void method1(HttpServletResponse response) throws IOException;

    void method2(HttpServletResponse response) throws IOException;

    void method3(HttpServletResponse response);

    void method4(HttpServletResponse response) throws IOException;

    void method5(HttpServletResponse response) throws IOException;

    void method6(HttpServletResponse response) throws IOException;

    void method7(HttpServletResponse response) throws IOException;

    void method8(HttpServletResponse response);
}
