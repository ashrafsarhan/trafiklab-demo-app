package com.trafiklab.api;

import com.trafiklab.dto.JourneyPatternPointOnLine;

import java.util.List;
import java.util.Map;

public interface SlService {

    Map<String, List<JourneyPatternPointOnLine>> getTopListOfBusLinesWithMostStops(int listSize);

}
