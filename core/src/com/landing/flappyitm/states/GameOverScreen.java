package com.landing.flappyitm.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
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

public class GameOverScreen extends State{

    private static final int BANNER_WIDTH = 350;
    private static final int BANNER_HEIGHT = 100;

    private static final int LEFT_BUTTONX = 72;

    private static final int RIGHT_BUTTONSPACE = 56;

    private static final int BOTTOM_BUTTONY = 60;

    private static final int SCORE_SPACEX = 230;

    private static final int SCORE_SPACEY = 247;
    

    int score, highscore;

    int wait;

    Texture Debug;
    Texture bg;
    Texture TryAgain;
    Texture HomeMenu;
    BitmapFont scoreFont;

    Sound ButtonSound;

    Preferences prefs;
    String names[];
    int scores[];

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Vector3 touch = new Vector3();

    public GameOverScreen (GameStateManager gsm, int score){
        super(gsm);
        this.score = score;

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait= 0;

        //Buttonsound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //get highscore from save file
        prefs = Gdx.app.getPreferences("flappyitm");
        this.highscore = prefs.getInteger("highscore",0);

        //Leaderboard
        names = new String[10];
        scores = new int[10];

        for (int x=0; x<10; x++)
        {
            names[x] = prefs.getString("name"+x, "-");
            scores[x] = prefs.getInteger("score"+x, 0);
        }


        //cheack if score beats highscore
        if(score > highscore){
            prefs.putInteger("highscore",score);
            prefs.flush();
        }

        //Load texture and fonts
        Debug = new Texture("Debug.png");
        bg = new Texture ("Background_Game_Over.png");
        TryAgain = new Texture ("play_again_btn.png");
        HomeMenu = new Texture ("home_btn.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));

        addScore(FlappyITM.PLAYERNAME, score);
    }


    public String getName(int x){
        //get the name of the x-th position in the Highscore-List
        return names[x];
    }

    public long getScore(int x)
    {
        //get the score of the x-th position in the Highscore-List
        return scores[x];
    }

    public boolean inHighscore(int score)
    {
        //test, if the score is in the Highscore-List
        int position;
        for (position=0; position<10&&this.scores[position]>score;
             position++);

        if (position==10) return false;
        return true;
    }

    public boolean addScore(String name, int score) {
        //add the score with the name to the Highscore-List
        int position;
        for (position = 0; position < 10 && this.scores[position] > score;
             position++);

        if (position == 10) return false;

        for (int x = 9; x > position; x--) {
            names[x] = names[x - 1];
            this.scores[x] = this.scores[x - 1];
        }


        this.names[position] = name;
        this.scores[position] = score;


        for (int x=0; x<10; x++)
        {
            prefs.putString("name"+x, this.names[x]);
            prefs.putInteger("score"+x, this.scores[x]);
        }
        prefs.flush();
        return true;

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

        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyScore), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber = new GlyphLayout(scoreFont,""+score, Color.WHITE, 0, Align.left, false);
        //GlyphLayout highscoreLayout = new GlyphLayout(scoreFont, ">>> "+d.getValue(Dictionary.Keys.KeyHighscore)+" <<<", Color.WHITE, 0, Align.left, false);
        //GlyphLayout highscoreNumber = new GlyphLayout(scoreFont, ""+highscore, Color.WHITE, 0, Align.left, false);
        scoreFont.getData().setScale(0.5f, 0.5f);

        scoreFont.draw(spriteBatch, scoreLayout, FlappyITM.WITDH - SCORE_SPACEX,SCORE_SPACEY);
        scoreFont.draw(spriteBatch, scoreNumber, FlappyITM.WITDH - SCORE_SPACEX, SCORE_SPACEY - scoreLayout.height * 4);
        //scoreFont.draw(spriteBatch, highscoreLayout, FlappyITM.WITDH/2 - highscoreLayout.width / 2, FlappyITM.HEIGHT - BANNER_HEIGHT - scoreLayout.height - scoreNumber.height - 15 * 4);
        //scoreFont.draw(spriteBatch, highscoreNumber, FlappyITM.WITDH/2 - highscoreNumber.width / 2, FlappyITM.HEIGHT - BANNER_HEIGHT - scoreLayout.height - scoreNumber.height - 16 * 6);


        //GlyphLayout tryAgainLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyTryagain));
        //GlyphLayout mainMenuLayout = new GlyphLayout(scoreFont, d.getValue(Dictionary.Keys.KeyMainmenu));

        float tryAgainX = LEFT_BUTTONX;
        float tryAgainY = TryAgain.getHeight();
        float mainMenuX = FlappyITM.WITDH-HomeMenu.getWidth()-RIGHT_BUTTONSPACE;
        float mainMenuY = HomeMenu.getHeight()+BOTTOM_BUTTONY;

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if (wait > FlappyITM.WAIT_TIME){

        if (Gdx.input.isTouched()){
            //Try Again
            if (touch.x > tryAgainX && touch.x < tryAgainX + TryAgain.getWidth() && touch.y > tryAgainY - TryAgain.getHeight() && touch.y < tryAgainY ){
                ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                this.dispose();
                spriteBatch.end();
                //game.setScreen(new MenuState(game));
                gsm.set(new PlayState(gsm));
                return;
            }

            //Main menu
            if (touch.x > mainMenuX && touch.x < mainMenuX + HomeMenu.getWidth() && touch.y > mainMenuY - HomeMenu.getHeight() && touch.y < mainMenuY ){
                ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                this.dispose();
                spriteBatch.end();
                //game.setScreen(new MenuState(game));
                gsm.set(new MenuState(gsm));
                return;
            }

        }

        }//Wait_Time

        //Draw
        spriteBatch.draw(TryAgain,LEFT_BUTTONX,BOTTOM_BUTTONY);
        spriteBatch.draw(HomeMenu,FlappyITM.WITDH-HomeMenu.getWidth()-RIGHT_BUTTONSPACE,BOTTOM_BUTTONY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        bg.dispose();
        HomeMenu.dispose();
        TryAgain.dispose();
        scoreFont.dispose();
        ButtonSound.dispose();
    }
}
