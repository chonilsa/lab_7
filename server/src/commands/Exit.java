package commands;

import data.Route;
import utils.ResponseFromServer;
import utils.UserInfo;

/**
 * Exit class
 */
public class Exit implements Command {

    /**
     * Exit the program
     *
     * @param args   arguments
     * @param object
     */
    @Override
    public void execute(String args, Route object, UserInfo userInfo, ResponseFromServer response) {
        System.exit(0);
    }

    @Override
    public String toString() {
        return "exit - завершение программы";
    }
}
