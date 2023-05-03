package com.trafiklab;

import com.trafiklab.utils.MapUtil;
import com.trafiklab.utils.SortOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActiveProfiles("test")
@SpringBootTest
class MapUtilTest {
    private final static Map<String, List<String>> TEST_MAP = new HashMap<>();

    static {
        TEST_MAP.put("100", Arrays.asList("0001"));
        TEST_MAP.put("200", Arrays.asList("0001", "0002"));
        TEST_MAP.put("300", Arrays.asList("0001", "0002", "0003"));
        TEST_MAP.put("400", Arrays.asList("0001", "0002", "0003", "0004"));
        TEST_MAP.put("500", Arrays.asList("0001", "0002", "0003", "0004", "0005"));
    }

    @Autowired
    private MapUtil<String, String> mapUtil;

    @Test
    void testGetFirstEntriesSuccess() {
        Assertions.assertNotNull(mapUtil.getTopEntryWithValueSize(TEST_MAP, SortOrder.ASC, 2), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals(2, mapUtil.getTopEntryWithValueSize(TEST_MAP, SortOrder.ASC, 2).size(), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals("500", mapUtil.getTopEntryWithValueSize(TEST_MAP, SortOrder.DESC, 2).get(0).getKey(), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals("400", mapUtil.getTopEntryWithValueSize(TEST_MAP, SortOrder.DESC, 2).get(1).getKey(), "Test GetFirstEntriesSuccess Fail");
    }

}
