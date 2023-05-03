package com.trafiklab;

import com.trafiklab.api.SlRepository;
import com.trafiklab.domain.LineDataResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class StopsLinesRepositoryTest {

    @Autowired
    private SlRepository<LineDataResponse> StopsLinesRepository;

    @Test
    void testGetBusJourneyPatternPointOnLineSuccess() {
        Optional<LineDataResponse> lineDataResponse = StopsLinesRepository.getBusJourneyPatternPointOnLine();
        Assertions.assertNotNull(lineDataResponse, "Test GetBusJourneyPatternPointOnLine Fail");
    }

}
