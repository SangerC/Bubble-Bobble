package animations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import screen.Level;

public class Sprite {
	
	private ArrayList<Image> idle;
	private ArrayList<Image> runRight;
	private ArrayList<Image> runLeft;
	private ArrayList<Image> die;
	private ArrayList<Image> shoot;
	private ArrayList<Image> jump;
	private ArrayList<Image> fall;
	private ArrayList<Image> currentAnimation;
	private int currentAnimationIndex;
	private Level level;
	
	public Sprite(String FolderName, Level level) {
		
		this.idle=new ArrayList<Image>();
		addAnimation(FolderName+"/idle",this.idle);
		
		this.runRight=new ArrayList<Image>();
		addAnimation(FolderName+"/runRight",this.runRight);
		
		this.runLeft=new ArrayList<Image>();
		addAnimation(FolderName+"/runLeft",this.runLeft);
		
		this.die=new ArrayList<Image>();
		addAnimation(FolderName+"/die",this.die);
		
		this.shoot=new ArrayList<Image>();
		addAnimation(FolderName+"/shoot",this.shoot);
		
		this.jump=new ArrayList<Image>();
		addAnimation(FolderName+"/jump",this.jump);
		
		this.fall=new ArrayList<Image>();
		addAnimation(FolderName+"/fall",this.fall);
		
		this.currentAnimationIndex=0;
		this.level=level;
	}
	
	public void addAnimation(String Folder, ArrayList<Image> list){
		File folder = new File(Folder);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			try {
				list.add(ImageIO.read(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void draw(Graphics2D g2){
		g2.drawImage(currentAnimation.get(currentAnimationIndex),0,0,level);
	}

	public void update(){
		if(this.currentAnimationIndex==this.currentAnimation.size()-1) {
			this.currentAnimationIndex=0;
		}
		else {
			this.currentAnimationIndex++;
		}
	}
	
	public void changeAnimation(String animation){
		if(animation.equals("idle")) {
			this.currentAnimation=this.idle;
		}
		else if(animation.equals("runRight")) {
			this.currentAnimation=this.runRight;
		}
		else if(animation.equals("runLeft")) {
			this.currentAnimation=this.runLeft;
		}
		else if(animation.equals("die")) {
			this.currentAnimation=this.die;
		}
		else if(animation.equals("shoot")) {
			this.currentAnimation=this.shoot;
		}
		else if(animation.equals("jump")) {
			this.currentAnimation=this.jump;
		}
		else if(animation.equals("fall")) {
			this.currentAnimation=this.fall;
		}
		
	}
	
}
