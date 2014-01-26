package fi.mimiiroju.fgj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {
	
	final FGJ2014 game;
    private SpriteBatch spriteBatch;
    private Texture startGame;

    @Override
    public void render(float delta)
    {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            spriteBatch.begin();
            spriteBatch.draw(startGame, 0, 0);
            spriteBatch.end();
            
            if(Gdx.input.justTouched())
                    game.setScreen(game.gameScreen);
    }
    
    @Override
    public void show()
    {
            spriteBatch = new SpriteBatch();
            startGame = new Texture(Gdx.files.internal("data/startgame.png"));
    }
	
	public MenuScreen(FGJ2014 game) {
		this.game = game;

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

}
