package dsa;

public class Test {
    public static void main(String[] args) {
    //case I
        Parent P  = new Child();
        P.run();

     //case II
      /*
        Child C = new Child();
        C.run(); // why parnent class walk() not invoke?

       */
 }

    static class Parent{
        void run(){
            walk();
            System.out.println(" parent run ");
        }
        void walk(){
            System.out.println(" parent walk ");
        }
    }

    static class Child extends Parent{
        void run(){
            super.run();
            System.out.println(" child run ");
        }
        void walk(){
            super.walk();
            System.out.println(" child walk ");
        }
    }
}
