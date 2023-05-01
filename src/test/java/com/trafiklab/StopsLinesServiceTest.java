package com.trafiklab;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest
public class StopsLinesServiceTest {

    @Autowired
    private SlService stopsLinesService;

    @Test
    void testGetTopListOfBusLinesWithMostStopsSuccess() {
        Map<String, List<JourneyPatternPointOnLine>> topListOfBusLinesWithMostStops = stopsLinesService.getTopListOfBusLinesWithMostStops(10);
        Assertions.assertNotNull(topListOfBusLinesWithMostStops, "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals(10, topListOfBusLinesWithMostStops.size());
    }

}
