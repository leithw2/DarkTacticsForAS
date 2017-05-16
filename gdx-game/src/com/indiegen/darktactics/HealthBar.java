package com.indiegen.darktactics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.*;
import java.util.*;

public class HealthBar extends Actor
{

	ShapeRenderer shape;
	int barHP;
	int barMP;
	int maxHP;
	BitmapFont font;
	String text;
	

    public HealthBar()
	{
		shape = new ShapeRenderer();
		text="";
		font = new BitmapFont();
		setBarHP(100);
		setMaxHP(80);
		setBarMP(80);

	}

	public void setMaxHP(int maxHP)
	{
		this.maxHP = maxHP;
	}

	public int getMaxHP()
	{
		return maxHP;
	}

	public void setBarMP(int mP)
	{
		barMP = mP;
	}

	public int getBarMP()
	{
		return barMP;
	}

	public void setBarHP(int hP)
	{
		barHP = hP;
	}

	public int getBarHP()
	{
		return barHP;
	}

    @Override
    public void draw(Batch batch, float parentAlpha)
	{    
		
	
		
		batch.end();	
		Gdx.gl.glEnable(GL20.GL_BLEND);
		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shape.begin(ShapeType.Filled);	
		shape.setProjectionMatrix(batch.getProjectionMatrix());

		shape.setColor(Color.ORANGE);
		shape.rect(65,49,82,8);
		
		
		shape.setColor(Color.RED);
		shape.rect(66, 50, barHP*80/getMaxHP(), 6);

		shape.setColor(Color.BLACK);
		shape.rect(66,43,80,3);	
		
		
		shape.setColor(Color.CYAN);
		shape.rect(66, 43, barMP, 3);
		
		

		shape.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);

		batch.begin();
		text=barHP+"";
		font.setColor(Color.RED);
		font.setScale(.7f);
		font.draw(batch, text, 130, 64);
		
	}
}
