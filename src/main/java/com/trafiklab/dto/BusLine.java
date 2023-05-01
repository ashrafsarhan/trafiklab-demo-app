package com.trafiklab.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusLine {
    private String lineNumber;
    private int stopsCount;
    private List<String> lineStops;
}
