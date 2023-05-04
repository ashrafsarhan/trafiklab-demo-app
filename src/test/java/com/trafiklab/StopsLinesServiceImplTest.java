package com.trafiklab;

import com.trafiklab.api.SlRepository;
import com.trafiklab.domain.JourneyPatternPointOnLine;
import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.dto.BusLine;
import com.trafiklab.service.StopsLinesServicePlainImpl;
import com.trafiklab.service.StopsLinesServiceStreamImpl;
import com.trafiklab.utils.MapUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ActiveProfiles("test")
@SpringBootTest
public class StopsLinesServiceImplTest {
    @Mock
    private SlRepository<LineDataResponse> stopsLinesRepository;
    @Mock
    private MapUtil<String, JourneyPatternPointOnLine> mapUtil;
    @InjectMocks
    private StopsLinesServicePlainImpl stopsLinesServicePlainImpl;

    @InjectMocks
    private StopsLinesServiceStreamImpl stopsLinesServiceStreamImpl;

    @BeforeEach
    public void setUp() {
        Mockito.when(stopsLinesRepository.getBusJourneyPatternPointOnLine()).thenReturn(TestHelper.LINE_DATA_RESPONSE);
        Mockito.when(mapUtil.getTopEntryWithValueSize(any(), eq(TestHelper.SORT_ORDER_DESC), eq(TestHelper.N))).thenReturn(TestHelper.TOP_ENTRY_WITH_VALUE_SIZE);
    }

    @Test
    void testGetTopListOfBusLinesWithMostStopsSuccessUsingPlainImpl() {
        List<BusLine> topListOfBusLinesWithMostStops = stopsLinesServicePlainImpl.getTopListOfBusLinesWithMostStops(TestHelper.N, TestHelper.SORT_ORDER_DESC);
        Assertions.assertNotNull(topListOfBusLinesWithMostStops, "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals(2, topListOfBusLinesWithMostStops.size(), "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals("500", topListOfBusLinesWithMostStops.get(0).getLineNumber(), "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals("400", topListOfBusLinesWithMostStops.get(1).getLineNumber(), "Test GetTopListOfBusLinesWithMostStops Fail");
    }

    @Test
    void testGetTopListOfBusLinesWithMostStopsSuccessUsingStreamImpl() {
        List<BusLine> topListOfBusLinesWithMostStops = stopsLinesServiceStreamImpl.getTopListOfBusLinesWithMostStops(TestHelper.N, TestHelper.SORT_ORDER_ASC);
        Assertions.assertNotNull(topListOfBusLinesWithMostStops, "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals(2, topListOfBusLinesWithMostStops.size(), "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals("100", topListOfBusLinesWithMostStops.get(0).getLineNumber(), "Test GetTopListOfBusLinesWithMostStops Fail");
        Assertions.assertEquals("200", topListOfBusLinesWithMostStops.get(1).getLineNumber(), "Test GetTopListOfBusLinesWithMostStops Fail");
    }

}
