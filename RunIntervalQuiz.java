package Components;

public class RunIntervalQuiz {
	
	public static void main(String[] args) {
		QuizFrameThread quizFrameThread = new QuizFrameThread();
		Thread thread = new Thread(quizFrameThread);
		thread.start();
	}
}
