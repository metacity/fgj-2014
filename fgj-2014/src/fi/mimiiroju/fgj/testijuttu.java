package fi.mimiiroju.fgj;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class testijuttu implements ApplicationListener {

	SpriteBatch spriteBatch;
	 Texture spriteTexture;
	 Sprite sprite;
	 float scrollTimer = 0.0f;
	        
	 @Override
	 public void create() {
	     spriteBatch = new SpriteBatch();
	     spriteTexture = new Texture(Gdx.files.internal("data/background2.png"));
	                 
	     spriteTexture.setWrap(TextureWrap.Repeat,TextureWrap.Repeat);
	     sprite = new Sprite(spriteTexture, 512, 256, 64, 64);
	     sprite.setSize(256, 256);
	 }
	 
	@Override
	 public void render() {
	     scrollTimer+=Gdx.graphics.getDeltaTime();
	     if(scrollTimer>1.0f)
	         scrollTimer = 0.0f;
	                
	     sprite.setU(scrollTimer);
	     sprite.setU2(scrollTimer+1);
	                
	     spriteBatch.begin();
	     sprite.draw(spriteBatch);
	     spriteBatch.end();
	 }

	@Override
	public void resize(int width, int height) {
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
