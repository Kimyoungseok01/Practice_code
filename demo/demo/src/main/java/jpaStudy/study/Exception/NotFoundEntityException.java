package jpaStudy.study.Exception;

import lombok.Getter;

@Getter
public class NotFoundEntityException extends RuntimeException{

  private ErrorCode errorCode;

  public NotFoundEntityException(String message,ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
