interface Printable2{  
void print();  
}  
interface Showable2{  
void print();  
}  

class TestInterface2 implements Printable2, Showable2{  
public void print(){
	System.out.println("Hello");
	}  

public static void main(String args[]){  
TestInterface2 obj = new TestInterface2();  
obj.print();  
 }  
}  