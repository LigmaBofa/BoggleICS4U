import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer15 extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel timer;
	Thread thread;
	static long initTime = System.currentTimeMillis();
	boolean stop;
	public static long second = -1;

	void start() {
		initTime = System.currentTimeMillis();
		stop = false;
		thread = new Thread(this);
		thread.start();
		second = 15;

	}
	
	public void setSeconds(int newSeconds) {
		this.second = newSeconds;
	}

	public long getTime() {
		return this.second;
	}
	
//	void pause() {
		
//	}
	
//	void continue1() {
		
//	}
	
	void stop() {
		stop = true;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	//	long elasped = System.currentTimeMillis() - initTime;
		
	//	counter = 15;
		
		if (second > -1 && !stop) {
			long elasped = System.currentTimeMillis() - initTime;
			second = 15 - (elasped / 1000);
			System.out.println(elasped + "  init: " + initTime + " s: " +  second);
			int timeInt = (int) second;
			String time = " " + timeInt;
			g.setColor(Color.RED);
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			g.drawString("Timer:" + time, 5, 35);
		} else {
			g.setColor(Color.RED);
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			if(second == -1) {
				g.drawString("Timer: 15", 5, 35);
			} else g.drawString("Timer: " + second, 5, 35);
			
		}
	}
	
	public void run() {

		try {
			while (!stop) {
				Thread.sleep(100);
				repaint();
			}
		//	g.drawString("Timer: " + second, 5, 35);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	long time = System.currentTimeMillis();
	long elasped = System.currentTimeMillis() - time;
	long second = 0;
	counter = 15;
	
	while (second > - 1) {
		elasped = System.currentTimeMillis() - time;;
		second = 15 - elasped / 1000;
		
		if (counter - 1 == second) {
			int num = (int) counter;
			return num;
			
		}
		counter = second;
	}*/
	
	static long counter;
	public Timer15() {
		this.initTime = 0;
		this.setBackground(Color.WHITE);
		
		/*timer = new JLabel("timer:");
		timer.setFont(new Font("Helvetica", Font.BOLD, 20));
		setVisible(true);
		
	//	long time = System.currentTimeMillis();
	//	long elasped = System.currentTimeMillis() - initTime;
		long elasped;
		long second = 0;
		counter = 15;
		
		while (second > - 1) {
			elasped = System.currentTimeMillis() - initTime;
			second = 15 - elasped / 1000;
			
			if (counter - 1 == second) {
			//	int num = (int) counter;
				timer.setText("timer: " + counter);
				System.out.println(counter);
				
			}
			counter = second;
		}*/
		
	}

}

/*
 * public class Timer {
 * 
 * public static void main(String[] args) throws InterruptedException {
 * 
 * 
 * long time = System.currentTimeMillis(); long elasped =
 * System.currentTimeMillis() - time; long second = 0; long counter = 15;
 * 
 * while (second > - 1) { elasped = System.currentTimeMillis() - time;; second =
 * 15 - elasped / 1000;
 * 
 * if (counter - 1 == second) { System.out.println(counter); } counter = second;
 * }
 * 
 * 
 * }
 * 
 * }
 */