package com.landing.flappyitm.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.landing.flappyitm.FlappyITM;

public class Danger implements InputProcessor {


    private static final int MOVEMENT = 100;
    private static final int GRAVITY = 4;

    private boolean movingRight;

    private Vector3 position;
    private Vector3 velocity;

    private Animation birdAnimation;
    private Texture texture;

    private Rectangle bounds;

    private Sound flap;

    public Danger(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("Danger.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        //flap = Gdx.audio.newSound(Gdx.files.internal("Shot.mp3"));
        bounds = new Rectangle(x,y,texture.getWidth() / 3, texture.getHeight());
        Gdx.input.setInputProcessor(this);
    }
    public void update(float dt){
        birdAnimation.update(dt);
        //if(position.y>0){
        //   velocity.add(0, GRAVITY,0);
        //}
        //velocity.scl(dt);

        //if(position.x <= 0){
        //    position.set(0,0,0);
        //}

        //position.add(velocity.x,0,0);

        //if(position.x >= FlappyITM.WITDH - texture.getWidth()/3){
        //    position.set(FlappyITM.WITDH - texture.getWidth()/3,0,0);
        //}

        //if(position.y <0){
        //    position.y = 0;
        //}
        //velocity.scl(1/dt);

        //bounds.setPosition(position.x, position.y);

    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        //velocity.y = 250;
        //flap.play(0.5f);
    }

    public void GoRight(){
        //velocity.x = 1f;
        //flap.play(FlappyITM.VOLUMESHOT);
        //Agregar sonido como flap.play
    }

    public void GoLeft(){
        //velocity.x = -100;
        //flap.play(FlappyITM.VOLUMESHOT);
        //Agregar sonido como flap.play
    }

    public void dispose(){
        texture.dispose();
        //flap.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            System.out.println(FlappyITM.WITDH);
            System.out.println(FlappyITM.HEIGHT);
      //  System.out.println("Touch X :"+Gdx.input.getX());
      //  System.out.println("Touch Y :"+Gdx.input.getY());
      //  System.out.println("Ancho completo: "+ Gdx.graphics.getWidth());
      //  System.out.println("Ancho a la mitad: "+ Gdx.graphics.getWidth()/2);
            //if (Gdx.input.getX() > 0 && Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
            //    velocity.x = -8f;
            //    flap.play(0.5f);
            //}
            //if (Gdx.input.getX() < Gdx.graphics.getWidth() - 0 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
            //    velocity.x = 8f;
            //    flap.play(0.5f);
           // }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //velocity.x = 0;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
