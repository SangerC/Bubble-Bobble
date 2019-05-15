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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.Timer;

import drawables.Bubble;
import drawables.Bullet;
import drawables.Enemy;
import drawables.Enoth;
import drawables.Fruit;
import drawables.Hero;
import drawables.Inquisitor;
import drawables.Obstacle;
import main.GameMain;

public class Level extends JPanel{

	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Bubble> bubbles;
	private ArrayList<Bullet> bullets;
	private ArrayList<Fruit> fruits;
	private Color backgroundColor;
	private Image backgroundImage;
	private Hero hero;
	private double heroStartX;
	private double heroStartY;
	private Timer youDiedTimer;
	private boolean youDied;
	private GameMain gameMain;
	
	
	public Level(String fileName,GameMain gameMain){
		FileReader file=null;
		this.gameMain=gameMain;
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
									 Double.valueOf(readParameter(setting,3)),
									 Double.valueOf(readParameter(setting,4)));
				this.heroStartX= Double.valueOf(readParameter(setting,0));
				this.heroStartY=Double.valueOf(readParameter(setting,1));
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
			else if(line.contains("enemies")){
				line = s.next();
				this.enemies = new ArrayList<Enemy>();
				while((!line.contains("}")) && (s.hasNext())){
					String setting = readAfter(line,'(');
					if(line.contains("inquisitor")){
						this.enemies.add(new Inquisitor(Double.valueOf(readParameter(setting,0)),
														Double.valueOf(readParameter(setting,1)),
														Double.valueOf(readParameter(setting,2)),
														Double.valueOf(readParameter(setting,3)),
														Double.valueOf(readParameter(setting,4)),
														this));
					}
					else if(line.contains("enoth")){
						this.enemies.add(new Enoth(Double.valueOf(readParameter(setting,0)),
												   Double.valueOf(readParameter(setting,1)),
												   Double.valueOf(readParameter(setting,2)),
												   Double.valueOf(readParameter(setting,3)),
												   Double.valueOf(readParameter(setting,4)),
												   this));
					}
					line = s.next();
				}
			}
		}		
		this.bubbles=new ArrayList<Bubble>();
		this.bullets=new ArrayList<Bullet>();
		this.fruits=new ArrayList<Fruit>();
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
		for(Bubble bub :this.bubbles){
			bub.draw(g2);
		}
		for(Enemy en :this.enemies){
			en.draw(g2);
		}
		for(Bullet bill :this.bullets){
			bill.draw(g2);
		}
		for(Fruit fill :this.fruits){
			fill.draw(g2);
		}
		drawLevelInfo(g2);
		if(youDied){
			g2.setColor(Color.red);
			g2.drawString("You Died", 800, 500);
		}
	}	
	public Hero getHero() {
		return this.hero;
	}
	public void update(){
		ArrayList<Bubble> bubblesToRemove = new ArrayList<Bubble>();
		ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
		ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		ArrayList<Fruit> fruitToRemove = new ArrayList<Fruit>();
		this.hero.update();
		for(Fruit fruit: this.fruits) {
			fruit.update();
			if(fruit.getDie()) {
				fruitToRemove.add(fruit);
			}
		}
		for(Bubble bub :this.bubbles){
			bub.update();
			if(bub.getDie()) {
				bubblesToRemove.add(bub);
			}
		}
		for(Bullet bill :this.bullets){
			bill.update();
			if(bill.getDie()) {
				bulletsToRemove.add(bill);
			}
		}
		for(Enemy en :this.enemies){
			en.update();
			if(en.getDie()) {
				enemiesToRemove.add(en);
			}
		
		}
		for(Bubble bub : bubblesToRemove){
			this.bubbles.remove(bub);
		}
		for(Bullet bill : bulletsToRemove){
			this.bullets.remove(bill);
		}
		for(Fruit fill : fruitToRemove){
			this.fruits.remove(fill);
		}
		for(Enemy en : enemiesToRemove){
			this.enemies.remove(en);
		}
	}

	public void checkCollisons() {
		this.hero.checkCollision(obstacles);
		for(Enemy en :this.enemies){
			en.checkCollision(obstacles);
			if(this.hero.getVulnerable()){
				this.hero.checkCollision(en);	
			}
			for(Bubble bub:this.bubbles){
				if(!bub.getFilled()&&en.getBubble()==null&&en.getVulnerable()) {
					en.checkCollision(bub);
				}
			}
		}
		for(Fruit fill: this.fruits){
			hero.checkCollision(fill);
			fill.checkCollision(obstacles);
		}
		if(this.hero.getVulnerable()) {
			for(Bullet bill: bullets) {
				hero.checkCollision(bill);
			}
		}
	}

	public void blowBubble() {
		if(this.hero.getFacingRight()){
			this.bubbles.add(new Bubble(this.hero.getX()+this.hero.getWidth(),this.hero.getY()-this.hero.getHeight()/2,this.hero.getBubbleColor(),true,this.hero.getBubbleSpeed()));
		}
		else{
			this.bubbles.add(new Bubble(this.hero.getX()-this.hero.getWidth(),this.hero.getY()-this.hero.getHeight()/2,this.hero.getBubbleColor(),false,this.hero.getBubbleSpeed()));
		}
	}
	
	public void addBullet(Bullet bill) {
		this.bullets.add(bill);
	}

	public void addFruit(Fruit fruit) {
		this.fruits.add(fruit);
	}
	public ArrayList<Enemy> getEnemies(){
		return this.enemies;
	}

	public void reset() {
		this.hero.move(this.heroStartX, this.heroStartY);
	}
	private void drawLevelInfo(Graphics2D g2){
		g2.setColor(Color.black);
		g2.drawString("Level: "+this.gameMain.getCurrentLevel()+"        Score: "+this.hero.getScore()+"        Lives:"+this.hero.getLife(), 10, 30);
	}
}
