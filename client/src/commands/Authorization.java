package commands;

import app.Client;
import lombok.Data;
import utils.RequestFromClient;
import utils.ResponseFromServer;
import utils.UserInfo;

import java.util.NoSuchElementException;
import java.util.Scanner;

@Data
public class Authorization {
    private String user;
    private String password;
    private final Client client;

    public Authorization(Client client) {
        this.client = client;
    }
    public void start() {
        Scanner input = new Scanner(System.in);
        try {
            while (true) {
                String command;
                while (true) {
                    System.out.print("Введите register, если у вас нет профиля, иначе login: ");
                    command = input.nextLine().trim();
                    if (command.equals("register") || command.equals("login")) break;
                }
                String login;
                String password;
                while(true) {
                    System.out.print("Введите логин: ");
                    login = input.nextLine().trim();
                    if (!login.equals("")) break;
                }

                while(true) {
                    System.out.print("Введите пароль: ");
                    password = input.nextLine().trim();
                    if (!password.equals("")) break;
                }

                RequestFromClient request = new RequestFromClient();
                request.setCommand(command);
                request.setUserInfo(new UserInfo(login, password));


                if(client.isConnected()) {
                    client.sendRequest(request);

                    ResponseFromServer response = client.getResponse();

                    if (response == null) continue;
                    System.out.println(response.getMessage());

                    if (response.getMessage().equals("Успешно")) {
                        this.user = login;
                        this.password = password;
                        break;
                    }
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Завершение программы.");
        }
    }

    public String getPassword() {
        return null;
    }

}