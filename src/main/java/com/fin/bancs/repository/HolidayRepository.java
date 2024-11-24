package com.fin.bancs.repository;

import java.time.LocalDate;

import com.fin.bancs.utils.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository  extends JpaRepository<Holidays, Long>{
    boolean existsByDate(LocalDate date);
}
