package com.indiegen.darktactics;

interface StateMachine<MyActor> {
    public void update(MyActor player, float delta);

    public Boolean enter(MyActor player, float delta);

    public Boolean exit(MyActor player, float delta);

}
