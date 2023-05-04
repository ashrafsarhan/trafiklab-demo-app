package com.trafiklab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SlControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTopListOfBusLinesWithMostStopsStreamImplWithDefaultRequestParamsSuccess() throws Exception {
        this.mockMvc.perform(get("/SL/TopListOfBusLinesWithMostStops/StreamImpl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lineNumber", is("636")))
                .andExpect(jsonPath("$[9].lineNumber", is("783")));
    }

    @Test
    public void testGetTopListOfBusLinesWithMostStopsPlainImplWithGivenRequestParamsSuccess() throws Exception {
        this.mockMvc.perform(get("/SL/TopListOfBusLinesWithMostStops/PlainImpl")
                        .param("listSize", "5")
                        .param("sortOrder", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stopsCount", is(4)))
                .andExpect(jsonPath("$[4].stopsCount", is(7)));
    }

}
