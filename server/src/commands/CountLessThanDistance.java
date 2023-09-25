package commands;

import app.CollectionManager;
import app.Server;
import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public class CountLessThanDistance implements Command {
    private final CollectionManager collectionManager;

    public CountLessThanDistance(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setMessage(collectionManager.countLessThanDistance(Integer.parseInt(args)));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "count_less_than_distance - выводит кол - во элементов, distance которых меньше заданного";
    }

}
