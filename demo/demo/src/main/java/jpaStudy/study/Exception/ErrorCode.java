package jpaStudy.study.Exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  NOT_FOUND(400,HttpStatus.NOT_FOUND,"NOT FOUND ENTITY"),
  INTER_SERVER_ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR,"INTER SEVER ERROR");

  private Integer code;

  private HttpStatus httpStatus;

  private String message;


}
