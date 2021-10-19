public class Power implements Function, Compute {

    private final double power;

    public Power(double power) {
        this.power = power;
    }

    @Override
    public double compute(double input) {
        return Math.pow(input, power);
    }

    @Override
    public Compute differential() {
//        power * Math.pow(input, power - 1)
        return new Multiply(new Constant(power), new Power(power-1));
    }

    @Override
    public Compute integral() {
        if (power == -1) {
//            Math.log(input)
            return new Log(Math.E);
        } else {
//            Math.pow(input, power+1)/(power+1)
            return new Divide(new Power(power+1), new Constant(power+1));
        }
    }

    @Override
    public String toString() {
        return "( x^"+power+" )";
    }
}
