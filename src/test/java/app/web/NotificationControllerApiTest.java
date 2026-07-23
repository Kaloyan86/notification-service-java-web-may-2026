package app.web;

import app.config.ApiKeyAuthenticationFilter;
import app.config.SecurityConfig;
import app.service.NotificationService;
import app.web.dto.NotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static app.util.NotificationFactory.getNotificationResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@WebMvcTest(NotificationController.class)
@Import({SecurityConfig.class, ApiKeyAuthenticationFilter.class, SecurityTestConfig.class})
@TestPropertySource(properties = "notification.service.api-key=api-key")
public class NotificationControllerApiTest {

    @MockitoBean
    private NotificationService notificationService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGetHistory_shouldReturnListWithNotifications_andReturnStatus200() throws Exception {

        List<NotificationResponse> notificationResponses = List.of(
                getNotificationResponse(), getNotificationResponse(), getNotificationResponse(), getNotificationResponse()
        );

        when(notificationService.getHistory(any())).thenReturn(notificationResponses);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-API-Key", SecurityTestConfig.TEST_API_KEY);

        MockHttpServletRequestBuilder request = get("/api/v1/notifications")
                .param("userId", UUID.randomUUID().toString())
                        .headers(httpHeaders);

        mockMvc.perform(request)
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].subject").isNotEmpty())
                .andExpect(jsonPath("$[0].type").isNotEmpty())
                .andExpect(jsonPath("$[0].status").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
