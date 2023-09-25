package commands;

import app.*;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class PrintAscending implements Command {
    private final CollectionManager collectionManager;

    public PrintAscending(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setObject(collectionManager.printAscending());
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "print_ascending - выводит элементы в порядке возрастания";
    }

}
