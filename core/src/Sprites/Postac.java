package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import Screens.PlayScreen;

public class Postac extends Sprite{
	public enum State{STANDING,RUNNING,DEAD};
	public State curentState;
	public State previusState;
	public World world;
	public static Body b2body;
	private TextureRegion ludekStand;
	private TextureRegion bombaDead;
	
	private Animation <TextureRegion>postacRun;
	private float stateTimer;
	private boolean runningRight;
	private static boolean bombaIsDead;
	
	
	public Postac(PlayScreen screen){
		super(screen.getAtlas().findRegion("b"));
		this.world = screen.getWorld();
		
		curentState = State.STANDING;
		previusState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();

        //get run animation frames and add them to marioRun Animation
        for(int i = 0; i < 3; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("a"),i*38,0,38,64));
        postacRun = new Animation<TextureRegion>(0.15f, frames);

        frames.clear();
		
		ludekStand = new TextureRegion(getTexture(),180, 38,38,64);
		bombaDead = new TextureRegion(getTexture(),180, 0,64,38);
		setBounds(0,0,16/MyGdxGame.PPM,33/MyGdxGame.PPM);
		setRegion(ludekStand);
		definePostac();
	}
	public void update(float dt){
		setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/4);
		setRegion(getFrame(dt));
	}
	public State getState(){
	if(bombaIsDead)
		return State.DEAD;
		
	else if(b2body.getLinearVelocity().x != 0 )
			return State.RUNNING;
			
		else
			return State.STANDING;
		
	}

	public TextureRegion getFrame(float dt){
		curentState = getState();
		TextureRegion region;
		switch(curentState){
		case DEAD:
			region = bombaDead;
		case RUNNING:
			region = (TextureRegion) postacRun.getKeyFrame(stateTimer,true);
			break;
			
		case STANDING:
			default:
				region = ludekStand;
		}
		 //if mario is running left and the texture isnt facing left... flip it.
	    if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
	        region.flip(true, false);
	        runningRight = false;
	    }

	    //if mario is running right and the texture isnt facing right... flip it.
	    else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
	        region.flip(true, false);
	        runningRight = true;
	    }

	    //if the current state is the same as the previous state increase the state timer.
	    //otherwise the state has changed and we need to reset timer.
	    stateTimer = curentState == previusState ? stateTimer + dt : 0;
	    //update previous state
	    previusState = curentState;
	    //return our final adjusted frame
	    return region;
	}


		
	
	public void definePostac() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(200/MyGdxGame.PPM,300/MyGdxGame.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6/MyGdxGame.PPM);
		
		fdef.filter.categoryBits = MyGdxGame.BOMBA_BIT;
		fdef.filter.maskBits = MyGdxGame.GROUND_BIT 
				| MyGdxGame.BRICK_BIT 
				| MyGdxGame.COIN_BIT 
				| MyGdxGame.ENEMY_BIT
				| MyGdxGame.OBJECT_BIT
				| MyGdxGame.ENEMY_HEAD_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		
		
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2 / MyGdxGame.PPM, 20/MyGdxGame.PPM), new Vector2(2 / MyGdxGame.PPM, 20/MyGdxGame.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head");
	}
	public static void isDead(){
		bombaIsDead = true;
	}

}
