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
	public long second = 15;
	public long originalSeconds = 15;

	void start(boolean gameMode) {
		initTime = System.currentTimeMillis();
		stop = false;
		thread = new Thread(this);
		thread.start();
		if (gameMode) {
			originalSeconds = 180;
		}
		else {
			originalSeconds = 15;
		}
		second = originalSeconds;
	}
	
	public void setSeconds(int newSeconds) {
		this.originalSeconds = newSeconds;
		this.second = newSeconds;

	}

	
	public long getTime() {
		return this.second;
	}
	
	public long getOriginalSeconds() {
		return this.originalSeconds;
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

		if (second > -1 && !stop) {
			long elasped = System.currentTimeMillis() - initTime;
			System.out.println(originalSeconds + " || " + second);
			g.setColor(Color.RED);
			g.setFont(new Font("Verdana", Font.PLAIN, 30));
			
			second = getOriginalSeconds() - (elasped / 1000);
			System.out.println(elasped + "  init: " + initTime + " s: " +  second);
			int timeInt = (int) second;
			String time = " " + timeInt;
			
			if(getOriginalSeconds() - (elasped)/1000 < -2) {
				g.drawString("Timer: " + getOriginalSeconds(), 5, 35);
			} else {
				g.drawString("Timer:" + time, 5, 35);
			}
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