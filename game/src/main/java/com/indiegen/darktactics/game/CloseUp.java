package com.indiegen.darktactics.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CloseUp extends Actor {

    ShapeRenderer shape;
    Texture closeUp;
    Assests assests;

    public CloseUp(Assests assests) {
        this.assests = assests;
        closeUp = assests.heroCloseUp;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(closeUp, 0, 0, 64, 64);
    }
}
