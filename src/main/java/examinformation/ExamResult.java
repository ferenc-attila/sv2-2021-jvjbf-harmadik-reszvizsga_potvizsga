package examinformation;

public class ExamResult {

    private int theory;
    private int practice;

    public ExamResult(int theory, int practice) {
        this.practice = practice;
        this.theory = theory;
    }

    public boolean areBothSuccess(int theoryMax, int practiceMax) {
        return ((theoryMax * 0.51) <= theory) && ((practiceMax * 0.51) <= practice);
    }

    public int getSumOfPoints() {
        return theory + practice;
    }

    public int getPractice() {
        return practice;
    }

    public int getTheory() {
        return theory;
    }
}
