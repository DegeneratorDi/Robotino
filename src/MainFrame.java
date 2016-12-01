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
