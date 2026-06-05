package objectville;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {

    public static CityMap load(String filePath) {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) lines.add(trimmed);
            }
        } catch (IOException e) {
            throw new InvalidCityMapException("Dosya okunamadi: " + filePath, e);
        }

        if (lines.isEmpty()) throw new InvalidCityMapException("Harita dosyasi bos: " + filePath);

        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int r = 0; r < rows; r++) {
            if (lines.get(r).length() != cols)
                throw new InvalidCityMapException("Satir " + r + " yanlis uzunlukta");
        }

        Cell[][] grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = parseCell(lines.get(r).charAt(c), r, c);
            }
        }

        return new CityMap(grid);
    }

    private static Cell parseCell(char ch, int row, int col) {
        switch (ch) {
            case 'E': return new EmptyCell(row, col);
            case 'R': return new Road(row, col);
            case 'H': return new HousingZone(row, col);
            case 'I': return new IndustrialZone(row, col);
            case 'C': return new CommercialZone(row, col);
            case 'P': return new PowerPlant(row, col);
            case 'W': return new WaterStation(row, col);
            case 'T': return new InternetHub(row, col);
            case 'F': return new PoliceStation(row, col);
            case 'D': return new Hospital(row, col);
            case 'S': return new School(row, col);
            default:
                throw new InvalidCityMapException("Bilinmeyen karakter '" + ch + "' at (" + row + "," + col + ")");
        }
    }
}
