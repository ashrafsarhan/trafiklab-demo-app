package com.trafiklab.utils;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MapUtil<K, V> {

    public List<Map.Entry<K, List<V>>> getTopEntryWithValueSize(Map<K, List<V>> map, SortOrder sortOrder, int n) {
        // Create a list from elements of HashMap
        List<Map.Entry<K, List<V>>> list =
                new LinkedList<>(map.entrySet());
        // Create a comparator based on the given sort order
        Comparator<Map.Entry<K, List<V>>> comparator = Comparator.comparingInt(e -> e.getValue().size());//ASC
        switch (sortOrder) {
            case DESC -> comparator = (e1, e2) -> e2.getValue().size() - e1.getValue().size();
        }
        // Sort the list
        Collections.sort(list, comparator);
        // Get a sub list based on the given n number of elements 
        return list.subList(0, n);
    }

}
