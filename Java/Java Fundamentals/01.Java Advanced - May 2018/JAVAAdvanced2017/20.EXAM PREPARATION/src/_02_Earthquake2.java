import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.ArrayDeque;
import java.util.ArrayList;

public class _02_Earthquake2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        ArrayDeque<ArrayDeque<Integer>> waves = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] currentWaveString = in.readLine().split("\\s+");
            ArrayDeque<Integer> currentWave = new ArrayDeque<>();

            for (String aCurrentWaveString : currentWaveString)
                currentWave.addLast(Integer.parseInt(aCurrentWaveString));

            waves.addLast(currentWave);
        }

        ArrayList<Integer> seismicities = new ArrayList<>();
        while (waves.size() > 0) {
            ArrayDeque<Integer> currentWave = waves.removeFirst();

            int seismicity = currentWave.removeFirst();

            if (currentWave.isEmpty()) {
                seismicities.add(seismicity);
            } else {
                boolean hasAdded = false;

                while (currentWave.size() > 0) {
                    int current = currentWave.peek();

                    if (seismicity >= current) {
                        currentWave.removeFirst();
                    } else {
                        seismicities.add(seismicity);
                        hasAdded = true;
                        break;
                    }
                }

                if (!hasAdded) {
                    seismicities.add(seismicity);
                }
            }

            if (currentWave.size() > 0) {
                waves.addLast(currentWave);
            }
        }

        System.out.println(seismicities.size());
        for (Integer seismicity : seismicities) {
            System.out.print(seismicity + " ");
        }
    }
}


