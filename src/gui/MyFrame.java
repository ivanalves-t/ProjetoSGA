package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

	
	public MyFrame() {
	
	

	this.setTitle("JFrame title goes here"); // sets title of frame
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
	this.setResizable(false); // prevent frame to being resized
	this.setSize(420,420); // sets the x-dimension, and y-dimension to frame
	this.setVisible(true); // make frame visible
	
	ImageIcon image = new ImageIcon("D:\\Downloads\\Images\\logo.jpg"); // create an ImageIcon
	this.setIconImage(image.getImage()); // change icon of frame
	this.getContentPane().setBackground(Color.GREEN); // change color of background
	this.getContentPane().setBackground(new Color(141, 23, 114)); // literally R, G, B code colors to change background
	this.getContentPane().setBackground(new Color(0xcd58ff)); // hexadecimal code colors to change background
	
	
	}
}
