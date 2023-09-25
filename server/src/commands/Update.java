package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * Update class
 */
public class Update implements Command {
    private final CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        final Long id = Long.parseLong(args);
        response.setMessage(collectionManager.updateById(id, object, userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "update - обновить значение элемента коллекции, id которого совпадает с исходным";
    }
}
