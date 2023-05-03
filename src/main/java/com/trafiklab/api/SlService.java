package com.trafiklab.api;

import com.trafiklab.utils.SortOrder;

public interface SlService<T>{

    T getTopListOfBusLinesWithMostStops(int listSize, SortOrder sortOrder);

}
