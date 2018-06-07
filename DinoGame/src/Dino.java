import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Dino extends Sprite
{
	private float yVel;

	private PImage[] running;
	private int imgNum = 0;
	
	public boolean jump;
	private long startTime;

	public Dino(PApplet marker)
	{
		super(10, 0);
		startTime = System.currentTimeMillis();
	}

	public void draw(DrawingSurface marker)
	{
		marker.pushMatrix();

		marker.image(img, x, y);
		
		update();
		imgUpdate();
		
		marker.popMatrix();

	}

	public void update()
	{
		if (y < DrawingSurface.FLOOR - hitBox.getHeight() + 5)
			yVel += 0.5; //gravity
		else
			yVel = 0;
		
		if (y >= DrawingSurface.FLOOR - hitBox.getHeight() + 5 && jump)
			yVel = -10; //jumpspeed
		

		PVector nextPosition = new PVector(x, y);
		nextPosition.add(new PVector(0, yVel));
		
		float offset = 0;

		if (nextPosition.y > offset && nextPosition.y < (DrawingSurface.SCREEN_HEIGHT - offset))
			setY(nextPosition.y);

	}
	
	public void imgUpdate()
	{
		if ((System.currentTimeMillis() - startTime) >= 100)
		{
			img = running[imgNum];
			
			if (imgNum < running.length - 1)
				imgNum++;
			else
				imgNum = 0;
			
			startTime = System.currentTimeMillis();
		}
	}

	public float getyPos()
	{
		return y;
	}

	public void setyPos(int yPos)
	{
		this.y = yPos;
	}

	public PImage[] getRunning()
	{
		return running;
	}

	public void setRunning(PImage[] running)
	{
		this.running = running;
		super.setImg(running[0]);
	}

	
}
