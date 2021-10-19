public class Constant implements Compute, Function {

    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double compute(double input) {
        return value;
    }

    @Override
    public Compute differential() {
        return new Constant(0);
    }

    @Override
    public Compute integral() {
        return new Multiply(this, new Power(1));
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
