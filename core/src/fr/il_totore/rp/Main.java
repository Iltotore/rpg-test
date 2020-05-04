package fr.il_totore.rp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.il_totore.rp.entity.Player;
import fr.il_totore.rp.key.*;
import fr.il_totore.rp.util.CompositeVelocity;
import fr.il_totore.rp.world.GameMap;
import fr.il_totore.rp.world.TiledGameMap;

import java.util.ArrayList;

public class Main extends ApplicationAdapter implements InputProcessor {

    public static final Affine2 SCALE_AFFINE = new Affine2();

    private Player player;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private GameMap gameMap;
    private float deltaX, deltaY;
    private InputController controller;
    private boolean pos = false;
    private Stage stage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        controller = new InputController();
        controller.put(new HoldInputProcessor(Keys.RIGHT), input -> player.walk(CompositeVelocity.RIGHT, new Vector3(1, 0, 0)));
        controller.put(new HoldInputProcessor(Keys.LEFT), input -> player.walk(CompositeVelocity.LEFT, new Vector3(-1, 0, 0)));
        controller.put(new HoldInputProcessor(Keys.UP), input -> player.walk(CompositeVelocity.UP, new Vector3(0, 1, 0)));
        controller.put(new HoldInputProcessor(Keys.DOWN), input -> player.walk(CompositeVelocity.DOWN, new Vector3(0, -1, 0)));
        controller.put(new ToggleInputProcessor(new Input(KeyInputType.DOWN, Keys.P)), input -> pos = !pos);
        Gdx.input.setInputProcessor(this);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        batch.setProjectionMatrix(camera.combined);

        TiledMap map = new TmxMapLoader().load("map.tmx");
        gameMap = new TiledGameMap(map, new OrthogonalTiledMapRenderer(map), new ArrayList<>());

        player = new Player(gameMap, new Vector3(14, 14, 1)) {
            @Override
            public void render(SpriteBatch batch) {
                if(pos) System.out.println(player.getPosition());
                camera.position.set(player.getPosition().cpy().add(0.5f, 0.5f, 0).scl(32, 32, 1));
                super.render(batch);
            }
        };
        gameMap.addEntity(player);
        gameMap.load();

        stage = new Stage();

        resize((int) w, (int) h);
    }

    @Override
    public void resize(int width, int height) {
        SCALE_AFFINE.setToScaling(width, height);
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.update();

        gameMap.tick(Gdx.graphics.getDeltaTime());
        gameMap.render(camera, batch);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gameMap.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        controller.control(new Input(KeyInputType.DOWN, i));
        stage.keyDown(i);
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        controller.control(new Input(KeyInputType.UP, i));
        stage.keyUp(i);
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return stage.keyTyped(c);
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return stage.touchDown(i, i1, i2, i3);
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return stage.touchUp(i, i1, i2, i3);
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return stage.touchDragged(i, i1, i2);
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return stage.mouseMoved(i, i1);
    }

    @Override
    public boolean scrolled(int i) {
        return stage.scrolled(i);
    }
}
