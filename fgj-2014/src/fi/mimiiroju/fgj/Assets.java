package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	private final static int PALLE_FRAME_COLS = 5;
	private final static int PALLE_FRAME_ROWS = 2;
	private final static int EXPLOSION_FRAME_COLS = 5;
	private final static int EXPLOSION_FRAME_ROWS = 5;

	static Texture background;
	static Texture mushroom;
	
	static Texture palleTexture;
	static TextureRegion[] palleFrames;
	
	static Texture explosionTexture;
	static TextureRegion[] explosionFrames;

	public static void load() {
		palleTexture = new Texture(Gdx.files.internal("data/palle2.png"));
		palleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmp = TextureRegion.split(
				palleTexture, 
				palleTexture.getWidth() / PALLE_FRAME_COLS, 
				palleTexture.getHeight() / PALLE_FRAME_ROWS
				);
		palleFrames = new TextureRegion[PALLE_FRAME_ROWS * PALLE_FRAME_COLS];
		int index = 0;
		for (int i = 0; i < PALLE_FRAME_ROWS; i++) {
			for (int j = 0; j < PALLE_FRAME_COLS; j++) {
				palleFrames[index++] = tmp[i][j];
			}
		}
		
		explosionTexture = new Texture(Gdx.files.internal("data/explosion.png"));
		explosionTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		tmp = TextureRegion.split(
				explosionTexture, 
				explosionTexture.getWidth() / EXPLOSION_FRAME_COLS, 
				explosionTexture.getHeight() / EXPLOSION_FRAME_ROWS
				);
		explosionFrames = new TextureRegion[EXPLOSION_FRAME_COLS * EXPLOSION_FRAME_ROWS];
		index = 0;
		for (int i = 0; i < EXPLOSION_FRAME_ROWS; i++) {
			for (int j = 0; j < EXPLOSION_FRAME_COLS; j++) {
				explosionFrames[index++] = tmp[i][j];
			}
		}
		
		mushroom = new Texture(Gdx.files.internal("data/mushroom.png"));
		background = new Texture(Gdx.files.internal("data/background2.png"));
	}

	public static void dispose() {
		background.dispose();
		mushroom.dispose();
		palleTexture.dispose();
		explosionTexture.dispose();
	}
}
