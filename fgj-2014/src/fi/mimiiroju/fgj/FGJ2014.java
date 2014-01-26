package fi.mimiiroju.fgj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FGJ2014 extends Game {
	
	SpriteBatch batch;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.graphics.setVSync(true);
		
		Assets.load();
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
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
		getScreen().dispose();
	}
	
}
