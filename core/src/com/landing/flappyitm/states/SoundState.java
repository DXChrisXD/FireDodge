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

public class SoundState extends State{

    private static final int BUTTONX = 56;

    private static final int BUTTONY = 60;

    private static final int TITLEX = 260;

    private static final int TITLEY = 589;

    private static final int TEXTX = 335;

    private static final int MUSICY = 515;

    private static final int SHOTY = 403;

    private static final int EXPLOTIONY = 287;

    private static final int LEFTBUTTONX = 307;

    private static final int RIGHTBUTTONX = 129;

    private static final int BUTTON1Y = 440;

    private static final int BUTTON2Y = 328;

    private static final int BUTTON3Y = 212;

    private static final int MIDDLETEXTX = 252;

    private static final int MIDDLETEXT1Y = 461;

    private static final int MIDDLETEXT2Y = 349;

    private static final int MIDDLETEXT3Y = 233;


    int wait;

    private Texture bg;
    Texture Debug;
    Texture Button;
    Texture Less;
    Texture Plus;
    BitmapFont scoreFont;
    BitmapFont scoreFont2;

    int music;
    int shot;
    int explotion;

    float Cmusic;
    float Cshot;
    float Cexplotion;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Preferences prefs;

    Vector3 touch = new Vector3();

    public SoundState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait=0;

        //ButtonSound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        Cmusic = FlappyITM.VOLUMEMUSIC;
        Cshot = FlappyITM.VOLUMESHOT;
        Cexplotion = FlappyITM.VOLUMEEXPLOTION;
        checkVolumeMusic();
        checkVolumeExplotion();
        checkVolumeShot();

        //Preferences
        prefs = Gdx.app.getPreferences("flappyitm");

        //Load texture and fonts
        Debug = new Texture("Debug.png");
        Button = new Texture ("back_btn.png");
        Less = new Texture ("less_btn.png");
        Plus = new Texture ("plus_btn.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        scoreFont2 = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        bg = new Texture ("Background_Sound.png");
    }

    public void checkVolumeMusic(){
        if(Cmusic > 0f && Cmusic <= 0.1f ){
            music = 10;
        }else if(Cmusic > 0.1f && Cmusic <= 0.2f){
            music = 20;
        }else if(Cmusic > 0.2f && Cmusic <= 0.3f){
            music = 30;
        }else if(Cmusic > 0.3f && Cmusic <= 0.4f){
            music = 40;
        }else if(Cmusic > 0.4f && Cmusic <= 0.5f){
            music = 50;
        }else if(Cmusic > 0.5f && Cmusic <= 0.6f){
            music = 60;
        }else if(Cmusic > 0.6f && Cmusic <= 0.7f){
            music = 70;
        }else if(Cmusic > 0.7f && Cmusic <= 0.8f){
            music = 80;
        }else if(Cmusic > 0.8f && Cmusic <= 0.9f){
            music = 90;
        }else if(Cmusic > 0.9f && Cmusic <= 1f){
            music = 100;
        }
        System.out.println("Cmusic: "+Cmusic);
        System.out.println("Cshot: "+Cshot);
        System.out.println("Cexplotion: "+Cexplotion);
        System.out.println("VOLUME MUSIC: "+FlappyITM.VOLUMEMUSIC);
        System.out.println("VOLUME SHOT: "+FlappyITM.VOLUMESHOT);
        System.out.println("VOLUME EXPLOTION: "+FlappyITM.VOLUMEEXPLOTION);
    }//Fin de checkVolumeMusic
    public void checkVolumeShot(){
        if(Cshot > 0.0f && Cshot <= 0.1f ){
            shot = 10;
        }else if(Cshot > 0.1f && Cshot <= 0.2f){
            shot = 20;
        }else if(Cshot > 0.2f && Cshot <= 0.3f){
            shot = 30;
        }else if(Cshot > 0.3f && Cshot <= 0.4f){
            shot = 40;
        }else if(Cshot > 0.4f && Cshot <= 0.5f){
            shot = 50;
        }else if(Cshot > 0.5f && Cshot <= 0.6f){
            shot = 60;
        }else if(Cshot > 0.6f && Cshot <= 0.7f){
            shot = 70;
        }else if(Cshot > 0.7f && Cshot <= 0.8f){
            shot = 80;
        }else if(Cshot > 0.8f && Cshot <= 0.9f){
            shot = 90;
        }else if(Cshot > 0.9f && Cshot <= 1f){
            shot = 100;
        }
    }
    public void checkVolumeExplotion(){
        if(Cexplotion > 0.0f && Cexplotion <= 0.1f ){
            explotion = 10;
        }else if(Cexplotion > 0.1f && Cexplotion <= 0.2f){
            explotion = 20;
        }else if(Cexplotion > 0.2f && Cexplotion <= 0.3f){
            explotion = 30;
        }else if(Cexplotion > 0.3f && Cexplotion <= 0.4f){
            explotion = 40;
        }else if(Cexplotion > 0.4f && Cexplotion <= 0.5f){
            explotion = 50;
        }else if(Cexplotion > 0.5f && Cexplotion <= 0.6f){
            explotion = 60;
        }else if(Cexplotion > 0.6f && Cexplotion <= 0.7f){
            explotion = 70;
        }else if(Cexplotion > 0.7f && Cexplotion <= 0.8f){
            explotion = 80;
        }else if(Cexplotion > 0.8f && Cexplotion <= 0.9f){
            explotion = 90;
        }else if(Cexplotion > 0.9f && Cexplotion <= 1f){
            explotion = 100;
        }
    }

    public void upVolumeMusic(){
        Cmusic += 0.1f;
        limitMusic();
    }
    public void upVolumeShot(){
        Cshot+= 0.1f;
        limitShot();
    }
    public void upVolumeExplotion(){
        Cexplotion += 0.1f;
        limitExplotion();
    }

    public void downVolumeMusic(){
        Cmusic += -0.1f;
    }
    public void downVolumeShot(){
        Cshot += -0.1f;
    }
    public void downVolumeExplotion(){
        Cexplotion += -0.1f;
    }

    public void updateVolumeMusic(){
        FlappyITM.VOLUMEMUSIC = Cmusic;
        prefs.putFloat("volumemusic",Cmusic);
        prefs.flush();
    }
    public void updateVolumeShot(){
        FlappyITM.VOLUMESHOT = Cshot;
        prefs.putFloat("volumeshot",Cshot);
        prefs.flush();
    }
    public void updateVolumeExplosion(){
        FlappyITM.VOLUMEEXPLOTION = Cexplotion;
        prefs.putFloat("volumeexplotion",Cexplotion);
        prefs.flush();
    }
    public void limitMusic(){
        if(Cmusic > 1f){
            Cmusic = 1f;
        }else if(Cmusic < -0.1f){
            Cmusic = 0f;
        }
    }
    public void limitShot(){
        if(Cshot > 1f){
            Cshot = 1f;
        }else if(Cshot < 0.01f){
            Cshot = 0f;
        }
    }
    public void limitExplotion(){
        if(Cexplotion > 1f){
            Cexplotion = 1f;
        }else if(Cexplotion < 0.01f){
            Cexplotion = 0f;
        }
    }

    public void cooldown(){
        wait = 0;
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

        GlyphLayout titleLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeySound), Color.WHITE, 0, Align.left, false);
        GlyphLayout textLayout1 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeySound1_1), Color.WHITE, 0, Align.left, false);
        GlyphLayout textLayout2 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeySound1_2), Color.WHITE, 0, Align.left, false);
        GlyphLayout textLayout3 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeySound1_3), Color.WHITE, 0, Align.left, false);
        GlyphLayout buttonLayout1 = new GlyphLayout(scoreFont2, ""+music+"%", Color.WHITE, 0, Align.left, false);
        GlyphLayout buttonLayout2 = new GlyphLayout(scoreFont2, ""+shot+"%", Color.WHITE, 0, Align.left, false);
        GlyphLayout buttonLayout3 = new GlyphLayout(scoreFont2, ""+explotion+"%", Color.WHITE, 0, Align.left, false);



        scoreFont.draw(spriteBatch, titleLayout, FlappyITM.WITDH - TITLEX,TITLEY);
        scoreFont2.draw(spriteBatch, textLayout1, FlappyITM.WITDH - TEXTX,MUSICY);
        scoreFont2.draw(spriteBatch, textLayout2, FlappyITM.WITDH - TEXTX,SHOTY);
        scoreFont2.draw(spriteBatch, textLayout3, FlappyITM.WITDH - TEXTX,EXPLOTIONY);
        scoreFont2.draw(spriteBatch, buttonLayout1, FlappyITM.WITDH - MIDDLETEXTX,MIDDLETEXT1Y);
        scoreFont2.draw(spriteBatch, buttonLayout2, FlappyITM.WITDH - MIDDLETEXTX,MIDDLETEXT2Y);
        scoreFont2.draw(spriteBatch, buttonLayout3, FlappyITM.WITDH - MIDDLETEXTX,MIDDLETEXT3Y);
        scoreFont.getData().setScale(0.8f, 0.8f);
        scoreFont2.getData().setScale(0.7f, 0.7f);


        float ButtonX = FlappyITM.WITDH-Button.getWidth()-BUTTONX;
        float ButtonY = Button.getHeight()+BUTTONY;
        float LessMusicX = FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX;
        float LessMusicY = Less.getHeight()+BUTTON1Y;
        float PlusMusicX = FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX;
        float PlusMusicY = Plus.getHeight()+BUTTON1Y;
        float LessShotX = FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX;
        float LessShotY = Less.getHeight()+BUTTON2Y;
        float PlusShotX = FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX;
        float PlusShotY = Plus.getHeight()+BUTTON2Y;
        float LessExplotionX = FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX;
        float LessExplotionY = Less.getHeight()+BUTTON3Y;
        float PlusExplotionX = FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX;
        float PlusExplotionY = Plus.getHeight()+BUTTON3Y;


        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if (wait > FlappyITM.WAIT_TIME){

            if (Gdx.input.isTouched()) {
                //Try Again
                if (touch.x > ButtonX && touch.x < ButtonX + Button.getWidth() && touch.y > ButtonY - Button.getHeight() && touch.y < ButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    updateVolumeMusic();
                    this.dispose();
                    spriteBatch.end();
                    gsm.set(new ConfigurationState(gsm));
                    return;
                }
                if (touch.x > LessMusicX && touch.x < LessMusicX + Less.getWidth() && touch.y > LessMusicY - Less.getHeight() && touch.y < LessMusicY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    downVolumeMusic();
                    checkVolumeMusic();
                    cooldown();
                }
                if (touch.x > PlusMusicX && touch.x < PlusMusicX + Plus.getWidth() && touch.y > PlusMusicY - Plus.getHeight() && touch.y < PlusMusicY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    upVolumeMusic();
                    checkVolumeMusic();
                    cooldown();
                }
                if (touch.x > LessShotX && touch.x < LessShotX + Less.getWidth() && touch.y > LessShotY - Less.getHeight() && touch.y < LessShotY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    downVolumeShot();
                    checkVolumeShot();
                    cooldown();
                }
                if (touch.x > PlusShotX && touch.x < PlusShotX + Plus.getWidth() && touch.y > PlusShotY - Plus.getHeight() && touch.y < PlusShotY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    upVolumeShot();
                    checkVolumeShot();
                    cooldown();
                }
                if (touch.x > LessExplotionX && touch.x < LessExplotionX + Less.getWidth() && touch.y > LessExplotionY - Less.getHeight() && touch.y < LessExplotionY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    downVolumeExplotion();
                    checkVolumeExplotion();
                    cooldown();
                }
                if (touch.x > PlusExplotionX && touch.x < PlusExplotionX + Plus.getWidth() && touch.y > PlusExplotionY - Plus.getHeight() && touch.y < PlusExplotionY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    upVolumeExplotion();
                    checkVolumeExplotion();
                    cooldown();
                }

            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(Button,FlappyITM.WITDH-Button.getWidth()-BUTTONX,BUTTONY);
        spriteBatch.draw(Less, FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX,BUTTON1Y);
        spriteBatch.draw(Plus, FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX,BUTTON1Y);
        spriteBatch.draw(Less, FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX,BUTTON2Y);
        spriteBatch.draw(Plus, FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX,BUTTON2Y);
        spriteBatch.draw(Less, FlappyITM.WITDH-Less.getWidth()-LEFTBUTTONX,BUTTON3Y);
        spriteBatch.draw(Plus, FlappyITM.WITDH-Plus.getWidth()-RIGHTBUTTONX,BUTTON3Y);


        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        bg.dispose();
        Button.dispose();
        Less.dispose();
        Plus.dispose();
        scoreFont.dispose();
        scoreFont2.dispose();
        ButtonSound.dispose();
    }
}
