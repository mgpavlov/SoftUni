import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class exam1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int problemsCount = Integer.parseInt(reader.readLine());
        int candidatesCount = Integer.parseInt(reader.readLine());

        String regexFullName = "^[A-Z][a-z]+ [A-Z][a-z]+$";
        Deque<String> problems = new ArrayDeque<>();
        Deque<String> candidates = new ArrayDeque<>();

        for (int i = 0; i < problemsCount; i++) {
            String problem = reader.readLine();

            problems.push(problem);

        }
        for (int i = 0; i < candidatesCount; i++) {
            String candidate = reader.readLine();
            if (!candidate.matches(regexFullName)) {
                continue;
            }
            candidates.add(candidate);
        }

        while (true) {
            if (candidates.size() == 1) {
                System.out.println(candidates.peek() + " gets the job!");
                return;
            }
            if (problems.isEmpty()) {
                System.out.println(String.join(", ", candidates));
                return;
            }

            int candidateSum = candidates.peek().chars().sum();
            //assert problems.peek() != null;
            int problemSum = problems.peek().chars().sum();

            if (candidateSum > problemSum) {
                String candidate = candidates.pop();
                String problem = problems.pop();

                candidates.add(candidate);
                System.out.println(candidate + " solved " + problem+".");
            } else {
                String candidate = candidates.pop();
                String problem = problems.pop();
                problems.add(problem);

                System.out.println(candidate + " failed " + problem + ".");
            }
        }
    }
}
