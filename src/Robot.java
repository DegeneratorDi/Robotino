import rec.robotino.api2.Com;
import rec.robotino.api2.OmniDrive;



public class Robot {


    private final Com _com;
    private final OmniDrive _omniDrive;

   public Robot() {
       _com = new Com();
       _omniDrive = new OmniDrive();

       _omniDrive.setComId(_com.id());
    }

   public void connect(String hostname, boolean block){

       System.out.print("Connecting...");
       _com.setAddress(hostname);
       _com.connectToServer(block);
    }

    public void move(float x, float y, float h) {

       _omniDrive.setVelocity(x,y,h);
       try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _omniDrive.setVelocity(0,0,0);
    }


}
