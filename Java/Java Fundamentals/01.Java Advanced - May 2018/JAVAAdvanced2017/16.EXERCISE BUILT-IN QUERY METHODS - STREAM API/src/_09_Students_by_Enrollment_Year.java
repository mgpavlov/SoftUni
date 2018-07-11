import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _09_Students_by_Enrollment_Year {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> students = new LinkedHashMap<>();

        while (true) {
            String line = bf.readLine();
            if ("END".equalsIgnoreCase(line)) {
                break;
            }

            List<String> tokens = Arrays.stream(line.split("\\s+"))
                    .filter(s -> (s != null && !s.isEmpty())).collect(Collectors.toList());
            if (tokens.isEmpty()) {
                return;
            }

            String facultyNumber = tokens.get(0);
            List<Integer> grades = tokens.subList(1, tokens.size()).stream()
                    .map(Integer::valueOf).collect(Collectors.toList());
            students.put(facultyNumber, grades);
        }

        students.entrySet().stream()
                .filter(s -> (s.getKey().endsWith("14") || s.getKey().endsWith("15")))
                .forEach(s -> System.out.println(Arrays.toString(s.getValue().toArray())
                        .replaceAll("[\\[\\],]", "")));
    }
}

//2011:
//        -- Aaron Gibson
//        -- Anne Freeman
//        -- Beverly Clark
//        -- Billy Romero
//        -- Cheryl Gray
//        -- David Johnston
//        -- Ernest Banks
//        -- Eugene Lawson
//        -- Frances Fisher
//        -- Gary Foster
//        -- Gloria Schmidt
//        -- Heather Knight
//        -- Helen Hernandez
//        -- James Nelson
//        -- Jesse Howard
//        -- Kathryn Lee
//        -- Marilyn Clark
//        2012:
//        -- Catherine Diaz
//        -- Craig Flores
//        -- Gerald Diaz
//        -- Jane Lopez
//        -- Juan Ferguson
//        -- Nicholas Reyes
//        -- William Alexander
//        2013:
//        -- Alice Baker
//        -- Andrea Harper
//        -- Ann Anderson
//        -- Carolyn Grant
//        -- Edward Nelson
//        -- Edward Rose
//        -- Elizabeth James
//        -- Fred Andrews
//        -- Jacqueline Stewart
//        -- Joan Lee
//        -- Julie Cunningham
//        -- Justin Watkins
//        -- Marie Simpson
//        -- Sara Mills
//        -- Terry Pierce
//        -- William Garcia
//        2014:
//        -- Andrea Kelly
//        -- Craig Ellis
//        -- Daniel Carter
//        -- Kelly Woods
//        -- Mildred Hansen
//        -- Rebecca Torres
//        -- Richard Edwards
//        -- Ruby Lewis
//        -- Shirley Hall
//        -- Susan Dixon
//        2015:
//        -- Amanda Hernandez
//        -- Anne Powell
//        -- Antonio Gonzalez
//        -- Antonio Rodriguez
//        -- Ashley Lee
//        -- Billy Bowman
//        -- Diana Flores
//        -- Fred Simmons
//        -- Lawrence Hernandez
//        -- Lillian Hart
//        -- Marilyn Price
//        -- Nancy Patterson
//        -- Robin Long
//        -- Tina Olson
//        2016:
//        -- Albert Cruz
//        -- Arthur James
//        -- Daniel Ferguson
//        -- Jonathan Gibson
//        -- Margaret Peterson
//        -- Michelle Daniels
//        -- Mildred Hart
//        -- Theresa Simpson
//        -- Victor Harvey
//        2017:
//        -- Craig King
//        -- Emily Howard
//        -- Frances Peters
//        -- Gerald Watson
//        -- Gloria Harvey
//        -- Julie Riley
//        -- Kelly Vasquez
//        -- Rose Hernandez
//        -- Stephen Roberts
//        2018:
//        -- Bonnie Shaw
//        -- Charles Alvarez
//        -- Cynthia Olson
//        -- Lawrence Welch
//        -- Steven Cole
//        -- Terry Frazier
//        2019:
//        -- Catherine Porter
//        -- Helen Williamson
//        -- Jeffrey Gibson
//        -- Justin Reyes
//        -- Lawrence Evans
//        -- Lois Patterson
//        -- Maria Price
//        -- Melissa Robinson
//        -- Raymond Stewart
//        -- Roger Adams
//        -- Sean Castillo
//        -- Susan Boyd
