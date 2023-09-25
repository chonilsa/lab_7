package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class InsertAtIndex implements Command {
    private final CollectionManager collectionManager;

    public InsertAtIndex(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        if(!collectionManager.insertAtIndex(object, Integer.parseInt(args), userInfo.getUser())) {
            response.setMessage("Элемент не добавлен. Вероятно, размер коллекции меньше, чем заданный индекс");
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "insert_at_index - вставляет элемент в заданный индекс";
    }
}
