package com.webservice.common;

public class Constants {

    public static final String USER_TABLE_NAME = "webservice.user_table";
    public static final String SESSION_TABLE_NAME = "webservice.session_table";

    public static final String DATA_INTEGRITY_VIOLATION_EXCEPTION = "DataIntegrityViolationException";

    public static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+)$";

    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    public static final String BAD_REQUEST_MESSAGE = "Invalid Request! One or more parameters entered are either empty or null.";
    public static final String PASSWORD_MISMATCH_MESSAGE = "User credentials did not match. Please try again!";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found!";
    public static final String SESSION_NOT_FOUND_MESSAGE = "Session not found! Please log in again.";

}
