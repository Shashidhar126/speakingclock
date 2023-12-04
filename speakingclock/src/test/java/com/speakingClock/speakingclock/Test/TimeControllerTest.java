package com.speakingClock.speakingclock.Test;

import com.speakingClock.speakingclock.Controller.TimeController;
import com.speakingClock.speakingclock.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimeController.class)
public class TimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeService timeService;

    @Test
    public void testConvertTimeToWords_ValidTime_ReturnsOk() throws Exception {
        String validTime = "08:34";
        String convertedTime = "It's eight thirty four"; // Mock expected conversion

        // Mock the behavior of timeService
        given(timeService.convertTimeToWords(validTime)).willReturn(convertedTime);

        mockMvc.perform(get("/time/convert")
                        .param("time", validTime)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

