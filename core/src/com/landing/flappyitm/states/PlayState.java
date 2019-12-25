package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.landing.flappyitm.FlappyITM;
import com.landing.flappyitm.sprites.Asteroid;
import com.landing.flappyitm.sprites.Bird;
import com.landing.flappyitm.sprites.Bullet;
import com.landing.flappyitm.sprites.CollisionRect;
import com.landing.flappyitm.sprites.Danger;
import com.landing.flappyitm.sprites.Explosion;
import com.landing.flappyitm.sprites.Tube;
import com.landing.flappyitm.sprites.ScrollingBackground;

import org.omg.CORBA.Current;

import java.util.ArrayList;
import java.util.Random;


public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private static final int LIMITSHOT = 1;

    private static final int HealthBarY = -180;

    private static final int SCOREY = 400;

    private static final int SCOREX = 64;

    private static final int DANGERX = 0;

    private static final int DANGERY = -229;

    public static float MIN_ASTEROID_SPAWN_TIME = 0.3f;
    public static float MAX_ASTEROID_SPAWN_TIME = 0.6f;

    private int RandomNumber = 0;

    private Bird bird;
    private Texture bg;

    private Texture Debug;

    private Texture ground;
    private Vector2 groundPos1;
    private Vector2 groundPos2;

    Vector3 touch = new Vector3();

    float asteroidSpawnTimer;

    Random random;

    ArrayList<Asteroid> asteroids;
    ArrayList<Explosion> explosions;
    private Array<Tube> tubes;

    Texture blank;

    int CurrentHealth;

    Texture HealthBar1;
    Texture HealthBar2;
    Texture HealthBar3;
    Texture HealthBar4;
    Texture HealthBar5;
    Texture HealthBar6;
    Texture HealthBar7;
    Texture HealthBar8;
    Texture HealthBar9;
    Texture HealthBar10;

    Texture bg2;

    Sound BulletExplotion;

    BitmapFont scoreFont;

    CollisionRect playerRect;

    Texture texture;
    Danger danger;

    float health = 1; //0=dead, 1 = full health

    int score;

    int timer;

    int wait;

    int limitshot;

    float y;
    float x;

    ArrayList<Bullet> bullets;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        y= 30;
        x = FlappyITM.WITDH/2 - 17;
        bird = new Bird(FlappyITM.WITDH/2 -17,0);
        bullets = new ArrayList<Bullet>();

        //Wait
        wait=0;

        //limitshot
        limitshot=0;

        //Animation
        danger = new Danger(DANGERX,DANGERY);

        //Bullet Explotion
        BulletExplotion = Gdx.audio.newSound(Gdx.files.internal("Explosion.mp3"));

        //HealthBar
        HealthBar1 = new Texture ("health_bar_10%.png");
        HealthBar2 = new Texture ("health_bar_20%.png");
        HealthBar3 = new Texture ("health_bar_30%.png");
        HealthBar4 = new Texture ("health_bar_40%.png");
        HealthBar5 = new Texture ("health_bar_50%.png");
        HealthBar6 = new Texture ("health_bar_60%.png");
        HealthBar7 = new Texture ("health_bar_70%.png");
        HealthBar8 = new Texture ("health_bar_80%.png");
        HealthBar9 = new Texture ("health_bar_90%.png");
        HealthBar10 = new Texture ("health_bar_100%.png");
        CurrentHealth = 100;


        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);
        Debug = new Texture("Debug.png");
        bg = new Texture("Background_Name.png");
        bg2 = new Texture ("Background_Game_#2.png");
        ground = new Texture("ground.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));

        playerRect = new CollisionRect(bird.getPosition().x,bird.getPosition().y, 100, 63);

        blank = new Texture("blank.png");

        score = 0;

        asteroids = new ArrayList<Asteroid>();
        random = new Random();
        asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;

        explosions = new ArrayList<Explosion>();

        //groundPos1 = new Vector2 (camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        //groundPos2 = new Vector2 ((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        //tubes = new Array<Tube>();

        //for(int i= 1; i<=TUBE_COUNT; i++){
        //    tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) ));
        //}
    }

    private void accelerate(){
        if(timer >= 1500){
            MAX_ASTEROID_SPAWN_TIME = 0.5f;
        }else if(timer >= 2500){
            MAX_ASTEROID_SPAWN_TIME = 0.4f;
        }else if(timer >= 4000){
            MIN_ASTEROID_SPAWN_TIME = 0.2f;
        }else if(timer >= 4800){
            MAX_ASTEROID_SPAWN_TIME = 0.3f;
        }else if(timer >= 6400){
            MIN_ASTEROID_SPAWN_TIME = 0.1f;
        }
    }

    @Override
    protected void handleInput() {

        if(wait >= FlappyITM.WAIT_TIME){

                if(Gdx.input.justTouched()){
                    bird.touchDown((int) camera.viewportWidth/2,(int) camera.viewportHeight/2,1,1);
                    //if(limitshot >= LIMITSHOT){
                    bullets.add(new Bullet(bird.getPosition().x + 4));
                    bullets.add(new Bullet(bird.getPosition().x + 96));
                    //}//Fin de la comprobacion limitshot
                }

       }//Fin de la comprobacion wait
    }

    @Override
    public void update(float dt) {
       // System.out.println("Posicion pajaro: "+ bird.getPosition().x);
        //score += 1;
        handleInput();
        timer += 1;
        wait += 1;
        //limitshot += 1;
        //if(limitshot > LIMITSHOT){
        //    ResetLimitShot();
        //}
        //updateGround();
        bird.update(dt);
        accelerate();
        //update bullets
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : bullets){
            bullet.update(dt);
            if (bullet.remove)
                bulletsToRemove.add(bullet);
        }
        bullets.removeAll(bulletsToRemove);

        //After player moves, update collision


        camera.position.y = bird.getPosition().y + 130;

        //Asteroid spawn code
        asteroidSpawnTimer -= dt;
        if (asteroidSpawnTimer <= 0){
            asteroidSpawnTimer = random.nextFloat() * (MAX_ASTEROID_SPAWN_TIME - MIN_ASTEROID_SPAWN_TIME) + MIN_ASTEROID_SPAWN_TIME;
            asteroids.add(new Asteroid(random.nextInt(FlappyITM.WITDH - Asteroid.WIDTH)));
        }

        //update asteroids
        ArrayList<Asteroid> asteroidsToRemove = new ArrayList<Asteroid>();
        for (Asteroid asteroid : asteroids){
            asteroid.update(dt);
            if(asteroid.remove)
                asteroidsToRemove.add(asteroid);
        }


        //update explosions
        ArrayList<Explosion> explosionsToRemove = new ArrayList<Explosion>();
        for (Explosion explosion : explosions){
            explosion.update(dt);
            if(explosion.remove)
                explosionsToRemove.add(explosion);
        }
        explosions.removeAll(explosionsToRemove);

        playerRect.move(bird.getPosition().x, bird.getPosition().y);

        //After all updates, check for collisions

        for (Bullet bullet : bullets) {
            for (Asteroid asteroid : asteroids) {
                if (bullet.getCollisionRect().collideWith(asteroid.getCollisionRect())) {//Collision occured
                    bulletsToRemove.add(bullet);
                    asteroidsToRemove.add(asteroid);
                    explosions.add(new Explosion(asteroid.getX(), asteroid.getY()));
                    BulletExplotion.play(FlappyITM.VOLUMEEXPLOTION);
                    score += 100;
                }
            }
        }
        bullets.removeAll(bulletsToRemove);

        for (Asteroid asteroid : asteroids){
            if(asteroid.getCollisionRect().collideWith(playerRect)){
                asteroidsToRemove.add(asteroid);
                CurrentHealth -= 10;
                health -= 1f;

                //if health is depleted, go to game over screen
                if(CurrentHealth <= 0){
                    this.dispose();
                    gsm.set(new GameOverScreen(gsm, score));
                    return;
                }
            }
        }
        asteroids.removeAll(asteroidsToRemove);




        //for(int i = 0;i<tubes.size;i++ ){
        //    Tube tube = tubes.get(i);
        //    if(camera.position.x - (camera.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
        //        tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
        //    }
        //    if(tube.collides(bird.getBounds())){
        //        gsm.set(new PlayState(gsm));
        //    }
        //}

        //if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
        //    gsm.set(new PlayState(gsm));
        //}



        danger.update(dt);

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        //FlappyITM.HEIGHT/2 - scoreLayout.height - 10
        spriteBatch.draw(bg,camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));
        spriteBatch.draw(bg2,camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));

        for (Bullet bullet : bullets){
            bullet.render(spriteBatch);
        }

        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);
        }
        for (Explosion explosion : explosions){
            explosion.render(spriteBatch);
        }



        //Draw CurrentHealth
        if(CurrentHealth == 100){
            spriteBatch.draw(HealthBar10, FlappyITM.WITDH / 2 - HealthBar10.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 90){
            HealthBar10.dispose();
            spriteBatch.draw(HealthBar9, FlappyITM.WITDH / 2 - HealthBar9.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 80){
            HealthBar9.dispose();
            spriteBatch.draw(HealthBar8, FlappyITM.WITDH / 2 - HealthBar8.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 70){
            HealthBar8.dispose();
            spriteBatch.draw(HealthBar7, FlappyITM.WITDH / 2 - HealthBar7.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 60){
            HealthBar7.dispose();
            spriteBatch.draw(HealthBar6, FlappyITM.WITDH / 2 - HealthBar6.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 50){
            HealthBar6.dispose();
            spriteBatch.draw(HealthBar5, FlappyITM.WITDH / 2 - HealthBar5.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 40){
            HealthBar5.dispose();
            spriteBatch.draw(HealthBar4, FlappyITM.WITDH / 2 - HealthBar4.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 30){
            HealthBar4.dispose();
            spriteBatch.draw(HealthBar3, FlappyITM.WITDH / 2 - HealthBar3.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 20){
            spriteBatch.draw(danger.getTexture(), danger.getPosition().x, danger.getPosition().y);
            HealthBar3.dispose();
            spriteBatch.draw(HealthBar2, FlappyITM.WITDH / 2 - HealthBar2.getWidth() / 2, HealthBarY);
        }else if(CurrentHealth == 10){
            spriteBatch.draw(danger.getTexture(), danger.getPosition().x, danger.getPosition().y);
            HealthBar2.dispose();
            spriteBatch.draw(HealthBar1, FlappyITM.WITDH / 2 - HealthBar1.getWidth() / 2, HealthBarY);
        }

        //Draw Health
        //if(health > 0.6f)
        //    spriteBatch.setColor(Color.GREEN);
        //else if(health > 0.2f)
        //    spriteBatch.setColor(Color.ORANGE);
        //else
        //    spriteBatch.setColor(Color.RED);
        //spriteBatch.draw(blank, 0, 0, FlappyITM.WITDH * health, 5);

        spriteBatch.setColor(Color.WHITE);

        //for (Tube tube:tubes){
        //    spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x,tube.getPosTopTube().y);
        //    spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        //}

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, ""+score);
        scoreFont.draw(spriteBatch, scoreLayout, SCOREX,SCOREY);

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(danger.getTexture(), danger.getPosition().x, danger.getPosition().y);

        //spriteBatch.draw(Debug,touch.x,touch.y);

//        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
//        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
          spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        //ground.dispose();
        bird.dispose();
        scoreFont.dispose();
        blank.dispose();
        BulletExplotion.dispose();
        HealthBar1.dispose();
        HealthBar2.dispose();
        HealthBar3.dispose();
        HealthBar4.dispose();
        HealthBar5.dispose();
        HealthBar6.dispose();
        HealthBar7.dispose();
        HealthBar8.dispose();
        HealthBar9.dispose();
        HealthBar10.dispose();
        danger.dispose();
        //for(Asteroid asteroid : asteroids){
        //   asteroid.dispose();
        //}
        System.out.println("STATE DISPOSED");
    }
 //   private void updateGround(){
 //       if(camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
 //           groundPos1.add(ground.getWidth() * 2, 0);
 //       }
 //       if(camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
 //           groundPos2.add(ground.getWidth() * 2, 0);
 //       }
 //   }

    private void GenerateRandom(){
        RandomNumber = 1+ (int) (Math.random()*10);
    }

    private void ResetLimitShot(){
        limitshot = 0;
    }
}
