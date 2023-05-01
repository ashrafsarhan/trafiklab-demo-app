package com.trafiklab.utils;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MapUtil<K,V> {

    public Map<K, List<V>> sortMapBasedOnListValueSize(Map<K, List<V>> unSortedMap) {
        return unSortedMap.entrySet()
                .parallelStream()
                .sorted(Comparator.comparing(l -> l.getValue().size(), Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<K, List<V>> getFirstEntries(Map<K, List<V>> sortedMap, int elementsToReturn) {
        return sortedMap.entrySet()
                .stream()
                .limit(elementsToReturn)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
