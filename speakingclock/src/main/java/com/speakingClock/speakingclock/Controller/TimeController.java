package com.speakingClock.speakingclock.Controller;

import com.speakingClock.speakingclock.Exception.InvalidTimeFormatException;
import com.speakingClock.speakingclock.Exception.TimeNotFoundException;
import com.speakingClock.speakingclock.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/convert")
    public ResponseEntity<String> convertTimeToWords(@RequestParam String time) {
        try {
            String convertedTime = timeService.convertTimeToWords(time);
            return ResponseEntity.ok(convertedTime);
        } catch (InvalidTimeFormatException | TimeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
