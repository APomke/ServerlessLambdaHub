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
  private String createdAt;
  private String updatedAt;

  public ResourceLayers(String name, String architecture, String runtimeCompatibility, String remarks, String s3Link, String localAddress, String version, long creatorId, long updaterId, String createdAt, String updatedAt) {
    this.name = name;
    this.architecture = architecture;
    this.runtimeCompatibility = runtimeCompatibility;
    this.remarks = remarks;
    this.s3Link = s3Link;
    this.localAddress = localAddress;
    this.version = version;
    this.creatorId = creatorId;
    this.updaterId = updaterId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
