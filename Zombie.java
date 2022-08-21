package prove02;

import java.awt.Color;

public class Zombie extends Creature implements Movable, Aggressor {

    public Zombie() {
        _health = 1;
    }

    public void attack(final Creature target) {
        // zombies do 10 damage
        if (target != null && !(target instanceof Plant)) {
            target.takeDamage(10);
        }
    }

    public void move() {
        // zombies move LTR
        _location.x++;
    }

    Shape getShape() {
        // zombie is square
        return Shape.Square;
    }

    Color getColor() {
        // zombie is blue
        return new Color(0, 0, 255);
    }

    Boolean isAlive() {
        // zombies can't die
        return true;
    }
}
