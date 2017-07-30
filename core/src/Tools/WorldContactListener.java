package Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.MyGdxGame;

import Sprites.Enemy;
//import Sprites.Enemy;
import Sprites.InteractiveTiledObject;
import Sprites.Postac;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA.getUserData()=="head" || fixB.getUserData()=="head"){
			Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
			Fixture object = head == fixA ? fixB : fixA;
			
			if(object.getUserData() != null && InteractiveTiledObject.class.isAssignableFrom(object.getUserData().getClass())){
				((InteractiveTiledObject) object.getUserData()).onHeadHit();
			}
		}
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		switch(cDef){
		case MyGdxGame.ENEMY_HEAD_BIT | MyGdxGame.BOMBA_BIT:
			
			 if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_HEAD_BIT)
                 ((Enemy)fixA.getUserData()).hitOnHead((Postac) fixB.getUserData());
             else
                 ((Enemy)fixB.getUserData()).hitOnHead((Postac) fixA.getUserData());
             break;
	            
		case MyGdxGame.ENEMY_BIT | MyGdxGame.BLOCK_BIT:
			
			 if(fixA.getFilterData().categoryBits == MyGdxGame.ENEMY_BIT){
                ((Enemy)fixA.getUserData()).reverseVelocity(true,false);
               
			 }
            else{
                ((Enemy)fixB.getUserData()).reverseVelocity(true,false);
                
            }
            break;
		case MyGdxGame.BOMBA_BIT | MyGdxGame.ENEMY_BIT:
			 
			
                 Postac.isDead();
             
             break;
             
		case MyGdxGame.BOMBA_BIT | MyGdxGame.COMPLETE_BIT:
			 
			Postac.finish();
           
        
        break;
		
		}
	}

	@Override
	public void endContact(Contact contact) {
	}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
