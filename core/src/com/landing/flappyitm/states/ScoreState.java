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

public class ScoreState extends State{

    private static final int BACKX = 56;

    private static final int BACKY = 60;

    private static final int SCORESX = 132;
    private static final int SCORESY = 589;

    private static final int NUMBERX = 119;

    private static final int NAMEX = 344;

    private static final int SCORE1Y = 515;
    private static final int SCORE2Y = 480;
    private static final int SCORE3Y = 445;
    private static final int SCORE4Y = 410;
    private static final int SCORE5Y = 370;
    private static final int SCORE6Y = 340;
    private static final int SCORE7Y = 305;
    private static final int SCORE8Y = 270;
    private static final int SCORE9Y = 235;
    private static final int SCORE10Y = 200;

    int wait;

    int highscore1, highscore2, highscore3, highscore4, highscore5, highscore6, highscore7, highscore8, highscore9, highscore10;

    private Texture bg;
    Texture gameOverBanner;
    Texture Debug;
    Texture Back;
    BitmapFont scoreFont;
    BitmapFont scoreFont2;

    Sound ButtonSound;

    String file ="language"+java.util.Locale.getDefault().toString()+".json";
    Json json = new Json();
    Dictionary d = json.fromJson(Dictionary.class, Gdx.files.internal(file));

    Preferences prefs;
    String names[];
    int scores[];

    Vector3 touch = new Vector3();

    public ScoreState(GameStateManager gsm){
        super(gsm);

        camera.setToOrtho(false, FlappyITM.WITDH,FlappyITM.HEIGHT);

        wait=0;

        //Button Sound
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("Button.mp3"));

        //get highscore from save file
        prefs = Gdx.app.getPreferences("flappyitm");
        this.highscore1 = prefs.getInteger("highscore",0);

        //Leaderboard
        names = new String[10];
        scores = new int[10];

        for (int x=0; x<10; x++)
        {
            names[x] = prefs.getString("name"+x, "-");
            scores[x] = prefs.getInteger("score"+x, 0);
        }

        //Load texture and fonts
        gameOverBanner = new Texture("game_over.png");
        Debug = new Texture("Debug.png");
        Back = new Texture ("back_btn.png");
        scoreFont = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        scoreFont2 = new BitmapFont(Gdx.files.internal("Futuristic_Armour.fnt"));
        bg = new Texture ("Background_Scores.png");
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
        GlyphLayout scoreNumber1 = new GlyphLayout(scoreFont2,""+getScore(0), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout1 = new GlyphLayout(scoreFont2,getName(0), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber2 = new GlyphLayout(scoreFont2,""+getScore(1), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout2 = new GlyphLayout(scoreFont2,getName(1), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber3 = new GlyphLayout(scoreFont2,""+getScore(2), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout3 = new GlyphLayout(scoreFont2,getName(2), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber4 = new GlyphLayout(scoreFont2,""+getScore(3), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout4 = new GlyphLayout(scoreFont2,getName(3), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber5 = new GlyphLayout(scoreFont2,""+getScore(4), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout5 = new GlyphLayout(scoreFont2,getName(4), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber6 = new GlyphLayout(scoreFont2,""+getScore(5), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout6 = new GlyphLayout(scoreFont2,getName(5), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber7 = new GlyphLayout(scoreFont2,""+getScore(6), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout7 = new GlyphLayout(scoreFont2,getName(6), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber8 = new GlyphLayout(scoreFont2,""+getScore(7), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout8 = new GlyphLayout(scoreFont2,getName(7), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber9 = new GlyphLayout(scoreFont2,""+getScore(8), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout9 = new GlyphLayout(scoreFont2,getName(8), Color.WHITE, 0, Align.left, false);
        GlyphLayout scoreNumber10 = new GlyphLayout(scoreFont2,""+getScore(9), Color.WHITE, 0, Align.right, false);
        GlyphLayout nameLayout10 = new GlyphLayout(scoreFont2,getName(9), Color.WHITE, 0, Align.left, false);


        scoreFont.draw(spriteBatch, scoreLayout, FlappyITM.WITDH - scoreLayout.width-SCORESX,SCORESY);
        scoreFont2.getData().setScale(0.5f, 0.5f);
        scoreFont2.draw(spriteBatch, scoreNumber1, FlappyITM.WITDH-NUMBERX, SCORE1Y);
        scoreFont2.draw(spriteBatch, nameLayout1, FlappyITM.WITDH-NAMEX, SCORE1Y);
        scoreFont2.draw(spriteBatch, scoreNumber2, FlappyITM.WITDH-NUMBERX, SCORE2Y);
        scoreFont2.draw(spriteBatch, nameLayout2, FlappyITM.WITDH-NAMEX, SCORE2Y);
        scoreFont2.draw(spriteBatch, scoreNumber3, FlappyITM.WITDH-NUMBERX, SCORE3Y);
        scoreFont2.draw(spriteBatch, nameLayout3, FlappyITM.WITDH-NAMEX, SCORE3Y);
        scoreFont2.draw(spriteBatch, scoreNumber4, FlappyITM.WITDH-NUMBERX, SCORE4Y);
        scoreFont2.draw(spriteBatch, nameLayout4, FlappyITM.WITDH-NAMEX, SCORE4Y);
        scoreFont2.draw(spriteBatch, scoreNumber5, FlappyITM.WITDH-NUMBERX, SCORE5Y);
        scoreFont2.draw(spriteBatch, nameLayout5, FlappyITM.WITDH-NAMEX, SCORE5Y);
        scoreFont2.draw(spriteBatch, scoreNumber6, FlappyITM.WITDH-NUMBERX, SCORE6Y);
        scoreFont2.draw(spriteBatch, nameLayout6, FlappyITM.WITDH-NAMEX, SCORE6Y);
        scoreFont2.draw(spriteBatch, scoreNumber7, FlappyITM.WITDH-NUMBERX, SCORE7Y);
        scoreFont2.draw(spriteBatch, nameLayout7, FlappyITM.WITDH-NAMEX, SCORE7Y);
        scoreFont2.draw(spriteBatch, scoreNumber8, FlappyITM.WITDH-NUMBERX, SCORE8Y);
        scoreFont2.draw(spriteBatch, nameLayout8, FlappyITM.WITDH-NAMEX, SCORE8Y);
        scoreFont2.draw(spriteBatch, scoreNumber9, FlappyITM.WITDH-NUMBERX, SCORE9Y);
        scoreFont2.draw(spriteBatch, nameLayout9, FlappyITM.WITDH-NAMEX, SCORE9Y);
        scoreFont2.draw(spriteBatch, scoreNumber10, FlappyITM.WITDH-NUMBERX, SCORE10Y);
        scoreFont2.draw(spriteBatch, nameLayout10, FlappyITM.WITDH-NAMEX, SCORE10Y);


        float backButtonX = FlappyITM.WITDH-Back.getWidth()-BACKX;
        float backButtonY = Back.getHeight()+BACKY;

        float touchX = Gdx.input.getX() , touchY = Gdx.input.getY();

        camera.unproject(touch.set(touchX,touchY,0));

        //spriteBatch.draw(Debug,touch.x,touch.y);

        //if try again and menu is being pressed

        if (wait > FlappyITM.WAIT_TIME){

            if (Gdx.input.isTouched()) {
                //Try Again
                if (touch.x > backButtonX && touch.x < backButtonX + Back.getWidth() && touch.y > backButtonY - Back.getHeight() && touch.y < backButtonY) {
                    ButtonSound.play(FlappyITM.VOLUMEMUSIC);
                    this.dispose();
                    spriteBatch.end();
                    //game.setScreen(new MenuState(game));
                    gsm.set(new SecondMenuState(gsm));
                    return;
                }

            }

        }//Wait_Time

        //Draw Buttons
        spriteBatch.draw(Back,FlappyITM.WITDH-Back.getWidth()-BACKX,BACKY);

        spriteBatch.end();

    }

    @Override
    public void dispose() {
        Debug.dispose();
        Back.dispose();
        scoreFont.dispose();
        scoreFont2.dispose();
        bg.dispose();
        ButtonSound.dispose();
    }
}
