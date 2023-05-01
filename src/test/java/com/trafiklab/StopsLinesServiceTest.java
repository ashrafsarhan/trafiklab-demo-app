package com.trafiklab;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import com.trafiklab.service.StopsLinesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class StopsLinesServiceTest {

    @Autowired
    private SlService stopsLinesService;

    @Test
    void testGetTopListOfBusLinesWithMostStopsSuccess() {
        Map<String, List<JourneyPatternPointOnLine>> topListOfBusLinesWithMostStops = stopsLinesService.getTopListOfBusLinesWithMostStops(10);
        Assertions.assertNotNull(topListOfBusLinesWithMostStops, "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals(10, topListOfBusLinesWithMostStops.size());
        Assertions.assertEquals(232, topListOfBusLinesWithMostStops.entrySet().stream().findFirst().get().getValue().size());
        Assertions.assertEquals(154, topListOfBusLinesWithMostStops.entrySet().stream().reduce((e1,e2) -> e2).get().getValue().size());
    }

}
