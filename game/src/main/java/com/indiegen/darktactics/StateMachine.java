package com.indiegen.darktactics;
import com.badlogic.gdx.scenes.scene2d.*;

interface StateMachine<MyActor>
{
	public void update(MyActor player,float delta);
	
	public Boolean enter(MyActor player ,float delta);
	
	public Boolean exit(MyActor player,float delta);
	
}
