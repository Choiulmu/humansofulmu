package com.humansofulmu.common.resonse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResultVO<T> {

    private int status;
    private String message;
    private T data;

    public ErrorResultVO(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.data = null;
    }

    public ErrorResultVO(ErrorCode errorCode, T data) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.data = data;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }


    public static ErrorResultVO of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResultVO(code, FieldError.of(bindingResult));
    }

    public static ErrorResultVO of(final ErrorCode code) {
        return new ErrorResultVO(code);
    }

    public static ErrorResultVO of(final ErrorCode code, final String reason) {
        return new ErrorResultVO(code, reason);
    }


    /**
     * 에러를 e.getBindingResult() 형태로 전달 받는 경우 해당 내용을 상세 내용으로 변경하는 기능을 수행한다.
     */
    @Getter
    public static class FieldError {
        private final String field;
        private final String value;
        private final String reason;

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .toList();
        }

        @Builder
        FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }
}
