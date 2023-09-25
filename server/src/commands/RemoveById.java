package commands;

import app.*;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * RemoveById class
 */
public class RemoveById implements Command {

    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setMessage(collectionManager.removeById(Long.parseLong(args), userInfo.getUser()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "remove_by_id - удаляет элемент из коллекции по его id";
    }
}
