package com.trafiklab.rest;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.JourneyPatternPointOnLine;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/SL")
public class SlController {

    private final SlService stopsLinesService;

    @GetMapping(value = "/TopListOfBusLinesWithMostStops/{listSize}")
    public Map<String, List<JourneyPatternPointOnLine>> getTopListOfBusLinesWithMostStops(@PathVariable Integer listSize) {
        return stopsLinesService.getTopListOfBusLinesWithMostStops(listSize);
    }

}
