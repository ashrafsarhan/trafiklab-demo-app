package com.trafiklab;

import com.trafiklab.dto.LineDataResponse;
import com.trafiklab.utils.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
@ActiveProfiles("test")
@SpringBootTest
class RestClientTest {

	@Autowired
	private RestClient<LineDataResponse> restClient;

	@Test
	void testLineDataResponseRestClientSuccess() {
		Optional<LineDataResponse> lineDataResponse = restClient
				.executeGetApiCallRetryable("https://api.sl.se/api2/linedata.json?key=74dc8f1ae23143858613b2dab98fee77&model=jour&DefaultTransportModeCode=BUS",
						LineDataResponse.class);
		Assertions.assertNotNull(lineDataResponse.get(), "Test LineDataResponseRestClient Fail ");
	}

	@Test
	void testLineDataResponseRestClientFail() {
		Assertions.assertThrows(RuntimeException.class, () -> {
		restClient
				.executeGetApiCallRetryable("https://api.sl.se/api/linedata.json?key=74dc8f1ae23143858613b2dab98fee77&model=jour&DefaultTransportModeCode=BUS",
						LineDataResponse.class);
				}
		, "Test LineDataResponseRestClient Fail");
	}

}
