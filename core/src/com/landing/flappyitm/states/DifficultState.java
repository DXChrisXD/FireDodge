package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
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

public class DifficultState extends State{

    private static final int BOTTOM_EXITBUTTONY = 67;

    private static final int RIGHT_EXITSPACE = 60;

    private static final int RIGHT_BUTTONX = 78;

    private static final int SOUNDY = 499;

    private static final int LANGUAGEY = 334;

    private static final int CREDITSY = 166;

    private static final int TEXTX = 269;

    private static final int TSOUNDY = 575;

    private static final int TLANGUAGEY = 409;

    private static final int TCREDITSY = 241;

    private static final int EASY = 1;

    private static final int NORMAL = 2;

    private static final int HARD = 3;


    int wait;

    private Texture bg;
    Texture gameOverBanner;
    Texture Debug;
    Texture ExitButton;
    Texture Sound;
    Texture Language;
    Texture Credits;
    BitmapFont scoreFont;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    public DifficultState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait=0;

        //ButtonSound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //Load texture and fonts
        gameOverBanner = new Texture("game_over.png");
        Debug = new Texture("Debug.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        ExitButton = new Texture ("dark_return_btn(Options_Config).png");
        Sound = new Texture ("sound_btn.png");
        Language = new Texture ("language_btn.png");
        Credits = new Texture ("credits_btn.png");
        bg = new Texture ("Background_Config.png");
    }



    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    wait += 1;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
        spriteBatch.begin();

        spriteBatch.draw(bg,camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));

        GlyphLayout soundLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeySound), Color.WHITE, 0, Align.left, false);
        GlyphLayout languageLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyLanguage), Color.WHITE, 0, Align.left, false);
        GlyphLayout creditsLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyCredits), Color.WHITE, 0, Align.left, false);

        scoreFont.draw(spriteBatch, soundLayout, FlappyITM.WITDH - TEXTX,TSOUNDY);
        scoreFont.draw(spriteBatch, languageLayout, FlappyITM.WITDH - TEXTX,TLANGUAGEY);
        scoreFont.draw(spriteBatch, creditsLayout, FlappyITM.WITDH - TEXTX,TCREDITSY);


        float exitButtonX = FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE;
        float exitButtonY = ExitButton.getHeight()+BOTTOM_EXITBUTTONY;
        float soundButtonX = FlappyITM.WITDH / 2 - Sound.getWidth() / 2;
        float soundButtonY = Sound.getHeight()+ SOUNDY;
        float languageButtonX = FlappyITM.WITDH / 2 - Language.getWidth() / 2;
        float languageButtonY = Language.getHeight()+ LANGUAGEY;
        float creditsButtonX = FlappyITM.WITDH / 2 - Credits.getWidth() / 2;
        float creditsButtonY = Credits.getHeight()+ CREDITSY;

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if (wait > FlappyITM.WAIT_TIME){

            if (Gdx.input.isTouched()) {
                //Try Again
                if (touch.x > exitButtonX && touch.x < exitButtonX + ExitButton.getWidth() && touch.y > exitButtonY - ExitButton.getHeight() && touch.y < exitButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new MenuState(gsm));
                    return;
                }
                if (touch.x > soundButtonX && touch.x < soundButtonX + Sound.getWidth() && touch.y > soundButtonY - Sound.getHeight() && touch.y < soundButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new PlayState(gsm));
                    return;
                }
                if (touch.x > languageButtonX && touch.x < languageButtonX + Language.getWidth() && touch.y > languageButtonY - Language.getHeight() && touch.y < languageButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new PlayState(gsm));
                    return;
                }
                if (touch.x > creditsButtonX && touch.x < creditsButtonX + Credits.getWidth() && touch.y > creditsButtonY - Credits.getHeight() && touch.y < creditsButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new PlayState(gsm));
                    return;
                }

            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(ExitButton,FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE,BOTTOM_EXITBUTTONY);
        spriteBatch.draw(Sound, FlappyITM.WITDH-Sound.getWidth()-RIGHT_BUTTONX,SOUNDY);
        spriteBatch.draw(Language, FlappyITM.WITDH-Language.getWidth()-RIGHT_BUTTONX,LANGUAGEY);
        spriteBatch.draw(Credits, FlappyITM.WITDH-Credits.getWidth()-RIGHT_BUTTONX,CREDITSY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        ExitButton.dispose();
        Sound.dispose();
        Language.dispose();
        Credits.dispose();
        bg.dispose();
        scoreFont.dispose();
        ButtonSound.dispose();
    }
}
