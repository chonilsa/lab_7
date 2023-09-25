package commands;

import utils.RequestFromClient;

public interface Command {
    RequestFromClient getRequest(boolean fromFile, String ... arg);
}