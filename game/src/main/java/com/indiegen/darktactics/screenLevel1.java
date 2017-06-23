package com.indiegen.darktactics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.input.GestureDetector.*;

import com.badlogic.gdx.Game;
public class screenLevel1 extends screen implements Levels
{
	
	screenLevel1(Game game, Batch batch){
		super(game, batch);
		
	}

	@Override
	public void initScreen()
	{
		
		// TODO: Implement this method
		actors.clear();
		stage.getActors().clear();

		player = new stdPlayer(playerTexture);
		player.setPosition(margen * 1, margen * 1);
		//player.setHP(200);
		healthBar.setBarHP(80);
		healthBar.maxHP = 120;
		floor.setX(0);
		floor.setY(0);
		floor.setWidth(margen * 8);
		player.setHeight(margen);
		floor.setHeight(8 * .99f * margen * texture.getHeight() / texture.getWidth());
		actors.add(player);

		actingActor = dummy;
		state = screenState.START;

		music1.setLooping(true);
		music1.play();
		music1.setVolume(0);

		actors.add(new stdEnemy(enemyTexture, margen * 4, margen * 5, "1"));
		actors.add(new stdEnemy(enemyTexture, margen * 10, margen * 6, "2"));
		actors.add(new stdEnemy(enemyTexture, margen * 5, margen * 5, "3"));
		actors.add(new stdEnemy(enemyTexture, margen * 3, margen * 1, "4"));

		for (MyActor actor : actors)
		{
			stage.addActor(actor);
		}
		readys.clear();

		stage.addActor(blood);

	}
	



}
