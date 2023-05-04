package com.trafiklab;

import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.repository.StopsLinesRepository;
import com.trafiklab.utils.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ActiveProfiles("test")
@SpringBootTest
public class StopsLinesRepositoryTest {

    @Mock
    private RestClient<LineDataResponse> restClient;

    @InjectMocks
    private StopsLinesRepository stopsLinesRepository;

    @BeforeEach
    public void setUp() {
        Mockito.when(restClient.executeGetApiCallRetryable(any(String.class), eq(LineDataResponse.class))).thenReturn(TestHelper.LINE_DATA_RESPONSE);
        Mockito.when(restClient.executeGetApiCallRetryable(eq(TestHelper.UNAVAILABLE_API_URL), eq(LineDataResponse.class))).thenAnswer(invocation -> {
            throw new Exception();
        });
    }

    @Test
    void testGetBusJourneyPatternPointOnLineSuccess() {
        Optional<LineDataResponse> lineDataResponse = stopsLinesRepository.getBusJourneyPatternPointOnLine();
        Assertions.assertTrue(lineDataResponse.isPresent(), "Test GetBusJourneyPatternPointOnLine Fail");
        Assertions.assertEquals(0, lineDataResponse.get().statusCode, "Test GetBusJourneyPatternPointOnLine Fail");
    }

    @Test
    void testGetBusJourneyPatternPointOnLineFail() {
        Assertions.assertThrows(Exception.class, () -> {
                    restClient
                            .executeGetApiCallRetryable(TestHelper.UNAVAILABLE_API_URL, LineDataResponse.class);
                }
                , "Test GetBusJourneyPatternPointOnLine Fail");
    }

}
