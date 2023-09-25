package app;

import commands.*;
import utils.RequestFromClient;

import java.util.HashMap;

public class CommandList {
    public final HashMap<String, Command> commands = new HashMap<>();

    public CommandList() {
        commands.put("add", new Add());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("update", new Update());
        commands.put("remove_by_id", new RemoveByID());
        commands.put("clear", new Clear());
        commands.put("insert_at_index", new InsertAtIndex());
        commands.put("print_ascending", new PrintAscending());
        commands.put("print_descending", new PrintDescending());
        commands.put("count_less_than_distance", new CountLessThanDistance());
        commands.put("reorder", new Reorder());
        commands.put("history", new History());
        commands.put("execute_script", new ExecuteScript(this));
        commands.put("help", new Help());
    }

    public RequestFromClient requestFormation(String command, boolean fromFile, String ... arg) {
        if (commands.containsKey(command)) {
            RequestFromClient request = commands.get(command).getRequest(fromFile, arg);
            if (request != null) {
                request.setCommand(command);
            }
            return request;
        }
        if (fromFile) {
            System.out.println("Команды " + command + " не существует.");
        }
        else {
            System.out.println("Запрос не может быть сформирован. Проверьте вводимые данные");
        }
        return null;
    }
}