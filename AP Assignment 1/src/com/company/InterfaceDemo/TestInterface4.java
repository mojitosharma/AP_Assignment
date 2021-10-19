interface Showable4{  
  void show();  
  interface Message{  
   void msg();  
  }  
}  
class TestInterface4 implements Showable4.Message{  
public void msg(){System.out.println("Hello nested interface");}  

public static void main(String args[]){  
  Showable4.Message message=new TestInterface4(); 
  message.msg(); 
 }  
}  