package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private int width;
	private int height; 
	private Panel panel;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Frame() {
		this.init();
	}
	
	private void init() {
		this.width = (int) (screenSize.getWidth() / 3);
		this.height = (int) (screenSize.getHeight() / 3);
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(this.width, this.height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new Panel();
		this.setContentPane(this.panel);
		this.setVisible(true);
	}
}
