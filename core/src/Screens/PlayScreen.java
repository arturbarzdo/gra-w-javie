package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

import Sprites.Kurvinox;
import Sprites.Postac;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
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
	private Kurvinox kurvinox;
	private Kurvinox kurvinox1;
	private Kurvinox kurvinox2;
	private Kurvinox kurvinox3;
	private Kurvinox kurvinox4;
	private Kurvinox kurvinox5;
	private Kurvinox kurvinox6;
	private Kurvinox kurvinox7;
	private Kurvinox kurvinox8;
	private Kurvinox kurvinox9;
	private Kurvinox kurvinox10;
	private Kurvinox kurvinox11;
	private Postac player;
	
	//box2d variables  
	  private World world;
	  private Box2DDebugRenderer b2dr;
	  
	  
	  //music and sounds
	  Music music = Gdx.audio.newMusic(Gdx.files.internal("kb.mp3"));
	  Sound sound = Gdx.audio.newSound(Gdx.files.internal("skok.mp3"));
	  Sound sound1 = Gdx.audio.newSound(Gdx.files.internal("strzal.mp3"));

	
	public PlayScreen(MyGdxGame game){
		atlas = new TextureAtlas("ludek/postacie1.pack");
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
		
		new B2WorldCreator(this);
		
		player = new Postac(this);
		world.setContactListener(new WorldContactListener());
		
		kurvinox = new Kurvinox(this,39f,10f);
		kurvinox1 = new Kurvinox(this,2f,7.9f);
		kurvinox2 = new Kurvinox(this,4.7f,10.5f);
		kurvinox3 = new Kurvinox(this,11.5f,0.9f);
		kurvinox4 = new Kurvinox(this,13.2f,0.9f);
		kurvinox5 = new Kurvinox(this,17f,0.9f);
		kurvinox6 = new Kurvinox(this,39f,0.9f);
		kurvinox7 = new Kurvinox(this,39f,4.2f);
		kurvinox8 = new Kurvinox(this,31f,6.5f);
		kurvinox9 = new Kurvinox(this,19f,8f);
		kurvinox10 = new Kurvinox(this,34.2f,0.9f);
		kurvinox11 = new Kurvinox(this,22f,0.9f);
		
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
		kurvinox.draw(game.batch);
		kurvinox1.draw(game.batch);
		kurvinox2.draw(game.batch);
		kurvinox3.draw(game.batch);
		kurvinox4.draw(game.batch);
		kurvinox5.draw(game.batch);
		kurvinox6.draw(game.batch);
		kurvinox7.draw(game.batch);
		kurvinox8.draw(game.batch);
		kurvinox9.draw(game.batch);
		kurvinox10.draw(game.batch);
		kurvinox11.draw(game.batch);
		player.draw(game.batch);
		
		game.batch.end();
		
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		music.play();
		if(gameOver()){
			Postac.bombaIsDead = false;
			Postac.Dead = false;
			game.setScreen(new GameOver(game));
			
			dispose();
		}
		if(Postac.finishlev){
			Postac.finishlev = false;
			
			game.setScreen(new GameOver(game));
			
			dispose();
		}
		
	}
	private void handleinput(float dt) {
	if(player.curentState!=Postac.State.DEAD){
		if ((Gdx.input.isKeyJustPressed(Input.Keys.UP))){
			player.b2body.applyLinearImpulse(new Vector2(0,5f),player.b2body.getWorldCenter(),true);
			//sound.play(2.0f);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
		
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
	} 
	}
	public void update(float dt){
		
		hud.update(dt);
		
		handleinput(dt);
		
		world.step(1/60f, 6, 2);
		kurvinox.update(dt);
		kurvinox1.update(dt);
		kurvinox2.update(dt);
		kurvinox3.update(dt);
		kurvinox4.update(dt);
		kurvinox5.update(dt);
		kurvinox6.update(dt);
		kurvinox7.update(dt);
		kurvinox8.update(dt);
		kurvinox9.update(dt);
		kurvinox10.update(dt);
		kurvinox11.update(dt);
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
	
	public TiledMap getMap(){
		return map;
	}
	
	public World getWorld(){
		return world;
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	public TextureAtlas getAtlas() {
		// TODO Auto-generated method stub
		return atlas;
	}
	public boolean gameOver(){
		if(player.curentState == Postac.State.DEAD && player.getStateTimer()>3){
			return true;
		}else
			return false;
	}
	
	
	@Override
	public void dispose() {
		
		renderer.dispose();
		game.dispose();
		map.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		music.dispose();
		
	}



}
