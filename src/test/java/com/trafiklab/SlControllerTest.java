package com.trafiklab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTopListOfBusLinesWithMostStopsSuccess() throws Exception {
        this.mockMvc.perform(get("/SL/TopListOfBusLinesWithMostStops/10"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lineNumber", is("636")))
                .andExpect(jsonPath("$[1].lineNumber", is("626")));
    }

}
