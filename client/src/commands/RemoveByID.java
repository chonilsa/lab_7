package commands;

import validator.CheckCorrectData;
import utils.RequestFromClient;

public class RemoveByID implements Command {
    @Override
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        RequestFromClient request = new RequestFromClient();
        CheckCorrectData check = new CheckCorrectData();
        if (check.isNumber(arg[0])) {
            request.setArg(arg[0]);
            return request;
        }
        return null;
    }
}