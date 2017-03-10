import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private String[][] answers;

	public AnswerSheet(int numQuestionsPerSheet, int numSheets) {

		answers = new String[numQuestionsPerSheet][numSheets];

	}

	public String[][] getAnswers() {
		return answers;
	}

	public void setAnswers(String[][] answers) {
		this.answers = answers;
	}

	//
	// public int getId() {
	// return id;
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }
	//
	public void addAnswer(int r, int c, String a) {
		answers[r][c] = a;
	}

	public void displayAnswers() {
		for (int i = 0; i < answers.length; i++) {
			for (int j = 0; j < answers[0].length; j++) {
				System.out.print(i + ": " + answers[i][j]);
			}
		}
	}

}
