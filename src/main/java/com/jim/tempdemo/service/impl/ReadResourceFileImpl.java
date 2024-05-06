package com.jim.tempdemo.service.impl;

import com.jim.tempdemo.service.ReadResourceFile;
import com.jim.tempdemo.utils.HttpServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
     * 1. 通过绝对路径读取
     * 只能在项目中使用，打包后失效（因为路径不对）
     * 测试了下能不能直接读取压缩包的内容，测试失败，无法读取，只能单独创建外部路径
     * @param response
     */
    @Override
    public void method1(HttpServletResponse response) {

        String fileName = "\\static\\a.png";
        String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
        String filePath = path + fileName;
        log.info("当前访问路径:{},文件名称为:{}",path,fileName);

        // 通过response控制当前返回状态为：预览
        HttpServletUtils.View(response);

        try{
            File file = new File(filePath);
            FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 2. 绝对路径
     * 只在开发中有效，打包后无效
     */
    @Override
    public void method2(HttpServletResponse response) {
        String filePath = this.getClass().getClassLoader().getResource("a.png").getPath();
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        String filePath = "D:\\DevCodes\\Gitlab\\TempDemo\\target\\TempDemo-0.0.1-SNAPSHOT!\\BOOT-INF\\classes!\\static\\a.png";
        log.info("当前访问路径:{}",filePath);

        // 通过response控制当前返回状态为：预览
        HttpServletUtils.View(response);

        try{
            File file = new File(filePath);
            FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void method3(HttpServletResponse response) {
        String filePath = this.getClass().getClassLoader().getResource("a.png").getFile();
//        String path = this.getClass().getClassLoader().getResource("").getPath();
//        String filePath = "D:\\DevCodes\\Gitlab\\TempDemo\\target\\TempDemo-0.0.1-SNAPSHOT!\\BOOT-INF\\classes!\\static\\a.png";
        log.info("当前访问路径:{}",filePath);

        // 通过response控制当前返回状态为：预览
        HttpServletUtils.View(response);

        try{
            File file = new File(filePath);
            FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
