package commands;

import app.CollectionManager;
import app.CommandsList;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class History implements Command {
    private final CollectionManager collectionManager;

    public History(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Clear existing collection
     *
     * @param args   arguments for command
     * @param object
     */
    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setObject(collectionManager.history(userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "history - показывает последние 5 команд пользователя";
    }
}
