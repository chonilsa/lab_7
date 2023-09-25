package commands;

import data.Route;
import input.ElementInput;
import utils.ObjectFromFileCreation;
import utils.RequestFromClient;

public class Add implements Command {
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        RequestFromClient request = new RequestFromClient();

        if (!fromFile) {
            ElementInput element = new ElementInput();
            request.setObject(element.resultElement());
        } else {
            ObjectFromFileCreation creation = new ObjectFromFileCreation();
            Route object = creation.createObject(arg);
            if (object == null) return null;
            request.setObject(object);
            return request;
        }
        return request;
    }
}