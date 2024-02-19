package com.knits.enterprise.config;

import com.knits.enterprise.dto.security.UserDto;

import java.time.format.DateTimeFormatter;

/**
 * Application constants.
 */
public final class Constants {
    private Constants() {}
    public static final Integer DEFAULT_JPA_PAGE_SIZE = 50;
    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String TIME_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_DD_MM_YYYY);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT_DD_MM_YYYY_HH_MM_SS);
}
