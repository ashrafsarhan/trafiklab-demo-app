package com.trafiklab.service;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import com.trafiklab.dto.LineDataResponse;
import com.trafiklab.utils.MapUtil;
import com.trafiklab.utils.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StopsLinesService implements SlService {

    private final RestClient<LineDataResponse> restClient;

    private final MapUtil<String, JourneyPatternPointOnLine> mapUtil;

    @Value("${lineDataApiUrl}")
    private String lineDataApiUrl;

    @Value("${lineDataApiKey}")
    private String lineDataApiKey;

    @SneakyThrows
    @Override
    @Cacheable(value = "TopListOfBusLinesWithMostStops")
    public Map<String, List<JourneyPatternPointOnLine>> getTopListOfBusLinesWithMostStops(int listSize) {
        Optional<LineDataResponse> lineDataResponse = restClient
                .executeGetApiCallRetryable(String.format("%s?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiUrl,lineDataApiKey),
                        LineDataResponse.class);
        if(lineDataResponse.isPresent() && lineDataResponse.get().statusCode == 0)
            return mapUtil.getFirstEntries(mapUtil.sortMapBasedOnListValueSize(lineDataResponse.get().responseData.getResult()
                    .stream().collect(Collectors.groupingBy(JourneyPatternPointOnLine::getLineNumber))), listSize);
        log.warn("Api call response with status code:{} does not have the expected data: {}", lineDataResponse.get().statusCode, lineDataResponse);
        return new HashMap<>();
    }
}