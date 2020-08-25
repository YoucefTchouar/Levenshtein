package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LevenshteinPanel extends JPanel {
	private JLabel insertWord1;
	private JTextField word1;
	private JLabel insertWord2;
	private JTextField word2;
	private JButton okBtn;
	private JPanel matrix;
	
	public LevenshteinPanel() {
		this.init();
	}
	
	private void init() {
		JPanel insertWords = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.insertWord1 = new JLabel("Please insert your first word");
		this.word1 = new JTextField();
		this.setBackground(Color.WHITE);
		insertWords.add(insertWord1);
		this.word1.setPreferredSize(new Dimension(100, 25));
		insertWords.add(word1);
		this.insertWord2 = new JLabel("Please insert your second word");
		this.word2 = new JTextField();
		insertWords.add(insertWord2);
		this.word2.setPreferredSize(new Dimension(100, 25));
		insertWords.add(word2);
		this.okBtn = new JButton("Ok");
		this.okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				generateMatrix(word1.getText(), word2.getText());
			}});
		insertWords.add(this.okBtn);
		this.add(insertWords);
		this.matrix = new JPanel();
	}
	
	private void generateMatrix(String word1, String word2) {
		this.remove(this.matrix);
		this.matrix = new LevenshteinMatrix(word1, word2);
		this.add(this.matrix);
		this.matrix.revalidate();
	}
}
