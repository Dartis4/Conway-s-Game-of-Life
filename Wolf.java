package prove02;

import java.awt.Color;
import java.util.Random;
import java.awt.Point;

public class Wolf extends Creature implements Movable, Aware, Aggressor, Spawner {

    Random _rand;
    char instinct;
    boolean hungry;

    public Wolf() {
        _rand = new Random();
        switch (_rand.nextInt(4)) {
            case 0:
                instinct = 'e';
                break;
            case 1:
                instinct = 'w';
                break;
            case 2:
                instinct = 's';
                break;
            default:
                instinct = 'n';
                break;
        }
        _health = 1;
        hungry = true;
    }

    Color getColor() {
        // wolf is gray
        return new Color(105, 105, 105);
    }

    Shape getShape() {
        // wolf is square
        return Shape.Square;
    }

    Boolean isAlive() {
        // wolf no die
        return _health > 0;
    }

    public void move() {
        // wolves move on instinct (preferred direction)
        switch (instinct) {
            case 'n':
                _location.y--;
                break;
            case 'e':
                _location.x++;
                break;
            case 's':
                _location.y++;
                break;
            case 'w':
                _location.x--;
                break;
            default:
                _location.y--;
                break;
        }
    }

    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        // wolf move on instinct (change movement direction (instinct) based on
        // surroundings)
        if (above == null && below == null && left == null && right == null) {
            return;
        }
        switch (instinct) {
            case 'n':
                if (right instanceof Animal) {
                    instinct = 'e';
                } else if (below instanceof Animal) {
                    instinct = 's';
                } else if (left instanceof Animal) {
                    instinct = 'w';
                }
                break;
            case 'e':
                if (above instanceof Animal) {
                    instinct = 'n';
                } else if (below instanceof Animal) {
                    instinct = 's';
                } else if (left instanceof Animal) {
                    instinct = 'w';
                }
                break;
            case 's':
                if (above instanceof Animal) {
                    instinct = 'n';
                } else if (right instanceof Animal) {
                    instinct = 'e';
                } else if (left instanceof Animal) {
                    instinct = 'w';
                }
                break;
            case 'w':
                if (above instanceof Animal) {
                    instinct = 'n';
                } else if (right instanceof Animal) {
                    instinct = 'e';
                } else if (below instanceof Animal) {
                    instinct = 's';
                }
                break;
            default:
                break;
        }
    }

    public void attack(Creature target) {
        // wolf does 5 damage
        if (target instanceof Animal) {
            target.takeDamage(5);
            hungry = false; // Eat
        }
    }

    public boolean canSpawn() {
        return !hungry;
    }

    @Override
    public Creature spawnNewCreature() {
        // make pup after hunting
        if (canSpawn()) {
            Point birthplace = new Point(getLocation());
            if (instinct == 'n') {
                birthplace.x--;
            } else if (instinct == 'e') {
                birthplace.y--;
            } else if (instinct == 's') {
                birthplace.x++;
            } else if (instinct == 'w') {
                birthplace.y++;
            }
            Wolf pup = new Wolf();
            pup.setLocation(birthplace);
            hungry = true;
            return pup;
        } else {
            return null;
        }
    }
}
