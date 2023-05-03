package com.trafiklab.rest;

import com.trafiklab.api.SlService;
import com.trafiklab.dto.BusLine;
import com.trafiklab.utils.SortOrder;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/SL")
@Tag(name = "Stops Lines")
public class SlController {

    private final SlService<List<BusLine>> stopsLinesServiceStreamImpl;

    private final SlService<List<BusLine>> stopsLinesServicePlainImpl;

    @GetMapping(value = "/TopListOfBusLinesWithMostStops/StreamImpl")
    public List<BusLine> getTopListOfBusLinesWithMostStopsByStreamImpl(@RequestParam(defaultValue = "10") Integer listSize, @RequestParam(defaultValue = "DESC") SortOrder sortOrder) {
        return stopsLinesServiceStreamImpl.getTopListOfBusLinesWithMostStops(listSize, sortOrder);
    }

    @GetMapping(value = "/TopListOfBusLinesWithMostStops/PlainImpl")
    public List<BusLine> getTopListOfBusLinesWithMostStopsByPlainImpl(@RequestParam(defaultValue = "10") Integer listSize, @RequestParam(defaultValue = "DESC") SortOrder sortOrder) {
        return stopsLinesServicePlainImpl.getTopListOfBusLinesWithMostStops(listSize, sortOrder);
    }

}
