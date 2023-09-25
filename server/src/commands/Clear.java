package commands;

import app.*;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * Clear class
 */
public class Clear implements Command {

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        boolean result = collectionManager.clear(userInfo.getUser());
        if(!result) {
            response.setMessage("Коллекция не очищена");
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "clear - очистить коллекцию";
    }
}
