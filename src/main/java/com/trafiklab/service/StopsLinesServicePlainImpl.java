package com.trafiklab.service;

import com.trafiklab.api.SlRepository;
import com.trafiklab.api.SlService;
import com.trafiklab.domain.JourneyPatternPointOnLine;
import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.dto.BusLine;
import com.trafiklab.utils.MapUtil;
import com.trafiklab.utils.SortOrder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StopsLinesServicePlainImpl implements SlService<List<BusLine>> {
    private final SlRepository<LineDataResponse> StopsLinesRepository;

    private final MapUtil<String, JourneyPatternPointOnLine> mapUtil;

    @SneakyThrows
    @Override
    @Cacheable(value = "TopListOfBusLinesWithMostStops")
    public List<BusLine> getTopListOfBusLinesWithMostStops(int listSize, SortOrder sortOrder) {
        List<BusLine> busLineTopList = new ArrayList<>();
        Optional<LineDataResponse> lineDataResponse = StopsLinesRepository.getBusJourneyPatternPointOnLine();
        if (lineDataResponse.isPresent()) {
            Map<String, List<JourneyPatternPointOnLine>> journeyPatternPointOnLineMap = new HashMap<>();
            for (JourneyPatternPointOnLine e : lineDataResponse.get().getResponseData().result) {
                if (journeyPatternPointOnLineMap.containsKey(e.lineNumber))
                    journeyPatternPointOnLineMap.get(e.lineNumber).add(e);
                else
                    journeyPatternPointOnLineMap.put(e.lineNumber, new ArrayList<>(Arrays.asList(e)));

            }
            List<Map.Entry<String, List<JourneyPatternPointOnLine>>> journeyPatternPointOnLineMapTopEntries = mapUtil.getTopEntryWithValueSize(journeyPatternPointOnLineMap, sortOrder, listSize);
            for (Map.Entry<String, List<JourneyPatternPointOnLine>> e : journeyPatternPointOnLineMapTopEntries) {
                BusLine busLine = BusLine.builder().lineNumber(e.getKey()).stopsCount(e.getValue().size()).lineStops(new ArrayList<>()).build();
                for (JourneyPatternPointOnLine journeyPatternPointOnLine : e.getValue()) {
                    busLine.getLineStops().add(journeyPatternPointOnLine.journeyPatternPointNumber);
                }
                busLineTopList.add(busLine);
            }
        } else
            log.warn("Api call response with status code:{} does not have the expected data: {}", lineDataResponse.get().statusCode, lineDataResponse);
        return busLineTopList;
    }
}