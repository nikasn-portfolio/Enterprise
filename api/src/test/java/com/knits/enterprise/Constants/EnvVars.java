package com.knits.enterprise.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvVars {
    public static final String BASE_URL= System.getenv("BASE_URL");
    public static final String TEST_USERNAME= "test";
    public static final String TEST_PASSWORD= "test";
}
