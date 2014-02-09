package fi.mimiiroju.fgj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FGJ2014 extends Game {
	
	SpriteBatch batch;
	MenuScreen menuScreen;
	GameScreen gameScreen;
	LevelFinishedScreen levelFinishedScreen;
	World world;
	
	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.graphics.setVSync(true);
		
		Assets.load();
		batch = new SpriteBatch();
		
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		levelFinishedScreen = new LevelFinishedScreen(this);
		
		setScreen(menuScreen);
		
		Assets.music.play();
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		Assets.dispose();
		menuScreen.dispose();
		if (gameScreen != null) {
			gameScreen.dispose();
		}
		if (levelFinishedScreen != null) {
			levelFinishedScreen.dispose();
		}
	}
	
}
