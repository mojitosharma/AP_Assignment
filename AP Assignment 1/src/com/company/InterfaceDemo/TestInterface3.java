interface Printable3{  
void print();  
}  
interface Showable3 extends Printable3{  
void show();  
}  
class TestInterface3 implements Showable3{  
public void print(){System.out.println("Hello");}  
public void show(){System.out.println("Welcome");}  
  
public static void main(String args[]){  
TestInterface3 obj = new TestInterface3();  
obj.print();  
obj.show();  
 }  
}  