package cleancode.minesweeper.tobe.minesweeper.board.position;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 일급 컬렉션
public class CellPositions {

    private final List<CellPosition> positions;

    private CellPositions(List<CellPosition> positions) {
        this.positions = positions;
    }

    public static CellPositions of(List<CellPosition> positions) {
        return new CellPositions(positions);
    }

    public static CellPositions from(Cell[][] board) {
        List<CellPosition> cellPositions = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                CellPosition cellPosition = CellPosition.of(row, col);
                cellPositions.add(cellPosition);
            }
        }

        return of(cellPositions);
    }

    public List<CellPosition> extractRandomPositions(int count) {
        // 기존 포지션에는 영향을 주지 않기 위해 새롭게 만들어서 셔플
        ArrayList<CellPosition> cellPositions = new ArrayList<>(positions);

        Collections.shuffle(cellPositions);
        return cellPositions.subList(0, count);

    }

    public List<CellPosition> subtract(List<CellPosition> positionListToSubtract) {
        // 기존 포지션에는 영향을 주지 않기 위해 새롭게 만들어서
        ArrayList<CellPosition> cellPositions = new ArrayList<>(positions);

        // 뺄 포지션들
        CellPositions positionsToSubtract = CellPositions.of(positionListToSubtract);

        return cellPositions.stream()
                .filter(positionsToSubtract::doesNotContain)
                .toList();
    }

    private boolean doesNotContain(CellPosition position) {
        return !positions.contains(position);
    }

    // 반환할 때는 항상 새롭게 만들어서 반환
    public List<CellPosition> getPositions() {
        return new ArrayList<>(positions);
    }
}
