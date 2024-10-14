package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @DisplayName("할인율이 없는 이용권 주문시 price 가 totalPrice 이다.")
    @Test
    void getTotalPriceNoDiscount() {
        // given
        StudyCafePassType passType = StudyCafePassType.HOURLY;
        int duration = 2;
        int price = 4_000;
        double discountRate = 0.0;
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, price, discountRate);
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(studyCafeSeatPass, null);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(price);
    }

    @DisplayName("할인율이 있는 이용권 주문시 할인을 적용받는다.")
    @Test
    void getTotalPriceInTwoWeeksOfWeekly() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;
        int duration = 2;
        int price = 100_000;
        double discountRate = 0.1;
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, price, discountRate);
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(studyCafeSeatPass, null);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(price - studyCafeSeatPass.getDiscountPrice());
    }

    @DisplayName("사물함을 추가로 구매시 totalPrice 는 사물함 가격까지 포함된다.")
    @Test
    void getTotalPriceWithLockerPass() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 12;
        int price = 700_000;
        double discountRate = 0.15;
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, price, discountRate);
        int lockerPrice = 30_000;
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passType, duration, lockerPrice);
        StudyCafePassOrder passOrder = StudyCafePassOrder.of(studyCafeSeatPass, lockerPass);

        // when
        int totalPrice = passOrder.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(price + lockerPrice - (int) (price * discountRate));
    }

}
