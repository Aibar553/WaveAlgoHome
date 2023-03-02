import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintPretty {

    public static String printBlockMap(int[][] arr) {
        StringBuilder blocs_map_str = new StringBuilder(arr.length);
        int gradient_max = find2DMaxStream(arr);

        for (int[] row : arr) {
            for (int cell : row) {
                switch (cell) {
                    case -1 -> blocs_map_str.append(Character.toString(0x2588));
                    case 1 ->
                        blocs_map_str.append(Character.toString(0x25B2));
                    case 0 -> blocs_map_str.append(" ");
                    case -2 -> blocs_map_str.append(Character.toString(0x25BC));
                    case -3 -> blocs_map_str.append(Character.toString(0x2551));
                    case -4 -> blocs_map_str.append(Character.toString(0x2550));
                    case -5 -> blocs_map_str.append(Character.toString(0x2557));
                    case -6 -> blocs_map_str.append(Character.toString(0x2554));
                    case -7 -> blocs_map_str.append(Character.toString(0x255D));
                    case -8 -> blocs_map_str.append(Character.toString(0x255A));
                    default -> {
                        if (cell > gradient_max * 2 / 3)
                            blocs_map_str.append(Character.toString(0x2593));
                        else if (cell > gradient_max / 3)
                            blocs_map_str.append(Character.toString(0x2592));
                        else
                            blocs_map_str.append(Character.toString(0x2591));
                    }
                }
            }
            blocs_map_str.append("\n");
        }
        return blocs_map_str.toString();
    }

    public static int[][] showPath(int[][] arr, String path_str) {
        String[] coords = path_str.split(",");
        if (coords.length < 3) {
            return arr;
        }
        int[] next_pair = new int[2];
        int[] curr_pair = new int[2];
        int[] prev_pair = { Integer.parseInt(coords[0].substring(0, coords[0].indexOf(':'))),
                Integer.parseInt(coords[0].substring(coords[0].indexOf(':') + 1)) };
        for (int i = 1; i < coords.length - 1; i++) {
            curr_pair[0] = Integer.parseInt(coords[i].substring(0, coords[i].indexOf(':')));
            curr_pair[0] = Integer.parseInt(coords[i].substring(coords[i].indexOf(':') + 1));
            next_pair[0] = Integer.parseInt(coords[i + 1].substring(0, coords[i + 1].indexOf(':')));
            next_pair[0] = Integer.parseInt(coords[i + 1].substring(coords[i + 1].indexOf(':') + 1));

            if (curr_pair[0] == prev_pair[0] && curr_pair[1] > prev_pair[1]) {
                if (curr_pair[0] == next_pair[0]) {
                    arr[curr_pair[1]][curr_pair[0]] = -3;
                } else if (curr_pair[0] > next_pair[0]) {
                    arr[curr_pair[1]][curr_pair[0]] = -7;
                } else {
                    arr[curr_pair[1]][curr_pair[0]] = -8;
                }
            } else if (curr_pair[0] == prev_pair[0] && curr_pair[1] < prev_pair[1]) {
                if (curr_pair[0] == next_pair[0]) {
                    arr[curr_pair[1]][curr_pair[0]] = -3;
                } else if (curr_pair[0] > next_pair[0]) {
                    arr[curr_pair[1]][curr_pair[0]] = -5;
                } else {
                    arr[curr_pair[1]][curr_pair[0]] = -6;
                }
            } else if (curr_pair[0] > prev_pair[0] && curr_pair[1] == prev_pair[1]) {
                if (curr_pair[1] == next_pair[1]) {
                    arr[curr_pair[1]][curr_pair[0]] = -4;
                } else if (curr_pair[1] > next_pair[1]) {
                    arr[curr_pair[1]][curr_pair[0]] = -7;
                } else {
                    arr[curr_pair[1]][curr_pair[0]] = -5;
                }
            } else {
                if (curr_pair[1] == next_pair[1]) {
                    arr[curr_pair[1]][curr_pair[0]] = -4;
                } else if (curr_pair[1] > next_pair[1]) {
                    arr[curr_pair[1]][curr_pair[0]] = -8;
                } else {
                    arr[curr_pair[1]][curr_pair[0]] = -6;
                }
            }
            prev_pair[0] = curr_pair[0];
            prev_pair[1] = curr_pair[1];
        }
        return arr;
    }

    public static int find2DMaxStream(int[][] arr) {
        IntSummaryStatistics stats = Stream.of(arr)
                .flatMapToInt(IntStream::of)
                .summaryStatistics();
        return stats.getMax();
    }
}
