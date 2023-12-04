package com.speakingClock.speakingclock.service;

import com.speakingClock.speakingclock.Exception.InputNotFoundException;
import com.speakingClock.speakingclock.Exception.InvalidInputFormatException;
import org.springframework.stereotype.Service;

@Service
public class UserInputService {

    public String processInput(String userInput) throws InvalidInputFormatException, InputNotFoundException {
        if (!isValidInputFormat(userInput)) {
            throw new InvalidInputFormatException("Invalid input format. Please provide input in HH:mm format.");
        }

        String[] parts = userInput.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidInputFormatException("Invalid input. Hours should be between 0-23 and minutes between 0-59.");
        }

        String processedInput = "Processed User Input: " + userInput;
        return processedInput;
    }

    private boolean isValidInputFormat(String userInput) {
        return userInput.matches("^\\d{2}:\\d{2}$");
    }
}
