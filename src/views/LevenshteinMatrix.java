package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.LevenshteinController;
import viewModels.LevenshteinViewModel;

public class LevenshteinMatrix extends JPanel {
	private String word1;
	private String word2;
	private JLabel grid[][];
	
	public LevenshteinMatrix(String word1, String word2) {
		this.word1 = word1;
		this.word2 = word2;
		this.init((word1.length() * 2) + 2, (word2.length() * 2) + 2);
	}
	
	public void init(int row, int column) {
		setLayout(new GridLayout(row, column));
		this.grid = new JLabel[row][column];
		char[] seq1 = word1.toCharArray();
		char[] seq2 = word2.toCharArray();
		LevenshteinViewModel result = new LevenshteinController().getLevenshteinMatrix(word1, word2);
		
		if(result == null) {
			JOptionPane.showMessageDialog(null, "Please enter words");
		} else {
			
			System.out.print(" " + "\t"  + " " + "\t");
			
			for(int i = 0; i < seq2.length; i++) 
			{
				System.out.print(seq2[i] + "\t");
			}
			
			System.out.println();
			
			for(int i = 0; i < result.matrix.length; i++) 
			{
				if(i != 0) 
				{
					System.out.print(seq1[i - 1] + "\t");
				}
				else 
				{
					System.out.print(" " + "\t");
				}
				
				for(int j = 0; j < result.matrix[0].length; j++) 
				{
					if(i != 0 && j != 0)
					{
						System.out.print(result.matrix[i][j] + "" + result.direction[i - 1][j - 1] + "\t");
					}
					else 
					{
						System.out.print(result.matrix[i][j] + "\t");
					}
				}
				
				System.out.println();
			}
			
			//initialize cells
			for(int i = 0; i < row; i++) {
		        for(int j = 0; j < column; j++) {
		            grid[i][j] = new JLabel();
		            grid[i][j].setHorizontalAlignment(JLabel.CENTER);
		            grid[i][j].setVerticalAlignment(JLabel.CENTER);
		            grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		            grid[i][j].setOpaque(true);
		        }
			}
			
			//add both words
			
			//add every cell (row)
			int letter = 0;
			for(int i = 3; i < column; i++) {
				if(i % 2 == 1) {
					grid[0][i].setText(seq2[letter] + "");
					letter++;
				}
			}
			
			//add every cell (column)
			letter = 0;
			for(int i = 3; i < row; i++) {
				if(i % 2 == 1) {
					grid[i][0].setText(seq1[letter] + "");
					letter++;
				}
			}
			
			//add values
			int hor = 0;
			int vert = 0;
			for(int i = 1; i < row; i++) {
				if(hor >= result.matrix.length - 1) {
					hor = 0;
					vert++;
				}
				for(int j = 1; j < column; j++) {
					if(i % 2 == 1 && j % 2 == 1) {
						grid[i][j].setText(Integer.toString(result.matrix[vert][hor]));
						hor++;
					}
				}
			}
			
			//add directions
			hor = 0;
			vert = 0;
			for(int i = 3; i < row; i++) {
				if(hor >= result.direction.length - 1) {
					hor = 0;
					vert++;
				}
				for(int j = 3; j < column; j++) {
					if(i % 2 == 1 && j % 2 == 1) {
						if(result.direction[vert][hor].contains("D-") || result.direction[vert][hor].contains("D+")) {
							grid[i - 1][j - 1] = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/ressource/arrow_southeast.svg.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
							grid[i - 1][j - 1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						}
						if(result.direction[vert][hor].contains("U")) {
							grid[i - 1][j] = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/ressource/arrow_south.svg.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
							grid[i - 1][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						}
						if(result.direction[vert][hor].contains("L")) {
							grid[i][j - 1] = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/ressource/arrow_east.svg.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
							grid[i][j - 1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						}
						hor++;
					}
				}
			}
			
			//add every cell
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < column; j++) {
					this.add(grid[i][j]);
				}
			}
		}
	}
}
