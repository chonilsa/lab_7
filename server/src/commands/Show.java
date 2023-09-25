package commands;

import app.*;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * Show class
 */
public class Show implements Command {

    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setObject(collectionManager.show());
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "show - выводит все элементы коллекции в строковом представлении";
    }
}
