package cleancode.studycafe.practice.model;

import cleancode.studycafe.practice.io.StudyCafeFileHandler;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> passes;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public static StudyCafeLockerPasses from(StudyCafeFileHandler handler) {
        return of(handler.readLockerPasses());
    }

    public Optional<StudyCafeLockerPass> getLockerPassFor(StudyCafePass studyCafePass) {
        return passes.stream()
            .filter(option ->
                option.isSamePassType(studyCafePass)
                    && option.isSameDuration(studyCafePass)
            )
            .findFirst();
    }
}
