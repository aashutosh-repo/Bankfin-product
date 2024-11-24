package com.fin.bancs.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fin.bancs.repository.HolidayRepository;
import com.fin.bancs.utils.Holidays;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class HolidayServices {

    @Autowired
    private HolidayRepository holidayRepository;


    @Autowired
    private ResourceLoader resourceLoader;

    public List<LocalDate> getHolidayList() {
        List<Holidays> allHolidays = holidayRepository.findAll();
        List<LocalDate> dates = new ArrayList<LocalDate>();
        allHolidays.forEach(date ->
        {
            dates.add(date.getDate());

        });
        return dates;
    }

    public LocalDate getValidWorkingDay(LocalDate inpDate,  int addType, int qtyToAdd) {
        LocalDate dateOut = inpDate;

        if(addType ==1 ) { //for days
            dateOut = inpDate.plusDays(qtyToAdd);
            dateOut = findAvailableDate(dateOut);
            dateOut = adjustWeekendsDates(dateOut);

        }
        if(addType == 2 ) { //For Month
            inpDate = inpDate.plusMonths(qtyToAdd);
            dateOut = inpDate;
            dateOut = adjustWeekendsDates(dateOut);
        }
        if(addType == 3 ) { //For Years
            inpDate = inpDate.plusYears(qtyToAdd);
            dateOut = inpDate;
            dateOut = adjustWeekendsDates(dateOut);
        }

        return dateOut;
    }

    // Recursive method to find an available date (not a holiday)
    public LocalDate findAvailableDate(LocalDate dateOut) {
        if (!holidayRepository.existsByDate(dateOut)) {
            return dateOut; // If the date doesn't exist in the DB, it's available
        }

        // If the date exists (it's a holiday), increment by one day and call recursively
        return findAvailableDate(dateOut.plusDays(1));
    }

    public LocalDate adjustWeekendsDates(LocalDate inpDate) {

        LocalDate dateOut = inpDate;

        if(inpDate.getDayOfWeek() == DayOfWeek.SUNDAY || inpDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            if (inpDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                dateOut= findAvailableDate(dateOut.plusDays(2));
            }else {
                dateOut= findAvailableDate(dateOut.plusDays(1));
            }
        }
        return dateOut;
    }

    public void loadHolidaysFromCSV() throws IOException, FileNotFoundException, java.io.IOException, CsvValidationException {
        ClassPathResource resource = new ClassPathResource("InputFiles/IndiaHolidays.csv");

        String csvFilePath = "classpath:InputFiles/IndiaHolidys.csv"; // Use classpath

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resourceLoader.getResource(csvFilePath).getInputStream()))) {
            String[] header = csvReader.readNext(); // Read header
            //try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                // Skip header row
                if ("date".equals(nextLine[0])) {
                    continue;
                }

                // Parse data from CSV
                String dateStr = nextLine[0];
                LocalDate date = LocalDate.parse(dateStr); // Assuming date in format yyyy-MM-dd
                String name = nextLine[1];
                String type = nextLine[2];

                // Create a new Holiday object
                Holidays holiday = new Holidays();
                holiday.setDate(date);
                holiday.setName(name);
                holiday.setType(type);

                // Save the Holiday object to the database
                holidayRepository.save(holiday);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }

}
