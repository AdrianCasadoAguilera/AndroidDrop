
package com.adriancasado.game25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverScreen implements Screen {

    final Drop game;
    int finalScore;
    Stage stage;
    Skin skin;
    TextButton retryButton;
    Texture backgroundTexture;


    public GameOverScreen(final Drop game, int score) {
        this.game = game;
        this.finalScore = score;
        backgroundTexture = new Texture("gameover_background.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        retryButton = new TextButton("Torna a jugar", skin);

        retryButton.getLabel().setFontScale(2f);
        retryButton.setSize(3.5f, 1.2f);

        retryButton.setPosition(
            game.viewport.getScreenWidth() / 2f - retryButton.getWidth() / 2f,
            game.viewport.getScreenHeight() / 2f - retryButton.getHeight() / 2f
        );

        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        stage.addActor(retryButton);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.font.draw(game.batch, "Fi de la partida!", 2, 4);
        game.font.draw(game.batch, "Puntuació: " + finalScore, 2, 3.5f);
        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
    }
}
