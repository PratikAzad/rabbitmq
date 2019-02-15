package com.apll.centermanagementsservice.config;

import io.vavr.Function2;
import io.vavr.control.Either;
import lombok.val;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.function.Function;

public class DateTimeZoneConverters {

    public Either<Exception,LocalDateTime> dateTimeFromString (String dateTimeString){
        Either<Exception, LocalDateTime> dateTime=convertDateTime(dateTimeString);
        if (dateTime.isRight()){
            return dateTime;
        }
        else {
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return this.convertDateTime(dateTimeString,formatter);
        }
    }

    public Either<Exception,YearMonth> yearMonthFromString (String dateTimeString){

        Either<Exception, LocalDateTime> yearMonth=this.convertDateTime(dateTimeString);
        if (yearMonth.isRight()){
            return Either.right(YearMonth.from(yearMonth.get()));
        }
        else {
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM");
            Either<Exception, YearMonth> year= convertYearMonth(dateTimeString,formatter);
            if (year.isRight()){
                return year;
            }
            else {
                DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("MMM-yyyy");
                return convertYearMonth(dateTimeString,formatter1);
            }
        }
    }

    public  Either<Exception,LocalDate> dateFromString (String dateTimeString){
        Either<Exception, LocalDateTime> localDateTimeEither=convertDateTime(dateTimeString);
        if (localDateTimeEither.isRight()){
            return Either.right(localDateTimeEither.get().toLocalDate());
        }
        else {
            return convertDate(dateTimeString);
        }

    }

    public Either<Exception,LocalTime> timeFromString(String time){
        Either<Exception, LocalDateTime> localDateTimeEither=convertDateTime(time);
        if (localDateTimeEither.isRight()){
            return Either.right(localDateTimeEither.get().toLocalTime());
        }
        else {
            Either<Exception, LocalTime> convertTimeWithZone=convertTime(time);
            if (convertTimeWithZone.isRight()){
                return convertTimeWithZone;
            }
            else {
                return convertTimeWithOutZone(time);
            }

        }
    }

    private Either<Exception,LocalDateTime> convertDateTime(String dateTimeString){

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ISO_DATE_TIME;

        return this.convertDateTime(dateTimeString,dateTimeFormatter).
                map(this::getLocalDateTimeInIST);

    }
    private Either<Exception,LocalTime> convertTime(LocalTime time){
        LocalDateTime localDateTime= LocalDateTime.of(LocalDate.now(),time);
        return Either.right(getLocalDateTimeInIST(localDateTime).toLocalTime());

    }

    private Either<Exception,LocalTime> convertTime(String time){
        DateTimeFormatter dateFormatter=new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .appendOffsetId()
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']').toFormatter();
        Function2<String,DateTimeFormatter,LocalDateTime> fun=(String a, DateTimeFormatter b)->
                LocalDateTime.of(LocalDate.now(),
                        LocalTime.parse(a,b));
        return exceptionHandler(fun.andThen(x->this.getLocalDateTimeInIST(x).toLocalTime()),
                time,dateFormatter);

    }
    private Either<Exception,LocalTime> convertTimeWithOutZone(String time){
        DateTimeFormatter dateFormatter=new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .toFormatter();
       return exceptionHandler((a,b)->LocalDateTime.of(LocalDate.now(),
               LocalTime.parse(a,b)).toLocalTime(),
               time,dateFormatter);
    }

    private Either<Exception,LocalDate> convertDate(String date){
        DateTimeFormatter dateFormatter=new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .optionalStart()
                .appendOffsetId()
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']').toFormatter();
        return exceptionHandler(( a,  b)-> LocalDateTime.of(LocalDate.parse(a,b),
                LocalTime.of(0,0,0)).toLocalDate(),date,dateFormatter);

    }

    private Either<Exception,LocalDateTime> convertDateTime(String dateTimeString,
                                                            DateTimeFormatter dateTimeFormatter){

        return exceptionHandler(LocalDateTime::parse,
                dateTimeString,dateTimeFormatter);


    }

    private LocalDateTime getLocalDateTimeInIST(LocalDateTime localDateTime){
        ZonedDateTime zonedDateTime=ZonedDateTime.of(localDateTime,
                ZoneId.of("Asia/Calcutta"));

        return localDateTime.plusSeconds(zonedDateTime.getOffset().getTotalSeconds());
    }

    private Either<Exception,YearMonth> convertYearMonth(String dateTimeString,
                                                            DateTimeFormatter dateTimeFormatter){

        return exceptionHandler(YearMonth::parse,
                dateTimeString,dateTimeFormatter);


    }

    private static  <T,R> Either<Exception,R> exceptionHandler(Function<T,R> function,T t){
        try {
           return Either.right(function.apply(t));
        }
        catch (Exception e){
           return Either.left(e);
        }
    }
    private static  <T1,T2,R> Either<Exception,R> exceptionHandler(Function2<T1,T2,R> function,
                                                                       T1 t1,
                                                                       T2 t2){
        try {
            return Either.right(function.apply(t1,t2));
        }
        catch (Exception e){
            return Either.left(e);
        }
    }

}
