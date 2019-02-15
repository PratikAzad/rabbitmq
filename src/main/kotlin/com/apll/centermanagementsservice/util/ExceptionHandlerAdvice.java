package com.apll.centermanagementsservice.util;

import com.apll.centermanagementsservice.config.ResponseWithError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
@Slf4j
public class ExceptionHandlerAdvice {

  @ExceptionHandler(ApllException.class)
  public ResponseWithError handleException(ApllException e, WebRequest request){
    log.error("error{}",e );
    return ResponseWithError.ofError(e.getErrorMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseWithError handleException(Exception e, WebRequest request){
    if(e instanceof MethodArgumentNotValidException) {
      System.out.println("");
    }
    log.error("error{}",e );
    return ResponseWithError.ofError("Something went Wrong");
  }



 /*@ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseWithError handleException(MethodArgumentNotValidException e, WebRequest request){
      FieldError objectError = e.getBindingResult().getFieldError();
      objectError.getDefaultMessage();
      return ResponseWithError.ofError(objectError.getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }*/

  /*@ExceptionHandler(-.class)
  public ResponseWithError handleException(HttpMessageNotReadableException e, WebRequest request){

      String message=e.getMostSpecificCause().getMessage()
      log.error("error{}",e );
      return ResponseWithError.ofError(e.getMessage());
  }
*/

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseWithError<List<Object>> handleException(MethodArgumentNotValidException e, WebRequest request){
   // FieldError objectError = e.getBindingResult().getFieldError();
    List<String> errors = new ArrayList<String>();
    for (FieldError error : e.getBindingResult().getFieldErrors()) {
     errors.add(error.getField() + " : " + error.getDefaultMessage());
    }

  return  ResponseWithError.ofError(errors.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
  }





  /*MessagingException*/
  @ExceptionHandler(MessagingException.class)
  public ResponseWithError handleException(MessagingException e, WebRequest request){
    log.error("error{}",e );
    return ResponseWithError.ofError("Mail not Sent.");
  }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex, WebRequest requset) {

        ExceptionMessage exceptionMessageObj = new ExceptionMessage();

        // Handle All Field Validation Errors
        if(ex instanceof MethodArgumentNotValidException) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
                sb.append(";");
            }
            exceptionMessageObj.setMessage(sb.toString());
        }else{
            exceptionMessageObj.setMessage(ex.getLocalizedMessage());
        }

        exceptionMessageObj.setError(ex.getClass().getCanonicalName());
        exceptionMessageObj.setPath(((ServletWebRequest) requset).getRequest().getServletPath());

        // return exceptionMessageObj;
        return new ResponseEntity<>(exceptionMessageObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
