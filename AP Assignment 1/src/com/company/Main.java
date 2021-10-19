public class Main {
    public static void main(String] args) {
        Animal A1 = new Elephant();
        Animal A2 = new Animal();
        A1.eat();
        A1.dance(A2);
    }
}
public interface Animal {
    public void eat();
    private void dance(Animal A);
}
public class Elephant implements Animal {
    private String name;
    public Elephant(String n) {name = n;}
    public void eat() {
        System.out.println("Elephant eating");
    }
    public void dance(Animal A) {
        System.out.println(this.name + " dancing with " + A.name);
    }
}
