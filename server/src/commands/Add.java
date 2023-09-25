package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import lombok.AllArgsConstructor;
import utils.ResponseFromServer;
import utils.UserInfo;

public class Add implements Command {
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        if(!collectionManager.add(object, userInfo.getUser())) {
            response.setMessage("Элемент не добавлен");
        }
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}
