package screen;

/**
 * The level screen class for the arcade game.
 * 
 * 
 * Creates the level layout and specifications
 * 
 * 
 * @author Cullen LaKemper and Tim Wilson
 *
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import drawables.Drawable;
import drawables.Enemy;
import drawables.Hero;
import drawables.Obstacle;

public class Level extends JPanel{

	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles;
	private Color backgroundColor;
	private Image backgroundImage;
	private Hero hero;
	
	public Level(String fileName){
		FileReader file=null;
		try {
			file = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner s = new Scanner(file);
		while(s.hasNext()) {
			String line = s.next();
			if(line.contains("backgroundColor")) {
				String setting = readAfter(line,'=');
				int r=Integer.valueOf(readParameter(setting,0));
				int g=Integer.valueOf(readParameter(setting,1));
				int b=Integer.valueOf(readParameter(setting,2));
				this.backgroundColor = new Color(r,g,b);
			}
			else if(line.contains("backgroundImage")){
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				this.backgroundImage = toolkit.getImage(readAfter(line,'='));
			}
			else if(line.contains("Hero")){
				String setting = readAfter(line,'(');
				this.hero = new Hero(Double.valueOf(readParameter(setting,0)),
									 Double.valueOf(readParameter(setting,1)),
									 Double.valueOf(readParameter(setting,2)),
									 Double.valueOf(readParameter(setting,3)));
			}
			else if(line.contains("obstacles")){
				line = s.next();
				obstacles = new ArrayList<Obstacle>();
				while((!line.contains("}")) && (s.hasNext())){
					String setting = readAfter(line,'(');
					obstacles.add(new Obstacle(Double.valueOf(readParameter(setting,0)),
											   Double.valueOf(readParameter(setting,1)),
											   Integer.valueOf(readParameter(setting,2)),
											   Integer.valueOf(readParameter(setting,3)),
											   new Color(Integer.valueOf(readParameter(setting,4)),Integer.valueOf(readParameter(setting,5)),Integer.valueOf(readParameter(setting,6))),
											   readParameter(setting,7)
					));
					line = s.next();
				}
			}
			
			
			
			
			
			
			
		}		
		
		
		
		
		
		
		
		
		this.setBackground(backgroundColor);
		this.repaint();
	}
	
	public String readAfter(String line, char marker){
		for(int i=0;i<line.length();i++){
			if(line.charAt(i)==marker){
				return line.substring(i+1,line.length());
			}
		}
		return null;
	}
	public String readParameter(String line, int parameter){
		for(int i=0;i<line.length();i++){
			if((line.charAt(i)==','||line.charAt(i)==';'||line.charAt(i)==')')&&parameter==0){
				return line.substring(0,i);
			}
			else if(line.charAt(i)==','&&parameter>0){
				return readParameter(line.substring(i+1,line.length()),parameter-1);
			}	
		}
		return null;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(this.backgroundImage, 0, 0,this);
		this.hero.draw(g2);
		for(Obstacle ob :this.obstacles){
			ob.draw(g2);
		}
	}	
	public Hero getHero() {
		return this.hero;
	}
	public void update(){
		this.hero.update();
	}

	public void checkCollisons() {
		this.hero.checkCollision(obstacles);
		
	}
}
