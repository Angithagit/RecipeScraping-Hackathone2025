package utils;

import java.io.*;

public class ProgressTracker {
    public static int read(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            return line != null ? Integer.parseInt(line.trim()) : 0;
        } catch (IOException | NumberFormatException e) {
            System.out.println("No progress found, starting fresh.");
            return 0;
        }
    }

    public static void save(String filePath, int index) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.valueOf(index));
        } catch (IOException e) {
            System.out.println("Failed to save progress: " + e.getMessage());
        }
    }
}
