package jpaStudy.study.Exception;

import jpaStudy.study.Entity.ResponseJsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundEntityException.class)
  public ResponseEntity<ResponseJsonObject> handleNotFoundEntityException(NotFoundEntityException exception){
    log.error("handleNotFoundEntityException",exception);
    //ErrorResponse response = new ErrorResponse(exception.getErrorCode());
//    ResponseJsonObject response = new ResponseJsonObject(exception.getErrorCode());
//    return new ResponseEntity<>(response,response.getHttpStatus());
    return ResponseJsonObject.toErrorResponse(exception.getErrorCode().NOT_FOUND);
  }

//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<ErrorResponse> handleException(Exception exception){
//    log.error("handleException", exception);
//    ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
//    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
}
