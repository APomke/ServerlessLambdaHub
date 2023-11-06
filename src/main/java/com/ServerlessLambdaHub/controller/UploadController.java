package com.ServerlessLambdaHub.controller;
import com.ServerlessLambdaHub.pojo.ResourceLayers;
import com.ServerlessLambdaHub.service.ResourceLayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private ResourceLayersService resourceLayersService;

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/uploadLayerFile")
    public String handleFileUpload(@RequestParam("dependencyName") String dependencyName,
                                   @RequestParam("architecture") String architecture,
                                   @RequestParam("runtime") String runtime,
                                   @RequestParam("layerArchive") MultipartFile file, Model model) {
        String uploadPath = "D:\\JavaProject\\ServerlessLambdaHub\\src\\main\\resources\\dependency";


        // 处理文件上传逻辑
        if (!file.isEmpty()) {
            try {

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 将上传的文件保存到 uploadDir 中
                File newFile = new File(uploadDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(newFile);

                //上传到s3
                S3Client s3Client = S3Client.builder()
                        .region(Region.US_EAST_1) // 根据你的S3桶所在的地区选择合适的Region
                        .credentialsProvider(DefaultCredentialsProvider.create())
                        .build();
                String bucketName = "dependency-bucket-1";
                String objectKey = architecture + "/" + runtime + "/" + file.getOriginalFilename();
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                PutObjectResponse response = s3Client.putObject(putObjectRequest, Paths.get(newFile.toURI()));

                System.out.println("File uploaded successfully. ETag: " + response.eTag());
                //保存到数据库
                // 创建一个 SimpleDateFormat 对象，指定日期格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // 获取当前时间
                Date currentDate = new Date();

                // 使用 SimpleDateFormat 格式化日期
                String formattedDate = sdf.format(currentDate);
                String s3link = "https://" + bucketName + ".s3.amazonaws.com" + "/" + architecture + "/" + runtime + "/" + file.getOriginalFilename();
                resourceLayersService.save(new ResourceLayers(dependencyName,architecture,runtime,null,s3link,s3link,"1",1,1,formattedDate,formattedDate));

                model.addAttribute("msg","依赖上传成功");
                return "upload";
            } catch (IOException e) {
                model.addAttribute("msg","依赖上传失败-系统错误");
                e.printStackTrace();
                return "upload";
            }
        } else {
            model.addAttribute("msg","依赖上传失败-文件为空");
            return "upload";
        }
    }
}
