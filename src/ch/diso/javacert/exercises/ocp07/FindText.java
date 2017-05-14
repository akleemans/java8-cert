package ch.diso.javacert.exercises.ocp07;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {
    private String fileName = "src/ch/diso/javacert/exercises/ocp07/gettys.html";

    public static void main(String[] args) {
        FindText find = new FindText();
        find.run();
    }

    private void run() {

        // for printing the current directory of the program
        File here = new File(".");
        System.out.println(here.getAbsolutePath());

        Arrays.asList(Pattern.compile("<h4>"), // alle Zeilen mit "<h4>
                Pattern.compile("\\bto\\b"), // Zeilen mit dem Wort "to"
                Pattern.compile("class=\"line\""), // alle Zeilen mit 'class="line"'
                Pattern.compile("\\{.*?\\}"), // alle Zeilen, welche mit "{" und "}" gruppiert sind
                Pattern.compile("^<[p|d]") // alle Zeilen, welche mit "<p" oder "<d" beginnen
        ).forEach(pattern -> {
                    System.out.println("Trying pattern: " + pattern);
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line = "";
                        int c = 1;
                        int matches = 0;
                        while ((line = reader.readLine()) != null) {
                            // Generate a matcher based on Pattern
                            Matcher matcher = pattern.matcher(line);

                            // Search for text
                            if (matcher.find()) {
                                matches++;
                                // Print results
                                System.out.println("Match found: " + line);
                            }
                            c++;
                        }
                        System.out.println(matches + " result(s) in " + c + " lines.\n");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}