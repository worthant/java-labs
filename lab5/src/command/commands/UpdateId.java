package command.commands;

import collection.City.City;
import collectionManagers.CityManager;
import collectionManagers.IdManager;
import command.Command;

/**
 * Command to update a city by its id number.
 */
public class UpdateId extends Command {
    public UpdateId() {
        super(true);
    }

    /**
     * Executes the UpdateId command by updating a city with a specified id number in the collection.
     * The method calls the checkArgument method to validate the input argument before execution.
     */
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            Object obj = IdManager.checkCityById((String) getArgument());
            long id = IdManager.generateId();
            if (obj != null) {
                CityManager.getCityCollection().remove(obj);
                City city = CityManager.getNewCity();
                city.setId(id);
                CityManager.getCityCollection().add(city);
            } else
                System.out.println("Элемента с таким id-номером нет в текущей коллекции!");
        }
    }


    /**
     * Checks if the input argument is valid for the UpdateId command.
     * The input argument should be a non-null string that can be parsed to an integer.
     * @param inputArgument the input argument to be checked
     * @return true if the input argument is valid, false otherwise
     */
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null) {
            System.out.println("Команда update_id имеет аргумент типа данных int!");
            return false;
        } else if (inputArgument instanceof String) {
            try {
                Integer.parseInt((String) inputArgument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда update_id имеет аргумент типа данных int!");
                return false;
            }
        }
        return false;
    }
}
