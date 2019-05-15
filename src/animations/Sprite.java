package animations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import screen.Level;

public class Sprite {
	
	private String currentAnimation;
	private HashMap<String,ArrayList<Image>> animations;
	private int currentAnimationIndex;
	private Level level;
	
	public Sprite(String FolderName, Level level) {
		this.animations = new HashMap<String,ArrayList<Image>>();
		
		ArrayList<Image> idle=new ArrayList<Image>();
		addAnimation(FolderName+"/idle",idle);
		animations.put("idle",idle);
		
		ArrayList<Image> runRight=new ArrayList<Image>();
		addAnimation(FolderName+"/runRight",runRight);
		animations.put("runRight",runRight);
		
		ArrayList<Image> runLeft=new ArrayList<Image>();
		addAnimation(FolderName+"/runLeft",runLeft);
		
		ArrayList<Image> die=new ArrayList<Image>();
		addAnimation(FolderName+"/die",die);
		
		ArrayList<Image> shoot=new ArrayList<Image>();
		addAnimation(FolderName+"/shoot",shoot);
		
		ArrayList<Image> jump=new ArrayList<Image>();
		addAnimation(FolderName+"/jump",jump);
		
		ArrayList<Image> fall=new ArrayList<Image>();
		addAnimation(FolderName+"/fall",fall);
		
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
		g2.drawImage(this.animations.get(this.currentAnimation).get(this.currentAnimationIndex),0,0,level);
	}

	public void update(){
		if(this.currentAnimationIndex==this.animations.get(this.currentAnimation).size()-1&&!this.currentAnimation.equals("die")&&!this.currentAnimation.equals("shoot")) {
			this.currentAnimationIndex=0;
		}
		else if(this.currentAnimationIndex==this.animations.get(this.currentAnimation).size()-1&&this.currentAnimation.equals("die")&&this.currentAnimation.equals("shoot")){

		}
		else{
			this.currentAnimationIndex++;
		}
	}
	
	public void setCurrentAnimation(String animation){
		this.currentAnimation=animation;
	}
	
	public String getCurrentAnimation() {
		return this.currentAnimation;
	}
	
	public int getCurrentAnimationIndex() {
		return this.currentAnimationIndex;
	}
	
	public HashMap<String, ArrayList<Image>> getAnimations(){
		return this.animations;
	}
}
