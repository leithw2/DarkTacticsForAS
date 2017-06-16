package com.indiegen.darktactics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

public interface stdActor
{
	public Vector2 getPosMap();
	
	public void setAnimation(int animations);
	public Animation getAnimation();
	public boolean isAnimationFinished();
	public void setDamage(int damage);
	public int getDamage();

	public boolean isDead();
	public void dead();
	
	public void setFontAlpha(float fontAlpha);
	public float getFontAlpha();
	
	
	public void setAttack(int attack);
	public int getAttack();

	public void setHP(int HP);
	public int getHP();
	
	public void drawLabel(int hit);
	
	public void setCurY(float curY);
	public float getCurY();
	
	public void setCurX(float curX);
	public float getCurX();

	public void setState(int state);
	public int getState();

	public void setFlipY(boolean flipY);
	public boolean getFlipY();

	public void setFlipX(boolean flipX);
	public boolean getFlipX();

	public void setVelX(int velX);
	public int getVelX();

	public void setDir(int dir);
	public int getDir();

	public void setColor(Color color);
	public Color getColor();

	public void setRectangle(Rectangle rectangle);
	public Rectangle getRectangle();

	public void setBoundingBox(BoundingBox boundingBox);
	public BoundingBox getBoundingBox();

	public boolean drawRect(MyRect rect);

	public boolean isTouched(float x, float y);
	
	public void setPlayerState(stdPlayerState playerState);
	public stdPlayerState getPlayerState();
	
	public void moveRects();
	public void attackRects();
	
	public int getSpeed();
	

	public int margen = 60;
	public int speed = 60;

	
	public void setFatigue(int fatigue);
	public int getFatigue();
	
	public Boolean isActing();
	public void Acting(Boolean acting);
	
	public TextureRegion getTurnTexture();
	public void setTurnTexture(TextureRegion turnTexture);
	//public enum stdState{WAITING, ACTING, MOVING, ATTACKING, CHARGING, GUARDING, ITEM_USING,GET_HITTING }
}
