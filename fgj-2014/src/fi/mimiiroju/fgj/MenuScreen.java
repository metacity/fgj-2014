package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class MenuScreen implements Screen {

	public static final String TOUCH_TO_START = "TOUCH TO START!";
	
	final FGJ2014 game;

	public MenuScreen(FGJ2014 game) {
		this.game = game;
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		Assets.menuFont.draw(
				game.batch, 
				TOUCH_TO_START, 
				0,
				Gdx.graphics.getHeight() / 2
				);
		game.batch.end();

		if (Gdx.input.justTouched()) {
			game.gameScreen = new GameScreen(game);
			game.setScreen(game.gameScreen);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
