
public class Main {
    public static void main(String[] args) {
        int[][] table = MapGenerator.generateMaze(13, 13, true, 1);
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        WaveAlgorithm.colorize(table);
        tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        System.out.println("Maze map");
        System.out.println(PrintPretty.printBlockMap(table));
        String testPath = SearchWay.SearchWays(table);

        System.out.println("Maze path");
        int[][] testMaze1 = PrintPretty.showPath(table, testPath);
        System.out.println(PrintPretty.printBlockMap(testMaze1));
    }
}
