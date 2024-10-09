package cleancode.studycafe.practice.model;

import cleancode.studycafe.practice.io.StudyCafeFileHandler;

import java.util.ArrayList;
import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public static StudyCafePasses from(StudyCafeFileHandler studyCafeFileHandler) {
        return of(studyCafeFileHandler.readStudyCafePasses());
    }

    public List<StudyCafePass> getHourlyPasses() {
        List<StudyCafePass> studyCafePasses = new ArrayList<>(passes);
        return studyCafePasses.stream()
            .filter(StudyCafePass::isHourly)
            .toList();
    }

    public List<StudyCafePass> getWeeklyPasses() {
        ArrayList<StudyCafePass> studyCafePasses = new ArrayList<>(passes);
        return studyCafePasses.stream()
            .filter(StudyCafePass::isWeekly)
            .toList();
    }

    public List<StudyCafePass> getFixedPasses() {
        ArrayList<StudyCafePass> studyCafePasses = new ArrayList<>(passes);
        return studyCafePasses.stream()
            .filter(StudyCafePass::isFixed)
            .toList();
    }
}
