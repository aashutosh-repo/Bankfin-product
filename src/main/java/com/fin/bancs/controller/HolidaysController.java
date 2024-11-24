package com.fin.bancs.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.fin.bancs.services.HolidayServices;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidaysController {

    @Autowired
    HolidayServices holidayservices;

    @GetMapping("getNextValidDate")
    public ResponseEntity<LocalDate> getValidDate(@RequestParam LocalDate dateinput,
                                                  @RequestParam int paramtoAdd, @RequestParam int qtyToAdd ){
        LocalDate date = holidayservices.getValidWorkingDay(dateinput,paramtoAdd,qtyToAdd);
        return ResponseEntity.ok(date);
    }

    @GetMapping("getHolidays")
    public ResponseEntity<List<LocalDate>> getHolidaysDates(){

        List<LocalDate> allHolidays =  holidayservices.getHolidayList();
        return ResponseEntity.ok(allHolidays);
    }

    @GetMapping("/load-holidays")
    public String loadHolidays() throws CsvValidationException, FileNotFoundException, java.io.IOException {
        try {
            holidayservices.loadHolidaysFromCSV();
            return "Holidays successfully loaded!";
        } catch (IOException e) {
            return "Failed to load holidays: " + e.getMessage();
        }
    }
}
