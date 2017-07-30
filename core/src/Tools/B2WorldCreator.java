package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;
import Sprites.Block;
import Sprites.Coin;

import Sprites.Ground;
import Sprites.LevelComplete;


public class B2WorldCreator {
		
	public B2WorldCreator(PlayScreen screen){
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
	
		  BodyDef bdef = new BodyDef();
	        PolygonShape shape = new PolygonShape();
	        FixtureDef fdef = new FixtureDef();
	        Body body;

		// ground
		
		for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MyGdxGame.PPM, (rect.getY() + rect.getHeight() / 2) / MyGdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MyGdxGame.PPM, rect.getHeight() / 2 / MyGdxGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MyGdxGame.OBJECT_BIT;
            body.createFixture(fdef);
					
		new Ground(screen,rect);
		}
		
		// gold
		
		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
		new Coin(screen,rect);
		}
		
		//block
		
		for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
		new Block(screen,rect);
		}
		//finish
		
				for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					
				new LevelComplete(screen,rect);
				}
		
	}
	
	}
	


