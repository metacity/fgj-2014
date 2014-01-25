package fi.mimiiroju.fgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

	private final static int FRAME_COLS = 6;
	private final static int FRAME_ROWS = 5;

	static Texture background;
	static Texture rock;
	static Texture palleTexture;
	static TextureRegion[] palleFrames;

	static BitmapFont font;

	public static void load() {
		Texture palle = new Texture(Gdx.files.internal("data/palle.gif"));
		palle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmp = TextureRegion.split(
				palle, 
				palle.getWidth() / FRAME_COLS, 
				palle.getHeight() / FRAME_ROWS
				);
		palleFrames = new TextureRegion[30];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				palleFrames[index++] = tmp[i][j];
			}
		}
		
		rock = new Texture(Gdx.files.internal("data/rock.png"));
		background = new Texture(Gdx.files.internal("data/background.png"));

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/Roboto-Regular.ttf"));
		font = generator.generateFont(20);
		generator.dispose();
	}

	public static void dispose() {
		background.dispose();
		rock.dispose();
		palleTexture.dispose();
		font.dispose();
	}
}
