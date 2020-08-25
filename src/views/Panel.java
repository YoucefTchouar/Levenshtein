package views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	private JComboBox<String> comboBox;
	private JLabel algoLabel;
	private JPanel algoPanel;
	private JPanel selectAlgo;
	
	public Panel() {
		this.init();
	}
	
	private void init() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.comboBox = new JComboBox<String>(new String[] {"Levenshtein"});
		this.algoLabel = new JLabel("Choose your algorithm");
		this.selectAlgo = new JPanel();
		this.selectAlgo.setBackground(Color.WHITE);
		this.selectAlgo.add(this.algoLabel);
		this.selectAlgo.add(this.comboBox);
		this.add(this.selectAlgo);
		
		switch(this.comboBox.getSelectedItem().toString()) {
			case "Levenshtein":
				this.algoPanel = new LevenshteinPanel();
		}
		
		this.add(algoPanel);
	}
}
