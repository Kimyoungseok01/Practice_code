package jpaStudy.study.Entity;

import java.time.LocalDateTime;
import java.util.List;
import jpaStudy.study.Exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJsonObject<T> {

  private Integer code;

  private HttpStatus httpStatus;

  private String message;

  private T data;
  //private final LocalDateTime timestamp = LocalDateTime.now();
/*  public ResponseJsonObject(ErrorCode errorCode){
      setCode(errorCode.getCode());
      setHttpStatus(errorCode.getHttpStatus());
      setMessage(errorCode.getMessage());
  }*/

  public static ResponseEntity<ResponseJsonObject> toErrorResponse(ErrorCode errorCode){
    return ResponseEntity
        .status(errorCode.getHttpStatus())
        .body(ResponseJsonObject.builder()
            .code(errorCode.getCode())
            .httpStatus(errorCode.getHttpStatus())
            .message(errorCode.getMessage())
            .build());
  };
}
