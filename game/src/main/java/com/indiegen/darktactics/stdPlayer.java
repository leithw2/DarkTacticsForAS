package com.indiegen.darktactics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.ArrayList;

class stdPlayer extends MyActor implements stdActor {

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getPotions() {
        return potions;
    }

    @Override
    public TextureRegion getTurnTexture() {
        // TODO: Implement this method
        return turnTexture;
    }

    @Override
    public void setTurnTexture(TextureRegion turnTexture) {
        // TODO: Implement this method
        this.turnTexture = turnTexture;
    }


    @Override
    public void Acting(Boolean acting) {
        // TODO: Implement this method
        this.acting = acting;
    }


    @Override
    public Boolean isActing() {
        // TODO: Implement this method
        return acting;
    }


    @Override
    public void setFatigue(int fatigue) {
        // TODO: Implement this method
        this.fatigue = fatigue;
    }

    @Override
    public int getFatigue() {
        // TODO: Implement this method
        return this.fatigue;
    }


    @Override
    public Vector2 getPosMap() {
        // TODO: Implement this method

        return new Vector2(getX() / margen, getY() / margen);
    }

    final int ATTACK = 50;

    final int WALK = 20;
    final int RUN = 50;
    final int GUARD = 20;
    final int HEAVYATTACK = 90;
    final int ROLL = 30;
    final int JUMP = 50;
    final int WAIT = 10;
    final int ITEM = 50;
    final int HURTS = 10;


    Texture texture;
    TextureRegion[] attackFrames;
    TextureRegion[] waitFrames;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;
    TextureRegion turnTexture;
    Animation attackAnimation;
    Animation waitAnimation;
    Animation walkAnimation;

    BoundingBox boundingBox;
    Rectangle rectangle;
    Rectangle rectUp;
    Rectangle rectDown;
    Rectangle rectLeft;
    Rectangle rectRight;

    int margen = 64;
    int velX = 0;
    Color color;
    int FRAME_COLS = 3;
    int FRAME_ROWS = 1;
    float delta = 0;
    int dir;
    boolean flipX = false;
    boolean flipY = false;
    int state = 0;
    ShapeRenderer shape;
    float curX;
    float curY;
    stdPlayerState actorState;
    int HP;
    int maxHP = 80;
    boolean dead = false;
    int fatigue = 0;
    Boolean acting = false;
    int potions = 1;
    int attack;

    public stdPlayer(Texture settexture) {

        rects = new ArrayList<MyRect>();
        boundingBox = new BoundingBox();

        setHP(maxHP);
        setAttack(30);
        texture = settexture;

        setName("player");
        setPosition(margen * 3, margen * 3);
        setWidth(margen);
        attack = 100;
        setHeight(margen);
        rectangle = new Rectangle(getX(), getY(), margen, margen);

        attackFrames = new TextureRegion[7];

        attackFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        attackFrames[1] = new TextureRegion(texture, 32, 0, 32, 32);
        attackFrames[2] = new TextureRegion(texture, 64, 0, 32, 32);
        attackFrames[3] = new TextureRegion(texture, 96, 0, 32, 32);
//		attackFrames[1] = new TextureRegion(texture, 32, 32, 32, 32);
        attackFrames[4] = new TextureRegion(texture, 32, 32, 32, 32);
        attackFrames[6] = new TextureRegion(texture, 64, 32, 32, 32);
        attackFrames[5] = new TextureRegion(texture, 64, 32, 32, 32);

        attackAnimation = new Animation(.08f, attackFrames);

        waitFrames = new TextureRegion[2];

        waitFrames[0] = new TextureRegion(texture, 0, 0, 32, 32);
        waitFrames[1] = new TextureRegion(texture, 96, 32, 32, 32);

        waitAnimation = new Animation(0.8f, waitFrames);

        walkFrames = new TextureRegion[2];

        walkFrames[0] = new TextureRegion(texture, 32, 64, 32, 32);
        walkFrames[1] = new TextureRegion(texture, 32, 64, 32, 32);

        walkAnimation = new Animation(0.8f, walkFrames);


        setAnimation(0);
        currentFrame = getAnimation().getKeyFrame(delta, true);
        delta = 0f;
        shape = new ShapeRenderer();

        actorState = stdPlayerState.WAITING;
        rects.add(new MyRect(getX(), getY(), margen, margen));
        setTurnTexture(new TextureRegion(texture, 64, 64, 16, 16));


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // TODO: Implement this method
        delta += Gdx.graphics.getDeltaTime();

        if (getPlayerState() == stdPlayerState.BEING_HITTING) {
            batch.setColor(1, 1 - fontAlpha, 1 - fontAlpha, 1);
            font.setColor(1, 0, 0, fontAlpha);
            font.setScale(1f);
            font.draw(batch, -getDamage() + " HP", getX(), getY() + margen + margen * (1 - fontAlpha) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }
        if (getPlayerState() == stdPlayerState.ITEM) {
            batch.setColor(1 - fontAlpha, 1, 1 - fontAlpha, 1);
            font.setColor(0, 1, 0, fontAlpha);
            font.setScale(1f);
            font.draw(batch, "+40 " + " HP", getX(), getY() + margen + margen * (1 - fontAlpha) / 2);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (getPlayerState() == stdPlayerState.WAITING_TO_MOVE || getPlayerState() == stdPlayerState.ATTACK_TARGETING) {
            batch.end();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setProjectionMatrix(batch.getProjectionMatrix());

            for (MyRect rect : rects) {
                drawRect(rect);
            }
            shape.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
            batch.begin();
        }

        currentFrame = getAnimation().getKeyFrame(delta, true);
        if (dir == 0 && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);

        }
        if (dir == 1 && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        setRectangle(new Rectangle(getX(), getY(), margen, margen));
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        //batch.draw(texture, getX(), getY(), 0, 0 , getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), (int)getWidth() * 0, (int)getHeight() * 0, texture.getWidth(), texture.getHeight(), getFlipX(), getFlipY());
        //batch.draw(texture, getX(), getY(), getWidth()/3,getHeight()/3);

    }

    @Override
    public boolean isAnimationFinished() {
        // TODO: Implement this method
        return getAnimation().isAnimationFinished(this.delta);
    }


    @Override
    public void setAnimation(int animations) {
        // TODO: Implement this method
        delta = 0;
        switch (animations) {
            case 0:

                animation = waitAnimation;
                break;
            case 1:

                animation = walkAnimation;
                break;
            case 2:

                animation = attackAnimation;
                break;

        }
    }

    @Override
    public Animation getAnimation() {
        // TODO: Implement this method
        return animation;
    }

    @Override
    public void dead() {
        // TODO: Implement this method
        this.dead = true;
    }


    @Override
    public boolean isDead() {
        // TODO: Implement this method

        return this.dead;
    }


    @Override
    public void setDamage(int damage) {
        // TODO: Implement this method
        this.damage = damage;
        setHP(getHP() - damage);
    }

    @Override
    public int getDamage() {
        // TODO: Implement this method
        return damage;
    }


    @Override
    public void setFontAlpha(float fontAlpha) {
        // TODO: Implement this method
        this.fontAlpha = fontAlpha;
    }

    @Override
    public float getFontAlpha() {
        // TODO: Implement this method
        return fontAlpha;
    }

    @Override
    public void setAttack(int attack) {
        // TODO: Implement this method
        this.attack = attack;
    }

    @Override
    public int getAttack() {
        // TODO: Implement this method
        return attack;
    }

    @Override
    public void setHP(int HP) {
        // TODO: Implement this method
        this.HP = HP;
        if (this.HP > maxHP) {
            this.HP = maxHP;
        }

    }

    @Override
    public int getHP() {
        // TODO: Implement this method
        return HP;
    }


    @Override
    public void attackRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margen, margen, margen));
        rects.add(new MyRect(getX(), getY() - margen, margen, margen));
        rects.add(new MyRect(getX() - margen, getY(), margen, margen));
        rects.add(new MyRect(getX() + margen, getY(), margen, margen));

        //shape.setColor(1, 0, 0, .2f);
    }


    @Override
    public void moveRects() {
        // TODO: Implement this method
        rects.clear();

        rects.add(new MyRect(getX(), getY() + margen, margen, margen));
        rects.add(new MyRect(getX(), getY() - margen, margen, margen));
        rects.add(new MyRect(getX() - margen, getY(), margen, margen));
        rects.add(new MyRect(getX() + margen, getY(), margen, margen));

        //shape.setColor(1, 1, 1, .2f);
    }


    @Override
    public int getSpeed() {
        // TODO: Implement this method
        return speed;
    }

    @Override
    public void setPlayerState(stdPlayerState newPlayerState) {
        // TODO: Implement this method
        getPlayerState().exit(this, delta);
        this.actorState = newPlayerState;
        getPlayerState().enter(this, delta);
    }


    @Override
    public stdPlayerState getPlayerState() {
        // TODO: Implement this method
        return actorState;
    }

    @Override
    public void setCurY(float curY) {
        this.curY = curY;
    }

    @Override
    public float getCurY() {
        return curY;
    }

    @Override
    public void setCurX(float curX) {
        this.curX = curX;
    }

    @Override
    public float getCurX() {
        return curX;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    @Override
    public boolean getFlipY() {
        return flipY;
    }

    @Override
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    @Override
    public boolean getFlipX() {
        return flipX;
    }

    @Override
    public void setVelX(int velX) {
        this.velX = velX;
    }

    @Override
    public int getVelX() {
        return velX;
    }

    @Override
    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public int getDir() {
        return dir;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getRectangle() {
        //rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public boolean drawRect(MyRect rect) {
        shape.setColor(rect.getColor());
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        return false;
    }

    @Override
    public boolean isTouched(float x, float y) {

        if (getPlayerState() == stdPlayerState.WAITING_TO_MOVE) {

        }

        return false;
    }


    @Override
    public void setRotation(float degrees) {
        // TODO: Implement this method
        super.setRotation(degrees);
    }

    @Override
    public float getRotation() {
        // TODO: Implement this method
        return super.getRotation();
    }

    @Override
    public void scaleBy(float scale) {
        // TODO: Implement this method


        super.scaleBy(scale);

    }

    @Override
    public void act(float delta) {
        // TODO: Implement this method
        getPlayerState().update(this, delta);

        super.act(delta);
    }

    @Override
    public void setPosition(float x, float y) {
        // TODO: Implement this method
        super.setPosition(x, y);
    }

    @Override
    public float getCenterX() {
        // TODO: Implement this method
        return super.getCenterX();
    }

    @Override
    public float getCenterY() {
        // TODO: Implement this method
        return super.getCenterY();
    }

    @Override
    public void setCenterPosition(float x, float y) {
        // TODO: Implement this method
        super.setCenterPosition(x, y);
    }
}
