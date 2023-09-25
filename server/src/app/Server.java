package app;

import lombok.Getter;
import utils.RequestFromClient;
import utils.ResponseFromServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int SERVER_PORT = 8080;
    private static final int BUFFER_SIZE = 1024 * 1024;
    @Getter
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    //private static ExecutorService sendResponse = Executors.newFixedThreadPool(10);


    public Server() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(SERVER_PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Проблемы с соединением");
        }
    }

    public SelectionKey register() {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            return client.register(selector, SelectionKey.OP_READ);
            //System.out.print("Канал зарегистрирован ");
        }
        catch (IOException e) {
            System.out.println("Ошибка в регистрации канала");
            return null;
        }
    }


    public RequestFromClient readRequest(SelectionKey key) {
        RequestFromClient request = new RequestFromClient();
        request.setKey(key);
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            socketChannel.read(buffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream input = new ObjectInputStream(bais);
            request = (RequestFromClient) input.readObject();
        }
        catch (IOException e) {
            key.cancel();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Ошибка получения запроса от клиента - " + key);
        }
        return request;
    }

    public static void sendResponse(ResponseFromServer response, SelectionKey key) {
        Thread t = new Thread(() -> {
            //System.out.println("This is " + Thread.currentThread().getName() + " " + key);
            SocketChannel socketChannel = (SocketChannel) key.channel();
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(response);
                socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                //System.out.println("This is " + Thread.currentThread().getName());
                objectOutputStream.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        t.start();
    }

}
