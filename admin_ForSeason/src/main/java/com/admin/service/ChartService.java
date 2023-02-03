package com.admin.service;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Service
public class ChartService {
    DateFormat sbFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Calendar getStringByDate(String str) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sbFormat.parse(str));
        return cal;
    }
}
