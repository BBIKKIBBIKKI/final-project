package com.wearei.finalsamplecode.common.apipayload.status;

import com.wearei.finalsamplecode.common.BaseCode;
import com.wearei.finalsamplecode.common.apipayload.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {
    // auth
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "404", "존재하지 않은 유저입니다"),
    _NOT_ADMIN_USER(HttpStatus.BAD_REQUEST, "400", "해당 유저는 관리자 권한이 존재하지 않습니다."),
    _NOT_OWNER_USER(HttpStatus.BAD_REQUEST, "400", "해당 유저는 가게 주인 권한이 존재하지 않습니다."),
    _EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 이메일입니다"),
    _PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "400", "비밀번호가 일치하지 않습니다."),
    _NOT_ALLOW_SAME_PASSWORD(HttpStatus.BAD_REQUEST, "400", "기존 비밀번호와 새로운 비밀번호는 같을 수 없습니다"),

    // deletion
    _DELETION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "500", "삭제에 실패하였습니다."),

    // search
    _INVALID_SEARCH_CRITERIA(HttpStatus.BAD_REQUEST,"400", "적절한 검색어가 아닙니다."),

    // AWS
    _FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "내부 서버 오류로 이미지를 업로드 할 수 없습니다."),
    _BAD_REQUEST_USER(HttpStatus.BAD_REQUEST,"404","이미 존재하는 유저입니다"),
    _BAD_REQUEST_EMAIL(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 이메일입니다"),
    _BAD_FOUND_EMAIL(HttpStatus.NOT_FOUND,"404","존재하지 않는 이메일입니다."),
    _BAD_REQUEST_PASSWORD(HttpStatus.BAD_REQUEST, "400", "비밀번호가 일치하지 않습니다."),

    // store
    _BAD_REQUEST_CREATE_STORE(HttpStatus.BAD_REQUEST, "400", "사장님 계정만 가게 생성이 가능합니다."),
    _BAD_REQUEST_STORE(HttpStatus.BAD_REQUEST, "400", "사장님만 접근 가능한 권한입니다."),
    _NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "404", "해당 가게는 존재하지 않습니다."),
    _STORE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 가게명 입니다."),

    // menu
    _NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "404", "존재하지 않는 메뉴입니다."),
    _INSUFFICIENT_INVENTORY(HttpStatus.BAD_REQUEST,"400","재고가 부족합니다."),

    // team
    _NOT_FOUND_TEAM(HttpStatus.NOT_FOUND, "404", "존재하지 않는 구단입니다."),

    // player
    _NOT_FOUND_PLAYER(HttpStatus.NOT_FOUND,"404","찾는 선수가 없습니다."),

    // ground
    _NOT_FOUND_GROUND(HttpStatus.NOT_FOUND, "404", "존재하지 않는 구장입니다."),

    // order
    _BAD_REQUEST_ORDER_AMOUNT(HttpStatus.BAD_REQUEST, "400", "최소 주문금액을 넘지 않습니다."),
    _BAD_REQUEST_ORDER_TIME(HttpStatus.BAD_REQUEST, "400", "가게 주문 시간이 아닙니다."),
    _INVALID_PAYMENT_METHOD(HttpStatus.BAD_REQUEST, "400", "유효하지 않은 결제 방법입니다."),
    _INVALID_ORDER_STATUS(HttpStatus.BAD_REQUEST, "400", "유효하지 않은 주문 상태입니다."),
    _BAD_REQUEST_UPDATE_STATUS(HttpStatus.BAD_REQUEST, "400", "사장님 계정만 주문 상태 변경이 가능합니다."),
    _NOT_FOUND_ORDER_LIST(HttpStatus.BAD_REQUEST, "404", "주문 목록이 존재하지 않습니다."),
    _NOT_FOUND_ORDER_MENU_LIST(HttpStatus.BAD_REQUEST, "404", "주문-메뉴 목록이 존재하지 않습니다."),
    _NOT_FOUND_POINT_HISTORY(HttpStatus.BAD_REQUEST, "404", "포인트 기록이 존재하지 않습니다."),
    _NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "404", "존재하지 않는 주문입니다."),

    // review
    _NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "404", "존재하지 않는 리뷰입니다."),
    _BAD_REQUEST_UPDATE_REVIEW(HttpStatus.BAD_REQUEST, "400", "본인의 리뷰만 수정할 수 있습니다."),

    // token
    _NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "404", "** JWT 토큰이 필요합니다.**"),
    _BAD_REQUEST_TOKEN(HttpStatus.BAD_REQUEST, "400", "잘못된 JWT 토큰입니다"),
    _INVALID_TOKEN(HttpStatus.BAD_REQUEST, "400", "유효하지 않은 토큰입니다"),
    _EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "400", "만료 토큰입니다"),
    _UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "400", "지원하지 않는 토큰입니다"),
    _EXCEPTION_ERROR_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰 검증 중 오류가 발생했습니다."),
    _INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, "400", "유효하지 않은 User Role"),

    // ad
    _FORBIDDEN(HttpStatus.FORBIDDEN, "403", "접근이 금지되었습니다. 접근 권한이 없습니다."),
    _NOT_FOUND_AD(HttpStatus.NOT_FOUND, "404", "광고를 찾을 수 없습니다."),

    // schedule
    _NOT_FOUND_SCHEDULE(HttpStatus.NOT_FOUND, "404", "존재하지 않는 일정입니다."),

    // comment
    _NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "404", "존재하지 않는 댓글입니다."),
    _NO_PERMISSION_COMMENT_MODIFICATION(HttpStatus.NOT_FOUND, "403", "해당 댓글의 수정권한이 없습니다."),

    // board
    _NOT_FOUND_BOARD(HttpStatus.NOT_FOUND, "404", "존재하지 않는 게시물입니다."),
    _NO_PERMISSION_BOARD_MODIFICATION(HttpStatus.NOT_FOUND, "403", "해당 게시글의 수정권한이 없습니다."),

    //like
    _LIKES_DONT_ZERO(HttpStatus.BAD_REQUEST, "400", "0이하로 감소시킬수 없습니다."),

    // lock
    _LOCK_ERROR(HttpStatus.BAD_REQUEST,"423","락을 획득하지 못했습니다.");


    private HttpStatus httpStatus;
    private String statusCode;
    private String message;

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .statusCode(statusCode)
                .message(message)
                .httpStatus(httpStatus)
                .success(false)
                .build();
    }
}
