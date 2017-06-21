package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

public class Coin extends InteractiveTiledObject{
	public Coin(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		
		
		BodyDef bdef= new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape =new PolygonShape();
		
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX()+bounds.getWidth()/2)/MyGdxGame.PPM, (bounds.getY()+bounds.getHeight()/2)/MyGdxGame.PPM);
		
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth()/2/MyGdxGame.PPM, bounds.getHeight()/2/MyGdxGame.PPM);
		fdef.shape = shape;
		body.createFixture(fdef);
	}

}
