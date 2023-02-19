package collectionManagers;

import collection.City.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.TreeSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVManager implements Managers{
    @Override
    public TreeSet<City> readFromFile(String pathToDataFile) {
        TreeSet<City> cities = new TreeSet<>();

        try (FileReader fileReader = new FileReader(pathToDataFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());) {

            for (CSVRecord fields : csvParser) {
                long id = Integer.parseInt(fields.get("id"));
                String name = fields.get("name");
                Integer x = Integer.parseInt(fields.get("x"));
                double y = Double.parseDouble(fields.get("y"));
                Coordinates coordinates = new Coordinates(x, y);
                java.util.Date creationDate = java.sql.Date.valueOf(LocalDate.now());
                Integer area = Integer.parseInt(fields.get("area"));
                int population = Integer.parseInt(fields.get("population"));
                Double metersAboveSeaLevel = null;
                if (fields.get("metersAboveSeaLevel") != null && !fields.get("metersAboveSeaLevel").isEmpty()) {
                    metersAboveSeaLevel = Double.parseDouble(fields.get("metersAboveSeaLevel"));
                }
                Climate climate = Climate.valueOf(fields.get("climate"));
                Government government = Government.valueOf(fields.get("government"));
                StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(fields.get("standardOfLiving"));

                /** TODO: create validators */
//                    if (!row[11].isEmpty()) {
//                        Human governor = new Human(row[11].trim());
//                    }
                Human governor = new Human(fields.get("governorName"));
                cities.add(new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor));
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println("Error reading city data file: " + e.getMessage());
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }

        return cities;
    }
}

