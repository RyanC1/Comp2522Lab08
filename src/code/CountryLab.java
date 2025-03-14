import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.Comparator;

/**
 * A program to demonstrate different stream and NIO capabilities
 *
 * @author Ted Ip
 * @author Joseph Louwerse
 * @author Ryan Chu
 * @version 1.0
 */
public class CountryLab
{
    final static int START = 0;

    /*
     * Reusable method to get non-null stream
     *
     * @param listToBeFiltered the string list to be converted
     */
    private static Stream<String> filteredStream(final List<String> listToBeFiltered)
    {
        return listToBeFiltered.stream()
                               .filter(Objects::nonNull)
                               .filter(p -> !p.isBlank());
    }

    /**
     * Drives the program
     * @param args unused
     */
    public static void main(final String[] args)
    {
        final Path         countriesPath;
        final Path         matchesPath;
        final Path         dataPath;
        final List<String> countries;
        final List<String> streamResults;


        countriesPath = Paths.get("src",
                                  "res",
                                  "week8countries.txt");
        matchesPath   = Paths.get("matches");
        dataPath      = Paths.get("matches",
                                  "data.txt");
        streamResults = new ArrayList<>();
        countries     = new ArrayList<>();


        try
        {
            if(Files.notExists(countriesPath))
            {
                System.out.println("Error. 'clues.txt' not found; exiting...");
                return;
            }
            countries.addAll(Files.readAllLines(countriesPath));
        }
        catch(final IOException e)
        {
            System.out.println("Error reading countries file : " +
                               e.getMessage());
            return;
        }


        /*
         * 1. Long Country Names: Write "Country names longer than 10 characters:" followed by all country names with more than 10 characters (always one country per line).
         */
        streamResults.add("Country names longer than 10 characters:");
        streamResults.addAll(filteredStream(countries)
                                     .filter(s -> s.length() > 10)
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 2. Short Country Names: Write "Country names shorter than 5 characters:" followed by all country names with fewer than 5 characters.
         */
        streamResults.add("Country names shorter than 5 characters:");
        streamResults.addAll(filteredStream(countries)
                                     .filter(s -> s.length() < 5)
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 3. Starting with "A": List all country names that start with the letter "A".
         */
        streamResults.add("Country names starting with 'A':");
        streamResults.addAll(filteredStream(countries)
                                     .filter(s -> s.startsWith("A"))
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 4. Ending with "land": List all country names that end with "land".
         */
        streamResults.add("Country names that end with the \"land\"");
        streamResults.addAll(filteredStream(countries)
                                     .filter(s -> s.endsWith("land"))
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 5. Containing "United": List all countries containing the word "United".
         */
        streamResults.add("Countries containing the word \"United\"");
        streamResults.addAll(filteredStream(countries)
                                     .filter(s -> s.contains("United"))
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 6. Sorted Names (Ascending): List all country names in alphabetical order.
         */
        streamResults.add("Country names in ascending order:");
        streamResults.addAll(filteredStream(countries)
                                     .sorted()
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 7. Sorted Names (Descending): List all country names in reverse alphabetical order.
         */
        streamResults.add("Country names in descending order:");
        streamResults.addAll(filteredStream(countries)
                                     .sorted(Comparator.reverseOrder())
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 8. Unique First Letters: List the unique first letters of all country names.
         */
        streamResults.add("List the unique first letters of all country names:");
        streamResults.addAll(filteredStream(countries)
                                     .map(country -> String.valueOf(country.charAt(START))) // Extract first letter
                                     .distinct() // Remove duplicates while keeping order
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 9. Count of Countries: Write the total count of country names.
         */
        streamResults.add("Total count of country names:");
        streamResults.add(String.valueOf(filteredStream(countries).count()));
        streamResults.add("\n---------------------------------------\n");

        /*
         * 10. Longest Country Name: Write the longest country name.
         */
        streamResults.add("Longest country name:");
        streamResults.add(filteredStream(countries)
                                  .max(Comparator.comparingInt(String::length))
                                  .orElse("No longest country name"));
        streamResults.add("\n---------------------------------------\n");

        /*
         * 11. Shortest Country Name: Write the shortest country name.
         */
        streamResults.add("Shortest country name:");
        streamResults.add(filteredStream(countries)
                                  .min(Comparator.comparingInt(String::length))
                                  .orElse("No shortest country name"));
        streamResults.add("\n---------------------------------------\n");

        /*
         * 12. Names in Uppercase: Write all country names converted to uppercase.
         */
        streamResults.add("Countries in uppercase:");
        streamResults.addAll(filteredStream(countries)
                                     .map(String::toUpperCase)
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 13. Countries with More Than One Word: List all country names with more than one word.
         */
        streamResults.add("Multi-word countries:");
        streamResults.addAll(filteredStream(countries)
                                     .filter(x -> x.matches(".*\\S+(\\s|-)\\S+.*"))
                                     .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 14. Country Names to Character Count: Map each country name to its character count,
         * writing each name and count as "Country: X characters".
         */
        streamResults.add("Countries to char count:");
        streamResults.addAll(filteredStream(countries).map(x -> x +
                                                                ": " +
                                                                x.length() +
                                                                " characters")
                                                      .toList());
        streamResults.add("\n---------------------------------------\n");

        /*
         * 15. Any Name Starts with "Z": Write "true" if any country name starts with "Z"; otherwise,
         *   "false".
         */
        streamResults.add("Does a country start with \"Z\":");
        streamResults.add(String.valueOf(
                filteredStream(countries)
                        .anyMatch(x -> x.matches("^Z.*"))));
        streamResults.add("\n---------------------------------------\n");

        /*
         * 16. All Names Longer Than 3: Write "true" if all country names are longer than 3 characters;
         * otherwise, "false".
         */
        streamResults.add("Are all countries longer than 3 characters:");
        streamResults.add(String.valueOf(
                filteredStream(countries)
                        .allMatch(x -> x.length() >
                                       3)));


        try
        {
            if(Files.notExists(matchesPath))
            {
                Files.createDirectory(matchesPath);
            }

            Files.write(dataPath,
                        streamResults,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

        }
        catch(final IOException e)
        {
            System.out.println("Something went wrong when creating data.txt:" +
                               e.getMessage());
        }

    }
}
