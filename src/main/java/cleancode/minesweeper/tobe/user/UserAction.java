package cleancode.minesweeper.tobe.user;

// enum 에는 웬만하면 description(설명)을 넣는 거 추천
public enum UserAction {

    OPEN("셀 열기"),
    FLAG("깃발 꽂기"),
    UNKNOWN("알 수 없음");

    private final String description;


    UserAction(String description) {
        this.description = description;
    }
}
