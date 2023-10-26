package apex.legend.laoqu.controller;

import apex.legend.laoqu.common.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/*
* 文件上传和加载
* */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${laoqu.path}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

       String originalFilename = file.getOriginalFilename();
        String suf = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用uuid作为文件名称防止重复造成覆盖
        String filename = UUID.randomUUID().toString()+suf;
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath+filename));//将临时文件转存到指定位置
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(filename);
    }
    @GetMapping("/download")
    public void download(String name , HttpServletResponse response){
        //输入流，通过输入流读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jepg");
            byte[] bytes = new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(bytes)) !=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
