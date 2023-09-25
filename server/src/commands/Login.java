package commands;

import app.Server;
import data.Route;
import database.UserExist;
import utils.ResponseFromServer;
import utils.UserInfo;

public class Login implements Command {

    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        response.setMessage(UserExist.login(userInfo.getUser(), userInfo.getPassword()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "login - вход в приложение";
    }
}