package com.ServerlessLambdaHub.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceLayers {
  private long id;
  private String name;
  private String architecture;
  private String runtimeCompatibility;
  private String remarks;
  private String s3Link;
  private String localAddress;
  private String version;
  private long creatorId;
  private long updaterId;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
}
