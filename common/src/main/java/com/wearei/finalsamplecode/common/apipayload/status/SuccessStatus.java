package com.wearei.finalsamplecode.common.apipayload.status;

import com.wearei.finalsamplecode.common.BaseCode;
import com.wearei.finalsamplecode.common.apipayload.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK(HttpStatus.OK, "200", "Ok"),
    _DELETION_SUCCESS(HttpStatus.OK, "200", "삭제가 완료되었습니다."),
    _LIKE_SUCCESS(HttpStatus.OK, "200","좋아요처리가완료되었습니다.");

    private HttpStatus httpStatus;
    private String statusCode;
    private String message;

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .statusCode(statusCode)
                .message(message)
                .httpStatus(httpStatus)
                .success(true)
                .build();
    }
}
