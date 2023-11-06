package com.ServerlessLambdaHub.service.impl;

import com.ServerlessLambdaHub.mapper.ResourceLayersMapper;
import com.ServerlessLambdaHub.pojo.ResourceLayers;
import com.ServerlessLambdaHub.service.ResourceLayersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ResourceLayersServiceImpl extends ServiceImpl<ResourceLayersMapper, ResourceLayers> implements ResourceLayersService {
}
