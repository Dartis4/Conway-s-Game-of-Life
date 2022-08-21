package prove02;

/**
 * Spawners make new creatures.
 * <p>
 * 
 * @author Dane Artis
 * @version 1.0
 * @since 2021-05-06
 * @see Creature
 */
public interface Spawner {

    /**
     * Spawns a new {@link Creature}.
     */
    public Creature spawnNewCreature();
}
