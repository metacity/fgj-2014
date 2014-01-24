package view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import actors.Palle;
import actors.World;

public class WorldRenderer {
	
	private World mWorld;
	private OrthographicCamera mCamera;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world) {		
		        this.mWorld = world;		
		        this.mCamera = new OrthographicCamera(mWorld.WORLD_WIDTH, mWorld.WORLD_HEIGHT);		
		        this.mCamera.position.set((mWorld.WORLD_WIDTH/2), (mWorld.WORLD_HEIGHT/2),0);		
		        this.mCamera.update();
		
		    }
		
		 
		
		    public void render() {		
		        // render blocks		
		        debugRenderer.setProjectionMatrix(mCamera.combined);	
		        debugRenderer.begin(ShapeType.Line);		
		        /*for (Block block : mWorld.getBlocks()) {		
		            Rectangle rect = block.getBounds();		
		            float x1 = block.getPosition().x + rect.x;
		            float y1 = block.getPosition().y + rect.y;	
		            debugRenderer.setColor(new Color(1, 0, 0, 1));
		            debugRenderer.rect(x1, y1, rect.width, rect.height);
		
		        }*/
		
		        // render Bob
		        Palle palle = mWorld.palle;
		        Rectangle rect = palle.getBounds();	
		        float x1 = palle.getPosition().x + rect.x;		
		        float y1 = palle.getPosition().y + rect.y;		
		        debugRenderer.setColor(new Color(0, 1, 0, 1));		
		        debugRenderer.rect(x1, y1, rect.width, rect.height);		
		        debugRenderer.end();
		    }
		
}

