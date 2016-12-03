import javax.swing.*;
import java.awt.*;



public class MainFrame extends JFrame {

    protected final CameraWidget cameraComponent;


    protected final JPanel centerPanel;

            public  MainFrame(Robot robot){


            cameraComponent = new CameraWidget(robot);


            centerPanel = new JPanel();
            centerPanel.setLayout( new BoxLayout(centerPanel, BoxLayout.X_AXIS) );
            centerPanel.add(Box.createRigidArea(new Dimension(0, 0)));
            centerPanel.add( cameraComponent );

            Container content = getContentPane();
            content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS) );
            content.add( centerPanel );

            //Place this component in the middle of the screen
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension dGuiSize = new Dimension( 640, 480 );
            setSize( dGuiSize );
            setLocation( (d.width - dGuiSize.width) / 2, (d.height - dGuiSize.height) / 2 );
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

            setVisible( true );
    }

}



/**
 * This widget listens for camera events and displays incoming images.
 */
        class CameraWidget extends JComponent
{
        private volatile Image cameraImg;

        public CameraWidget(Robot robot)
        {

                robot.addListener( new CameraStreamImpl());

                setMinimumSize( new Dimension(32, 24) );
                setPreferredSize( new Dimension(320, 240) );
                setMaximumSize( new Dimension(Short.MAX_VALUE, Short.MAX_VALUE) );

                setDoubleBuffered( true );
        }

        @Override
        protected void paintComponent(Graphics g)
        {
                if(cameraImg != null)
                {
                        g.drawImage( cameraImg, 0, 0, getWidth(), getHeight(), null );
                }
                else
                {
                        g.setColor( Color.BLACK );
                        g.fillRect( 0, 0, getWidth(), getHeight() );
                        g.setColor( Color.WHITE );
                        g.drawString( "No camera image", getWidth() / 2 - 50, getHeight() / 2 - 5 );
                }
        }

        private class CameraStreamImpl implements CameraStream
        {
                @Override
                public void onImageReceived(Image img)
                {
                        cameraImg = img;
                        repaint();
                }


        }
}