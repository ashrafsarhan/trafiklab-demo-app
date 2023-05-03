package com.trafiklab.api;

import java.util.Optional;

public interface SlRepository<T> {

    Optional<T> getBusJourneyPatternPointOnLine();

}
