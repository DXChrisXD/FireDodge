package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.landing.flappyitm.FlappyITM;

public class ProfileState extends State implements TextInputListener{

    private static final int BUTTONX = 56;

    private static final int BUTTONY = 60;

    private static final int TITLEX = 260;

    private static final int TITLEY = 589;

    private static final int TEXT1X = 348;

    private static final int TEXT1Y = 525;

    private static final int TEXT2Y = 189;

    private static final int INPUTX = 281;

    private static final int INPUTY = 391;

    private static final int SAVEX = 143;

    private static final int SAVEY = 246;

    private static final int TSAVEX = 231;

    private static final int TSAVEY = 288;

    private static final int BUTTONINPUTX = 116;

    private static final int BUTTONINPUTY = 359;


    int wait;

    int AnchoTexto;

    private Texture bg;
    Texture Debug;
    Texture Button;
    Texture Save;
    Texture ButtonInput;
    BitmapFont scoreFont;
    BitmapFont scoreFont2;

    String TextName;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    Preferences prefs;

    public ProfileState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait=0;
        AnchoTexto = 241;

        //Preferences
        prefs = Gdx.app.getPreferences("flappyitm");
        this.TextName = prefs.getString("playername","Player");

        //ButtonSound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //Load texture and fonts
        Debug = new Texture("Debug.png");
        Button = new Texture ("back_btn.png");
        Save = new Texture ("save_btn.png");
        ButtonInput = new Texture ("name_input.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        scoreFont2 = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        bg = new Texture ("Background_Add_Name.png");
    }

    public void updatePlayername(){
        FlappyITM.PLAYERNAME = TextName;
        prefs.putString("playername",TextName);
        prefs.flush();
    }//Fin del metodo updatePlayername

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

        GlyphLayout titleLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyProfile), Color.WHITE, 0, Align.left, false);
        GlyphLayout textLayout1 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeyProfile1_1), Color.WHITE, AnchoTexto, Align.center, true);
        //GlyphLayout textLayout2 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeyProfile1_2), Color.WHITE, AnchoTexto, Align.center, true);
        GlyphLayout inputLayout = new GlyphLayout(scoreFont2, TextName, Color.BLACK, 0, Align.left, false);
        GlyphLayout saveLayout = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeySave), Color.WHITE, 0, Align.center, false);


        spriteBatch.draw(ButtonInput, FlappyITM.WITDH - ButtonInput.getWidth() - BUTTONINPUTX,BUTTONINPUTY);

        scoreFont.draw(spriteBatch, titleLayout, FlappyITM.WITDH - TITLEX,TITLEY);
        scoreFont2.draw(spriteBatch, textLayout1, FlappyITM.WITDH - TEXT1X,TEXT1Y);
        //scoreFont2.draw(spriteBatch, textLayout2, FlappyITM.WITDH - TEXT1X,TEXT2Y);
        scoreFont2.draw(spriteBatch, inputLayout, FlappyITM.WITDH - INPUTX,INPUTY);
        scoreFont2.draw(spriteBatch, saveLayout, FlappyITM.WITDH - TSAVEX,TSAVEY);
        scoreFont.getData().setScale(0.8f, 0.8f);
        scoreFont2.getData().setScale(0.7f, 0.7f);


        float ButtonX = FlappyITM.WITDH-Button.getWidth()-BUTTONX;
        float ButtonY = Button.getHeight()+BUTTONY;
        float SaveX = FlappyITM.WITDH-Save.getWidth()-SAVEX;
        float SaveY = Save.getHeight()+SAVEY;
        float ButtonInputX = FlappyITM.WITDH-ButtonInput.getWidth()-BUTTONINPUTX;
        float ButtonInputY = ButtonInput.getHeight()+BUTTONINPUTY;

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if (wait > FlappyITM.WAIT_TIME){

            if (Gdx.input.isTouched()) {
                //Try Again
                if (touch.x > ButtonX && touch.x < ButtonX + Button.getWidth() && touch.y > ButtonY - Button.getHeight() && touch.y < ButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new SecondMenuState(gsm));
                    return;
                }
                if (touch.x > SaveX && touch.x < SaveX + Save.getWidth() && touch.y > SaveY - Save.getHeight() && touch.y < SaveY) {
                    cooldown();
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    updatePlayername();
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new SecondMenuState(gsm));
                    return;
                }
                if (touch.x > ButtonInputX && touch.x < ButtonInputX + ButtonInput.getWidth() && touch.y > ButtonInputY - ButtonInput.getHeight() && touch.y < ButtonInputY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    Gdx.input.getTextInput(this, d.getValue(Dictionary.Keys.KeyPlayername),TextName,d.getValue(Dictionary.Keys.KeyPlayerhint));
                    cooldown();
                }

            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(Button,FlappyITM.WITDH-Button.getWidth()-BUTTONX,BUTTONY);
        spriteBatch.draw(Save,FlappyITM.WITDH - Save.getWidth() - SAVEX,SAVEY);
        //spriteBatch.draw(ButtonInput, FlappyITM.WITDH - ButtonInput.getWidth() - BUTTONINPUTX,BUTTONINPUTY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        bg.dispose();
        scoreFont.dispose();
        scoreFont2.dispose();
        Button.dispose();
        ButtonSound.dispose();
        Save.dispose();
    }

    @Override
    public void input(String text) {
        this.TextName = text;
    }

    @Override
    public void canceled() {
        //TextName = "Player";
    }
}
