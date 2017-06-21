package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import Sprites.Postac;
import Tools.B2WorldCreator;
import scenes.Hud;

public class PlayScreen implements Screen {
	
	private MyGdxGame game;
	private OrthographicCamera gameCam;
	private Viewport gameport;
	Texture texture;
	private Hud hud;
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private TextureAtlas atlas;
	
	
	
	//sprites
	
	public Postac player;
	
	//box2d variables  
	  private World world;
	  private Box2DDebugRenderer b2dr;
	
	public PlayScreen(MyGdxGame game){
		atlas = new TextureAtlas("ludek/player.pack");
		this.game= game;
		//texture = new Texture("p.png");
		gameCam = new OrthographicCamera();
		gameport = new FitViewport(MyGdxGame.V_WIDTH/MyGdxGame.PPM,MyGdxGame.V_HEIGHT/MyGdxGame.PPM,gameCam);
		hud = new Hud(game.batch);
		
		maploader = new TmxMapLoader();
		map = maploader.load("l1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map,1/MyGdxGame.PPM);
		gameCam.position.set(gameport.getWorldWidth()/2/MyGdxGame.PPM,gameport.getWorldHeight()/2/MyGdxGame.PPM,0);
	
		world = new World(new Vector2(0,-10),true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		player = new Postac(world,this);
		
		
	}
	
	
	
	@Override
	public void show() {
		
	    
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//map render
		renderer.render();
		
		//b2box renederer
		b2dr.render(world, gameCam.combined);
		game.batch.setProjectionMatrix(gameCam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();
		
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		
	}
	private void handleinput(float dt) {
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			player.b2body.applyLinearImpulse(new Vector2(0,5f),player.b2body.getWorldCenter(),true);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
		
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
	} 
	
	public void update(float dt){
		handleinput(dt);
		
		world.step(1/60f, 6, 2);
		player.update(dt);
		gameCam.position.x = player.b2body.getPosition().x;
		gameCam.position.y = player.b2body.getPosition().y;
		gameCam.update();
		renderer.setView(gameCam);
	}
	

	@Override
	public void resize(int width, int height) {
		gameport.update(width,height);
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		texture.dispose();
		renderer.dispose();
		game.dispose();
		map.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	
	}

	public TextureAtlas getAtlas() {
		// TODO Auto-generated method stub
		return atlas;
	}

}
