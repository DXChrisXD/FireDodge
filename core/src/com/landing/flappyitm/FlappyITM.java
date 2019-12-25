package com.landing.flappyitm;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.landing.flappyitm.sprites.ScrollingBackground;
import com.landing.flappyitm.states.GameStateManager;
import com.landing.flappyitm.states.MenuState;
import com.landing.flappyitm.sprites.Bird;

public class FlappyITM extends ApplicationAdapter {

	public static final int WITDH =480;
	public static final int HEIGHT = 720;
	public static final int WAIT_TIME = 10;
	public static float VOLUMEMUSIC;
	public static float VOLUMESHOT;
	public static float VOLUMEEXPLOTION;
	public static String PLAYERNAME;
	public static float DIFFICULT = 1;
	public static final String TITLE = "FlappyITM para Android";
	private Music music;

	Preferences prefs;


	private GameStateManager gsm;

	public SpriteBatch batch;
	//public ScrollingBackground scrollingBackground;


	@Override
	public void create () {

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 1, 0, 1);
		//this.scrollingBackground = new ScrollingBackground();
		gsm.push(new MenuState(gsm));
		prefs = Gdx.app.getPreferences("flappyitm");
		this.VOLUMEMUSIC = prefs.getFloat("volumemusic",0.1f);
		this.VOLUMESHOT = prefs.getFloat("volumeshot",0.5f);
		this.VOLUMEEXPLOTION = prefs.getFloat("volumeexplotion",0.5f);
		this.PLAYERNAME = prefs.getString("playername", "Player");
		setUpMusic();
		System.out.println("Volume Music" + this.VOLUMEMUSIC);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //HZ
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}

	private void setUpMusic(){
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(VOLUMEMUSIC);
		music.play();
	}

    //public void resize(int width, int height){
   //     this.scrollingBackground.resize(width,height);
   //     super.resize(width,height);
   // }
}
