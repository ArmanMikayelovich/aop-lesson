package com.mikayelovich.controller;

import com.mikayelovich.model.TimeObject;
import com.mikayelovich.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TimeController {

    @Autowired
    private TimeService timeService;

    @RequestMapping(path = "/time")
    public TimeObject time(@RequestHeader(name = "Accept") String acceptHeader) {

        TimeObject timeObject = new TimeObject();
        timeObject.setTitle(acceptHeader);

        timeObject.setTimestamp(LocalDateTime.now().toString());
        return timeObject;
    }

  @GetMapping(path = "/calculate-days-between")
    public Map time(@RequestParam(name = "from") String from,
                    @RequestParam(name = "to") String to) {
      return Collections.singletonMap("value", timeService.calculateDateBetween(from, to));
  }
}
