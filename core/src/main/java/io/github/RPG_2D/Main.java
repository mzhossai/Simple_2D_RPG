//package io.github.RPG_2D;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
///** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
//
//public class Main extends ApplicationAdapter {
//    private SpriteBatch batch;
//    private Texture image, playerImage;
//    private Player player;
//
//
//    private TiledMap map;
//    private OrthogonalTiledMapRenderer renderer;
//    private OrthographicCamera camera;
//    private Viewport viewport;
//
//
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        image = new Texture("libgdx.png");
//
//        map = new TmxMapLoader().load("homeInterior.tmx");
//        renderer = new OrthogonalTiledMapRenderer(map);
//
//
//        int mapWidth = map.getProperties().get("width", Integer.class);
//        int mapHeight = map.getProperties().get("height", Integer.class);
//        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
//        int tileHeight = map.getProperties().get("tileheight", Integer.class);
//
//        float worldWidth = mapWidth * tileWidth;
//        float worldHeight = mapHeight * tileHeight;
//
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(worldWidth, worldHeight, camera);
//
//        camera.position.set(worldWidth/2, worldHeight/2, 0);
//        camera.update();
//
//
//        playerImage = new Texture("bigrock.png");
//        player = new Player(playerImage,worldWidth/2 , worldHeight/2);
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void render() {
//        float deltaTime = Gdx.graphics.getDeltaTime();
//        player.update(deltaTime);
//
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//
//
//        camera.update();
//        renderer.setView(camera);
//        renderer.render();
//
//        player.draw(batch);
//        batch.end();
//    }
//
//
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        image.dispose();
//        playerImage.dispose();
//        map.dispose();
//        renderer.dispose();
//    }
//}














package io.github.RPG_2D;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image, playerSheet;
    private TextureRegion playerImage;
    private Player player;


    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;



    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        map = new TmxMapLoader().load("RPSTestWorld.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);


        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);

        float worldWidth = mapWidth * tileWidth;
        float worldHeight = mapHeight * tileHeight;

        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);

        camera.position.set(worldWidth/2, worldHeight/2, 0);
        camera.update();


        // Make a method that initializes the player image in the Player class to make it cleaner
        // and to all add different animations for each movement
        playerSheet = new Texture("sprites/characters/player.png");
        playerImage = new TextureRegion(playerSheet, 9,9, 13, 15);
        player = new Player(playerImage, worldWidth/2, worldHeight/2 ,1.9f);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        player.update(deltaTime);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();


        camera.update();
        renderer.setView(camera);
        renderer.render();

        player.draw(batch);
        batch.end();
    }



    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        playerSheet.dispose();
        map.dispose();
        renderer.dispose();
    }
}
