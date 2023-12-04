package com.speakingClock.speakingclock.Test;

import com.speakingClock.speakingclock.Controller.UserInputController;
import com.speakingClock.speakingclock.service.UserInputService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserInputController.class)
public class UserInputControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInputService userInputService;

    @Test
    public void testProcessUserInput_ValidInput_ReturnsOk() throws Exception {
        String validInput = "11:25";
        String processedInput = "Processed User Input: " + validInput; // Mock expected processing

        // Mock the behavior of userInputService
        given(userInputService.processInput(validInput)).willReturn(processedInput);

        mockMvc.perform(post("/user-input/process")
                        .content(validInput)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

