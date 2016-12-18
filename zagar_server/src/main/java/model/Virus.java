package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.List;

import static model.GameConstants.INITIAL_VIRUS_MASS;

public class Virus extends GameUnit {

    @NotNull
    private static final Logger log = LogManager.getLogger(Virus.class);

    public Virus(@NotNull Location location, float speed) {
        super(location, speed * 2, INITIAL_VIRUS_MASS);
        if (log.isInfoEnabled()) {
            log.info(toString() + " created");
        }
    }

    public Virus(@NotNull Location location) {
        super(location, INITIAL_VIRUS_MASS);
        if (log.isInfoEnabled()) {
            log.info(toString() + " created");
        }
    }

    @Override
    public String toString() {
        return "Virus{" +
                "color=" + this.getColor() +
                ", location=" + this.getLocation() +
                ", mass=" + this.getMass() +
                ", speed=" + this.getSpeed() +
                ", radius=" + this.getRadius() +
                '}';
    }

    public static protocol.model.Virus[] generateProtocolVirusesFromModel(List<Virus> virusesIn) {
       protocol.model.Virus[] virusesOut = new protocol.model.Virus[virusesIn.size()];
        int k = 0;
        for (model.Virus virusGot : virusesIn) {
            virusesOut[k] = new protocol.model.Virus(virusGot.getId(),
                    virusGot.getMass(),
                    virusGot.getLocation().getX(),
                    virusGot.getLocation().getY());
            k++;
        }
        return virusesOut;
    }

}
