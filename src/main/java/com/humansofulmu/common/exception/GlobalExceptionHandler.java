package com.humansofulmu.common.exception;

import com.humansofulmu.PostController;
import com.humansofulmu.common.resonse.ErrorCode;
import com.humansofulmu.common.resonse.ErrorResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

//TODO: RestControllerAdvice 재설정
@Slf4j
//@RestControllerAdvice(annotations = {RestController.class}, basePackageClasses = {PostController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResultVO> handleNoSuchElementException(
            NoSuchElementException ex) {
        log.error("HttpMessageNotReadableException", ex);
        final ErrorResultVO response = ErrorResultVO.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
