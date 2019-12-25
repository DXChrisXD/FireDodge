package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.landing.flappyitm.FlappyITM;

public class MenuState extends State{

    private static final int LEFT_BUTTONX = 72;

    private static final int RIGHT_BUTTONSPACE = 56;

    private static final int BOTTOM_BUTTONY = 60;

    private static final int BOTTOM_EXITBUTTONY = 598;

    private static final int RIGHT_EXITSPACE = 67;

    int highscore;

    int wait;

    private Texture bg;
    Texture gameOverBanner;
    Texture Debug;
    Texture TryAgain;
    Texture HomeMenu;
    Texture StartButton;
    Texture ExitButton;
    BitmapFont scoreFont;
    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    public MenuState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait = 0;

        //Sound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //Load texture and fonts
        gameOverBanner = new Texture("game_over.png");
        Debug = new Texture("Debug.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        TryAgain = new Texture ("config_btn.png");
        HomeMenu = new Texture ("user_btn.png");
        StartButton = new Texture ("main_btn.png");
        ExitButton = new Texture ("exit_btn.png");
        bg = new Texture ("Background_Home.png");
    }



    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        wait+=1;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(bg,camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));


        float tryAgainX = LEFT_BUTTONX;
        float tryAgainY = TryAgain.getHeight()+BOTTOM_BUTTONY;
        float mainMenuX = FlappyITM.WITDH-HomeMenu.getWidth()-RIGHT_BUTTONSPACE;
        float mainMenuY = HomeMenu.getHeight()+BOTTOM_BUTTONY;
        float startButtonX = FlappyITM.WITDH / 2 - StartButton.getWidth() / 2;
        float startButtonY = FlappyITM.HEIGHT / 2 + StartButton.getHeight() / 2;
        float exitButtonX = FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE;
        float exitButtonY = ExitButton.getHeight()+BOTTOM_EXITBUTTONY;

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if(wait > FlappyITM.WAIT_TIME){

            if (Gdx.input.isTouched()){
                //Try Again
                if (touch.x > tryAgainX && touch.x < tryAgainX + TryAgain.getWidth() && touch.y > tryAgainY - TryAgain.getHeight() && touch.y < tryAgainY ){
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new ConfigurationState(gsm));
                    return;
                }

                //Main menu
                if (touch.x > mainMenuX && touch.x < mainMenuX + HomeMenu.getWidth() && touch.y > mainMenuY - HomeMenu.getHeight() && touch.y < mainMenuY ){
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new SecondMenuState(gsm));
                    return;
                }
                if (touch.x > startButtonX && touch.x < startButtonX + StartButton.getWidth() && touch.y > startButtonY - StartButton.getHeight() && touch.y < startButtonY ){
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new PlayState(gsm));
                    return;
                }
                if (touch.x > exitButtonX && touch.x < exitButtonX + ExitButton.getWidth() && touch.y > exitButtonY - ExitButton.getHeight() && touch.y < exitButtonY ){
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    Gdx.app.exit();
                    return;
                }
            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(TryAgain,LEFT_BUTTONX,BOTTOM_BUTTONY);
        spriteBatch.draw(HomeMenu,FlappyITM.WITDH-HomeMenu.getWidth()-RIGHT_BUTTONSPACE,BOTTOM_BUTTONY);
        spriteBatch.draw(StartButton,FlappyITM.WITDH/2 - StartButton.getWidth() / 2, FlappyITM.HEIGHT/2 - StartButton.getHeight() /2);
        spriteBatch.draw(ExitButton,FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE,BOTTOM_EXITBUTTONY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        TryAgain.dispose();
        HomeMenu.dispose();
        StartButton.dispose();
        ExitButton.dispose();
        bg.dispose();
        ButtonSound.dispose();
    }
}

