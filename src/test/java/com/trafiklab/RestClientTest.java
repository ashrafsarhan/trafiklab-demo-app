package com.trafiklab;

import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.utils.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ActiveProfiles("test")
@SpringBootTest
class RestClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestClient restClient;

    @Value("${lineDataApiUrl}")
    private String lineDataApiUrl;

    @Value("${lineDataApiKey}")
    private String lineDataApiKey;

    @BeforeEach
    public void setUp() {
        Mockito.when(restTemplate.exchange(eq(String.format("%s?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiUrl, lineDataApiKey)), eq(HttpMethod.GET), any(), eq(LineDataResponse.class))).thenReturn(TestHelper.LINE_DATA_RESPONSE_ENTITY);
        Mockito.when(restTemplate.exchange(eq(TestHelper.UNAVAILABLE_API_URL), eq(HttpMethod.GET), any(), eq(LineDataResponse.class))).thenAnswer(invocation -> { throw new Exception(); });
    }

    @Test
    void testLineDataResponseRestClientSuccess() {
        Optional<LineDataResponse> lineDataResponse = restClient
                .executeGetApiCallRetryable(String.format("%s?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiUrl, lineDataApiKey),
                        LineDataResponse.class);
        Assertions.assertNotNull(lineDataResponse.get(), "Test LineDataResponseRestClient Fail ");
    }

    @Test
    void testLineDataResponseRestClientFail() {
        Assertions.assertThrows(Exception.class, () -> {
                    restClient
                            .executeGetApiCallRetryable(TestHelper.UNAVAILABLE_API_URL, LineDataResponse.class);
                }
                , "Test LineDataResponseRestClient Fail");
    }

}
