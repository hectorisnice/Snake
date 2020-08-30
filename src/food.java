import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Random;

public class food {

    private static JFrame frmfood;
    public static food window;
    private Random r = new Random();
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public static void launch() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new food();
                    window.frmfood.setVisible(true);
                    snake.returnFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public food() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmfood = new JFrame();

        frmfood.setTitle("");
        frmfood.setBounds((int)(r.nextDouble()*(screenSize.getWidth() - (snake.frmSnakegame.getX() + snake.frmSnakegame.getWidth()) - 150 ) + (snake.frmSnakegame.getX() + snake.frmSnakegame.getWidth()) ), (int)(r.nextDouble()*(screenSize.getHeight()-150)), 150, 150);
        frmfood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmfood.setResizable(false);
        SpringLayout springLayout = new SpringLayout();
        frmfood.getContentPane().setLayout(springLayout);

        JLabel lblNom = new JLabel("nom");
        springLayout.putConstraint(SpringLayout.NORTH, lblNom, 10, SpringLayout.NORTH, frmfood.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblNom, 10, SpringLayout.WEST, frmfood.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblNom, -10, SpringLayout.SOUTH, frmfood.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, lblNom, -10, SpringLayout.EAST, frmfood.getContentPane());
        frmfood.getContentPane().add(lblNom);

    }

    public static int getXCoord(){
        return frmfood.getX();
    }

    public static int getYCoord(){
        return frmfood.getY();
    }

    public static void goodClick(){
        frmfood.hide();
        snake.moreSpeeed();
        launch();
    }
}
