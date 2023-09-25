package commands;

import utils.RequestFromClient;

public class PrintAscending implements Command {
    @Override
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        RequestFromClient request = new RequestFromClient();
        return request;
    }
}