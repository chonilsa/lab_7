package app;

import commands.*;
import utils.RequestFromClient;
import utils.ResponseFromServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.*;


public class CommandsList {
    public final HashMap<String, Command> commands = new HashMap<>();
    private static HashMap<String, LinkedList<String>> history = new HashMap<>();
    ExecutorService executor = Executors.newFixedThreadPool(10);

    public CommandsList(CollectionManager collectionManager) {
        commands.put("add", new Add(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("update", new Update(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("history", new History(collectionManager));
        commands.put("print_ascending", new PrintAscending(collectionManager));
        commands.put("reorder", new Reorder(collectionManager));
        commands.put("count_less_than_distance", new CountLessThanDistance(collectionManager));
        commands.put("print_descending", new PrintDescending(collectionManager));
        commands.put("insert_at_index", new InsertAtIndex(collectionManager));
        commands.put("help", new Help(commands, collectionManager));
        commands.put("exit", new Exit());
        commands.put("register", new Register());
        commands.put("login", new Login());
    }

    public void execute(RequestFromClient request) {
        executor.submit(() -> {
            synchronized (history) {
                String user = request.getUserInfo().getUser();
                if (!history.containsKey(user)) history.put(user, new LinkedList<>());
                history.get(user).add(request.getCommand());
                if (history.get(user).size() > 5) history.get(user).removeFirst();
            }

            ResponseFromServer response = new ResponseFromServer();
            response.setKey(request.getKey());
            response.setCommand(request.getCommand());
            commands.get(request.getCommand()).execute(request.getArg(), request.getObject(), request.getUserInfo(), response);
        });
    }

    public static ArrayList<String> getHistory(String user) {
        ArrayList<String> historyArray = new ArrayList<>();
        synchronized (history) {
            if (!history.containsKey(user)) return historyArray;
            historyArray.addAll(history.get(user));
            return historyArray;
        }
    }
}
