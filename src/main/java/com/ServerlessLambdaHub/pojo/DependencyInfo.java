package com.ServerlessLambdaHub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependencyInfo {
    private String s3Link;
    private String localAddress;
}
