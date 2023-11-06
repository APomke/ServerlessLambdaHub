package com.ServerlessLambdaHub.pojo;


import com.sun.tracing.dtrace.NameAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
  private long id;
  private String username;
  private String email;
  private String password;
  private String fullName;
  private String role;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;


}
