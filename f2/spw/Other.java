package f2.spw;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Other{
    JFrame frame1;
    public Other(){
       frame1 = new JFrame("SPACE WAR");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(825, 640);
        frame1.getContentPane().setLayout(new BorderLayout());
        
        JButton bt = new JButton("START !!");
        bt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setVisible(false);
                JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(825, 640);
		GridLayout gl = new GridLayout(1, 2);
		gl.setHgap(5);

		frame.getContentPane().setLayout(gl);

		SpaceShip v = new SpaceShip(180, 580, 20, 20);
		SpaceShip v1 = new SpaceShip(180, 580, 20, 20);

		GamePanel gp = new GamePanel();
		GamePanel gp1 = new GamePanel();

		GameEngine engine = new GameEngine(gp, v);
		GameEngine1 engine1 = new GameEngine1(gp1, v1);
		
		frame.addKeyListener(engine);
		frame.addKeyListener(engine1);
		frame.add(gp1);
		frame.add(gp);
		frame.setVisible(true);
		
		engine.start();
		engine1.start();
            }
        });

        frame1.add(bt, BorderLayout.CENTER);
        frame1.setVisible(true);
    }
}