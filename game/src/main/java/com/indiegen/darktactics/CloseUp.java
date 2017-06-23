package com.indiegen.darktactics;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class CloseUp extends Actor
{

	ShapeRenderer shape;
	Texture closeUp;
	Assests assests;

    public CloseUp(Assests assests)
	{
		this.assests = assests;
		closeUp=assests.heroCloseUp;
		
	}

	

    @Override
    public void draw(Batch batch, float parentAlpha)
	{  
	
		batch.draw(closeUp,0,0,64,64);
	}
}
