import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class snake {

    private static Robot rob;
    private static Robot robert;
    private static int Multiplier = 0;
    private static int Multiplier2 = 0;
    public static JFrame frmSnakegame;
    private static JLabel lblDifficulty;
    private static JButton btnStartGame;
    private static int direction2;
    private static boolean run;
    private static boolean press;
    private static JSpinner spinner_1;
    private static JSpinner spinner;
    private static int points=0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    snake window = new snake();
                    window.frmSnakegame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public snake() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frmSnakegame = new JFrame();
        frmSnakegame.setTitle("SnakeGame");
        frmSnakegame.setBounds(100, 100, 200, 250);
        frmSnakegame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSnakegame.setResizable(false);
        SpringLayout springLayout = new SpringLayout();
        frmSnakegame.getContentPane().setLayout(springLayout);
        frmSnakegame.setFocusable(true);
        frmSnakegame.setVisible(true);
        frmSnakegame.setFocusableWindowState(true);

        btnStartGame = new JButton("Start");
        springLayout.putConstraint(SpringLayout.NORTH, btnStartGame, -70, SpringLayout.SOUTH, frmSnakegame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnStartGame, 10, SpringLayout.WEST, frmSnakegame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnStartGame, -10, SpringLayout.SOUTH, frmSnakegame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnStartGame, -10, SpringLayout.EAST, frmSnakegame.getContentPane());
        frmSnakegame.getContentPane().add(btnStartGame);
        btnStartGame.setFocusable(true);


        spinner = new JSpinner();
        springLayout.putConstraint(SpringLayout.WEST, spinner, 0, SpringLayout.WEST, btnStartGame);
        springLayout.putConstraint(SpringLayout.SOUTH, spinner, -13, SpringLayout.NORTH, btnStartGame);
        springLayout.putConstraint(SpringLayout.EAST, spinner, -100, SpringLayout.EAST, btnStartGame);
        frmSnakegame.getContentPane().add(spinner);
        spinner.setValue(1);
        spinner.setVisible(false);

        lblDifficulty = new JLabel("Difficulty:");
        springLayout.putConstraint(SpringLayout.SOUTH, lblDifficulty, 0, SpringLayout.NORTH, spinner);
        springLayout.putConstraint(SpringLayout.EAST, lblDifficulty, 65, SpringLayout.WEST, spinner);
        frmSnakegame.getContentPane().add(lblDifficulty);
        lblDifficulty.setVisible(false);

        spinner_1 = new JSpinner();
        springLayout.putConstraint(SpringLayout.WEST, spinner_1, 0, SpringLayout.WEST, btnStartGame);
        springLayout.putConstraint(SpringLayout.SOUTH, spinner_1, -3, SpringLayout.NORTH, lblDifficulty);
        springLayout.putConstraint(SpringLayout.EAST, spinner_1, 0, SpringLayout.EAST, spinner);
        frmSnakegame.getContentPane().add(spinner_1);

        JLabel lblSkips = new JLabel("Level:");
        springLayout.putConstraint(SpringLayout.WEST, lblSkips, 0, SpringLayout.WEST, btnStartGame);
        springLayout.putConstraint(SpringLayout.SOUTH, lblSkips, 0, SpringLayout.NORTH, spinner_1);
        frmSnakegame.getContentPane().add(lblSkips);

        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Multiplier = (int) spinner.getValue();
            }
        });

        spinner_1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Multiplier2 = (int) spinner.getValue();
            }
        });

        frmSnakegame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && run && direction2 != 2) {
                    direction2 = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && run && direction2 != 1) {
                    direction2 = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && run && direction2 != 4) {
                    direction2 = 3;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && run && direction2 != 3) {
                    direction2 = 4;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE && run) {
                    direction2 = -1;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && run){
                    press = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE && run){
                    press = true;
                }

            }

        });


        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!run) {
                    run = true;
                    food.launch();
                    snake.returnFocus();
                } else if(run) {
                    direction2 = -1;
                    run = false;
                }
            }
        });

        moveMouse();
    }

    public int getDirection() {
        return direction2;
    }

    public static boolean inBounds (){
        if (MouseInfo.getPointerInfo().getLocation().x > food.screenSize.getWidth() - 2 || (MouseInfo.getPointerInfo().getLocation().y
                > food.screenSize.getHeight() - 2) || MouseInfo.getPointerInfo().getLocation().x < 2  || MouseInfo.getPointerInfo().getLocation().y < 2) {
            return false;
        }
        return true;
    }

    public static void returnFocus(){
        int storeDirection = direction2;
        direction2 = -1;
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        try {
            robert = new Robot();
        }
        catch(Exception e1) {
            //  Block of code to handle errors
        }

        robert.mouseMove(frmSnakegame.getX()+50,frmSnakegame.getY()+50);
        robert.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robert.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robert.mouseMove(x,y);
        frmSnakegame.requestFocus();
        direction2 = storeDirection;
    }

    public static void moreSpeeed(){
        if((int)spinner.getValue() < 5){
            spinner.setValue((int)spinner.getValue()+1);
            Multiplier = (int)spinner.getValue()+1;
        } else if ((int)spinner.getValue() == 5){
            spinner.setValue(0);
            Multiplier = 0;
            spinner_1.setValue((int)spinner_1.getValue()+1);
            Multiplier2 = (int)spinner_1.getValue()+1;
        }
        points+=10;
        lblDifficulty.setText(String.valueOf(points));
        lblDifficulty.setVisible(true);
    }

    public void moveMouse(){

        SwingWorker<Integer, Integer> scan = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {

                try {
                    rob = new Robot();
                }
                catch(Exception e1) {
                    //  Block of code to handle errors
                }

                int direction = getDirection();
                boolean loop = true;

                while(loop) {
                    direction = getDirection();

                    if(press) {
                        if(MouseInfo.getPointerInfo().getLocation().x >= food.getXCoord() && MouseInfo.getPointerInfo().getLocation().x <= food.getXCoord()+150){
                            if(MouseInfo.getPointerInfo().getLocation().y >= food.getYCoord() && MouseInfo.getPointerInfo().getLocation().y <= food.getYCoord()+150){
                                food.goodClick();
                            }

                        }
                        press = false;
                    }

                    if(direction == 1) {
                        rob.mouseMove(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y-(1+Multiplier2));
                        try {
                            Thread.sleep(1+(6-Multiplier));
                        }
                        catch(Exception e2) {
                            //  Block of code to handle errors
                        }
                    }

                    if(direction == 2) {
                        rob.mouseMove(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y+(1+Multiplier2));
                        try {
                            Thread.sleep(1+(6-Multiplier));
                        }
                        catch(Exception e2) {
                            //  Block of code to handle errors
                        }
                    }

                    if(direction == 3) {
                        rob.mouseMove(MouseInfo.getPointerInfo().getLocation().x-(1+Multiplier2), MouseInfo.getPointerInfo().getLocation().y);
                        try {
                            Thread.sleep(1+(6-Multiplier));
                        }
                        catch(Exception e2) {
                            //  Block of code to handle errors
                        }
                    }

                    if(direction == 4) {
                        rob.mouseMove(MouseInfo.getPointerInfo().getLocation().x+(1+Multiplier2), MouseInfo.getPointerInfo().getLocation().y);
                        try {
                            Thread.sleep(1+(6-Multiplier));
                        }
                        catch(Exception e2) {
                            //  Block of code to handle errors
                        }
                    }

                    loop = inBounds();

                }

                //show leaderboard
                return null;

            }
        };
        scan.execute();
    }
}

																																																