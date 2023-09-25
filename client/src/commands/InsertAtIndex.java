package commands;

import validator.CheckCorrectData;
import input.ElementInput;
import utils.RequestFromClient;

public class InsertAtIndex implements Command{

    @Override
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        RequestFromClient request = new RequestFromClient();
        CheckCorrectData check = new CheckCorrectData();
        if (check.isNumber(arg[0])) {
            request.setArg(arg[0]);
            ElementInput element = new ElementInput();
            request.setObject(element.resultElement());
            return request;
        }
        return null;
    }
}
