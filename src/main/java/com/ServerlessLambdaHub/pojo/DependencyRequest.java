package com.ServerlessLambdaHub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DependencyRequest {
    private String dependencyName;
    private String architecture;
    private String runtime;
}
