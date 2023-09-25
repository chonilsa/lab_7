package utils;

import lombok.Data;

import java.io.Serializable;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;

@Data
public class ResponseFromServer implements Serializable {
    private String message = " - выполнение команды прошло успешно";
    private String command;
    private transient SelectionKey key;
    private ArrayList<? extends Serializable> object;
}
