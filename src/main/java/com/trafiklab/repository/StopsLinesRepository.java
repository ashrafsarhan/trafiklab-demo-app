package com.trafiklab.repository;

import com.trafiklab.api.SlRepository;
import com.trafiklab.domain.LineDataResponse;
import com.trafiklab.utils.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StopsLinesRepository implements SlRepository<LineDataResponse> {

    private final RestClient<LineDataResponse> restClient;

    @Value("${lineDataApiUrl}")
    private String lineDataApiUrl;

    @Value("${lineDataApiKey}")
    private String lineDataApiKey;


    @Override
    @Cacheable(value = "BusJourneyPatternPointOnLine")
    public Optional<LineDataResponse> getBusJourneyPatternPointOnLine() {
        Optional<LineDataResponse> lineDataResponse = restClient
                .executeGetApiCallRetryable(String.format("%s?key=%s&model=jour&DefaultTransportModeCode=BUS", lineDataApiUrl, lineDataApiKey),
                        LineDataResponse.class);
        if (lineDataResponse.isPresent() && lineDataResponse.get().statusCode == 0)
            return lineDataResponse;
        return Optional.empty();
    }
}
