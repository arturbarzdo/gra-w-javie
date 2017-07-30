package Sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import Sprites.Postac;
import com.mygdx.game.MyGdxGame;
import Screens.PlayScreen;

public class Kurvinox extends Enemy {

	private float stateTime;
	private Animation<TextureRegion> walkAnimation;
	private Array<TextureRegion> frames;
	public boolean setToDestroy;
	public boolean destroyed;
	public boolean turn;
	public enum State{WALKING,DEAD};
	public State curentState;
	public State previusState;
	
	
public Kurvinox(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		
		 frames = new Array<TextureRegion>();
	        for(int i = 0; i < 2; i++)
	        frames.add(new TextureRegion(screen.getAtlas().findRegion("kurvinox1"), i*90,0,90,79));
	        walkAnimation = new Animation<TextureRegion>(0.4f, frames);
	        stateTime = 0;
	        setToDestroy = false;
			destroyed = false;
			turn = false;
	        setBounds(getX(),getY(),33/MyGdxGame.PPM,33/MyGdxGame.PPM);
	        curentState = previusState = State.WALKING;
	        
}

	@Override
public void defineEnemy() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(getX(),getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(8/MyGdxGame.PPM);
		
		fdef.filter.categoryBits = MyGdxGame.ENEMY_BIT;
		fdef.filter.maskBits = MyGdxGame.GROUND_BIT 
				| MyGdxGame.BLOCK_BIT 
				| MyGdxGame.COIN_BIT
				| MyGdxGame.OBJECT_BIT
				| MyGdxGame.BOMBA_BIT
				| MyGdxGame.ENEMY_BIT
				| MyGdxGame.ENEMY_HEAD_BIT;
		
		 fdef.shape = shape;
		 b2body.createFixture(fdef).setUserData(this);
		 
	     PolygonShape head= new PolygonShape();
	     Vector2[] vertice = new Vector2[4];
	     vertice[0] = new Vector2(-8,12).scl(1/MyGdxGame.PPM);
	     vertice[1] = new Vector2(8,12).scl(1/MyGdxGame.PPM);
	     vertice[2] = new Vector2(-5,5).scl(1/MyGdxGame.PPM);
	     vertice[3] = new Vector2(5,5).scl(1/MyGdxGame.PPM);
	     
	     head.set(vertice);
	     
	     fdef.shape = head;
	     fdef.restitution = 0.5f;
	     fdef.filter.categoryBits = MyGdxGame.ENEMY_HEAD_BIT;
	    
	    b2body.createFixture(fdef).setUserData(this);
	
}
	
	



public void update(float dt){
		stateTime+=dt;
		setRegion(getFrame(dt));
	if(setToDestroy && !destroyed){
			world.destroyBody(b2body);
			 
	    	  setBounds(getX(),getY()-0.33f,33/MyGdxGame.PPM,33/MyGdxGame.PPM);
			  stateTime = 0;
			destroyed = true;
			curentState = State.DEAD;
	}
		
	else if(!destroyed){
		
		b2body.setLinearVelocity(velocity);
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 4);
		setRegion(walkAnimation.getKeyFrame(stateTime, true));
		
	}
}
public TextureRegion getFrame(float dt){
	TextureRegion region ;
	  switch (curentState){
	  default:
          region = walkAnimation.getKeyFrame(stateTime, true);
          break;
      
      case DEAD: 
    	 region = new TextureRegion(((PlayScreen) screen).getAtlas().findRegion("plama"), 0,0,79,16);
		break;	
      
  }
	
			
	  if(velocity.x < 0 && region.isFlipX() == false){
          region.flip(true, false);
      }
      if(velocity.x > 0 && region.isFlipX() == true){
          region.flip(true, false);
      }
			
		    stateTime = curentState == previusState ? stateTime + dt : 0;
		    //update previous state
		    previusState = curentState;
		    //return our final adjusted frame
		    return region;
			
		}
		
	public void draw(Batch batch){
		if(!destroyed || stateTime<3){
			super.draw(batch);
		}
	
	}



	@Override
	public void hitOnHead(Postac postac) {
		setToDestroy = true;
		
	}


	
	
}
