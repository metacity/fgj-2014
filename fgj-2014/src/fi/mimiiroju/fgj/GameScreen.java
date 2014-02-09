package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {
	final FGJ2014 game;
	final World world;
	final WorldRenderer renderer;

	final FPSLogger fpslogger = new FPSLogger();

	public GameScreen(FGJ2014 game) {
		this.game = game;

		world = new World();
		renderer = new WorldRenderer(game.batch, world);
	}

	public void setLevel(Stage level) {

	}

	private void update(float delta) {
		world.update(delta);
		if (world.collided) {
			game.setScreen(game.menuScreen);
			dispose();
		}
		if(world.finished) {
			Gdx.app.log("finished ?", "TO TEH FINAL SCREENEH");
			game.setScreen(game.levelFinishedScreen);
			dispose();
			world.finished = false;
			world.ended = false;
			Gdx.app.log("ended ? ", String.valueOf(world.ended));
			Gdx.app.log("finished ? ", String.valueOf(world.finished));
		}
		
	}

	private void draw() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw();
		fpslogger.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
