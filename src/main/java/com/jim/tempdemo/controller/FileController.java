package com.jim.tempdemo.controller;

import com.jim.tempdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jim
 * @Description 图片
 * @createTime 2024年05月06日
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/img",produces = "image/jpeg")
    public void doloadAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileService.download(request,response);
    }

    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        fileService.getCaptcha(request,response);
    }

}
