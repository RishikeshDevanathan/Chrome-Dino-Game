import processing.core.PImage;

public class Sprite
{
	protected float x, y;
	protected PImage img;
	protected Rectangle hitBox;
	
	public Sprite(float x, float y)
	{
		this.x = x;
		this.y = y;
		this.img = new PImage();
		this.hitBox = new Rectangle();
	}
	

	public void draw(DrawingSurface marker)
	{
		marker.pushMatrix();
		//hitBox.draw(marker);
		marker.image(img, x, y);
		
		marker.popMatrix();
	}
	
	public float getX()
	{
		return x;
	}
	public void setX(float x)
	{
		this.x = x;
		this.hitBox.setX(x);
	}
	public float getY()
	{
		return y;
	}
	public void setY(float y)
	{
		this.y = y;
		this.hitBox.setY(y);

	}
	public PImage getImg()
	{
		return img;
	}
	public void setImg(PImage img)
	{
		this.img = img;
		this.hitBox = new Rectangle(x, y, img.pixelWidth, img.height);
	}


	public Rectangle getHitBox()
	{
		return hitBox;
	}


	public void setHitBox(Rectangle hitBox)
	{
		this.hitBox = hitBox;
	}

	
	
}
