public class Main {
    public static void main(String[] args) {
        Exponential exp1 = new Exponential(2);
        Log log1 = new Log(10);
        Power pow1 = new Power(3);
        Constant const1 = new Constant(5);

        System.out.println(exp1);
        System.out.println(log1);
        System.out.println(pow1);
        System.out.println(const1);

        System.out.println();

        System.out.println(exp1.differential());
        System.out.println(log1.differential());
        System.out.println(pow1.differential());
        System.out.println(const1.differential());

        System.out.println();

        System.out.println(exp1.integral());
        System.out.println(log1.integral());
        System.out.println(pow1.integral());
        System.out.println(const1.integral());

        System.out.println();

        Compute composite_func = new Add(log1, pow1);
        composite_func = new Divide(composite_func, const1);
        composite_func = new Subtract(exp1, composite_func);

        System.out.println(composite_func);
        System.out.println(composite_func.compute(10));
    }
}
