package com.devteria.identity_service.exception;

public enum ErrorCode {
    INVALID_KEY(0001, "Invalid messagex key"),
    UNCATEGORIZED_EXISTED(1001, "Uncategorized error"),
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1003, "Username at least 3 characters"),
    PASSWORD_INVALID(1004, "Password at least 3 characters"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
