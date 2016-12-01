import rec.robotino.api2.Com;
import rec.robotino.api2.OmniDrive;
import rec.robotino.api2.Camera;

import java.awt.*;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;


public class Robot {

    private final Camera _camera;
    private final Com _com;
    private final OmniDrive _omniDrive;

    protected final Collection<RobotListener> _listeners;

   public Robot() {
       _camera = new MyCamera();
       _com = new Com();
       _omniDrive = new OmniDrive();

       _omniDrive.setComId(_com.id());
        _camera.setComId(_com.id());

       _listeners = new CopyOnWriteArrayList<RobotListener>();
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

    class MyCamera extends Camera
    {
        public MyCamera()
        {
            setCameraNumber(0);
            setBGREnabled(true);
        }

        @Override
        public void imageReceivedEvent(Image img, long dataSize, long width, long height, long step)
        {
            for(RobotListener listener : _listeners) {
                listener.onImageReceived(img);
            }
        }
    }




    public void addListener(RobotListener listener)
    {
        _listeners.add( listener );
    }


}
