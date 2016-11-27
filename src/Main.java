import java.io.IOException;

public class Main {




    public static void main(String[] args) throws IOException {
        Robot robot = new Robot();
        Joystick joystick = new Joystick();


        String hostname = "127.0.0.1";
        robot.connect(hostname,true);
        float s =1f, rs=-1f;
       robot.move(s, s, s);

        joystick.getHidDevices();
        joystick.setDevise(0);
        joystick.run();
        joystick.test(1);

        while(true) {

            if(joystick.getStatusButton("up")) {
                robot.move(s, 0, 0);
            }

            if(joystick.getStatusButton("down")) {
                robot.move(rs, 0, 0);
            }

            if(joystick.getStatusButton("left")) {
                robot.move(0, s, 0);
            }
            if(joystick.getStatusButton("right")) {
                robot.move(0, rs, 0);
            }

            if(joystick.getStatusButton("LT")) {
                robot.move(0, 0, s);
            }
            if(joystick.getStatusButton("RT")) {
                robot.move(0, 0, rs);
            }

            if(joystick.getStatusButton("10")){
                System.exit(0);
            }
        }


    }


}
