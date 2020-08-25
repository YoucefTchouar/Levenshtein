package algorithms;

public class Levenshtein 
{
	private static final int INSERTION = -2;
	private static final int SUPPRESSION = -2;
	private static final int CORRESPONDANCE = 1;
	private static final int SUBSTITUTION = -1;
	
	private int[][] matrix;
	private String[][] direction;
	
	public void levenshtein(String sseq1, String sseq2) 
	{
		char[] seq1 = sseq1.toCharArray();
		char[] seq2 = sseq2.toCharArray();
		int[][] matrix = new int[seq1.length + 1][seq2.length + 1];
		String[][] direction = new String[seq1.length][seq2.length];
		
		matrix[0][0] = 0;
		
		for(int i = 1; i < seq1.length + 1; i++)
		{
			matrix[i][0] = matrix[i - 1][0] + INSERTION; 
		}
		
		for(int i = 1; i < seq2.length + 1; i++)
		{
			matrix[0][i] = matrix[0][i - 1] + SUPPRESSION;
		}
		
		int indexSeq1 = 0;
		int indexSeq2 = 0;
		
		for(int i = 1; i < seq1.length + 1; i++) 
		{
			for(int j = 1; j < seq2.length + 1; j++) 
			{
				if(seq1[i - 1] != seq2[j - 1]) 
				{
					matrix[i][j] = Math.max(matrix[i - 1][j] + INSERTION, Math.max(matrix[i - 1][j - 1] + SUBSTITUTION, matrix[i][j - 1] + SUPPRESSION));
					
					if(matrix[i][j] == matrix[i - 1][j] + INSERTION) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "U" : "U";
					}
					if(matrix[i][j] == matrix[i - 1][j - 1] + SUBSTITUTION) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "D-" : "D-";
					}
					if(matrix[i][j] == matrix[i][j - 1] + SUPPRESSION) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "L" : "L";
					}
				}
				else 
				{
					matrix[i][j] = Math.max(matrix[i - 1][j] + INSERTION, Math.max(matrix[i - 1][j - 1] + CORRESPONDANCE, matrix[i][j - 1] + SUPPRESSION));
					
					if(matrix[i][j] == matrix[i - 1][j] + INSERTION) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "U" : "U";
					}
					if(matrix[i][j] == matrix[i - 1][j - 1] + CORRESPONDANCE) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "D+" : "D+";
					}
					if(matrix[i][j] == matrix[i][j - 1] + SUPPRESSION) 
					{
						direction[indexSeq1][indexSeq2] = (direction[indexSeq1][indexSeq2] != null) ? direction[indexSeq1][indexSeq2] + "L" : "L";
					}
				}
				
				indexSeq2++;
			}
			
			indexSeq2 = 0;
			indexSeq1++;
		}
		
		this.matrix = matrix;
		this.direction = direction;
	}
	
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	public String[][] getDirection() {
		return this.direction;
	}
}
