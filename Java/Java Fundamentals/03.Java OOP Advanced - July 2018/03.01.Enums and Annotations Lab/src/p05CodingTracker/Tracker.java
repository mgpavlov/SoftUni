package p05CodingTracker;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {
    @Author(name = "Goshko")
    public static void printMethodsByAuthor() {
        Map<String, List<String>> methodsByAuthor = new HashMap<>();

        Class<?> trackerClass = Tracker.class;
        Method[] methods = trackerClass.getDeclaredMethods();
        for (Method method : methods) {
            Author annotation = method.getAnnotation(Author.class);
            if (annotation != null) {
                methodsByAuthor.putIfAbsent(annotation.name(), new ArrayList<>());
                methodsByAuthor.get(annotation.name()).add(method.getName());
            }
        }

        for (String author : methodsByAuthor.keySet()) {
            System.out.print(author + ": ");
            System.out.println(String.join(", ", methodsByAuthor.get(author)));
        }
    }
    @Author(name = "Goshko")
    public void methodByGoshko(){
        System.out.println("Goshko is a GOD");
    }

    @Author(name = "Pesho")
    public void methodByPesho() {
        System.out.println("Pesho is a bird");
    }
}
