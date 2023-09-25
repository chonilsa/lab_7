package commands;

import app.Server;
import data.Route;
import database.UserExist;
import utils.ResponseFromServer;
import utils.UserInfo;


public class Register implements Command {
    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        System.out.println("In register");
        response.setMessage(UserExist.register(userInfo.getUser(), userInfo.getPassword()));
        Server.sendResponse(response, response.getKey());
    }

    @Override
    public String toString() {
        return "register - регистрация";
    }
}