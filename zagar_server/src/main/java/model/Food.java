package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.List;

import static model.GameConstants.FOOD_MASS;

public class Food extends GameUnit {

    @NotNull
    private static final Logger LOG = LogManager.getLogger(Food.class);

    public Food(@NotNull Color color, @NotNull Location location) {
        super(color, location, FOOD_MASS);
        LOG.info(toString() + " created");
    }

    public Food(@NotNull Location location) {
        super(location, FOOD_MASS);
        LOG.info(toString() + " created");
    }

    @Override
    public boolean equals(@NotNull Object object) {
        if (object.getClass() != Food.class) return false;
        Food food = (Food) object;
        return (this.getLocation().equals(food.getLocation()));
    }

    @Override
    public int hashCode() {
        return this.getLocation().getX() * 3 + this.getLocation().getY() * 5;
    }

    @Override
    public String toString() {
        return "Food{" +
                "color=" + this.getColor() +
                ", location=" + this.getLocation() +
                ", mass=" + this.getMass() +
                ", radius=" + this.getRadius() +
                '}';
    }

    public static protocol.model.Food[] generateProtocolFoodFromModel(ConcurrentHashSet<Food> foodIn) {
       protocol.model.Food[] foodOut = new protocol.model.Food[foodIn.size()];
        int counter = 0;
        for (model.Food foodGot : foodIn) {
            foodOut[counter] = new protocol.model.Food(foodGot.getLocation().getX(),
                    foodGot.getLocation().getY(),
                    foodGot.getId(),
                    foodGot.getColor().getRed(),
                    foodGot.getColor().getGreen(),
                    foodGot.getColor().getBlue());
            counter++;
        }
        return foodOut;
    }

}
