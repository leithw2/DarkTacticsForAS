package com.indiegen.darktactics;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.*;
import org.apache.http.conn.util.*;

public interface stdActor
{
	public Vector2 getPosMap();
	
	public void setTexture(Texture texture);
	public Texture getTexture();
	
	
	

	
	public void setAnimation(int animations);
	public Animation getAnimation();
	public boolean isAnimationFinished();
	public void setDamage(int damage);
	public int getDamage();

	public boolean isDead();
	public void dead();
	
	public void setFontAlpha(float fontAlpha);
	public float getFontAlpha();
	
	public void setAttack(int attack)
	public int getAttack()

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
	
	public void setPlayerState(stdPlayerState playerState)
	public stdPlayerState getPlayerState();
	
	public void moveRects();
	public void attackRects();
	
	public int getSpeed();
	
	public BitmapFont font;
	public int margen = 60;
	public int speed = 60;
	//public int attack;
	
	public void setFatigue(int fatigue);
	public int getFatigue();
	
	public Boolean isActing();
	public void Acting(Boolean acting);
	
	public TextureRegion getTurnTexture();
	public void setTurnTexture(TextureRegion turnTexture);
	
	public void setDefence(int defence);
	public int getDefence();
	
	public void initialice();
	
	//public enum stdState{WAITING, ACTING, MOVING, ATTACKING, CHARGING, GUARDING, ITEM_USING,GET_HITTING }
}
