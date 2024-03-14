package com.example.demo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataConvert {
    public static LocalDate obterData(String dataEmString){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dataEmString, formato);
    }

    public static LocalDateTime obterHoraLimiteCompleta(String dataLimiteEmString, String horaLimiteEmString){
        LocalDate dataLimiteParse = LocalDate.parse(dataLimiteEmString);
        LocalTime horaLimteParse = LocalTime.parse(horaLimiteEmString);
        return LocalDateTime.of(dataLimiteParse,horaLimteParse);
    } 
}
