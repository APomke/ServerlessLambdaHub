package com.ServerlessLambdaHub.controller;

import com.ServerlessLambdaHub.pojo.DependencyInfo;
import com.ServerlessLambdaHub.pojo.DependencyRequest;
import com.ServerlessLambdaHub.pojo.ResourceLayers;
import com.ServerlessLambdaHub.service.ResourceLayersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {
    @Autowired
    private ResourceLayersService resourceLayersService;

    @PostMapping("/getUrl")
    public DependencyInfo getUr(@RequestBody DependencyRequest request) {
        // 从request中获取依赖信息
        String dependencyName = request.getDependencyName();
        String architecture = request.getArchitecture();
        String runtime = request.getRuntime();

        //获取url
        LambdaQueryWrapper<ResourceLayers> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ResourceLayers::getName,dependencyName);
        lambdaQueryWrapper.eq(ResourceLayers::getArchitecture,architecture);
        lambdaQueryWrapper.eq(ResourceLayers::getRuntimeCompatibility,runtime);
        ResourceLayers layers = resourceLayersService.getOne(lambdaQueryWrapper);
        String s3Link = layers.getS3Link();
        String localAddress = layers.getLocalAddress();

        return new DependencyInfo(s3Link, localAddress);
    }
}
