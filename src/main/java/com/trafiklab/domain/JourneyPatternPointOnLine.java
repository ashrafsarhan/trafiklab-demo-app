package com.trafiklab.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JourneyPatternPointOnLine {
    @JsonProperty("LineNumber")
    public String lineNumber;
    @JsonProperty("DirectionCode")
    public String directionCode;
    @JsonProperty("JourneyPatternPointNumber")
    public String journeyPatternPointNumber;
    @JsonProperty("LastModifiedUtcDateTime")
    public String lastModifiedUtcDateTime;
    @JsonProperty("ExistsFromDate")
    public String existsFromDate;
}
