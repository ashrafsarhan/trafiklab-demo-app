package com.trafiklab.service;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import com.trafiklab.dto.LineDataResponse;
import com.trafiklab.utils.RestClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopsLinesService implements SlService {

    private final RestClient<LineDataResponse> restClient;

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
        return lineDataResponse.get().responseData.getResult()
                .parallelStream().collect(Collectors.groupingBy(JourneyPatternPointOnLine::getLineNumber))
                .entrySet()
                .parallelStream()
                .sorted(Comparator.comparing(l -> l.getValue().size(), Comparator.reverseOrder()))
                .limit(listSize)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }
}