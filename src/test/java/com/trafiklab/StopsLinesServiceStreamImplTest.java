package com.trafiklab;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.BusLine;
import com.trafiklab.utils.SortOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class StopsLinesServiceStreamImplTest {

    @Autowired
    private SlService<List<BusLine>> stopsLinesServiceStreamImpl;

    @Test
    void testGetTopListOfBusLinesWithMostStopsSuccess() {
        List<BusLine> topListOfBusLinesWithMostStops = stopsLinesServiceStreamImpl.getTopListOfBusLinesWithMostStops(10, SortOrder.DESC);
        Assertions.assertNotNull(topListOfBusLinesWithMostStops, "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals(10, topListOfBusLinesWithMostStops.size());
        Assertions.assertEquals("636", topListOfBusLinesWithMostStops.get(0).getLineNumber());
        Assertions.assertEquals(154, topListOfBusLinesWithMostStops.get(9).getStopsCount());
    }

}
