package fr.il_totore.rp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Player;
import fr.il_totore.rp.world.GameMap;
import fr.il_totore.rp.world.TiledGameMap;

import java.util.Collections;

public class Main extends ApplicationAdapter {

    public static final Affine2 SCALE_AFFINE = new Affine2();

    private Player player;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private GameMap gameMap;
    private float deltaX, deltaY;

    @Override
    public void create() {
        batch = new SpriteBatch();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        batch.setProjectionMatrix(camera.combined);

        TiledMap map = new TmxMapLoader().load("map.tmx");

        player = new Player(gameMap, new Vector3(camera.position)){
            @Override
            public void render(SpriteBatch batch) {
                super.render(batch);
                System.out.println(player.getPosition());
                camera.position.set(player.getPosition());
            }
        };
        player.setVelocity(new Vector3(1, 0, 1f));

        gameMap = new TiledGameMap(map, new OrthogonalTiledMapRenderer(map), Collections.singletonList(player));
        resize((int)w, (int)h);
    }

    @Override
    public void resize(int width, int height) {
        SCALE_AFFINE.setToScaling(width, height);
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMap.tick(Gdx.graphics.getDeltaTime());
        gameMap.render(camera, batch);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameMap.dispose();
    }
}
