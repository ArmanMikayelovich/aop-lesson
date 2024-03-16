package com.mikayelovich.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TimeService {

    public long calculateDateBetween(String from, String to){
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);

        return Math.abs(ChronoUnit.DAYS.between(dateFrom, dateTo));
    }

}
