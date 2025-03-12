import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A program to demonstrate different stream functions.
 * @author Ted, Joseph and Ryan
 * @version 1.0
 */


public class  CountryLab
{
    //TODO make a function to add a stream of strings into a txt file
    //TODO for now just end each stream with foreach(System.out::println) to see the stream

    /**
     * Reusable method to get non-null stream
     * */
    private static Stream<String> filteredStream (final List<String> listToBeFiltered)
    {
        return listToBeFiltered.stream().filter(p-> p != null && !p.isBlank());
    }


    public static void main(final String[] args) throws
                                                 IOException
    {
        Path countriesPath = Paths.get("src",
                                   "res",
                                   "week8countries.txt");

        Path matches = Paths.get("src",
                                 "res",
                                 "matches");

        Path data = Paths.get("src",
                              "res",
                              "matches",
                              "data.txt");

        if(Files.notExists(matches))
        {
            Files.createDirectory(matches);
        }

        if(Files.notExists(data))
        {
            Files.createFile(data);
        }


        List<String> countries = new ArrayList<>();

        try
        {
            countries = Files.readAllLines(countriesPath);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        //this is an example of what we are doing
        //countries.stream().forEach(System.out::println);

        /*
         * 1. Long Country Names: Write "Country names longer than 10 characters:" followed by all country names with more than 10 characters (always one country per line).
         */
        System.out.println("\n1. Country names longer than 10 characters:");
        final List<String> filteredStream1 = filteredStream(countries)
                .filter(s -> s.length() > 10)
                .toList();
        filteredStream1.forEach(System.out::println);
        /*
         * 2. Short Country Names: Write "Country names shorter than 5 characters:" followed by all country names with fewer than 5 characters. * */
        System.out.println("\n2. Country names shorter than 5 characters:");
        final List<String> filteredStream2 = filteredStream(countries)
                .filter(s -> s.length() < 5)
                .toList();
        filteredStream2.forEach(System.out::println);
        /*
         * 3. Starting with "A": List all country names that start with the letter "A".
         */
        System.out.println("\n3. Country names that start with the letter \"A\"");
        final List<String> filteredStream3 = filteredStream(countries)
                .filter(s -> s.startsWith("A"))
                .toList();
        filteredStream3.forEach(System.out::println);
        /*
         * 4. Ending with "land": List all country names that end with "land".
         */
        System.out.println("\n4. Country names that end with the \"land\"");
        final List<String> filteredStream4 = filteredStream(countries)
                .filter(s -> s.endsWith("land"))
                .toList();
        filteredStream4.forEach(System.out::println);
        /*
         * 5. Containing "United": List all countries containing the word "United".
         */
        System.out.println("\n5. Countries containing the word \"United\"");
        final List<String> filteredStream5 = filteredStream(countries)
                .filter(s -> s.contains("United"))
                .toList();
        filteredStream5.forEach(System.out::println);

        /*
         * 6. Sorted Names (Ascending): List all country names in alphabetical order.
         */
        System.out.println("\n6. Country names in ascending order:");
        final List<String> filteredStream6 = filteredStream(countries)
                .sorted()
                .toList();
        filteredStream6.forEach(System.out::println);

        /*
         * 7. Sorted Names (Descending): List all country names in reverse alphabetical order.
         */
        System.out.println("\n7. Country names in descending order:");
        final List<String> filteredStream7 = filteredStream(countries)
                .sorted(Comparator.reverseOrder())
                .toList();
        filteredStream7.forEach(System.out::println);

        /*
         * 8. Unique First Letters: List the unique first letters of all country names.
         */
        final List<Character> firstCharList;

        System.out.println("\n8. List the unique first letters of all country names:");
        firstCharList = filteredStream(countries)
                .map(country -> country.charAt(0)) // Extract first letter
                .distinct() // Remove duplicates while keeping order
                .collect(Collectors.toList());
        firstCharList.forEach(System.out::println);

        /*
         * 9. Count of Countries: Write the total count of country names.
         */
        System.out.println("\n9. Total count of country names:");
        final long count = filteredStream(countries).count();
        System.out.println(count);

        /*
         * 10. Longest Country Name: Write the longest country name.
         */
        System.out.println("\n10. Longest country name:");
        Optional<String> longest = filteredStream(countries)
                .max(Comparator.comparingInt(String::length));

        System.out.println(longest);

        /*
         * 11. Shortest Country Name: Write the shortest country name.
         */
        System.out.println("\n11. Shortest country name:");
        Optional<String> shortest = filteredStream(countries)
                .min(Comparator.comparingInt(String::length));

        System.out.println(shortest);

        /*
         * 12. Names in Uppercase: Write all country names converted to uppercase.
         */
        System.out.println("\n12. Countries in uppercase:");
        filteredStream(countries)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        /*
         * 13. Countries with More Than One Word: List all country names with more than one word.
         */
        System.out.println("\n13. Multi-word countries:");
        filteredStream(countries)
                .filter(x->x.matches(".*\\S+(\\s|-)\\S+.*"))
                .forEach(System.out::println);

        /*
         * 14. Country Names to Character Count: Map each country name to its character count,
         * writing each name and count as "Country: X characters".
         */
        System.out.println("\n14. Countries to char count:");
        filteredStream(countries)
                .map(x-> x + ": " + x.length() + " characters")
                .forEach(System.out::println);

        /*
         * 15. Any Name Starts with "Z": Write "true" if any country name starts with "Z"; otherwise,
         *   "false".
         */
        System.out.println("\n15. Does a country start with \"Z\":");
        System.out.println(
                filteredStream(countries)
                .anyMatch(x->x.matches("^Z.*")));

        /*
         * 16. All Names Longer Than 3: Write "true" if all country names are longer than 3 characters;
         * otherwise, "false".
         */
        System.out.println("\n16. Are all countries longer than 3 characters:");
        System.out.println(
                filteredStream(countries)
                        .allMatch(x->x.length() > 3));


    }
}
