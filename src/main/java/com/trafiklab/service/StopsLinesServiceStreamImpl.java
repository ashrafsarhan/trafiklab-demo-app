package com.trafiklab.service;

import com.trafiklab.api.SlRepository;
import com.trafiklab.api.SlService;
import com.trafiklab.domain.JourneyPatternPointOnLine;
import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.dto.BusLine;
import com.trafiklab.utils.SortOrder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StopsLinesServiceStreamImpl implements SlService<List<BusLine>> {

    private final SlRepository<LineDataResponse> StopsLinesRepository;

    @SneakyThrows
    @Override
    @Cacheable(value = "TopListOfBusLinesWithMostStops")
    public List<BusLine> getTopListOfBusLinesWithMostStops(int listSize, SortOrder sortOrder) {
        Optional<LineDataResponse> lineDataResponse = StopsLinesRepository.getBusJourneyPatternPointOnLine();
        if (lineDataResponse.isPresent())
            return lineDataResponse.get().responseData.getResult()
                    .stream().collect(Collectors.groupingBy(JourneyPatternPointOnLine::getLineNumber))
                    .entrySet()
                    .parallelStream()
                    .sorted((SortOrder.DESC.equals(sortOrder)) ?
                            (e1, e2) -> e2.getValue().size() - e1.getValue().size() :
                            Comparator.comparingInt(e -> e.getValue().size()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                    .entrySet()
                    .stream()
                    .limit(listSize)
                    .map(entry -> BusLine.builder()
                            .lineNumber(entry.getKey())
                            .stopsCount(entry.getValue().size())
                            .lineStops(entry.getValue()
                                    .stream().map(JourneyPatternPointOnLine::getJourneyPatternPointNumber)
                                    .collect(Collectors.toList()))
                            .build()).collect(Collectors.toList());
        log.warn("Api call response with status code:{} does not have the expected data: {}", lineDataResponse.get().statusCode, lineDataResponse);
        return new ArrayList<>();
    }
}