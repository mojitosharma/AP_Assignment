public class Exponential implements Compute, Function {
    private final double base;

    public Exponential(double base) {
        this.base = base;
    }

    @Override
    public double compute(double input) {
        return Math.pow(this.base, input);
    }

    @Override
    public Compute differential() {
        return new Multiply(this, new Constant(Math.log(base)));
    }

    @Override
    public Compute integral() {
        return new Divide(this, new Constant(Math.log(base)));
    }

    @Override
    public String toString() {
        return "( "+base+"^x )";
    }
}
