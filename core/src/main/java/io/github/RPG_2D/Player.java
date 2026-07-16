package io.github.RPG_2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;

public class Player {

    // Need TEXTURE and POSITION of player

    private Texture playerSheet;
    private TextureRegion playerImage;    // object that holds image data of player
    private Vector2 position;   // object that holds position data of player
    private final float speed = 100f;
    private float scale = 1;

    private Animation<TextureRegion> currentAnimation;

    private Animation<TextureRegion> idleAnimation;

    private Animation<TextureRegion> walkRightAnimation;
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> walkUpAnimation;
    private Animation<TextureRegion> walkDownAnimation;

    private Animation<TextureRegion> rightAttackAnimation;



    private TextureRegion[][] frame;
    private TextureRegion[][] rFrame;
    private TextureRegion[][] lFrame;
    private float stateTime = 0.2f;



    // Default Constructor
    public Player (TextureRegion playerImage, float x, float y, float scale) {
        this.playerImage = playerImage;
        this.position = new Vector2(x,y);
        this.scale = scale;

        // Character asset stuff
        playerSheet = new Texture("sprites/characters/player.png");


        frame = TextureRegion.split(playerSheet, 48,48);
        rFrame = TextureRegion.split(playerSheet, 48,48);
        lFrame = TextureRegion.split(playerSheet, 48,48);


        // Creates a texture region and loops through row: 0 column: i++ storing all the keyframes of character
        // into idleFrames[]
        TextureRegion[] idleFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            idleFrames[i] = frame[0][i];
        }
        // Uses the idleFrames array like a flipBook with a 0.2 seconds delay
        idleAnimation = new Animation<>(0.2f, idleFrames);




        // Right Walking Animation
        TextureRegion[] walkRightFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            walkRightFrames[i] = rFrame[1][i];

        }
        walkRightAnimation = new Animation<>(0.2f, walkRightFrames);


        // Left Walking Animation
        TextureRegion[] walkLeftFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            walkLeftFrames[i] = lFrame[1][i];
            walkLeftFrames[i].flip(true, false);    // Flips the "Walk Right" animation to walk left
        }
        walkLeftAnimation = new Animation<>(0.2f, walkLeftFrames);


        // Up Walking Animation
        TextureRegion[] walkUpFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            walkUpFrames[i] = frame[2][i];

        }
        walkUpAnimation = new Animation<>(0.2f, walkUpFrames);


        // Down Walking Animation
        TextureRegion[] walkDownFrames = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            walkDownFrames[i] = frame[3][i];
        }
        walkDownAnimation = new Animation<>(0.2f, walkDownFrames);


        // Attack Animations
        TextureRegion[] attackRightFrames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            attackRightFrames[i] = frame[7][i];
        }
        rightAttackAnimation = new Animation<>(0.2f, attackRightFrames);


        stateTime = 0f;     // resets the time between
    }


    public void update(float deltaTime) {
        stateTime += deltaTime;
        boolean moving = false;

        try {
            // MOVEMENT
            if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
                position.y += speed * deltaTime;
                currentAnimation = walkUpAnimation;
                moving = true;
            }
            else if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
                position.x -= speed * deltaTime;
                currentAnimation = walkLeftAnimation;
                moving = true;
            }
            else if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
                position.y -= speed * deltaTime;
                currentAnimation = walkDownAnimation;
                moving = true;
            }
            else if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
                position.x += speed * deltaTime;
                currentAnimation = walkRightAnimation;
                moving = true;
            }


            // ATTACK
            if (Gdx.input.isKeyPressed(Keys.SPACE)) {
                currentAnimation = rightAttackAnimation;
            }


            if (!moving) {
                currentAnimation = idleAnimation;
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Draws the player with texture and position data
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(currentAnimation.getKeyFrame(stateTime, true), position.x, position.y, 30 * scale, 30 * scale);
    }

    public void dispose() {
        playerSheet.dispose();
    }
}
