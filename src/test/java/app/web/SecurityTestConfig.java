package app.web;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

/**
 * Test configuration for Spring Security setup in web layer tests.
 * Provides beans and configurations needed for testing authenticated endpoints.
 */
@TestConfiguration
@ActiveProfiles("test")
public class SecurityTestConfig {

    /**
     * Default API key for testing purposes
     */
    public static final String TEST_API_KEY = "api-key";
}

