public class Multiply implements Compute {

    final Compute f1, f2;

    public Multiply(Compute f1, Compute f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public double compute(double input) {
        return f1.compute(input) * f2.compute(input);
    }

    @Override
    public String toString() {
        return "( "+f1 + " * " + f2+" )";
    }
}
