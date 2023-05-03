package commandManager.commands;

import models.City;
import models.handlers.CityHandler;
import models.handlers.CollectionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import responses.CommandStatusResponse;

import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Prints the population of all cities
 *  in descending order.
 *
 * @author worthant
 * @since 1.0
 */
public class PrintDescendingCommand implements BaseCommand {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab6.commands.printFDA");
    private CommandStatusResponse response;

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getDescr() {
        return "Prints the elements of the collection in descending order.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<TreeSet<City>, City> collectionHandler = CityHandler.getInstance();
        TreeSet<City> cityCollection = collectionHandler.getCollection();

        if (cityCollection.isEmpty()) {
            response = CommandStatusResponse.ofString("There's nothing to show...");
        } else {
            String output = cityCollection.descendingSet().stream()
                    .map(city -> String.valueOf(city.getMetersAboveSeaLevel()))
                    .collect(Collectors.joining("\n"));
            response = CommandStatusResponse.ofString(output);
        }

        logger.info(response.getResponse());
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
