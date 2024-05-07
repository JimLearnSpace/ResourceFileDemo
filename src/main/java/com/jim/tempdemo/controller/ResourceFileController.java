package com.jim.tempdemo.controller;

import com.jim.tempdemo.service.ReadResourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jim
 * @Description
 * @createTime 2024年05月06日
 */
@RestController
@RequestMapping("/resources")
public class ResourceFileController {

    @Autowired
    private ReadResourceFile rrFile;

    @GetMapping(value = "/1",produces = "image/jpeg")
    public void test1(HttpServletResponse response) throws IOException {
        rrFile.method1(response);
    }

    @GetMapping(value = "/2",produces = "image/jpeg")
    public void test2(HttpServletResponse response) throws IOException {
        rrFile.method2(response);
    }

    @GetMapping(value = "/3",produces = "image/jpeg")
    public void test3(HttpServletResponse response) {
        rrFile.method3(response);
    }

    @GetMapping(value = "/4",produces = "image/jpeg")
    public void test4(HttpServletResponse response) throws IOException {
        rrFile.method4(response);
    }

    @GetMapping(value = "/5",produces = "image/jpeg")
    public void test5(HttpServletResponse response) throws IOException {
        rrFile.method5(response);
    }

    @GetMapping(value = "/6",produces = "image/jpeg")
    public void test6(HttpServletResponse response) throws IOException {
        rrFile.method6(response);
    }

    @GetMapping(value = "/7",produces = "image/jpeg")
    public void test7(HttpServletResponse response) throws IOException {
        rrFile.method7(response);
    }


}
