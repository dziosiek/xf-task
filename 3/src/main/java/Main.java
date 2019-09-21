import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Task3 task = new Task3();
        task.setMineField("*.*...\n....*.\n......");
        System.out.println(task.getHintField());
    }
}

class Task3 implements MineSweeper {

    private String mineField;

    public void setMineField(String mineField) throws IllegalArgumentException {

        List<String> rows = Arrays.asList(mineField.split("\n"));
        if (rows.size() < 1) return;
        rows.forEach(substring -> {
            if (rows.get(0).length() != substring.length())
                throw new IllegalArgumentException("Substring has different length from others");
            Arrays.asList(substring.split("")).forEach(s -> {
                if (!(s.equals("*") || s.equals(".")))
                    throw new IllegalArgumentException("invalid char \"" + s + "\" in string: \"" + mineField+"\"");
            });
        });

        this.mineField = mineField;

    }

    public String getHintField() throws IllegalStateException {

        if (mineField == null || mineField.length() == 0) throw new IllegalArgumentException();

        List<List<Character>> matrix = Arrays.stream(mineField.split("\n"))
                .map(string -> string.chars().mapToObj(c -> (char) c)
                        .collect(Collectors.toList())).collect(Collectors.toList());

        int x = matrix.get(0).size();
        int y =matrix.size();

        StringBuilder results = new StringBuilder();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                boolean up, down , left, right;
                up = i>0;
                down = i<y-1;
                left = j>0;
                right = j<x-1;

                if(matrix.get(i).get(j)=='.'){
                    int minesAround=0;
                    if(left && matrix.get(i).get(j-1) =='*')  minesAround++;
                    if(right && matrix.get(i).get(j+1)=='*') minesAround++;
                    if(up && matrix.get(i-1).get(j)=='*') minesAround++;
                    if(down && matrix.get(i+1).get(j)=='*') minesAround++;
                    if(up && left && matrix.get(i-1).get(j-1)=='*') minesAround++;
                    if(up && right && matrix.get(i-1).get(j+1)=='*') minesAround++;
                    if(down && left && matrix.get(i+1).get(j-1)=='*') minesAround++;
                    if(down && right && matrix.get(i+1).get(j+1)=='*') minesAround++;

                    results.append(minesAround);
                }
                else results.append('*');

            }
            results.append("\n");
        }

        return results.toString();
    }
}
