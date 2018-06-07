import java.awt.Color;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

public class DrawingSurface extends PApplet
{

    public static final float SCREEN_WIDTH = 500f;
    public static final float SCREEN_HEIGHT = 500f;
    public static final float FLOOR = 300f;
    
	private Dino dino;
	private Sprite floor1, floor2;
	private Sprite cloud1, cloud2, cloud3;
	private Sprite obstacle1, obstacle2;
	private float speed;
	private long timer;
	private int score;
	private boolean dead;
	
	private PImage endGame;
	
	// create the house and person objects
    public DrawingSurface()
    {
    	dino = new Dino(this);
    	floor1 = new Sprite(0, FLOOR);
    	cloud1 = new Sprite(300, FLOOR - 100);
    	cloud2 = new Sprite(100, FLOOR - 150);
    	cloud3 = new Sprite(200, FLOOR - 120);
    	obstacle1 = new Sprite(400, FLOOR - 20);
    	obstacle2 = new Sprite(700, FLOOR - 30);
    	speed = 0;
    	timer = System.currentTimeMillis();
    	score = 0;
    	dead = false;
    	endGame = new PImage();
    }
    
    public void setup()
    {
    	floor1.setImg(loadImage("floor.png"));
    	floor2 = new Sprite((float) (floor1.getX() + floor1.getHitBox().getWidth()), FLOOR);
    	floor2.setImg(loadImage("floor.png"));
    	
    	cloud1.setImg(loadImage("cloud.png"));
    	cloud2.setImg(loadImage("cloud.png"));
    	cloud3.setImg(loadImage("cloud.png"));
    	
    	obstacle1.setImg(loadImage("small_cactus\\small_" + (int)(Math.random() * 3 + 1) + ".png"));
    	obstacle2.setImg(loadImage("big_cactus\\big_" + (int)(Math.random() * 3 + 1) + ".png"));

    	endGame = loadImage("GameOver.png");
    	
    	PImage[] temp = new PImage[4];
    	
    	for (int i = 0; i < 4; i++)
		{
			temp[i] = loadImage("running\\run" + (i+1) + ".png");
		}
    	
    	dino.setRunning(temp);
    }

    // loops 60 times/sec to draw the characters with the updated changes
    public void draw()
    {
    	if (!dead)
    	{
        	background(color(247, 247, 247));
            scroll();
            
            textSize(15);
            fill(0);
            text(score, 450, 30);
            
            floor1.draw(this);
            floor2.draw(this);
            cloud1.draw(this);
            cloud2.draw(this);
            cloud3.draw(this);
            
            obstacle1.draw(this);
            obstacle2.draw(this);
            
            if (obstacle1.getHitBox().overlaps(dino.getHitBox()) 
            		|| obstacle2.getHitBox().overlaps(dino.getHitBox()))
            	dead = true;
            
        	dino.draw(this);
        	
        	if (System.currentTimeMillis() - timer > 5000)
        	{
        		speed++;
        		timer = System.currentTimeMillis();
        		score += 10;
        	}
        	
        	if (score >= 30)
        	{
        		filter(PApplet.INVERT);
        	}
    	}
    	else
    	{
    		image(endGame, SCREEN_WIDTH / 2 - endGame.pixelWidth/2, 
    				SCREEN_HEIGHT / 2 - endGame.pixelHeight/2);
    		
    	}

    }

    public void scroll()
    {
    	if (floor1.getX() + floor1.getHitBox().getWidth() < SCREEN_WIDTH)
    		floor2.setX((float) (floor1.getX() + floor1.getHitBox().getWidth()));
    	if (floor2.getX() + floor2.getHitBox().getWidth() < SCREEN_WIDTH)
    		floor1.setX((float) (floor2.getX() + floor2.getHitBox().getWidth()));
    	
    	if (!cloud1.getHitBox().inWindow((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT))
    		cloud1.setX(SCREEN_WIDTH + (float)(Math.random() * 50));
    	if (!cloud2.getHitBox().inWindow((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT))
    		cloud2.setX(SCREEN_WIDTH + (float)(Math.random() * 50));
    	if (!cloud3.getHitBox().inWindow((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT))
    		cloud3.setX(SCREEN_WIDTH + (float)(Math.random() * 100));
    	
    	if (!obstacle1.getHitBox().inWindow((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT))
    	{
    		obstacle1.setImg(loadImage("small_cactus\\small_" + (int)(Math.random() * 3 + 1) + ".png"));
    		obstacle1.setX(SCREEN_WIDTH + (float)(Math.random() * 50));
    	}
    	if (!obstacle2.getHitBox().inWindow((int)SCREEN_WIDTH, (int)SCREEN_HEIGHT))
    	{
			obstacle2.setImg(loadImage("big_cactus\\big_" + (int)(Math.random() * 3 + 1) + ".png"));
        	obstacle2.setX(SCREEN_WIDTH + (float)(Math.random() * 100));
    	}
    	
		
		if (Math.abs(obstacle2.getX() + obstacle2.getHitBox().getWidth() - obstacle1.getX()) < 70)
    		System.out.println("close");
    	
    	
    	floor1.setX(floor1.getX() - 5 - speed);
    	floor2.setX(floor2.getX() - 5 - speed);
    	cloud1.setX(cloud1.getX() - 2 - speed);
    	cloud2.setX(cloud2.getX() - 2 - speed);
    	cloud3.setX(cloud3.getX() - 2.25f - speed);
    	obstacle1.setX(obstacle1.getX() - 5 - speed);
    	obstacle2.setX(obstacle2.getX() - 5 - speed);

    }

    /*
     * keyPressed listener that controls the keyboard based movements
     */
    public void keyPressed()
    {
        if (keyCode == KeyEvent.VK_SPACE)
        	dino.jump = true; 
        

    }
    
    public void keyReleased()
    {
        if (keyCode == KeyEvent.VK_SPACE)
        	dino.jump = false; 
        

    }
    
    public void mouseClicked()
    {
    	if (dead)
    	{
    		dead = false;
    		dino = new Dino(this);
        	floor1 = new Sprite(0, FLOOR);
        	cloud1 = new Sprite(300, FLOOR - 100);
        	cloud2 = new Sprite(100, FLOOR - 150);
        	cloud3 = new Sprite(200, FLOOR - 120);
        	obstacle1 = new Sprite(400, FLOOR - 20);
        	obstacle2 = new Sprite(700, FLOOR - 30);
        	speed = 0;
        	timer = System.currentTimeMillis();
        	score = 0;
        	dead = false;
        	endGame = new PImage();
        	
        	setup();
    	}
    		
    	
    }

}
