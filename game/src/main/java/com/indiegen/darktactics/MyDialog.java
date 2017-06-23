package com.indiegen.darktactics;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
public class MyDialog
{

	BitmapFont _fontChat;
 	Dialog dialog;
	Stage stage;
	callBack myCallBack;
	LabelStyle style;
	Assests assests;
	TextureRegion myTex;



	public  MyDialog(final callBack myCallBack, Stage stage, Assests assests)
	{

		this.stage = stage;
		this.myCallBack = myCallBack; 
		this.assests = assests;
		_fontChat = new BitmapFont();
		style = new LabelStyle(_fontChat, Color.WHITE);
		Label label1 = new Label("Level 1 completed", style);
		label1.setAlignment(Align.center);
		label1.setWrap(true);
		//style.font.setScale(1, -1);
		style.fontColor = Color.WHITE;

		Skin tileSkin = new Skin();
		Texture tex = assests.hmi;
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Pixmap pixmap2 = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap2.setColor(Color.BLACK);
		pixmap2.fill();

		//tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tileSkin.add("white", tex);
		tileSkin.add("default", new BitmapFont());

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = tileSkin.newDrawable("white");
		textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
		textButtonStyle.checked = tileSkin.newDrawable("white",
													   Color.LIGHT_GRAY);
		textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = _fontChat;
		textButtonStyle.font.setScale(1, 1);
		textButtonStyle.fontColor = Color.BLACK;
		tileSkin.add("default", textButtonStyle);

		TextButton btnYes = new TextButton("Exit", tileSkin);
		TextButton btnNo = new TextButton("Restart", tileSkin);

		// /////////////////
		Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
		dialog = new Dialog("", skinDialog) {
			@Override
			public float getPrefWidth()
			{
				// force dialog width
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}

			@Override
			public float getPrefHeight()
			{
				// force dialog height
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}
		};
		dialog.setModal(true);
		dialog.setMovable(false);
		dialog.setResizable(false);

		btnYes.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					// Do whatever here for exit button

					//_parent.changeState("StateMenu");
					dialog.hide();
					dialog.cancel();
					dialog.remove();  
					myCallBack.buttonExit();

					return true;
				}

			});


		btnNo.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					//Do whatever here for cancel


					dialog.hide();
					dialog.cancel();
					dialog.remove();   
					myCallBack.buttonRestart();

					return true;
				}

			});

		TextureRegion myTex = new TextureRegion(assests.Background);
		myTex.flip(false, false);
		//myTex.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Drawable drawable = new TextureRegionDrawable(myTex);
		dialog.setBackground(drawable);

		float btnSize = 64f;
		Table t = new Table();
		//t.debug();
		t.center();
		t.setFillParent(true);

		dialog.getContentTable().center();
		//dialog.add("duuuu");

		dialog.getContentTable().add(label1);
//
		t.add(btnYes).width(btnSize).height(btnSize).pad(10f);
		t.add(btnNo).width(btnSize).height(btnSize).pad(10f);

//dialog.getButtonTable().pad(10f);
//
		dialog.getButtonTable().add(t);
		//dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
		dialog.setName("quitDialog");


	}

	public MyDialog levelCompleted()
	{

		Label label1 = new Label("Level 1 completed", style);
		label1.setAlignment(Align.center);
		label1.setWrap(true);
		//style.font.setScale(1, -1);
		style.fontColor = Color.WHITE;

		Skin tileSkin = new Skin();
		Texture tex = assests.hmi;
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Pixmap pixmap2 = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap2.setColor(Color.BLACK);
		pixmap2.fill();

		//tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tileSkin.add("white", tex);
		tileSkin.add("default", new BitmapFont());

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = tileSkin.newDrawable("white");
		textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
		textButtonStyle.checked = tileSkin.newDrawable("white",
													   Color.LIGHT_GRAY);
		textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = _fontChat;
		textButtonStyle.font.setScale(1, 1);
		textButtonStyle.fontColor = Color.BLACK;
		tileSkin.add("default", textButtonStyle);

		TextButton btnYes = new TextButton("Exit", tileSkin);
		TextButton btnNo = new TextButton("Restart", tileSkin);

		// /////////////////
		Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
		dialog = new Dialog("", skinDialog) {
			@Override
			public float getPrefWidth()
			{
				// force dialog width
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}

			@Override
			public float getPrefHeight()
			{
				// force dialog height
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}
		};
		dialog.setModal(true);
		dialog.setMovable(false);
		dialog.setResizable(false);

		btnYes.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					// Do whatever here for exit button

					//_parent.changeState("StateMenu");
					dialog.hide();
					dialog.cancel();
					dialog.remove();   
					myCallBack.buttonExit();
					return true;
				}

			});


		btnNo.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					//Do whatever here for cancel


					dialog.hide();
					dialog.cancel();
					dialog.remove();  
					myCallBack.buttonRestart();


					return true;
				}

			});

		myTex = new TextureRegion(assests.Background);
		myTex.flip(false, false);
		//myTex.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Drawable drawable = new TextureRegionDrawable(myTex);
		dialog.setBackground(drawable);

		float btnSize = 64f;
		Table t = new Table();
		//t.debug();
		t.center();
		t.setFillParent(true);

		dialog.getContentTable().center();
		//dialog.add("duuuu");

		dialog.getContentTable().add(label1).padTop(50f);
//
		t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
		t.add(btnNo).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
//dialog.getButtonTable().pad(10f);
//
		dialog.getButtonTable().add(t);
		//dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
		dialog.setName("quitDialog");
		return this;

	}

	public MyDialog welcome()
	{

		{


			_fontChat = new BitmapFont();
			LabelStyle style = new LabelStyle(_fontChat, Color.WHITE);
			Label label1 = new Label("Level 1 you need to survive and reach the exit", style);
			label1.setAlignment(Align.center);
			label1.setWrap(true);
			//style.font.setScale(1, -1);
			style.fontColor = Color.WHITE;


			Skin tileSkin = new Skin();
			Texture tex = assests.hmi;
			Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
			pixmap.setColor(Color.WHITE);
			pixmap.fill();
			Pixmap pixmap2 = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
			pixmap2.setColor(Color.BLACK);
			pixmap2.fill();

			//tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			tileSkin.add("white", tex);
			tileSkin.add("default", new BitmapFont());

			TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
			textButtonStyle.up = tileSkin.newDrawable("white");
			textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
			textButtonStyle.checked = tileSkin.newDrawable("white",
														   Color.LIGHT_GRAY);
			textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
			textButtonStyle.font = _fontChat;
			textButtonStyle.font.setScale(1, 1);
			textButtonStyle.fontColor = Color.BLACK;
			tileSkin.add("default", textButtonStyle);

			TextButton btnYes = new TextButton("Ok", tileSkin);
			//TextButton btnNo = new TextButton("Restart", tileSkin);

			// /////////////////
			Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
			dialog = new Dialog("", skinDialog) {
				@Override
				public float getPrefWidth()
				{
					// force dialog width
					// return Gdx.graphics.getWidth() / 2;
					return 200f;
				}

				@Override
				public float getPrefHeight()
				{
					// force dialog height
					// return Gdx.graphics.getWidth() / 2;
					return 200f;
				}
			};
			dialog.setModal(true);
			dialog.setMovable(false);
			dialog.setResizable(false);

			btnYes.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y,
											 int pointer, int button)
					{

						// Do whatever here for exit button
						//myCallBack.buttonExit();
						//_parent.changeState("StateMenu");
						dialog.hide();
						dialog.cancel();
						dialog.remove();   

						return true;
					}

				});




			TextureRegion myTex = new TextureRegion(assests.Background);
			myTex.flip(false, false);
			//myTex.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			Drawable drawable = new TextureRegionDrawable(myTex);
			dialog.setBackground(drawable);

			float btnSize = 64f;
			Table t = new Table();
			//t.debug();
			t.center();
			t.setFillParent(true);

			dialog.getContentTable().center();
			//dialog.add("duuuu");

			dialog.getContentTable().add(label1).width(200).padTop(50f);
//
			t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
			//t.add(btnNo).width(btnSize).height(btnSize).pad(10f);
//dialog.getButtonTable().pad(10f);
//
			dialog.getButtonTable().add(t);
			//dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
			dialog.setName("Welcome");


		}
		return this;
	}

	public MyDialog youAreDead()
	{

		Label label1 = new Label("You are dead", style);
		label1.setAlignment(Align.center);
		label1.setWrap(true);
		//style.font.setScale(1, -1);
		style.fontColor = Color.WHITE;

		Skin tileSkin = new Skin();
		Texture tex = assests.hmi;
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Pixmap pixmap2 = new Pixmap((int)Gdx.graphics.getWidth() / 4, (int)Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap2.setColor(Color.BLACK);
		pixmap2.fill();

		//tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tileSkin.add("white", tex);
		tileSkin.add("default", new BitmapFont());

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = tileSkin.newDrawable("white");
		textButtonStyle.down = tileSkin.newDrawable("white", Color.RED);
		textButtonStyle.checked = tileSkin.newDrawable("white",
													   Color.LIGHT_GRAY);
		textButtonStyle.over = tileSkin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = _fontChat;
		textButtonStyle.font.setScale(1, 1);
		textButtonStyle.fontColor = Color.BLACK;
		tileSkin.add("default", textButtonStyle);

		TextButton btnYes = new TextButton("Exit", tileSkin);
		TextButton btnNo = new TextButton("Restart", tileSkin);

		// /////////////////
		Skin skinDialog = new Skin(Gdx.files.internal("uiskin.json"));
		dialog = new Dialog("", skinDialog) {
			@Override
			public float getPrefWidth()
			{
				// force dialog width
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}

			@Override
			public float getPrefHeight()
			{
				// force dialog height
				// return Gdx.graphics.getWidth() / 2;
				return 200f;
			}
		};
		dialog.setModal(true);
		dialog.setMovable(false);
		dialog.setResizable(false);

		btnYes.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					// Do whatever here for exit button

					//_parent.changeState("StateMenu");
					dialog.hide();
					dialog.cancel();
					dialog.remove();   
					myCallBack.buttonExit();
					return true;
				}

			});


		btnNo.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
										 int pointer, int button)
				{

					//Do whatever here for cancel


					dialog.hide();
					dialog.cancel();
					dialog.remove();  
					myCallBack.buttonRestart();


					return true;
				}

			});

		myTex = new TextureRegion(assests.Background);
		myTex.flip(false, false);
		//myTex.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Drawable drawable = new TextureRegionDrawable(myTex);
		dialog.setBackground(drawable);

		float btnSize = 64f;
		Table t = new Table();
		//t.debug();
		t.center();
		t.setFillParent(true);

		dialog.getContentTable().center();
		//dialog.add("duuuu");

		dialog.getContentTable().add(label1).padTop(50f);
//
		t.add(btnYes).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
		t.add(btnNo).width(btnSize).height(btnSize).pad(10f).padBottom(50f);
//dialog.getButtonTable().pad(10f);
//
		dialog.getButtonTable().add(t);
		//dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
		dialog.setName("quitDialog");
		return this;

	}

	public void show()
	{
		dialog.show(stage).setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2);
		stage.addActor(dialog);


	}
}
		
