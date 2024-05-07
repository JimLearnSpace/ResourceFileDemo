package com.jim.tempdemo.service.impl;

import com.jim.tempdemo.service.ReadResourceFile;
import com.jim.tempdemo.utils.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

/**
 * @author Jim
 * @Description 测试读取Resource下的文件
 * @createTime 2024年05月06日
 */
@Service
@Slf4j
public class ReadResourceFileImpl implements ReadResourceFile {


    /**
     * 1. 运行时可访问/打包后可访问
     * 可以访问resources文件夹下的文件（若要访问子文件，则文件夹\\开头即可）
     * 注意：要访问rosources/a/xxx.png的话:
     * 1. 要写成a/xxx.png（开发/打包后均可使用）
     * 2. 写成a\\xxx.png的话，仅开发中可用，打包后不可用（且仅限Windows）
     * @param response
     */
    @Override
    public void method1(HttpServletResponse response) throws IOException {
        /* -- 访问resources下的文件 -- */
        // InputStream in = this.getClass().getClassLoader().getResourceAsStream("a.png");
        /* -- 访问resources/static下的文件（Windows开发中）-- */
        // InputStream in = this.getClass().getClassLoader().getResourceAsStream("static\\a.png");
        /* -- 访问resources/static下的文件（开发中/打包后） -- */
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("static/b.jpg");

        HttpServletUtils.View(response);
        log.info("访问method4/InputStream");
        FileCopyUtils.copy(in, response.getOutputStream());
    }



    /**
     * 2. 开发中/打包后可访问
     * 与1一样
     * @param response
     */
    @Override
    public void method2(HttpServletResponse response) throws IOException {
        // 情况1 | 访问resources下的文件
        // InputStream in = this.getClass().getResourceAsStream("/a.png");
        // 情况2 | 访问rources/static下的文件
        // InputStream in = this.getClass().getResourceAsStream("/static/a.png");
        InputStream in = this.getClass().getResourceAsStream("\\static\\a.png");

        HttpServletUtils.View(response);
        log.info("访问method2/InputStream");
        FileCopyUtils.copy(in, response.getOutputStream());
    }


    /**
     * 开发中/打包后都可
     * 与1/2一样
     * 需要抛异常
     * @param response
     */
    @Override
    public void method3(HttpServletResponse response) {
        HttpServletUtils.View(response);

        try{
            /* 情况1 | 获取resources下的文件*/
            // ClassPathResource classPathResource = new ClassPathResource("a.png");
            /* 情况2 | 获取rosources/static下的文件 */
            // InputStream in = new ClassPathResource("static/a.png").getInputStream();
            InputStream in = new ClassPathResource("static\\a.png").getInputStream();
            FileCopyUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 4. 通过绝对路径读取
     * 只能在项目中使用，打包后失效（因为路径不对）
     * 测试了下能不能直接读取压缩包的内容，测试失败，无法读取，只能单独创建外部路径
     * @param response
     */
    @Override
    public void method4(HttpServletResponse response) throws IOException {

        /**/
        String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
        /* 情况1 | 获取resources下的文件*/
        // path += "a.png";
        /* 情况2 | 获取resources/static下的文件*/
        path += "static/a.png";
        // 获取文件流
        File file = new File(path);
        InputStream in = Files.newInputStream(file.toPath());

        HttpServletUtils.View(response);
        FileCopyUtils.copy(in, response.getOutputStream());

    }


    /**
     * 5. 绝对路径
     * 只在开发中有效，打包后无效
     */
    @Override
    public void method5(HttpServletResponse response) throws IOException {
        /* 情况1 | 访问resources下的文件 */
        // String filePath = this.getClass().getClassLoader().getResource("a.png").getPath();
        /* 情况1 | 访问resources子文件夹下的文件 */
        String filePath = this.getClass().getClassLoader().getResource("static/a.png").getPath();

        HttpServletUtils.View(response);
        File file = new File(filePath);
        FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
    }


    /**
     * 6. 运行时可访问，打包后不可访问
     * @param response
     */
    @Override
    public void method6(HttpServletResponse response) throws IOException {
        String filePath = this.getClass().getClassLoader().getResource("a.png").getFile();
        log.info("当前访问路径:{}",filePath);

        // 通过response控制当前返回状态为：预览
        HttpServletUtils.View(response);

        // 获取文件流并返回给前端
        File file = new File(filePath);
        FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
    }







    /**
     * 开发中
     * @param response
     */
    @Override
    public void method7(HttpServletResponse response) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/resources/a.png";
        log.info("路径为:{}",path);
        HttpServletUtils.View(response);
        log.info("访问method7/InputStream");
        File file = new File(path);
        FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
    }

    @Override
    public void method8(HttpServletResponse response) {

    }


}
