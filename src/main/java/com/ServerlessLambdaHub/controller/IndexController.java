package com.ServerlessLambdaHub.controller;

import com.ServerlessLambdaHub.pojo.ResourceLayers;
import com.ServerlessLambdaHub.service.ResourceLayersService;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private ResourceLayersService resourceLayersService;

    @RequestMapping("/")
    public String index(Model model){
        //获取目前支持的层
        List<ResourceLayers> resourceLayersList = resourceLayersService.list();
        //取出所有运行时
        List<String> runtimeList = new ArrayList<>();
        //取出所有依赖
        List<String> dependencyList = new ArrayList<>();
        //取出所有架构
        List<String> architectureList = new ArrayList<>();

        for (ResourceLayers resourceLayers:resourceLayersList){
            if (!runtimeList.contains(resourceLayers.getRuntimeCompatibility())){
                runtimeList.add(resourceLayers.getRuntimeCompatibility());
            }
            if (!dependencyList.contains(resourceLayers.getName())){
                dependencyList.add(resourceLayers.getName());
            }
            if (!architectureList.contains(resourceLayers.getArchitecture())){
                architectureList.add(resourceLayers.getArchitecture());
            }
        }
        model.addAttribute("runtimeList",runtimeList);
        model.addAttribute("dependencyList",dependencyList);
        model.addAttribute("architectureList",architectureList);


        return "index";
    }
}
