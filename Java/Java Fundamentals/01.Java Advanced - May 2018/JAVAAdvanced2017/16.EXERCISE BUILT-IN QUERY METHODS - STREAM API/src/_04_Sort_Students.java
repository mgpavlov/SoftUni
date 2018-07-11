import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _04_Sort_Students {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<String> students = new ArrayList<>();

        while (true) {
            String line = bf.readLine();
            if ("END".equalsIgnoreCase(line)) {
                break;
            }

            if (line == null || line.isEmpty()) {
                continue;
            }

            students.add(line);
        }

        students.stream()
                .sorted((s1, s2) -> {
                    if (s1.split("\\s+")[1].compareTo(s2.split("\\s+")[1]) == 0) {
                        return s2.split("\\s+")[0].compareTo(s1.split("\\s+")[0]);
                    } else {
                        return s1.split("\\s+")[1].compareTo(s2.split("\\s+")[1]);
                    }
                })
                .forEach(System.out::println);
    }
}


//    Roger Adams
//    William Alexander
//    Charles Alvarez
//    Ann Anderson
//    Fred Andrews
//    Alice Baker
//    Ernest Banks
//    Billy Bowman
//    Susan Boyd
//    Daniel Carter
//    Sean Castillo
//    Marilyn Clark
//    Beverly Clark
//    Steven Cole
//    Albert Cruz
//    Julie Cunningham
//    Michelle Daniels
//    Gerald Diaz
//    Catherine Diaz
//    Susan Dixon
//    Richard Edwards
//    Craig Ellis
//    Lawrence Evans
//    Juan Ferguson
//    Daniel Ferguson
//    Frances Fisher
//    Diana Flores
//    Craig Flores
//    Gary Foster
//    Terry Frazier
//    Anne Freeman
//    William Garcia
//    Jonathan Gibson
//    Jeffrey Gibson
//    Aaron Gibson
//    Antonio Gonzalez
//    Carolyn Grant
//    Cheryl Gray
//    Shirley Hall
//    Mildred Hansen
//    Andrea Harper
//    Mildred Hart
//    Lillian Hart
//    Victor Harvey
//    Gloria Harvey
//    Rose Hernandez
//    Lawrence Hernandez
//    Helen Hernandez
//    Amanda Hernandez
//    Jesse Howard
//    Emily Howard
//    Elizabeth James
//    Arthur James
//    David Johnston
//    Andrea Kelly
//    Craig King
//    Heather Knight
//    Eugene Lawson
//    Kathryn Lee
//    Joan Lee
//    Ashley Lee
//    Ruby Lewis
//    Robin Long
//    Jane Lopez
//    Sara Mills
//    James Nelson
//    Edward Nelson
//    Tina Olson
//    Cynthia Olson
//    Nancy Patterson
//    Lois Patterson
//    Frances Peters
//    Margaret Peterson
//    Terry Pierce
//    Catherine Porter
//    Anne Powell
//    Marilyn Price
//    Maria Price
//    Nicholas Reyes
//    Justin Reyes
//    Julie Riley
//    Stephen Roberts
//    Melissa Robinson
//    Antonio Rodriguez
//    Billy Romero
//    Edward Rose
//    Gloria Schmidt
//    Bonnie Shaw
//    Fred Simmons
//    Theresa Simpson
//    Marie Simpson
//    Raymond Stewart
//    Jacqueline Stewart
//    Rebecca Torres
//    Kelly Vasquez
//    Justin Watkins
//    Gerald Watson
//    Lawrence Welch
//    Helen Williamson
//    Kelly Woods
