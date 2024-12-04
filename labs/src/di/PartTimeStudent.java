package di;

public class PartTimeStudent implements Student {
    @Override
    public void complain() {
        System.out.println("Hey I am a part time student with a complaint");
    }
}
