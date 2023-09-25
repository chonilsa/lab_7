package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class Reorder implements Command {
    private final CollectionManager collectionManager;

    public Reorder(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        collectionManager.reorder();
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "reorder - отсортировать в порядке обратном нынешнему";
    }
}
