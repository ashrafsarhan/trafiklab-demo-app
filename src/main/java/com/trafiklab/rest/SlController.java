package com.trafiklab.rest;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.BusLine;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/SL")
public class SlController {

    private final SlService stopsLinesService;

    @GetMapping(value = "/TopListOfBusLinesWithMostStops/{listSize}")
    public List<BusLine> getTopListOfBusLinesWithMostStops(@PathVariable Integer listSize) {
        return stopsLinesService.getTopListOfBusLinesWithMostStops(listSize)
                .entrySet()
                .stream()
                .map(entry -> {
                    return BusLine.builder()
                            .lineNumber(entry.getKey())
                            .stopsCount(entry.getValue().size())
                            .lineStops(entry.getValue()
                                    .stream().map(JourneyPatternPointOnLine::getJourneyPatternPointNumber)
                                    .collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList());
    }

}
