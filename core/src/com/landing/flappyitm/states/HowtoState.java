package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.landing.flappyitm.FlappyITM;
import com.landing.flappyitm.sprites.Animation;
import com.landing.flappyitm.sprites.General;

public class HowtoState extends State{

    private static final int BUTTONX = 60;

    private static final int BUTTONY = 74;

    private static final int TITLEX = 275;

    private static final int TITLEY = 616;

    private static final int TEXT1X = 381;

    private static final int TEXT2X = 211;

    private static final int TEXT1Y = 557;

    private static final int TEXT2Y = 412;

    private static final int TEXT3Y = 202;

    private static final int GENERALX = -100;

    private static final int GENERALY = -125;

    int wait;

    int AnchoTexto, AnchoTexto2;

    private Texture bg;
    Texture Debug;
    Texture Button;
    BitmapFont scoreFont;
    BitmapFont scoreFont2;
    Texture texture;
    General general;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    public HowtoState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait=0;
        AnchoTexto = 263;
        AnchoTexto2 = 134;

        //Animation
        general = new General(GENERALX,GENERALY);

        //ButtonSound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //Load texture and fonts
        Debug = new Texture("Debug.png");
        Button = new Texture ("return_btn_(Howto_Story).png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        scoreFont2 = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        bg = new Texture ("Background_How_To_#1.png");
    }



    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
    wait += 1;
    general.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                
        spriteBatch.begin();

        spriteBatch.draw(bg,camera.position.x - (camera.viewportWidth/2),camera.position.y - (camera.viewportHeight/2));

        GlyphLayout titleLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyInstructions), Color.WHITE, 0, Align.left, false);
        GlyphLayout textLayout1 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeyHowto1_1), Color.WHITE, AnchoTexto, Align.left, true);
        GlyphLayout textLayout2 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeyHowto1_2), Color.WHITE, AnchoTexto2, Align.left, true);
        GlyphLayout textLayout3 = new GlyphLayout(scoreFont2, d.getValue(Dictionary.Keys.KeyHowto1_3), Color.WHITE, AnchoTexto2, Align.left, true);


        scoreFont.draw(spriteBatch, titleLayout, FlappyITM.WITDH - TITLEX,TITLEY);
        scoreFont2.draw(spriteBatch, textLayout1, FlappyITM.WITDH - TEXT1X,TEXT1Y);
        scoreFont2.draw(spriteBatch, textLayout2, FlappyITM.WITDH - TEXT2X,TEXT2Y);
        scoreFont2.draw(spriteBatch, textLayout3, FlappyITM.WITDH - TEXT2X,TEXT3Y);
        scoreFont.getData().setScale(0.8f, 0.8f);
        scoreFont2.getData().setScale(0.7f, 0.7f);


        float ButtonX = FlappyITM.WITDH-Button.getWidth()-BUTTONX;
        float ButtonY = Button.getHeight()+BUTTONY;

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
                    gsm.set(new HowtoState2(gsm));
                    return;
                }

            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(Button,FlappyITM.WITDH-Button.getWidth()-BUTTONX,BUTTONY);
        spriteBatch.draw(general.getTexture(), general.getPosition().x, general.getPosition().y);

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
        general.dispose();
    }
}
