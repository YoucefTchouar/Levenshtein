package viewModels;

public class LevenshteinViewModel {
	public int[][] matrix;
	public String[][] direction;
	
	public LevenshteinViewModel(int[][] matrix, String[][] direction) {
		this.matrix = matrix;
		this.direction = direction;
	}
}
