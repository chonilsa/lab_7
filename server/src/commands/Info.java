package commands;

import app.*;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * Info class
 */
public class Info implements Command {

    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Prints the collection info
     *
     * @param args   arguments
     * @param object
     */
    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setMessage(collectionManager.info());
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "info - выводит информацию о коллекции (тип, дата инициализации и тд)";
    }
}
