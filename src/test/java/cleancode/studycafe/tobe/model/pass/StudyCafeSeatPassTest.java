package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StudyCafeSeatPassTest {

    @DisplayName("고정석 이외의 좌석 이용권은 사물함을 이용할 수 없다.")
    @Test
    void cannotUseLockerWithHourly() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4_000, 0.0);

        // when
        boolean result = seatPass.cannotUseLocker();

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("고정석 좌석 이용권은 사물함을 이용할 수 있다.")
    @Test
    void cannotUseLockerWithFixed() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250_000, 0.1);

        // when
        boolean result = seatPass.cannotUseLocker();

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("좌석 이용권과 사물함 이용권의 기간과 유형이 동일하면 true 를 반환한다.")
    @Test
    void isSameDurationTypeWithMatch() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250_000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10_000);

        // when
        boolean result = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("좌석 이용권과 사물함 이용권의 기간과 유형이 다르면 false 를 반환한다.")
    @Test
    void isSameDurationTypeWithMistMatch() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250_000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30_000);

        // when
        boolean result = seatPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("좌석 이용권 타입이이 일치하면 true를 반환")
    @Test
    void isSamePassTypeWithMatch() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250_000, 0.1);

        // when
        boolean result = seatPass.isSamePassType(StudyCafePassType.FIXED);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("좌석 이용권 타입이이 일치하지 않으면 false를 반환")
    @Test
    void isSamePassTypeWithMisMatch() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250_000, 0.1);

        // when
        boolean result = seatPass.isSamePassType(StudyCafePassType.WEEKLY);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("가격에 할인율을 곱하면 할인할 가격이다.")
    @Test
    void getDiscountPrice() {
        // given
        int price = 250_000;
        double discountRate = 0.1;
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, price, discountRate);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo((int)(price * discountRate));
    }
}
