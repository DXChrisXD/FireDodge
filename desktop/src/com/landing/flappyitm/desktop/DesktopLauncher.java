package com.landing.flappyitm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.landing.flappyitm.FlappyITM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyITM.WITDH;
		config.height = FlappyITM.HEIGHT;
		config.title = FlappyITM.TITLE;
		new LwjglApplication(new FlappyITM(), config);
	}
}
