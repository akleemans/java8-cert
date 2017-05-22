package ch.diso.javacert.examples;

import java.time.*;
import java.util.Arrays;

public class LocalDateTimeExample {

    public static void main(String[] args) {
        // print all four different kinds of LocalDate/Time
        Arrays.asList(
                LocalDate.now(),
                LocalTime.now(),
                LocalDateTime.now(),
                ZonedDateTime.now()).stream()
                .forEach(System.out::println);

        // LocalDate etc. may never be constructed directly!
        //LocalDate localDate = new LocalDate(); - will not compile: "LocalDate(...) has private access"

        // daily Scrum Standup at 9:15
        LocalTime dailyStandup = LocalTime.of(9, 15);
        // tomorrow (as seen from today, 18.5.17)
        LocalDate tomorrow = LocalDate.of(2017, Month.MAY, 19);

        // Constructing a new ZonedDateTime.
        ZoneId bern = ZoneId.of("Europe/Zurich");
        ZonedDateTime bernStandup = ZonedDateTime.of(tomorrow, dailyStandup, bern);
        System.out.println("Scrum standup meeting tomorrow in Bern: " + bernStandup); // 2017-05-19T09:15+02:00

        // Find other timezones
        String europeanTimezones = ZoneId.getAvailableZoneIds().stream()
                .filter(s -> s.contains("Europe"))
                .limit(10)
                .reduce("", (s, t) -> s + ", " + t);
        System.out.print("Some timezones in Europe: " + europeanTimezones);

        // When does our team in Shanghai have to be ready?
        ZoneId shanghai = ZoneId.of("Asia/Shanghai");
        ZonedDateTime shanghaiStandup = bernStandup.withZoneSameInstant(shanghai);
        System.out.println("\nScrum standup meeting tomorrow in Shanghai: " + shanghaiStandup); // 15:15
    }
}
