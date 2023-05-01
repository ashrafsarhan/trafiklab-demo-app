package com.trafiklab;

import com.trafiklab.dto.LineDataResponse;
import com.trafiklab.utils.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
class RestClientTest {

    @Autowired
    private RestClient<LineDataResponse> restClient;

    @Value("${lineDataApiUrl}")
    private String lineDataApiUrl;

    @Value("${lineDataApiKey}")
    private String lineDataApiKey;

    @Test
    void testLineDataResponseRestClientSuccess() {
        Optional<LineDataResponse> lineDataResponse = restClient
                .executeGetApiCallRetryable(String.format("%s?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiUrl, lineDataApiKey),
                        LineDataResponse.class);
        Assertions.assertNotNull(lineDataResponse.get(), "Test LineDataResponseRestClient Fail ");
    }

    @Test
    void testLineDataResponseRestClientFail() {
        Assertions.assertThrows(RuntimeException.class, () -> {
                    restClient
                            .executeGetApiCallRetryable(String.format("https://api.sl.se/api/linedata.json?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiKey),
                                    LineDataResponse.class);
                }
                , "Test LineDataResponseRestClient Fail");
    }

}
