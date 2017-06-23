package com.indiegen.darktactics;


import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class Game extends AndroidApplication {
	game initgame;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		
		initgame = new game();
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		initialize(initgame,cfg);
		
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		//initgame= null;
		super.onDestroy();
	}



	
	
}
