package prove02;

import java.awt.Color;
import java.util.List;

public class Rabbit extends Animal implements Forager {

    char instinct;

    public Rabbit() {
        this.instinct = '*';
    }

    public Shape getShape() {
        return Shape.Circle;
    }

    public Color getColor() {
        return new Color(235, 131, 193);
    }

    @Override
    public void move() {
        if (instinct == '*') {
            // Choose a random direction each time move() is called.
            switch (_rand.nextInt(4)) {
                case 0:
                    _location.x++;
                    break;
                case 1:
                    _location.x--;
                    break;
                case 2:
                    _location.y++;
                    break;
                case 3:
                    _location.y--;
                    break;
                default:
                    break;
            }
        } else {
            switch (instinct) {
                case 'n':
                    _location.y--;
                    break;
                case 'e':
                    _location.x--;
                    break;
                case 's':
                    _location.y++;
                    break;
                case 'w':
                    _location.x++;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void senseFood(List<Creature> above, List<Creature> below, List<Creature> left, List<Creature> right) {
        for (Creature c : above) {
            if (c instanceof Plant) {
                instinct = 'n';
            }
        }
        for (Creature c : below) {
            if (c instanceof Plant) {
                instinct = 's';
            }
        }
        for (Creature c : left) {
            if (c instanceof Plant) {
                instinct = 'e';
            }
        }
        for (Creature c : right) {
            if (c instanceof Plant) {
                instinct = 'w';
            }
        }
    }

    @Override
    public void attack(Creature target) {
        if (target instanceof Plant) {
            target.takeDamage(1);
            _health += 1;
            instinct = '*';
        }
    }
}
