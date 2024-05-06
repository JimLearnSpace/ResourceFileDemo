package com.jim.tempdemo.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jim.tempdemo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.io.*;
import java.net.URLEncoder;


/**
 * @author Jim
 * @Description
 * @createTime 2024年05月06日
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {


    /**
     * 1. 不同的 HttpServletResponse 设置可以控制是下载图片还是预览图片
     * 2. 下载的文件流或预览的文件流都可以直接在img标签中显示，只是说访问该端口的时候是下载还是预览
     */

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("D:\\Users\\Pictures\\Saved Pictures\\a.png");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        FileCopyUtils.copy(Files.newInputStream(file.toPath()), response.getOutputStream());
    }

    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收到验证码请");
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        //将验证码文本内容放入session
        request.getSession().setAttribute("captcha",text);
        //根据文本验证码内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
