package p02WarningLevels;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<Message> messages;
    private Importance importanceLevel;


    public Logger(String importanceLevel) {
        this.importanceLevel = Enum.valueOf(Importance.class, importanceLevel.toUpperCase());
        this.messages =  new ArrayList<>();
    }
    Iterable<Message>getMessages(){
        return this.messages;
    }

    public void addMessage(String importance, String message) {
        if (Enum.valueOf(Importance.class, importance.toUpperCase()).compareTo(this.importanceLevel) >= 0){
            this.messages.add(new Message(importance, message));
        }
    }


}
