package animations;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import drawables.Entity;
import screen.Level;

public class Sprite {
	
	private static final int UPDATEDELAY=100;
	
	private String currentAnimation;
	private HashMap<String, ArrayList<Image>> animations;
	private int currentAnimationIndex;
	private Timer updateTimer;
	private Level level;
	private int width;
	private int height;
	
	public Sprite(String FolderName, Level level, int width, int height) {
		this.animations = new HashMap<String,ArrayList<Image>>();
		
		ArrayList<Image> idleLeft=new ArrayList<Image>();
		addAnimation(FolderName+"/idleLeft",idleLeft);
		animations.put("idleLeft",idleLeft);
		
		ArrayList<Image> idleRight=new ArrayList<Image>();
		addAnimation(FolderName+"/idleRight",idleRight);
		animations.put("idleRight",idleRight);
		
		ArrayList<Image> runRight=new ArrayList<Image>();
		addAnimation(FolderName+"/runRight",runRight);
		animations.put("runRight",runRight);
		
		ArrayList<Image> runLeft=new ArrayList<Image>();
		addAnimation(FolderName+"/runLeft",runLeft);
		animations.put("runLeft",runLeft);
		
		ArrayList<Image> die=new ArrayList<Image>();
		addAnimation(FolderName+"/die",die);
		animations.put("die",die);
		
		ArrayList<Image> shootLeft=new ArrayList<Image>();
		addAnimation(FolderName+"/shootLeft",shootLeft);
		animations.put("shootLeft",shootLeft);
		
		ArrayList<Image> shootRight=new ArrayList<Image>();
		addAnimation(FolderName+"/shootRight",shootRight);
		animations.put("shootRight",shootRight);
		
		ArrayList<Image> jump=new ArrayList<Image>();
		addAnimation(FolderName+"/jump",jump);
		animations.put("jump",jump);
		
		ArrayList<Image> fall=new ArrayList<Image>();
		addAnimation(FolderName+"/fall",fall);
		animations.put("fall",fall);
		
		this.currentAnimationIndex=0;
		this.currentAnimation="idleLeft";
		this.level=level;
		this.width=width;
		this.height=height;
		this.updateTimer=new Timer(UPDATEDELAY,new UpdateListener(this));
		this.updateTimer.start();
	}
	
	public void addAnimation(String Folder, ArrayList<Image> list){
		File f = new File(Folder);
	    File[] files = f.listFiles();
	    Arrays.sort(files);
		for (File file : files) {
			try {
				list.add(ImageIO.read(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void draw(Graphics2D g2){
		g2.drawImage(this.animations.get(this.currentAnimation).get(this.currentAnimationIndex),0,0,this.width,this.height,level);
	}

	public void update(){
		if(this.currentAnimationIndex==this.animations.get(this.currentAnimation).size()-1&&!this.currentAnimation.equals("die")&&!this.currentAnimation.equals("shoot")&&!this.currentAnimation.equals("jump")) {
			this.currentAnimationIndex=0;
		}
		else if(this.currentAnimationIndex==this.animations.get(this.currentAnimation).size()-1&&this.currentAnimation.equals("die")&&this.currentAnimation.equals("shoot")&&!this.currentAnimation.equals("jump")){
		}
		else{
			this.currentAnimationIndex++;
		}
	}
	
	public void setCurrentAnimation(String animation){
		this.currentAnimation=animation;
		this.currentAnimationIndex=0;
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
	
	private class UpdateListener implements ActionListener{
		
		private Sprite sprite;
		
		public UpdateListener(Sprite sprite){
			this.sprite=sprite;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.sprite.update();
		}
	}
}
