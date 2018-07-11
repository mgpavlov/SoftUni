import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _02_Students_by_First_and_Last_Name {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Set<String> names = new LinkedHashSet<>();

        while (true) {
            String line = bf.readLine().trim();
            if ("END".equalsIgnoreCase(line)) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            } else {
                names.add(line);
            }

            if (names.isEmpty()) {
                return;
            }
        }

        names.stream()
                .filter(s -> (s != null && !s.isEmpty()))
                .filter(s -> s.split("\\s+")[0].compareTo(s.split("\\s+")[1]) < 0)
                .forEach(System.out::println);
    }
}

//    Aaron Gibson
//    Antonio Gonzalez
//    Cheryl Gray
//    Craig King
//    Craig Ellis
//    Andrea Harper
//    Margaret Peterson
//    Edward Rose
//    Kelly Vasquez
//    Lois Patterson
//    Daniel Ferguson
//    Jane Lopez
//    Billy Romero
//    Craig Flores
//    Justin Watkins
//    Alice Baker
//    Lawrence Welch
//    Helen Hernandez
//    Heather Knight
//    Bonnie Shaw
//    Anne Freeman
//    Nancy Patterson
//    Ashley Lee
//    Marie Simpson
//    Melissa Robinson
//    Rebecca Torres
//    Marilyn Price
//    Gloria Harvey
//    Jacqueline Stewart
//    Gerald Watson
//    Gloria Schmidt
//    Kelly Woods
//    Fred Simmons
//    Joan Lee
//    Carolyn Grant
//    Albert Cruz
//    Kathryn Lee
//    Frances Peters
//    Eugene Lawson
//    Emily Howard
//    Nicholas Reyes
//    Beverly Clark
//    Amanda Hernandez
//    Catherine Porter
//    Billy Bowman
//    David Johnston
//    Arthur James
//    Julie Riley
//    Helen Williamson
//    Anne Powell
//    Elizabeth James
//    Cynthia Olson
//    Raymond Stewart
//    Catherine Diaz
//    Antonio Rodriguez
//    Maria Price
//    Diana Flores
//    James Nelson
//    Justin Reyes
//    Edward Nelson
//    Andrea Kelly
