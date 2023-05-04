package com.trafiklab;

import com.trafiklab.domain.JourneyPatternPointOnLine;
import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.domain.ResponseData;
import com.trafiklab.utils.SortOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class TestHelper {

    static final Map<String, List<String>> TEST_MAP = new HashMap<>() {{
        put("100", Arrays.asList("0001"));
        put("200", Arrays.asList("0001", "0002"));
        put("300", Arrays.asList("0001", "0002", "0003"));
        put("400", Arrays.asList("0001", "0002", "0003", "0004"));
        put("500", Arrays.asList("0001", "0002", "0003", "0004", "0005"));
    }};

    static final Optional<LineDataResponse> LINE_DATA_RESPONSE = Optional.of(LineDataResponse
            .builder().statusCode(0).responseData(ResponseData
                    .builder().result(
                            Arrays.asList(
                                    JourneyPatternPointOnLine.builder().lineNumber("100").journeyPatternPointNumber("0001").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("200").journeyPatternPointNumber("0001").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("200").journeyPatternPointNumber("0002").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("300").journeyPatternPointNumber("0001").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("300").journeyPatternPointNumber("0002").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("300").journeyPatternPointNumber("0003").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0001").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0002").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0003").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0004").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0001").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0002").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0003").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0004").build(),
                                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0005").build()
                            )
                    ).build()).build());

    static final ResponseEntity<LineDataResponse> LINE_DATA_RESPONSE_ENTITY = new ResponseEntity<>(
            LINE_DATA_RESPONSE.get(),
            HttpStatus.OK
    );

    static final List<Map.Entry<String, List<JourneyPatternPointOnLine>>> TOP_ENTRY_WITH_VALUE_SIZE = Arrays.asList(
            Map.entry("500", Arrays.asList(
                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0001").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0002").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0003").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0004").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("500").journeyPatternPointNumber("0005").build())),
            Map.entry("400", Arrays.asList(
                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0001").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0002").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0003").build(),
                    JourneyPatternPointOnLine.builder().lineNumber("400").journeyPatternPointNumber("0004").build()
            )));
    static final SortOrder SORT_ORDER_ASC = SortOrder.ASC;
    static final SortOrder SORT_ORDER_DESC = SortOrder.DESC;
    static final int N = 2;

    static final String UNAVAILABLE_API_URL = "UNAVAILABLE_API_URL";
}