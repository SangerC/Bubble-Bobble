package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

import drawables.Drawable;

public class Level extends JPanel{

	private ArrayList<Drawable> drawables;
	private Color backgroundColor=Color.cyan;
	
	
	public Level(String fileName){
		
		FileReader file=null;
		try {
			file = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner s = new Scanner(file);
		while(s.hasNext()) {
			String line = s.next();
			if(line.contains("backgroundColor")) {
				int r;
				int b;
				int g;
				for(int i=0;i<line.length();i++){
					if(line.charAt(i)=='='){
						for(int j=i+1;j<line.length();j++){
							if(line.charAt(j)==','){
								r=Integer.parseInt(line.substring(i+1,j));
								System.out.println(r);
								break;
							}
							
						}
						break;
					}
				}
			}
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0,0,1280,720);
	}
}
