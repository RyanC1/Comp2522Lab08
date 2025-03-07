import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;


public class CountryLab
{
    //TODO make a function to add a stream of strings into a txt file
    //TODO for now just end each stream with foreach(System.out::println) to see the stream

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
        countries.stream().forEach(System.out::println);
    }
}
