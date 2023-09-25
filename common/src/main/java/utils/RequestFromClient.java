package utils;
import data.Route;
import lombok.Data;
import java.io.Serializable;
import java.nio.channels.SelectionKey;

@Data
public class RequestFromClient implements Serializable {
    private String command;
    private String arg;
    private Route object;
    private UserInfo userInfo;
    private transient SelectionKey key;
}
