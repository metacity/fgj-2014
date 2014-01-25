package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	private final static int FRAME_COLS = 5;
	private final static int FRAME_ROWS = 2;

	static Texture background;
	static Texture rock;
	static Texture palleTexture;
	static TextureRegion[] palleFrames;

	public static void load() {
		palleTexture = new Texture(Gdx.files.internal("data/palle2.png"));
		palleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmp = TextureRegion.split(
				palleTexture, 
				palleTexture.getWidth() / FRAME_COLS, 
				palleTexture.getHeight() / FRAME_ROWS
				);
		palleFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				palleFrames[index++] = tmp[i][j];
			}
		}
		
		rock = new Texture(Gdx.files.internal("data/stone.png"));
		background = new Texture(Gdx.files.internal("data/background.png"));
	}

	public static void dispose() {
		background.dispose();
		rock.dispose();
		palleTexture.dispose();
	}
}
