package com.indiegen.darktactics;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.*;
import com.badlogic.gdx.scenes.scene2d.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;

public class MyActor extends Actor implements stdActor
{

	@Override
	public void setDefence(int defence)
	{
		// TODO: Implement this method
	}

	@Override
	public int getDefence()
	{
		// TODO: Implement this method
		return 0;
	}


	@Override
	public TextureRegion getTurnTexture()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void setTurnTexture(TextureRegion turnTexture)
	{
		// TODO: Implement this method
		
	}
	

	@Override
	public void Acting(Boolean acting)
	{
		// TODO: Implement this method
		
	}
	

	@Override
	public Boolean isActing()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void setFatigue(int fatigue)
	{
		// TODO: Implement this method
		
	}

	@Override
	public int getFatigue()
	{
		// TODO: Implement this method
		return 0;
	}
	
	
	@Override
	public Vector2 getPosMap()
	{
		// TODO: Implement this method

		return new Vector2(getX()/margen,getY()/margen);
	}
	@Override
	public boolean isAnimationFinished()
	{
		// TODO: Implement this method
		return true;
	}
	

	@Override
	public void setAnimation(int animations)
	{
		// TODO: Implement this method
		
	}

	@Override
	public Animation getAnimation()
	{
		// TODO: Implement this method
		return null;
	}


	@Override
	public void dead()
	{
		// TODO: Implement this method
		dead=true;
	}


	@Override
	public boolean isDead()
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public void setRectangle(Rectangle rectangle)
	{
		// TODO: Implement this method
	}


	@Override
	public void setDamage(int damage)
	{
		// TODO: Implement this method
		this.damage=damage;
	}

	@Override
	public int getDamage()
	{
		// TODO: Implement this method
		return damage;
	}

	@Override
	public void setFontAlpha(float fontAlpha)
	{
		// TODO: Implement this method
		this.fontAlpha=fontAlpha;
	}

	@Override
	public float getFontAlpha()
	{
		// TODO: Implement this method
		return fontAlpha;
	}

	@Override
	public void drawLabel(int hit)
	{
		// TODO: Implement this method

	}


	@Override
	public void setAttack(int attack)
	{
		// TODO: Implement this method
	}

	@Override
	public int getAttack()
	{
		// TODO: Implement this method
		return 0;
	}


	@Override
	public void setHP(int HP)
	{
		// TODO: Implement this method
	}

	@Override
	public int getHP()
	{
		// TODO: Implement this method
		return 0;
	}


	@Override
	public void moveRects()
	{
		// TODO: Implement this method
	}

	@Override
	public void attackRects()
	{
		// TODO: Implement this method
	}

	@Override
	public int getSpeed()
	{
		// TODO: Implement this method
		return speed;
	}


	@Override
	public void setPlayerState(stdPlayerState playerState)
	{
		// TODO: Implement this method
	}

	@Override
	public stdPlayerState getPlayerState()
	{
		// TODO: Implement this method
		return null;
	}


	@Override
	public void setCurY(float curY)
	{
		// TODO: Implement this method
	}

	@Override
	public float getCurY()
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public void setCurX(float curX)
	{
		// TODO: Implement this method
	}

	@Override
	public float getCurX()
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public void setState(int state)
	{
		// TODO: Implement this method
	}

	@Override
	public int getState()
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public void setFlipY(boolean flipY)
	{
		// TODO: Implement this method
	}

	@Override
	public boolean getFlipY()
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public void setFlipX(boolean flipX)
	{
		// TODO: Implement this method
	}

	@Override
	public boolean getFlipX()
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public void setVelX(int velX)
	{
		// TODO: Implement this method
	}

	@Override
	public int getVelX()
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public void setDir(int dir)
	{
		// TODO: Implement this method
	}

	@Override
	public int getDir()
	{
		// TODO: Implement this method
		return 0;
	}

	@Override
	public Rectangle getRectangle()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void setBoundingBox(BoundingBox boundingBox)
	{
		// TODO: Implement this method
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public boolean drawRect(MyRect rect)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean isTouched(float x, float y)
	{
		// TODO: Implement this method
		return false;
	}
	int damage=0;
	ArrayList<MyRect> rects;
	boolean dead=false;
	float fontAlpha;
	Animation animation;
	
}
