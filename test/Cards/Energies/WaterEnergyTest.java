package Cards.Energies;

import Controller.Game;
import Players.Trainer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class WaterEnergyTest {

    private WaterEnergy energy;

    @Before
    public void setUp() {
        energy = new WaterEnergy();
    }

    @Test
    public void notifyType() {
        NullGame game = new NullGame();
        energy.notifyType(game);
        assertTrue(game.playEnergyCalled());
    }

    private class NullGame extends Game {
        private boolean play_energy_called;

        NullGame() {
            super(new Trainer(new ArrayList<>()), new Trainer(new ArrayList<>()));
            this.play_energy_called = false;
        }

        @Override
        public void playWaterEnergy(IEnergy energy) {
            this.play_energy_called = true;
        }

        boolean playEnergyCalled() {
            return this.play_energy_called;
        }
    }
}