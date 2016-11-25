public class Main {

    public static void main(String[] args) {
        Robot robot = new Robot();
        String hostname = "127.0.0.1";

        robot.connect(hostname,true);

        float s =0.6f;

            robot.move(s, s, s);






    }

}
