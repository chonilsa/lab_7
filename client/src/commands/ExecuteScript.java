package commands;

import java.io.IOException;
import app.Client;

import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashSet;

import app.Main;
import validator.CheckCorrectPath;
import app.CommandList;
import utils.RequestFromClient;
import utils.ResponseFromServer;

public class ExecuteScript implements Command {
    private final CommandList commandsList;
    private final CheckCorrectPath checkPathCorrect;
    private HashSet<Path> existingPathList;

    public ExecuteScript(final CommandList commandsList) {
        this.checkPathCorrect = new CheckCorrectPath();
        this.existingPathList = new HashSet<>();
        this.commandsList = commandsList;
    }

    @Override
    public RequestFromClient getRequest(boolean fromFile, String ... arg) {
        try {
            if (!checkPathCorrect.checkPath(arg[0])) {
                return null;
            }

            Path path = Paths.get(arg[0].trim());
            Path base = Paths.get("");
            Path absolutePath = base.resolve(path).toAbsolutePath();

            if (existingPathList.contains(absolutePath)) {
                System.out.println("Вызов скрипта зациклен");
                return null;
            }

            existingPathList.add(absolutePath);
            Scanner inputFromFile = new Scanner(absolutePath);
            while (inputFromFile.hasNext()) {
                String command = inputFromFile.nextLine();

                String[] commandCut = command.trim().split("\\s+");
                String commandName = commandCut[0];

                RequestFromClient request;

                if (commandCut.length == 1) {
                    request = commandsList.requestFormation(commandName, true, "");
                } else if (commandCut.length == 2) {
                    request = commandsList.requestFormation(commandName, true, arg[1]);
                } else {
                    request = commandsList.requestFormation(commandName, true, Arrays.copyOfRange(commandCut, 1, commandCut.length));
                }



                if (Client.getClient().isConnected()) {
                    if (request == null) continue;
                    request.setUserInfo(Main.userInfo);

                    Client.getClient().sendRequest(request);

                    ResponseFromServer response = Client.getClient().getResponse();
                    System.out.println(response.getCommand() + response.getMessage());

                    if (response.getObject() == null) continue;

                    for (Object o: response.getObject()) {
                        System.out.println(o);
                    }
                }
            }
            existingPathList.clear();
        }
        catch (IOException e) {
            System.out.println("Нет доступа к файлу");
        }
        return null;
    }

    @Override
    public String toString() {
        return "execute_script - исполняет команды из файла";
    }
}