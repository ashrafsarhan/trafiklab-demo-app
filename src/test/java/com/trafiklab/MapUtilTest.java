package com.trafiklab;

import com.trafiklab.utils.MapUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest
class MapUtilTest {

    @InjectMocks
    private MapUtil<String, String> mapUtil;

    @Test
    void testGetFirstEntriesSuccess() {
        Assertions.assertNotNull(mapUtil.getTopEntryWithValueSize(TestHelper.TEST_MAP, TestHelper.SORT_ORDER_ASC, TestHelper.N), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals(2, mapUtil.getTopEntryWithValueSize(TestHelper.TEST_MAP, TestHelper.SORT_ORDER_ASC, TestHelper.N).size(), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals("500", mapUtil.getTopEntryWithValueSize(TestHelper.TEST_MAP, TestHelper.SORT_ORDER_DESC, TestHelper.N).get(0).getKey(), "Test GetFirstEntriesSuccess Fail");
        Assertions.assertEquals("400", mapUtil.getTopEntryWithValueSize(TestHelper.TEST_MAP, TestHelper.SORT_ORDER_DESC, TestHelper.N).get(1).getKey(), "Test GetFirstEntriesSuccess Fail");
    }

}
