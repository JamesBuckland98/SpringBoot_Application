//package com.nsa.cm6213.example.charity2018.domain;
//
//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
//import java.time.LocalDateTime;
//import java.sql.Timestamp;
//
///**
// * @see https://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
// */
//
//@Converter(autoApply = true)
//public class LocalDateAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
//
//    @Override
//    public Timestamp convertToDatabaseColumn(LocalDateTime locDate) {
//        return (locDate == null ? null : Timestamp.valueOf(locDate));
//    }
//
//    @Override
//    public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
//        return (sqlDate == null ? null : sqlDate.toLocalDateTime());
//    }
//}

