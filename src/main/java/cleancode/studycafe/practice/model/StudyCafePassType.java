package cleancode.studycafe.practice.model;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public boolean isHourly() {
        return this == StudyCafePassType.HOURLY;
    }

    public boolean isWeekly() {
        return this == StudyCafePassType.WEEKLY;
    }

    public boolean isFixed() {
        return this == StudyCafePassType.FIXED;
    }
}
