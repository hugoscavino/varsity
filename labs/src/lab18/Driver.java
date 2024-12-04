package lab18;

public class Driver {
    public static void main(String[] args) {
        A a1 = new A(5,10);
        A a2 = new A(20,30);
        a2 = a1;
        a1.x++;
        a2.y--;
        System.out.println("a1: x= " + a1.x + ", y= " + a1.y);
        System.out.println("a2: x= " + a2.x + ", y= " + a2.y);

    }
}
