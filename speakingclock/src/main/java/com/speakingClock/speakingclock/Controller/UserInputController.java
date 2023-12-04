package com.speakingClock.speakingclock.Controller;

import com.speakingClock.speakingclock.Exception.InputNotFoundException;
import com.speakingClock.speakingclock.Exception.InvalidInputFormatException;
import com.speakingClock.speakingclock.service.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-input")
public class UserInputController {

    @Autowired
    private UserInputService userInputService;

    @PostMapping("/process")
    public ResponseEntity<String> processUserInput(@RequestBody String userInput) {
        try {
            String processedInput = userInputService.processInput(userInput);
            return ResponseEntity.ok(processedInput);
        } catch (InvalidInputFormatException | InputNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
