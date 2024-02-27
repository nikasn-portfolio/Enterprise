package com.knits.enterprise.config;


import java.time.format.DateTimeFormatter;

/**
 * Application constants.
 */
public final class Constants {
    private Constants() {}
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String LOGIN_ERROR_MESSAGE = "Invalid login format. It should match the pattern: "
            + "username@domain.com or username with allowed special characters. Allowed characters: "
            + "letters, numbers, ! $ & * + = ? ^ _ ` { | } ~ . -";
    public static final Integer DEFAULT_JPA_PAGE_SIZE = 50;
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String TIME_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT_DD_MM_YYYY_HH_MM_SS);
}
