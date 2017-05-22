package ch.diso.javacert.examples;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class FormatExample {

    public static void main(String[] args) {
        // Use NumberFormat to print numbers, currencies & percentages
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        System.out.println(numberFormat.format(32_000.5f)); // will print 32'000.5

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        System.out.println(currencyFormat.format(1_000.75f)); // will print SFr. 1'000.75

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        System.out.println(percentFormat.format(0.67f)); // will print 67 %

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        System.out.println(integerFormat.format(12_345)); // will print 12'345

        // .format can be called from both sides
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        dateTimeFormatter.format(localDateTime);
        localDateTime.format(dateTimeFormatter);

        // Parse & format dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("LocalDateTime.now(), formatted as hh:mm: " + formatter.format(LocalDateTime.now()));
        System.out.println("Parse LocalTime 11:22 with formatter: " + LocalTime.parse("11:22", formatter));

        // Using predefined formatters: ISO_LOCAL_DATE_TIME, FormatStyle.SHORT, FormatStyle.MEDIUM
        System.out.println("Now as ISO_LOCAL_DATE_TIME: " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));

        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println("Now as FormatStyle.SHORT: " + formatter2.format(LocalDateTime.now()));

        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("Now as FormatStyle.MEDIUM: " + formatter3.format(LocalDateTime.now()));
    }
}
