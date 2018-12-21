package callofduty;

import callofduty.core.MissionControlImpl;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionControl;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.Reader;
import callofduty.io.Writer;
import callofduty.managers.MissionManagerImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        InputReader reader = new Reader();
        OutputWriter writer = new Writer();
        MissionControl controller = new MissionControlImpl();

        MissionManager manager = new MissionManagerImpl(controller, writer);

        String line = reader.readLine();
        while (true) {
            if ("Over".equals(line)) {
                manager.over(null);
                break;
            }
            String[] tokens = line.split("\\s+");
            List<String> arguments = Arrays.stream(tokens).skip(1).collect(Collectors.toList());
            switch (tokens[0]) {
                case "Agent":
                    System.out.println(manager.agent(arguments));
                    break;
                case "Request":
                    System.out.println(manager.request(arguments));
                    break;
                case "Complete":
                    System.out.println(manager.complete(arguments));
                    break;
                case "Status":
                    System.out.println(manager.status(arguments));
                    break;
            }
            line = reader.readLine();
        }
    }
}




