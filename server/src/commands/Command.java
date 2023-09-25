package commands;


import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

public interface Command {
    void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response);
}
