package com.francisca.game.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.francisca.game.sprites.elements.Coin;
import com.francisca.game.sprites.elements.Pig;

/**
 * Created by ZeCarlos on 03/06/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() instanceof Coin || fixB.getUserData() instanceof Coin)
        {
            Fixture coin = fixA.getUserData() instanceof Coin ? fixA : fixB;
            Fixture object = coin == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Pig)
            {
                System.out.println("Porco apanhou moeda");
                ((Pig) object.getUserData()).catchCoin();
            }

            ((Coin) coin.getUserData()).onCreatingCollision();
        }
        else if(fixA.getUserData() instanceof Pig || fixB.getUserData() instanceof Pig)
        {
            Fixture pig = fixA.getUserData() instanceof Pig ? fixA : fixB;
            Fixture object = pig == fixA ? fixB : fixA;

            if(object.getUserData() == "obstacle")
            {
                ((Pig) pig.getUserData()).hit = true;
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
