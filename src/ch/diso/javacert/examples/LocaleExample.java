package ch.diso.javacert.examples;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LocaleExample {

    public static void main(String[] args) {
        // What's de default on this machine?
        System.out.println("Default: " + Locale.getDefault()); // de_CH

        // We can override the default:
        Locale france = new Locale("fr", "FR");
        Locale.setDefault(france);
        System.out.println("New default: " + france); // fr_FR

        // We can also just specify the language...
        Locale francais = new Locale("fr");
        System.out.println("Seulement francais: " + francais); // fr

        // ...or use a builder to build it
        Locale nederlands = new Locale.Builder().setLanguage("nl").setRegion("NL").build();
        System.out.println("Nederlands: " + nederlands); // nl_NL

        // Load a ResourceBundle, according to some rules if not found directly.
        ResourceBundle rb = ResourceBundle.getBundle("strings", nederlands);

        // Say something in netherlands.
        // The interesting part is that for "hello", which exists in both strings_nl (only language)
        // and the more specific strings_nl_NL (language + region), is taken from the more specific
        // property file, strings_nl_NL.
        System.out.println("Nederlands: " + rb.getString("hello")); // Hoi!

        // We can create a Property map based on a ResourceBundle
        Properties props = new Properties();
        rb.keySet().forEach(k -> props.put(k, rb.getString(k)));

        // Because "whatsup" does not exists in strings_nl_NL, Java falls back
        // on strings_nl, where an entry should exist. If not, we take a default.
        String whatsup = props.getProperty("whatsup", "What's up?");

        // We can be sure that 'whatsup' contains something, hopefully
        // the local translation, but at least the default.
        System.out.println("key 'whatsup' got translated to: " + whatsup);
    }
}