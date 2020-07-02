package com.syh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 文件上传和下载
 * @author: 沈煜辉
 * @create: 2019-12-06 10:58
 **/
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/image.do")
    public void fileDownload(String filePath, HttpServletResponse response){
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }
        byte[] date = new byte[1024*8];
        try (
                InputStream is = new FileInputStream(file);
                OutputStream os = response.getOutputStream();
        ){
//            设置下载文件的名称
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;");
            int len=0;
            while ((len=is.read(date))!=-1){
                os.write(date,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //图片上传测试
    @ResponseBody
    @RequestMapping("upload.do")
    public Map upload(MultipartFile file,HttpServletRequest request){

        String prefix="";
        //保存上传
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf("."));
                File dir = new File("F:"+File.separator+"javaProject"+File.separator+"Paper"+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"img"+File.separator+"product"+File.separator+"small"+File.separator);
                if (!dir.mkdir()){
                    dir.mkdirs();
                }
                String fileContent = UUID.randomUUID().toString().replaceAll("-","");
                String fileName = fileContent+prefix;
                File files=new File(dir,fileName);
                String rimage = "img/product/small/"+fileName;
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src",rimage);
                return map;
            }
        }catch (Exception e){
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }


    /*@PostMapping("/upload.do")
    @ResponseBody
    public Map upload(@RequestParam("file") MultipartFile file){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf("."));
                File dir = new File("H:"+File.separator+"javaProject"+File.separator+"Paper"+File.separator+"target"+File.separator+"paper"+File.separator+"img"+File.separator+"product"+File.separator+"small"+File.separator);
                if (!dir.mkdir()){
                    dir.mkdirs();
                }
                String fileContent = UUID.randomUUID().toString().replaceAll("-","");
                String fileName = fileContent+prefix;
                File files=new File(dir,fileName);
                String rimage = "img/product/small/"+fileName;
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map.put("src",rimage);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }*/
}
