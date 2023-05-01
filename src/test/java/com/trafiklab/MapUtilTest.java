package com.trafiklab;

import com.trafiklab.utils.MapUtil;
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
	@Autowired
	private MapUtil<String, String> mapUtil;
	private final static Map<String, List<String>> TEST_MAP = new HashMap<>();

	static {
		TEST_MAP.put("100", Arrays.asList("0001"));
		TEST_MAP.put("200", Arrays.asList("0001", "0002"));
		TEST_MAP.put("300", Arrays.asList("0001", "0002", "0003"));
		TEST_MAP.put("400", Arrays.asList("0001", "0002", "0003", "0004"));
		TEST_MAP.put("500", Arrays.asList("0001", "0002", "0003", "0004", "0005"));
	}

	@Test
	void testSortMapBasedOnListValueSizeSuccess() {
		Assertions.assertNotNull(mapUtil.sortMapBasedOnListValueSize(TEST_MAP), "Test SortMapBasedOnListValueSizeSuccess Fail");
		Assertions.assertEquals(5, mapUtil.sortMapBasedOnListValueSize(TEST_MAP).size(), "Test SortMapBasedOnListValueSizeSuccess Fail");
		Assertions.assertEquals("500", mapUtil.sortMapBasedOnListValueSize(TEST_MAP).entrySet().stream().findFirst().get().getKey(), "Test SortMapBasedOnListValueSizeSuccess Fail");
	}

	@Test
	void testGetFirstEntriesSuccess() {
		Assertions.assertNotNull(mapUtil.getFirstEntries(mapUtil.sortMapBasedOnListValueSize(TEST_MAP), 2), "Test GetFirstEntriesSuccess Fail");
		Assertions.assertEquals(2, mapUtil.getFirstEntries(mapUtil.sortMapBasedOnListValueSize(TEST_MAP),2).size(), "Test GetFirstEntriesSuccess Fail");
		Assertions.assertEquals("500", mapUtil.sortMapBasedOnListValueSize(TEST_MAP).entrySet().stream().findFirst().get().getKey(), "Test GetFirstEntriesSuccess Fail");
		Assertions.assertEquals("400", mapUtil.getFirstEntries(mapUtil.sortMapBasedOnListValueSize(TEST_MAP),2).entrySet().stream().reduce((e1,e2) -> e2).get().getKey(), "Test GetFirstEntriesSuccess Fail");
	}

}
