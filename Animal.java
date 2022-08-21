package prove02;

import java.awt.Color;
import java.util.Random;

/**
*  Animals move around and eat plants. They are represented by red squares.
* <p>
* @author  Brother Falin
* @version 1.0
* @since   2016-12-08 
* @see Creature
*/
public class Animal extends Creature implements Movable, Aggressor {
	
	Random _rand;
	
	/**
	* Creates an animal with 1 health point.
	*/
	public Animal() {
		_rand = new Random();
		_health = 1;
	}
	
	// No javadocs are necessary for these methods because they will inherit the 
	// documentation from the superclass. We only need to add docs here if we are
	// doing something non-obvious in our overridden version.
	
	public Boolean isAlive() {
		return _health > 0;
	}
	
	public Shape getShape() {
		return Shape.Square;
	}
	
	public Color getColor() {
		return new Color(165, 33, 135);
	}
	
	/**
	* If the creature we've encountered is a plant, we'll eat it. Otherwise, we ignore it.
	* @param target The {@link Creature} we've encountered.
	*/
	public void attack(Creature target) {
		// Animals only eat plants. Give the plant 1 damage
		// and increase our health by one.
		if(target instanceof Plant) {
			target.takeDamage(1);
			_health++;
		}
	}
	
	/**
	* Move the animal in a random direction.
	*/
	public void move() {
		
		// Choose a random direction each time move() is called.
		switch(_rand.nextInt(4)) {
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
	}
}
ndler(rows, cols, creatures);
		_handler.setStartingPositions();

		// This is all AWT stuff for setting up the window
		JFrame win = new JFrame("Life with Java");
		win.setSize(_world);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.getContentPane().setBackground(new Color(233, 232, 231));
		win.add( this );
		
		// This strange little construction is called an anonymous inner class.
		// Here, we're using it to handle window resizing because that's how AWT
		// makes us do it.
		// You can read more about anonymous classes here: 
		// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
		win.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				_world = new Dimension(getWidth(), getHeight());
				_cell = new Dimension(_world.width / _totalStartingCreatures, 
				                      _world.height / _totalStartingCreatures);
				repaint();
			}
		});

		win.setVisible(true);
		
		// Since this class implements the Runnable interface, we can 
		// have the simulation updating happen on a background thread.
		Thread t = new Thread(this);
		t.start();
	}
	
	/**
	* This function is called on the background thread by virtue of the {@link java.lang.Runnable}
	* interface.
    * <p>
    * Every time it's called, we update all of the creatures, redraw the window, and take a brief .25
    * sleep so the game doesn't run blindingly fast.
    */
	public void run() {
		
		// This infinite loop will run as long as the simulation is
		// active.
		while(true)
		{
			// Give all of the creatures a chance to respond to their
			// enviroment.
			_handler.updateCreatures();

			// Then redraw everything in its new position
			repaint();
			
			// Pause the background thread for 0.25 seconds so we can watch
			// the simulation unfold. Note how we have to wrap the sleep()
			// call in a try/catch block.
			try {
				Thread.sleep(250);
			} catch (Exception e) {
				
			}
			
		}
	}

	// This function will inherit its docs from the JPanel class, so we'll just
	// add some inline developer notes.
	public void paintComponent( Graphics context ) {
		
		super.paintComponent(context);
		
		try {

			// Java's enhanced for loop (AKA for-each loop).
			// See: http://stackoverflow.com/a/11685345/28106
			for(Creature c : _handler.getCreatures()) {
				
				// Don't draw things that are dead
				if(!c.isAlive())
					continue;
				
				Point p = c.getLocation();
				int x = p.x * _cell.width;
				int y = p.y * _cell.height;
				
				context.setColor(c.getColor());
				
				// You can add additional creature visualizations by adding 
				// additional values to the Shape enum (in Shape.java)
				// and handling them here. 
				// For details on what kind of drawing you can do, see:
				// https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
				switch (c.getShape()) {
					case Circle:
						context.fillOval(x, y, _cell.width, _cell.height);
						break;
					case Square:
						context.fillRect(x, y, _cell.width, _cell.height);
						break;
					default:
						break;
				}
			}
			
			// Draw the grid lines
			context.setColor(new Color(187, 187, 185));
			int rows = _world.height / _cell.height;
			int cols = _world.width / _cell.width;

			for(int r = 0; r < rows; r++) {
				context.drawLine(0, r * _cell.height, _world.width, r * _cell.height);
			}
			
			for(int c = 0; c < cols; c++) {
				context.drawLine(c * _cell.width, 0, c * _cell.width, _world.height);
			}
		} catch (ConcurrentModificationException cme) {
			// Ignore this error when it happens.  It will redraw correctly next time.
			// This occurs when the list is updated while repainting the screen
			// due to the user changing the window size.
		}
	}
}