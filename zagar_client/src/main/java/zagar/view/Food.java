package zagar.view;

import org.jetbrains.annotations.NotNull;
import protocol.utils.Calculator;
import protocol.GameConstraints;
import zagar.Game;

import java.awt.*;

public class Food {

    private double x, y;
    private int id;
    private static float size = Calculator.calculateRadius(GameConstraints.FOOD_MASS);
    private static float mass = GameConstraints.FOOD_MASS;
    private int r, g, b;


    public Food(double x, double y, int id, int r, int g, int b) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void render(@NotNull Graphics2D g, float scale) {

            Color color = new Color(this.r, this.g, this.b);
            g.setColor(color);
            int size = (int) ((Food.size * 2f * scale) * Game.zoom);

            float avgX = 0;
            float avgY = 0;

            for (Cell c : Game.player) {
                if (c != null) {
                    avgX += c.xRender;
                    avgY += c.yRender;
                }
            }

            avgX /= Game.player.size();
            avgY /= Game.player.size();

            int x = (int) ((this.x - avgX) * Game.zoom) + GameFrame.size.width / 2 - size / 2;
            int y = (int) ((this.y - avgY) * Game.zoom) + GameFrame.size.height / 2 - size / 2;

            if (x < -size - 30 || x > GameFrame.size.width + 30 || y < -size - 30 || y > GameFrame.size.height + 30) {
                return;
            }
        int massRender = (int) this.size;
        Polygon hexagon = new Polygon();
        int a = massRender / 20 + 5;
        a = Math.min(a, 50);
        for (int i = 0; i < a; i++) {
            int pointX = (int) (x+ (size / 2) * Math.cos(i * 2 * Math.PI / a)) + size / 2;
            int pointY = (int) (y + (size / 2) * Math.sin(i * 2 * Math.PI / a)) + size / 2;
            hexagon.addPoint(pointX, pointY);
        }
        g.fillPolygon(hexagon);
    }

    @Override
    public String toString() {
        return "Food{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }

}