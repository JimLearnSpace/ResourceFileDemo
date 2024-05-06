package com.jim.tempdemo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jim
 * @Description
 * @createTime 2024年05月06日
 */
public interface FileService {

    void download(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void getCaptcha(HttpServletRequest request, HttpServletResponse response);
}
