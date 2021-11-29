
package com.company;

public class Calculator<T> {
    private T arg1;
    private T arg2;

    public Calculator() {
    }

    public void set_arg(T t1, T t2) {
        this.arg1 = t1;
        this.arg2 = t2;
    }

    public boolean check(T temp) {
        if (this.arg1 instanceof Integer && this.arg2 instanceof Integer) {
            return (Integer)temp == (Integer)this.arg2 / (Integer)this.arg1;
        } else if (this.arg1 instanceof String && this.arg2 instanceof String) {
            String var10000 = (String)temp;
            String var10001 = (String)this.arg1;
            return var10000.equals(var10001 + (String)this.arg2);
        } else {
            return false;
        }
    }

    public String toString() {
        if (this.arg1 instanceof Integer && this.arg2 instanceof Integer) {
            return "Calculate the result of " + this.arg2 + " divided by " + this.arg1;
        } else {
            return this.arg1 instanceof String && this.arg2 instanceof String ? "Calculate the concatenation of strings " + this.arg1 + " and " + this.arg2 : "";
        }
    }
}
