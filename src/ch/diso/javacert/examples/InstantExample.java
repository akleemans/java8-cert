package ch.diso.javacert.examples;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class InstantExample {

    public static void main(String[] args) {
        // "An instant is a point in time."
        Instant now = Instant.now();
        System.out.println("Instant now: " + now);

        // Construct an Instant from a ZonedDateTime
        LocalDateTime localDateTime = LocalDateTime.of(2017, 5, 31, 12, 30);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Zurich"));
        System.out.println("Constructed ZonedDateTime: " + zonedDateTime);
        Instant later = zonedDateTime.toInstant();
        // Notice, no TimeZone on Instant!
        System.out.println("Instant of that ZonedDateTime: " + later);

        // Calculate duration
        Duration duration = Duration.between(now, later);
        System.out.println("Duration between now and: " + duration);

        // Duration - time-based amount of time, format: PT..D..H..S
        // Using ChronoUnit to set unit to add.
        Duration threeHours = Duration.of(3, ChronoUnit.HOURS);
        Duration sevenDays = Duration.of(7, ChronoUnit.DAYS);
        Duration mixedDuration = Duration.of(2, ChronoUnit.DAYS);
        mixedDuration.plus(5, ChronoUnit.HOURS);
        mixedDuration.plus(45, ChronoUnit.SECONDS);

        System.out.println("Three hours: " + threeHours);
        System.out.println("Seven days: " + sevenDays);
        System.out.println("Mixed duration: " + mixedDuration);

        // Period - date-based amount of time, format: P..Y..M..D
        System.out.println("Format : " + Period.of(1, 2, 3)); // P1Y2M3D
        Period mixedPeriod = Period.of(1, 2, 3);
        mixedPeriod.plusMonths(3);
        mixedPeriod.plusDays(24);
        System.out.println("Mixed period " + mixedPeriod);

        // Can add duration to Timeunit
        System.out.println("In three hours it will be: " + Instant.now().plus(threeHours));


        // Fun with Time change - "Daylight Savings".
        // Next time change in Switzerland: 29.10.2017, 03:00 -> 02:00
        LocalDate date = LocalDate.of(2017, Month.OCTOBER, 29);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("Europe/Zurich");
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zone);

        // start with 1:30, then keep adding an hour
        System.out.println(dateTime); // 2017-10-29T01:30+02:00[Europe/Zurich]
        dateTime = dateTime.plusHours(1);
        System.out.println(dateTime); // 2017-10-29T02:30+02:00[Europe/Zurich]
        dateTime = dateTime.plusHours(1);

        // still 2:30 in the morning, but now only +01:00 offset!
        System.out.println(dateTime); // 2017-10-29T02:30+01:00[Europe/Zurich]
        dateTime = dateTime.plusHours(1);
        System.out.println(dateTime); // 2017-10-29T03:30+01:00[Europe/Zurich]


        // by the way, how many days to that time change?
        long days = ChronoUnit.DAYS.between(LocalDateTime.now(), dateTime);
        System.out.println("Days until time change: " + days); // 157 as of today
    }
}
