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

public class SecondMenuState extends State{

    private static final int BOTTOM_EXITBUTTONY = 67;

    private static final int RIGHT_EXITSPACE = 60;

    private static final int RIGHT_BUTTONX = 76;

    private static final int PROFILEY = 530;

    private static final int SCOREY = 404;

    private static final int HOWTOY = 278;

    private static final int STORYY = 152;

    private static final int TEXTX = 268;

    private static final int TPROFILEY = 591;

    private static final int TSCORESY = 464;

    private static final int THOWTOY = 338;

    private static final int TSTORYY = 212;

    int wait;

    private Texture bg;
    Texture gameOverBanner;
    Texture Debug;
    Texture ExitButton;
    Texture Profile;
    Texture Score;
    Texture Howto;
    Texture Story;
    BitmapFont scoreFont;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    public SecondMenuState(GameStateManager gsm){
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
        Profile = new Texture ("profile_btn.png");
        Score = new Texture ("scores_btn.png");
        Howto = new Texture ("How_To_btn.png");
        Story = new Texture ("story_btn.png");
        bg = new Texture ("Background_Options-01.png");
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

        GlyphLayout profileLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyProfile), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoresLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyScores), Color.WHITE, 0, Align.left, false);
        GlyphLayout howtoLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyHowto), Color.WHITE, 0, Align.left, false);
        GlyphLayout storyLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyStory), Color.WHITE, 0, Align.left, false);

        scoreFont.draw(spriteBatch, profileLayout, FlappyITM.WITDH - TEXTX,TPROFILEY);
        scoreFont.draw(spriteBatch, scoresLayout, FlappyITM.WITDH - TEXTX,TSCORESY);
        scoreFont.draw(spriteBatch, howtoLayout, FlappyITM.WITDH - TEXTX,THOWTOY);
        scoreFont.draw(spriteBatch, storyLayout, FlappyITM.WITDH - TEXTX,TSTORYY);

        float exitButtonX = FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE;
        float exitButtonY = ExitButton.getHeight()+BOTTOM_EXITBUTTONY;
        float profileButtonX = FlappyITM.WITDH / 2 - Profile.getWidth() / 2;
        float profileButtonY = Profile.getHeight()+ PROFILEY;
        float scoresButtonX = FlappyITM.WITDH / 2 - Score.getWidth() / 2;
        float scoresButtonY = Score.getHeight()+ SCOREY;
        float howtoButtonX = FlappyITM.WITDH / 2 - Howto.getWidth() / 2;
        float howtoButtonY = Howto.getHeight()+ HOWTOY;
        float storyButtonX = FlappyITM.WITDH / 2 - Story.getWidth() / 2;
        float storyButtonY = Story.getHeight()+ STORYY;

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
                if (touch.x > profileButtonX && touch.x < profileButtonX + Profile.getWidth() && touch.y > profileButtonY - Profile.getHeight() && touch.y < profileButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new ProfileState(gsm));
                    return;
                }
                if (touch.x > scoresButtonX && touch.x < scoresButtonX + Score.getWidth() && touch.y > scoresButtonY - Score.getHeight() && touch.y < scoresButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new ScoreState(gsm));
                    return;
                }
                if (touch.x > howtoButtonX && touch.x < howtoButtonX + Howto.getWidth() && touch.y > howtoButtonY - Howto.getHeight() && touch.y < howtoButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new HowtoState(gsm));
                    return;
                }
                if (touch.x > storyButtonX && touch.x < storyButtonX + Story.getWidth() && touch.y > storyButtonY - Story.getHeight() && touch.y < storyButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new StoryState(gsm));
                    return;
                }
            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(ExitButton,FlappyITM.WITDH-ExitButton.getWidth()-RIGHT_EXITSPACE,BOTTOM_EXITBUTTONY);
        spriteBatch.draw(Profile, FlappyITM.WITDH-Profile.getWidth()-RIGHT_BUTTONX,PROFILEY);
        spriteBatch.draw(Score, FlappyITM.WITDH-Profile.getWidth()-RIGHT_BUTTONX,SCOREY);
        spriteBatch.draw(Howto, FlappyITM.WITDH-Profile.getWidth()-RIGHT_BUTTONX,HOWTOY);
        spriteBatch.draw(Story, FlappyITM.WITDH-Profile.getWidth()-RIGHT_BUTTONX,STORYY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        ExitButton.dispose();
        Profile.dispose();
        Score.dispose();
        Howto.dispose();
        Story.dispose();
        scoreFont.dispose();
        bg.dispose();
        ButtonSound.dispose();
    }
}
