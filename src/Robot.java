import rec.robotino.api2.Com;
import rec.robotino.api2.OmniDrive;
import rec.robotino.api2.Camera;

import java.awt.*;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;


public class Robot {

    private final Camera _camera;
    private final Com _com;
    private final OmniDrive _omniDrive;

    protected final Collection<CameraStream> _cameraStream;

   public Robot() {
       _camera = new MyCamera();
       _com = new MyCom();
       _omniDrive = new OmniDrive();

       _omniDrive.setComId(_com.id());
        _camera.setComId(_com.id());

       _cameraStream = new CopyOnWriteArrayList<CameraStream>();
   }

   public void connect(String hostname, boolean block){

       System.out.print("Connecting...");
       _com.setAddress(hostname);
       _com.connectToServer(block);
    }

    class MyCom extends Com
    {
        Timer _timer;

        public MyCom()
        {
            _timer = new Timer();
            _timer.scheduleAtFixedRate(new OnTimeOut(), 0, 5);
        }

        class OnTimeOut extends TimerTask
        {
            public void run()
            {
                processEvents();
            }
        }

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
            for(CameraStream listener : _cameraStream) {
                listener.onImageReceived(img);
            }
        }
    }




    public void addListener(CameraStream listener)
    {
        _cameraStream.add( listener );
    }

    public void removeListener(CameraStream listener)
    {
        _cameraStream.remove( listener );
    }


}
