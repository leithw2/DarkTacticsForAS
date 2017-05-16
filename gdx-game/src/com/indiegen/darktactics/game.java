package com.indiegen.darktactics;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;


import com.badlogic.gdx.Game;

public class game extends Game implements ApplicationListener
{

	Batch batch;
	SpriteBatch spriteBatch;
	screen screen1;
	Assests assests;
	
	
	game() 
	{
		//assests = new Assests();
	}
    
	
	@Override
	public void create()
	{
		//assests = new Assests();
		batch = new SpriteBatch();
	    //world = new World(new Vector2(1,1),true);
		screen1= new screen(this,batch);
		setScreen(screen1);
		
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
		//spriteBatch.dispose();
		batch.dispose();
		
		super.dispose();
		//screen1.dispose();
		
		
		
		
	}
	
}
