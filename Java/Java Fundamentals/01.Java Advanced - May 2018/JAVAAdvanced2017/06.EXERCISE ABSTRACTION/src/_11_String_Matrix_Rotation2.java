import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100

public class _11_String_Matrix_Rotation2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = reader.readLine();
        Integer rotations = Integer.parseInt(inputLine.split("[]\\(\\)]")[1]);
        rotations /= 90;
        rotations %= 4;
        StringBuilder matrixLine = new StringBuilder();
        inputLine = reader.readLine();
        Integer maxLen = 0;

        while (!inputLine.equals("END")) {
            if (inputLine.length() > maxLen) maxLen = inputLine.length();
            matrixLine.append(inputLine).append("\n");
            inputLine = reader.readLine();
        }

        String[] matrixL = matrixLine.toString().split("\n");
        StringBuilder output = new StringBuilder();

        switch (rotations) {
            case 0:
                output = matrixLine;
                break;
            case 1:
                output = CalculateMatrix(maxLen, matrixL, output);
                break;
            case 2:
                output = matrixLine.reverse();
                break;

            case 3:
                output = CalculateMatrix(maxLen, matrixL, output);
                output.reverse();
                break;
        }
        System.out.println(output.toString());
    }

    private static StringBuilder CalculateMatrix(Integer maxLen, String[] matrixL, StringBuilder output) {
        StringBuilder outputBuilder = new StringBuilder(output);
        for (int j = 0; j < maxLen; j++) {
            for (int i = matrixL.length - 1; i >= 0; i--) {
                try {
                    outputBuilder.append(matrixL[i].charAt(j));
                } catch (Exception e) {
                    outputBuilder.append(" ");
                }
            }
            outputBuilder.append("\n");
        }
        output = outputBuilder;
        return output;
    }
}
