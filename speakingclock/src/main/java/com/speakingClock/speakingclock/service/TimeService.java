package com.speakingClock.speakingclock.service;

import com.speakingClock.speakingclock.Exception.InvalidTimeFormatException;
import com.speakingClock.speakingclock.Exception.TimeNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

    public String convertTimeToWords(String time) throws InvalidTimeFormatException, TimeNotFoundException {
        String[] parts = time.split(":");
        int hours;
        int minutes;

        try {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTimeFormatException("Invalid time format. Please provide time in HH:mm format.");
        }

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidTimeFormatException("Invalid time. Hours should be between 0-23 and minutes between 0-59.");
        }

        LocalTime localTime = LocalTime.of(hours, minutes);
        String result;

        if (localTime.equals(LocalTime.MIDNIGHT)) {
            result = "It's Midnight";
        } else if (localTime.equals(LocalTime.NOON)) {
            result = "It's Midday";
        } else {
            String hourInWords = convertToWords(hours);
            String minuteInWords = convertToWords(minutes);
            result = "It's " + hourInWords + " " + ((minutes != 0) ? minuteInWords : "") + ((minutes != 0) ? " " + minuteInWords : "");
        }

        return result;
    }

    private String convertToWords(int number) {
        String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty"};

        if (number < 20) {
            return units[number];
        } else {
            return tens[number / 10] + ((number % 10 != 0) ? " " + units[number % 10] : "");
        }
    }
}
