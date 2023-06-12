package Components;

import javax.swing.JFrame;

public class QuizFrameThread implements Runnable {

	@Override
	public void run() {
		JFrame frame = new QuizFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}

}
