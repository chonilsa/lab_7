package app;

import java.util.ArrayList;
import java.util.LinkedList;

import commands.Authorization;
import utils.RequestFromClient;
import utils.ResponseFromServer;
import utils.UserInfo;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final String STOP_PROGRAM = "exit";
    public static UserInfo userInfo;

    public static void main(String[] args) {
        CommandList commandsList = new CommandList();
        Client client = new Client();
        client.setClient(client);
        Authorization authorization = new Authorization(client);
        client.connect();

        try {
            Scanner input = new Scanner(System.in);
            authorization.start();
            userInfo = new UserInfo(authorization.getUser(), authorization.getPassword());
            while (true) {
                System.out.print("Введите команду: ");
                String command = input.nextLine();

                String arg = "";
                String[] commandCut = command.trim().split("\\s+");
                String commandName = commandCut[0];

                if (commandCut.length > 1) {
                    arg = commandCut[1];
                }

                if (commandName.equals(STOP_PROGRAM)) {
                    client.close();
                    return;
                }

                RequestFromClient request = commandsList.requestFormation(commandName, false, arg);

                if(request == null) continue;
                request.setUserInfo(userInfo);

                if (client.isConnected()) {

                    client.sendRequest(request);

                    ResponseFromServer response = client.getResponse();

                    if (response == null) continue;
                    System.out.println(response.getCommand() + " " + response.getMessage());
                    if (response.getObject() == null) continue;

                    ArrayList<?> object = response.getObject();
                    for (Object o : object) {
                        System.out.println(o);
                    }
                } else {
                    client.reconnect();
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Завершение программы.");
        }
    }

}