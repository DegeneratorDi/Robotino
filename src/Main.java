import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Robot robot = new Robot();
        Joystick joystick = new Joystick();

        //String hostname = "127.0.0.1";
        //robot.connect(hostname,true);
        //float s =1f;
       // robot.move(s, s, s);

        joystick.getHidDevices();
        joystick.setDevise(0);
        joystick.run();
        joystick.test(10);




    }

}
