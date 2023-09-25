package commands;

import data.Route;
import input.ElementInput;
import utils.ObjectFromFileCreation;
import validator.CheckCorrectData;
import utils.RequestFromClient;

import java.util.Arrays;

public class Update implements Command {
    @Override
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        RequestFromClient request = new RequestFromClient();
        CheckCorrectData check = new CheckCorrectData();

        if (check.isNumber(arg[0])) {
            request.setArg(arg[0]);

            if (fromFile) {
                ObjectFromFileCreation creation = new ObjectFromFileCreation();
                Route object = creation.createObject(Arrays.copyOfRange(arg, 1, arg.length));
                if (object == null) return null;
                request.setObject(object);
            } else {
                ElementInput element = new ElementInput();
                request.setObject(element.resultElement());
            }

            return request;
        }

        return null;
    }
}