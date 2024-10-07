package cleancode.minesweeper.tobe.cell;

import java.util.Arrays;
import java.util.List;

// 일급 컬렉션
// 1. 필드는 반드시 하나이며, 컬렉션이어야 한다.
public class Cells {

    // 필드는 반드시 컬렉션 하나!
    private final List<Cell> cells;

    public Cells(List<Cell> cells) {
        this.cells = cells;
    }

    public static Cells of(List<Cell> cells) {
        return new Cells(cells);
    }

    public static Cells from(Cell[][] cells) {
        List<Cell> cellList = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .toList();
        return of(cellList);
    }

    public boolean isAllChecked() {
        return cells.stream()
                .allMatch(Cell::isChecked);
    }
}
