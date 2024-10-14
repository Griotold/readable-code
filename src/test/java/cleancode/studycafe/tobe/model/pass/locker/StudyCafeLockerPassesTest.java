package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeLockerPassesTest {

    @DisplayName("좌석 이용권에 맞는 사물함 이용권을 찾는다.")
    @Test
    void findLockerPassByWithFixed() {
        // given
        StudyCafeLockerPasses studyCafeLockerPasses = createStudyCafeLockerPasses();

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10_000, 0.1);

        // when
        Optional<StudyCafeLockerPass> lockerPassBy = studyCafeLockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(lockerPassBy).isPresent();
        assertThat(lockerPassBy.get().getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(lockerPassBy.get().getDuration()).isEqualTo(4);
        assertThat(lockerPassBy.get().getPrice()).isEqualTo(10_000);
    }

    @DisplayName("좌석 이용권에 맞는 사물함 이용권이 없다면 빈 Optional 객체를 반환한다.")
    @Test
    void findLockerPassByWithHourly() {
        // given
        StudyCafeLockerPasses studyCafeLockerPasses = createStudyCafeLockerPasses();

        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6_500, 0.0);

        // when
        Optional<StudyCafeLockerPass> lockerPassBy = studyCafeLockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(lockerPassBy).isEmpty();
    }

    private static StudyCafeLockerPasses createStudyCafeLockerPasses() {
        StudyCafeLockerPass lockerPass1 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10_000);
        StudyCafeLockerPass lockerPass2 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30_000);

        List<StudyCafeLockerPass> allLockerPass = new ArrayList<>();
        allLockerPass.add(lockerPass1);
        allLockerPass.add(lockerPass2);

        return StudyCafeLockerPasses.of(allLockerPass);
    }

}
