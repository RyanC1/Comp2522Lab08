import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


public class CountryLab
{
    private static Stream<String> getStream(final List<String> list) {
        return list.stream();
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

        final List<String> countries;

        try
        {
            countries = Files.readAllLines(countriesPath);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }


    }
}
