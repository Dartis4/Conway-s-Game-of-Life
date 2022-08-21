package prove02;

import java.util.List;

public interface Forager {
    /**
     * This method is called every frame and indicates what creatures are nearby. If
     * there is no creature in a particular location, that value will be null.
     * 
     * @param above The {@link Creature} list above us.
     * @param below The {@link Creature} list below us.
     * @param left  The {@link Creature} list to the left of us.
     * @param right The {@link Creature} list to the right of us.
     */
    public void senseFood(List<Creature> above, List<Creature> below, List<Creature> left, List<Creature> right);
}
cation() {
		return _location;
	}
	
	/**
	* Sets the location of the creature in game coordinates.
	* @param newValue The new location of the creature. 
	*/
	public void setLocation(Point newValue) {
		_location = newValue;
	}
	
	/**
	* Allows the creature to take damage if something comes by to attack it.
	* @param damage The amount of damage inflicted by the attacker
	*/
	public void takeDamage(int damage) {
		_health -= damage;
	}
	
	// Since the following methods are marked as abstract, subclasses
	// must implement them.	

	/**
	* Gets the {@link Shape} used to represent this creature.
	* @return The shape drawn for this creature.
	*/
	abstract Shape getShape();

	/**
	* Gets the {@link java.awt.Color} used to represent this creature.
	* @return The color drawn for this creature.
	*/
	abstract Color getColor();

	/**
	* Is the creature alive?
	* @return A boolean indicating if the creature is alive.
	*/
	abstract Boolean isAlive();
}