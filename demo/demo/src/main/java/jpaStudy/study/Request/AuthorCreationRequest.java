package jpaStudy.study.Request;

import lombok.Data;

@Data
public class AuthorCreationRequest {
  private String firstName;
  private String lastName;
}
