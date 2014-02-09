package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class LevelFinishedScreen implements Screen {

	final FGJ2014 game;
	Palle palle;
	
	public final String LEVEL_FINISHED = "Level finished!";
	String CoinsCollected = "Coins collected: ";
		
	
	public LevelFinishedScreen(FGJ2014 game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0.2f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		Assets.menuFont.draw(game.batch, LEVEL_FINISHED, 400, (Gdx.graphics.getHeight() - 200));
		Assets.font.draw(game.batch, CoinsCollected/* + palle.coinAmount */, 460, (Gdx.graphics.getHeight() - 350));
		//Gdx.app.log("Kolikoita: ", String.valueOf(palle.coinAmount));
		Assets.font.draw(game.batch, "Touch here to menu screen", 250, (Gdx.graphics.getHeight() - 650));
		game.batch.end();
		
		if(Gdx.input.getX() > 250 && Gdx.input.getX() < 400 && Gdx.input.getY() > 630 && Gdx.input.getY() < 670) {
			game.menuScreen = new MenuScreen(game);
			game.setScreen(game.menuScreen);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
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
	
	
}
