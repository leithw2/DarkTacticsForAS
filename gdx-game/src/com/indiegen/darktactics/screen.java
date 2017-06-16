package com.indiegen.darktactics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.*;
import com.badlogic.gdx.graphics.Texture.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.input.GestureDetector.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;

import com.badlogic.gdx.Game;


public class screen implements Screen, GestureListener, callBack, Levels, InputProcessor
{

	@Override
	public boolean keyDown(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean keyUp(int p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean keyTyped(char p1)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean touchDown(int p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
		
		
		return false;
	}

	@Override
	public boolean touchUp(int p1, int p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean touchDragged(int p1, int p2, int p3)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean mouseMoved(int p1, int p2)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean scrolled(int p1)
	{
		// TODO: Implement this method
		return false;
	}


	@Override
	public void initScreen()
	{
		// TODO: Implement this method

		//uiText = "level 1 completed";

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

		//actors.add(new stdEnemy(enemyTexture, margen * 4, margen * 5, "1"));
		//actors.add(new stdEnemy(enemyTexture, margen * 10, margen * 6, "2"));
		//actors.add(new stdEnemy(enemyTexture, margen * 5, margen * 5, "3"));
		//actors.add(new stdEnemy(enemyTexture, margen * 3, margen * 1, "4"));
		actors.add(new KingSkeleton(assests.kingSkeleton, margen*18,margen*6,"Boss"));
		
		for (MyActor actor : actors)
		{
			stage.addActor(actor);
		}
		readys.clear();

		stage.addActor(blood);

		
		
		
	}
	


	public void buttonItem()
	{
		// TODO: Implement this method

		if (player.getPlayerState() == stdPlayerState.ITEM)
		{
			player.setPlayerState(stdPlayerState.READY);
		}
		else
		{
			if (player.getPlayerState() == stdPlayerState.READY && player.getPotions() > 0)
			{
				player.setPlayerState(stdPlayerState.ITEM);

				player.setHP(player.getHP() + 40);
				player.setPotions(player.getPotions() - 1);
				ui.setUpItemSkin();

				actingActor = player;
				player.setFatigue(30);
				
				assests.potionSound.play();
				//thisGame.setScreen(new screen(thisGame,batch));
			}
		}



	}


	public void buttonGuard()
	{
		// TODO: Implement this method
		if (player.getPlayerState() == stdPlayerState.GUARD)
		{
			player.setPlayerState(stdPlayerState.READY);
		}
		else
		{
			if (player.getPlayerState() == stdPlayerState.READY)
			{
				player.setPlayerState(stdPlayerState.GUARD);
				//drawRects(player);
				actingActor = player;
				player.setFatigue(player.GUARD);
				//music2.play();
			}
		}
	}


	@Override
	public void buttonAttack()
	{
		// TODO: Implement this method
		if (player.getPlayerState() == stdPlayerState.ATTACK_TARGETING)
		{
			player.setPlayerState(stdPlayerState.READY);
		}
		else
		{
			if (player.getPlayerState() == stdPlayerState.READY)
			{
				player.setPlayerState(stdPlayerState.ATTACK_TARGETING);
				drawRects(player);
				actingActor = player;
				//music2.play();
			}
		}

	}

	@Override
	public void buttonMove()
	{
		// TODO: Implement this method
		if (player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE)
		{
			player.setPlayerState(stdPlayerState.READY);
		}
		else
		{
			if (player.getPlayerState() == stdPlayerState.READY)
			{

				player.setPlayerState(stdPlayerState.WAITING_TO_MOVE);
				drawRects(player);
				actingActor = player;
			}
		}


	}


	@Override
	public void buttonExit()
	{
		// TODO: Implement this method
		System.exit(0);
	}

	@Override
	public void buttonRestart()
	{
		// TODO: Implement this method
		text = "thanks for playing";
		initScreen();
	}



	public enum screenState
	{ 	
		START, 
		RUNNING,
		FINISH,
		WIN,
		LOST
		}

	public enum ShaderSelection
	{
		Default,
		Ambiant,
		Light,
		Final
		}


	Game thisGame;
	Assests assests;
	Batch batch;
	ShapeRenderer shape;
	Texture texture;
	Texture playerTexture;
	Texture enemyTexture;
	Message message;
	stdCharacter floor;
	stdPlayer player;
	MyActor actingActor;
	stdEnemy enemy;
	MyActor dummy;
	ArrayList<MyActor> actors;
	ArrayList<MyActor> readys;
	HealthBar healthBar;
	MyDialog dialog;
	Blood blood;
	Stage stage;
	Stage uiStage;
	Table table;

	int tileSize=32;
	int margen;
	int hmiHeight=1440;
	int hmiWidth=2560;
	float currentzoom=1;
	float newzoom=1;
	
	int actorsReady;
	float touchX=0;
	float touchY=0;
	float cameraX;
	float cameraY;
	TextButton attackButton;

	RandomXS128 rand;

	String text="";
	String uiText="";

	BitmapFont font;
	private Viewport viewport;
	private Viewport uiViewport;
    private Camera camera;
	Vector3 touchVec;
	//Map map;
	TextureRegion tileRegion;
	TextureRegion brickRegion;
	TextureRegion grassRegion;
	TextureRegion groundRegion;
	TextureRegion doorRegion;
	callBack mycallback;
	int countActors;

	Texture tilesTexture;
	StateMachine stateMachine;
	screenState state;
	Ui ui;
	Maps maps;
	MyDialog mydialog;
	FrameBuffer lightBuffer;
	TextureRegion lightBufferRegion;
	Music music2;
	Music music1;
	long id;

	public screen(Game game, Batch batch)
	{
		thisGame = game;
		this.batch = batch;

		touchVec = new Vector3();
		hmiHeight = 640;
		hmiWidth = 360;
		rand = new RandomXS128();
		margen = 64;
		camera = new OrthographicCamera(hmiWidth, hmiHeight);
        viewport = new FitViewport(hmiWidth, hmiHeight, camera);
		uiViewport = new FitViewport(hmiWidth, hmiHeight);
        stage = new Stage(viewport);
		uiStage = new Stage(uiViewport);
		viewport.apply();
		table = new Table();
		table.setFillParent(true);
		uiStage.addActor(table);
		assests = new Assests();

		music2 = assests.music;
		music1 = assests.music2;


		texture = assests.texture;
		playerTexture  = assests.hero21;
		enemyTexture = assests.enemy;
		healthBar = new HealthBar();

		//closeUp = new CloseUp(assests);
		message = new Message();
		tilesTexture = assests.tiles;
		tileRegion = new TextureRegion(tilesTexture, tileSize * 19, tileSize * 6, tileSize, tileSize);
		brickRegion = new TextureRegion(tilesTexture, tileSize * 7, tileSize * 7, tileSize, tileSize);
		grassRegion = new TextureRegion(tilesTexture, tileSize * 1, tileSize * 1, tileSize, tileSize);
		groundRegion = new TextureRegion(tilesTexture, tileSize * 0, tileSize * 1, tileSize, tileSize);
		doorRegion = new TextureRegion(tilesTexture, tileSize * 5, tileSize * 19, tileSize, tileSize);
		shape = new ShapeRenderer();
		blood = new Blood(shape);
		floor = new stdCharacter(texture);
		dummy = new stdEnemy(enemyTexture,9999,9999,"dummy");
		actors = new ArrayList<MyActor>();
		readys = new ArrayList<MyActor>();


        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);

		//InputProcessor in = new InputProcessor(this);
		
        im.addProcessor(gd);
        im.addProcessor(uiStage);
		im.addProcessor(this);


        Gdx.input.setInputProcessor(im);


		font = new BitmapFont(); 
		ui = new Ui(this, assests);	

		ui.getAttackButton().setPosition(hmiWidth - margen, 0);
		ui.getMoveButton().setPosition(hmiWidth - margen * 2, 0);		
		ui.getGuardButton().setPosition(hmiWidth - margen, 64);
		ui.getItemButton().setPosition(hmiWidth - margen * 2, 64);

		mydialog = new MyDialog(this, uiStage, assests);

		mydialog.welcome().show();



		table.addActor(ui.getAttackButton());
		table.addActor(ui.getMoveButton());
		table.addActor(ui.getHealthBar());
		table.addActor(ui.getMessage());
		table.addActor(ui.getCloseUp());
		table.addActor(ui.getGuardButton());
		table.addActor(ui.getItemButton());


		initScreen();
		maps = new Maps(assests);



	}
	


	@Override
	public void render(float delta)
	{

		Gdx.gl.glClearColor(0, 0, 0, 0);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND); // Or GL20
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2 , 0);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		shape.setProjectionMatrix(camera.combined);


		if (music1.getPosition() <= 3 && music1.getVolume() <= 1)	
		{
			music1.setVolume(music1.getVolume() + 0.03f);
		}
		if (music1.getPosition() >= 71 && music1.getVolume() >= 0)	
		{
			music1.setVolume(music1.getVolume() - 0.03f);

		}


		for (MyActor actor : actors)
		{
			if (actor.getPlayerState() == stdPlayerState.FINISH)
			{
				//actingActor = dummy;
				actor.setPlayerState(stdPlayerState.WAITING);
			}
			if(actor.getHP()<=0)
			{
				actor.dead();
				actor.setPlayerState(stdPlayerState.FINISH);

			}
			

		}

		if (maps.getMap()[(int)player.getX() / margen][(int)player.getY() / margen] == 5)
		{
			state = screenState.WIN;
		}

		isTurnEnd2();


		if (state == screenState.WIN)
		{
			mydialog.levelCompleted().show();
			state = screenState.FINISH;
		}

		if (state != screenState.FINISH)
		{
			AIturn();
		}
		ui.getMessage().setText("");	
	
		maps.drawMap(batch, font, text);
		ui.getHealthBar().setBarHP(player.getHP());

		if (actingActor == player && player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE)
		{
			ui.getMessage().setText("Select Direction");
		}

		if (actingActor == player && player.getPlayerState() == stdPlayerState.ATTACK_TARGETING)
		{
			ui.getMessage().setText("Select Target");
		}
		
		
		stage.draw();
		stage.act();
		drawLights();

		uiStage.draw();
		removeDeads();
		drawTurns();

	}

	void drawTurns()
	{
		int turn =1;
		int sangria= 16;

		for (MyActor actor : readys)
		{


			uiStage.getBatch().begin();
			uiStage.getBatch().draw(actor.getTurnTexture(), hmiWidth - 34 - sangria, hmiHeight - 16 - 48 * turn, 32, 32);
			sangria = 0;

			//font.setColor(Color.RED);
			//font.setScale(.5f);
			//font.draw(uiStage.getBatch(), actor.getName()+" "+actor.getPlayerState(), hmiWidth - 100, hmiHeight - 128 - 34 * turn);
			uiStage.getBatch().end();

			turn++;
			if (turn >= 7)
			{
				break;
			}


		}
		//initDebugger();
		turn = 1;
	}

	void AIturn()
	{
		if (readys.size() > 0)
		{
			if (readys.get(0).getName() != "player" 
				&& readys.get(0).getPlayerState() == stdPlayerState.READY 
				)
			{
				AImove(readys.get(0));
			}

			if (readys.get(0).getPlayerState() == stdPlayerState.WAITING)
				readys.remove(0);

		}

	}

	void removeDeads()
	{
		Iterator<MyActor> itr = readys.listIterator();
		while (itr.hasNext())
		{
			MyActor ready = itr.next();

			if (ready.isDead())
			{
				//text=text+" "+ready.getName()+" "+ready.getPlayerState();
				itr.remove();
				ready.remove();
			}
		}



		for (int i=0 ;i < actors.size();i++)
		{


			if (actors.get(i).isDead() && actors.get(i).getName() != "player")
			{
				//readys.remove(actors.get(i));
				actors.get(i).remove();
				actors.remove(i);

				break;

			}

			if (actors.get(i).isDead() && actors.get(i).getName() == "player")
			{
				readys.remove(actors.get(i));
				actors.get(i).remove();

				actors.remove(i);
				mydialog.youAreDead().show();
				state = screenState.FINISH;

			}

		}
	}

	void initDebugger()

	{

		shape.begin(ShapeType.Line);
		shape.setColor(Color.RED);

		for (int x=0;x < 9;x++)
		{
			shape.line(margen * x, 0 , margen * x, hmiHeight);

		}
		for (int x=0;x < 11;x++)
		{
			shape.line(0, margen * x, hmiWidth, margen * x);

		}
		shape.end();

	}



	boolean isTurnEnd()
	{
		actorsReady = 0;

		for (MyActor actor : actors)
		{
			if (actor.getPlayerState() == stdPlayerState.WAITING)
			{
				actor.setFatigue(actor.getFatigue() - 1);

				if (actor.getFatigue() <= 0)
				{

					actor.setPlayerState(stdPlayerState.READY);
					actingActor = actor;
					


					if (readys.size() <= 5)
					{
						readys.add(actor);
						
					}

				}


			}		

		}

		return true;

	}
	boolean isTurnEnd2()
	{
		actorsReady = 0;

		while (readys.size() < 7)
		{
			for (MyActor actor : actors)
			{
				actor.setFatigue(actor.getFatigue() - 1);

				if (actor.getFatigue() <= 0)
				{

					actor.setPlayerState(stdPlayerState.READY);
					//actingActor = actor;
					//actingActor.Acting(true);
					readys.add(actor);
					//actor.setDefence(0);
					actor.setFatigue(20);
				}
			}
		}		
		return true;

	}


	public void drawRects(MyActor actor)
	{
		for (MyRect rect : actor.rects)
		{

			for (MyActor otherActor : actors)
			{
				if (rect.contains(otherActor.getX() + margen / 2, otherActor.getY() + margen / 2) && actor != otherActor)
				{
					rect.setColor(new Color(1, 0, 0, .2f));
					rect.hasTarget = false;
				}

				if (maps.getMap()[(int)rect.getX() / margen][(int)rect.getY() / margen] == 3 || 
					maps.getMap()[(int)rect.getX() / margen][(int)rect.getY() / margen] == 6)
				{

					rect.setColor(new Color(1, 0, 0, .2f));
					rect.isEnable = false;
				}
			}
		}
	}

	@Override
	public void resize(int p1, int p2)
	{
		// Fakedlight system (alpha blending)

// if lightBuffer was created before, dispose, we recreate a new one
		

	}


	@Override
	public void show()
	{

		// TODO: Implement this method
	}

	@Override
	public void hide()
	{


		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		thisGame.dispose();
		// TODO: Implement this method
	}

////////////////////////////////////////////////////////////////////

	@Override
	public boolean touchDown(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method

		//camera.position.set(p1, p2 , 0);
		currentzoom=newzoom;
		
		
		return false;
	}

	public void AImove(MyActor actor)
	{



		MyRect targetRect=new MyRect(9999, 9999, margen, margen);
		if (actor.getPlayerState() == stdPlayerState.READY && !actor.isDead())
		{
			actor.setPlayerState(stdPlayerState.WAITING_TO_MOVE);
			drawRects(actor);
			for (MyRect rect: actor.rects)
			{

				if (rect.isEnable() && rect.hasTarget)
				{
					rect.distance = (float)(Math.sqrt(Math.pow(player.getX() - rect.getX(), 2) + Math.pow(player.getY() - rect.getY(), 2)));
				}

				else
				{
					rect.distance = 9999;		
					if (rect.contains(player.getX() + margen / 2, player.getY() + margen / 2) && actor.getPlayerState() != stdPlayerState.BEING_HITTING)
					{ 

						if (actor.getX() < player.getX())
						{
							actor.setDir(1);
						}
						else
						{
							actor.setDir(0);
						}
						player.setDamage(actor.getAttack());
						actor.setPlayerState(stdPlayerState.ATTACK_TARGETING);
						player.setPlayerState(stdPlayerState.BEING_HITTING);
						actor.setPlayerState(stdPlayerState.ATTACKING);
						//enemy.setHP(enemy.getHP()-player.getAttack());
						//assests
						assests.swordAttackSound.play();
						actor.setFatigue(50);
						blood.createBlood(player);
						//readys.remove(0);
						return;
					}
				}

			}
			for (MyRect rect: actor.rects)
			{
				if (rect.distance < targetRect.distance)
				{
					targetRect = rect;
				}		
			}

			if (targetRect.hasTarget())
			{
				actor.setCurX(targetRect.getX());
				actor.setCurY(targetRect.getY());
				if (actor.getX() < player.getX())
				{
					actor.setDir(1);
				}
				else
				{
					actor.setDir(0);
				}
				actor.setPlayerState(stdPlayerState.MOVING);
				assests.walkSound.play();
				//readys.remove(0);
				actor.setFatigue(20);

				return;
			}
		}
	}

	@Override
	public boolean tap(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method

		touchVec.set(p1, p2, 0);
		touchVec = camera.unproject(touchVec);

		if (player.getPlayerState() != stdPlayerState.WAITING)
		{
			if (player.getPlayerState() == stdPlayerState.WAITING_TO_MOVE)
			{
				for (MyRect rect: player.rects)
				{
					if (rect.contains(touchVec.x, touchVec.y) 
						&& 
						rect.hasTarget() && rect.isEnable())
					{
						player.setCurX(rect.getX());
						player.setCurY(rect.getY());

						if (player.getX() < player.getCurX())
						{
							player.setDir(1);
						}
						else
						{
							player.setDir(0);
						}



						player.setPlayerState(stdPlayerState.MOVING);
						assests.walkSound.play();
						player.setFatigue(player.WALK);



						//readys.remove(0);
						return false;
					}
				}

			}
			if (player.getPlayerState() == stdPlayerState.ATTACK_TARGETING)
			{
				for (MyRect rect: player.rects)
				{

					if (rect.contains(touchVec.x, touchVec.y))
					{
						for (MyActor actor : actors)
						{
							if (actor.getRectangle().contains(touchVec.x, touchVec.y) && actor.getName() != player.getName())
							{
								actor.setDamage(player.getAttack());
								actor.setPlayerState(stdPlayerState.BEING_HITTING);
								player.setPlayerState(stdPlayerState.ATTACKING);
								//enemy.setHP(enemy.getHP()-player.getAttack());
								assests.swordAttackSound.play();
								blood.createBlood(actor);
								if (actor.getX() > player.getX())
								{
									player.setDir(1);
								}
								else
								{
									player.setDir(0);	
								}
								if (actor.getX() == player.getX())
								{
									player.setDir(2);
								}

								actingActor = actor;
								player.setFatigue(player.ATTACK);
								//readys.remove(0);

								return false;
								//player.setPlayerState(stdPlayerState.WAITING_OTHERS);

							}
						}
					}

				}
			}
		}


		return false;
	}

	@Override
	public boolean longPress(float p1, float p2)
	{
		// TODO: Implement this method

		return false;
	}

	@Override
	public boolean fling(float p1, float p2, int p3)
	{


		return false;
	}

	@Override
	public boolean pan(float p1, float p2, float deltaX, float deltaY)
	{
		// TODO: Implement this method
		//touchX=touchX- deltaX/(Gdx.graphics.getWidth()/hmiWidth);
		//touchY=touchY+ deltaY/(Gdx.graphics.getWidth()/hmiWidth);

		return false;
	}

	@Override
	public boolean panStop(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	@Override
	public boolean zoom(float inicialDistance, float finalDistance)
	{
		// TODO: Implement this method
		
		newzoom=(currentzoom+(inicialDistance-finalDistance)*.005f);
		if(newzoom>3)
			{
			newzoom=3;
			}
		if(newzoom<.5f)
		{
			newzoom=0.5f;
		}
		((OrthographicCamera)this.stage.getCamera()).zoom=newzoom;
		return false;
	}

	
	@Override
	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4)
	{
		// TODO: Implement this method
		
		
		return false;
	}
	

	void drawLights2()
	{

		// start rendering to the lightBuffer
		
		
		lightBuffer = new FrameBuffer(Format.RGBA8888, (int)camera.viewportWidth, (int)camera.viewportHeight, false);

		lightBuffer.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, 0, (int)camera.viewportWidth, (int)camera.viewportHeight);

		lightBufferRegion.flip(false, true);
		
		
		lightBuffer.begin();

// setup the right blending
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendEquation(GL20.GL_FUNC_REVERSE_SUBTRACT);

// set the ambient color values, this is the "global" light of your scene
// imagine it being the sun.  Usually the alpha value is just 1, and you change the darkness/brightness with the Red, Green and Blue values for best effect

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

// start rendering the lights to our spriteBatch
		batch.begin();


// set the color of your light (red,green,blue,alpha values)
		batch.setColor(0.9f, 0.9f, .9f, .9f);

// tx and ty contain the center of the light source
		float tx= (camera.position.x - camera.viewportWidth*newzoom / 2);
		float ty= (camera.position.y - camera.viewportHeight*newzoom / 2);

// tw will be the size of the light source based on the "distance"
// (the light image is 128x128)
// and 96 is the "distance"  
// Experiment with this value between based on your game resolution 
// my lights are 8 up to 128 in distance
		//float tw=assests.light.getWidth();

// make sure the center is still the center based on the "distance"
		float tw=camera.viewportWidth*newzoom;
		float th=camera.viewportHeight*newzoom;

		float lightSize =64;
		float lightWidth =256;
		float lightHeight =256;
		
		
// and render the sprite
		batch.draw(assests.light, camera.position.x - assests.light.getWidth() / 2, camera.position.y - assests.light.getHeight() / 2, assests.light.getWidth(), assests.light.getHeight());
		batch.draw(assests.light, 64 * 3 - lightSize/2, -lightSize/2, lightWidth, lightHeight);
		batch.draw(assests.light, 64 * 0 - lightSize/2, 64 * 6 - lightSize/2, lightWidth, lightHeight);
		batch.draw(assests.light, 64 * 4 - lightSize/2, 64 * 2 - lightSize/2, lightWidth, lightHeight);
		batch.draw(assests.light, 64 * 6 - lightSize/2, 64 * 3 - lightSize/2, lightWidth, lightHeight);
		batch.end();

		stage.getBatch().begin();
		stage.getBatch().setColor(0.9f, 0.9f, .9f, 1f);
		stage.getBatch().draw(assests.light, camera.position.x - assests.light.getWidth() / 2, camera.position.y - assests.light.getHeight() / 2, assests.light.getWidth(), assests.light.getHeight());
		//stage.getBatch().setColor(0.9f, 0.0f, .0f, 1f);
		stage.getBatch().draw(assests.light, 64 * 3 - lightSize/2, -lightSize/2, lightWidth, lightHeight);
		stage.getBatch().draw(assests.light, 64 * 0 - lightSize/2, 64 * 6 - lightSize/2, lightWidth, lightHeight);
		stage.getBatch().draw(assests.light, 64 * 4 - lightSize/2, 64 * 2 - lightSize/2, lightWidth, lightHeight);
		stage.getBatch().draw(assests.light, 64 * 6 - lightSize/2, 64 * 3 - lightSize/2, lightWidth, lightHeight);
		//stage.getBatch().draw(assests.light, 0, 0, 400, 400);
		stage.getBatch().end();


		lightBuffer.end();


// now we render the lightBuffer to the default "frame buffer"
// with the right blending !

		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);


		batch.begin();
		batch.setColor(0.9f, 0.9f, .9f, .9f);
		batch.draw(lightBufferRegion, tx, ty, tw, th);               
		//batch.draw(lightBufferRegion, tx+64*3, ty, tw, th);
		batch.end();

		stage.getBatch().begin();
		stage.getBatch().setColor(0.9f, .9f, .9f, .9f);
		stage.getBatch().draw(lightBufferRegion, tx, ty, tw, th);               
		//stage.getBatch().draw(lightBufferRegion, tx+64*3, ty, tw, th);
		stage.getBatch().end();


		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		Gdx.gl.glDisable(GL20.GL_BLEND);
		Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);
		//Gdx.gl20.glBlendFunc(
// post light-rendering
// you might want to render your statusbar stuff here


	}
	

	void drawLights()
	{

		// start rendering to the lightBuffer

		if (lightBuffer != null) 
			lightBuffer.dispose();
		lightBuffer = new FrameBuffer(Format.RGBA8888, (int)camera.viewportWidth, (int)camera.viewportHeight, false);

		lightBuffer.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		lightBufferRegion = new TextureRegion(lightBuffer.getColorBufferTexture(), 0, 0, (int)camera.viewportWidth, (int)camera.viewportHeight);

		lightBufferRegion.flip(false, true);


		lightBuffer.begin();

// setup the right blending
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendEquation(GL20.GL_FUNC_REVERSE_SUBTRACT);

// set the ambient color values, this is the "global" light of your scene
// imagine it being the sun.  Usually the alpha value is just 1, and you change the darkness/brightness with the Red, Green and Blue values for best effect

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

// start rendering the lights to our spriteBatch
		batch.begin();


// set the color of your light (red,green,blue,alpha values)
		batch.setColor(0.9f, 0.9f, .9f, .9f);

// tx and ty contain the center of the light source
		float tx= (camera.position.x - camera.viewportWidth*newzoom / 2);
		float ty= (camera.position.y - camera.viewportHeight*newzoom / 2);

// tw will be the size of the light source based on the "distance"
// (the light image is 128x128)
// and 96 is the "distance"  
// Experiment with this value between based on your game resolution 
// my lights are 8 up to 128 in distance
		//float tw=assests.light.getWidth();

// make sure the center is still the center based on the "distance"
		float tw=camera.viewportWidth*newzoom;
		float th=camera.viewportHeight*newzoom;

		float lightSize =256;


// and render the sprite
		batch.draw(assests.light, camera.position.x - assests.light.getWidth() / 2, camera.position.y - assests.light.getHeight() / 2, assests.light.getWidth(), assests.light.getHeight());
		batch.draw(assests.light, 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
		batch.draw(assests.light, 64 * 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
		batch.draw(assests.light, 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
		batch.draw(assests.light, 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
		batch.end();

		stage.getBatch().begin();
		stage.getBatch().setColor(0.9f, 0.9f, .9f, 1f);
		stage.getBatch().draw(assests.light, camera.position.x - assests.light.getWidth() / 2, camera.position.y - assests.light.getHeight() / 2, assests.light.getWidth(), assests.light.getHeight());
		//stage.getBatch().setColor(0.9f, 0.0f, .0f, 1f);
		stage.getBatch().draw(assests.light, 64 * 3 - lightSize / 4 - 32, -lightSize / 4 - 32, lightSize, lightSize);
		stage.getBatch().draw(assests.light, 64 * 0 - lightSize / 4 - 32, 64 * 6 - lightSize / 4 - 32, lightSize, lightSize);
		stage.getBatch().draw(assests.light, 64 * 4 - lightSize / 4 - 32, 64 * 2 - lightSize / 4 - 32, lightSize, lightSize);
		stage.getBatch().draw(assests.light, 64 * 6 - lightSize / 4 - 32, 64 * 3 - lightSize / 4 - 32, lightSize, lightSize);
		//stage.getBatch().draw(assests.light, 0, 0, 400, 400);
		stage.getBatch().end();


		lightBuffer.end();


// now we render the lightBuffer to the default "frame buffer"
// with the right blending !

		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		//Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);


		batch.begin();
		batch.setColor(0.9f, 0.9f, .9f, .9f);
		batch.draw(lightBufferRegion, tx, ty, tw, th);               
		//batch.draw(lightBufferRegion, tx+64*3, ty, tw, th);
		batch.end();

		stage.getBatch().begin();
		stage.getBatch().setColor(0.9f, .9f, .9f, .9f);
		stage.getBatch().draw(lightBufferRegion, tx, ty, tw, th);               
		//stage.getBatch().draw(lightBufferRegion, tx+64*3, ty, tw, th);
		stage.getBatch().end();


		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		Gdx.gl.glDisable(GL20.GL_BLEND);
		Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);
		//Gdx.gl20.glBlendFunc(
// post light-rendering
// you might want to render your statusbar stuff here


	}

}
