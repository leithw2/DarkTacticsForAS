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

public class Message extends Actor
{

	ShapeRenderer shape;
	
	BitmapFont font;
	String text;


    public Message()
	{
		shape = new ShapeRenderer();
		text="hello world";
		font = new BitmapFont();
		//setBarHP(100);
		//setBarMP(10);

	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	

    @Override
    public void draw(Batch batch, float parentAlpha)
	{    
		
		font.setColor(Color.RED);
		font.setScale(2f);
		font.draw(batch, text, 30, 100);

	}
}
