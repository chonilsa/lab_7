package app;

import utils.RequestFromClient;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String args[]) throws IOException {

        CollectionManager collectionManager = new CollectionManager();
        CommandsList commandsList = new CommandsList(collectionManager);
        ExecutorService clientPool = Executors.newCachedThreadPool();
        Server server = new Server();
        System.out.println("Сервер запущен");

        if (collectionManager.collection_initialization())
            System.out.println("Загрузка данных из базы данных прошла успешно");
        else System.out.println("Коллекция неинициализирована");

        while(true) {
            server.getSelector().select(3000);
            Set<SelectionKey> keys = server.getSelector().selectedKeys();

            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SelectionKey resultKey = server.register();

                    clientPool.submit(() -> {
                        SelectionKey newKey = resultKey;
                        while (true) {
                            RequestFromClient request = server.readRequest(newKey);

                            if (request.getCommand() != null) {
                                if (request.getCommand().equals("exit")) {
                                    key.cancel();
                                }
                                request.setKey(newKey);
                                commandsList.execute(request);
                            }
                        }
                    });
                }
            }
        }
    }
}