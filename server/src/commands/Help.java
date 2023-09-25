package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Help class
 */
public class Help implements Command {
    private final HashMap<String, Command> commands;
    private CollectionManager collectionManager;


    public Help(HashMap<String, Command> commands, CollectionManager collectionManager) {
        this.commands = commands;
        this.collectionManager = collectionManager;
    }


    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        ArrayList<String> descriptionCommands = new ArrayList<>();
        for (Command description: commands.values()) {
            descriptionCommands.add(description.toString());
        }
        response.setObject(descriptionCommands);
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "help - выводит справку по доступным командам";
    }
}
