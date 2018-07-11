import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   //100/100
import java.util.ArrayList;

public class _12_To_the_Stars2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<double[]> systemsCoordinates = new ArrayList<>();
        String[] systems = new String[3];

        for (int i = 0; i < 3; i++) {
            String[] input = reader.readLine().split(" ");
            systems[i] = input[0];
            systemsCoordinates.add(i, new double[] { Double.parseDouble(input[1]), Double.parseDouble(input[2])});
        }

        String[] location = reader.readLine().split(" ");
        int moves = Integer.parseInt(reader.readLine());

        double currentLocationX = Double.parseDouble(location[0]);
        double currentLocationY = Double.parseDouble(location[1]);

        while (moves >= 0) {

            String currentLocation = "space";

            for (int i = 0; i < systemsCoordinates.size(); i++) {

                double leftEdge = systemsCoordinates.get(i)[0] - 1;
                double rightEdge = systemsCoordinates.get(i)[0] + 1;
                double lowerEdge = systemsCoordinates.get(i)[1] - 1;
                double upperEdge = systemsCoordinates.get(i)[1] + 1;

                if (currentLocationX >= leftEdge && currentLocationX <= rightEdge
                        && currentLocationY >= lowerEdge && currentLocationY <= upperEdge) {
                    currentLocation = systems[i];
                }
            }
            System.out.println(currentLocation.toLowerCase());
            currentLocationY++;
            moves--;
        }
    }
}
