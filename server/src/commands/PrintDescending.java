package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class PrintDescending implements Command {
    private final CollectionManager collectionManager;

    public PrintDescending(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * @param args   arguments
     * @param object
     */
    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setObject(collectionManager.printDescending());
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "print_descending - выводит элементы в порядке убывания";
    }
}
