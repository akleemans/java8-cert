package ch.diso.javacert.examples;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

    public static void main(String[] args) {
        // source for addresses: https://blogs.msdn.microsoft.com/testing123/2009/02/06/email-address-test-cases/
        HashMap<String, Boolean> addresses = new HashMap<>();
        addresses.put("email@domain.com", true);
        addresses.put("firstname.lastname@domain.com", true);
        addresses.put("email@subdomain.domain.com", true);
        addresses.put("email@123.123.123.123", true);
        addresses.put("email@0.1.2.3", true);
        addresses.put("\"email\"@domain.com", true);
        addresses.put("1234567890@domain.com", true);
        addresses.put("email@domain-one.com", true);
        addresses.put("_______@domain.com", true);
        addresses.put("email@domain.name", true);

        addresses.put("plainaddress", false);
        addresses.put("#@%^%#$@#$@#.com", false);
        addresses.put("@domain.com", false);
        addresses.put("email.domain.com", false);
        addresses.put("email@domain@domain.com", false);
        addresses.put(".email@domain.com", false);
        addresses.put("email.@domain.com", false);
        addresses.put("email..email@domain.com", false);
        addresses.put("あいうえお@domain.com", false);
        addresses.put("email@domain", false);
        addresses.put("email@domain..com", false);

        // prepare a email address matcher
        // source: http://stackoverflow.com/a/742588/811708
        Pattern simpleEmailPattern = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

        addresses.keySet().forEach(email -> {
            Matcher simpleEmailMatcher = simpleEmailPattern.matcher(email);
            // handle all four cases
            if (simpleEmailMatcher.matches()) {
                if (addresses.get(email)) {
                    System.out.println("Found valid email: " + email);
                } else {
                    System.out.println("Match found, but false positive: " + email);
                }
            } else {
                if (addresses.get(email)) {
                    System.out.println("No match found, but valid: " + email);
                } else {
                    System.out.println("Found invalid email: " + email);
                }
            }
        });

        // now find IP addresses
        System.out.println("\nNow for some IPs.");
        // match any string that consists of four 1-3 digit numbers, separated by a dot
        Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        String allAddresses = addresses
                .keySet()
                .stream()
                .reduce(String::concat)
                .orElse("");

        Matcher ipMatcher = ipPattern.matcher(allAddresses);
        // check if match found
        while (ipMatcher.find()) {
            System.out.println("Found IP address: " + ipMatcher.group());
        }

    }
}
