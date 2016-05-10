package handling;

/**
 * Subject class that is the one an Observer observes. Contains the addObserver,
 * removeObserver and NotifyObserver methods.
 * 
 * @author Tom Biscardi
 */
public interface Subject {
	public void addObserver(Observer o);

	public void removeObserver(Observer o);

	public void NotifyObserver(Object args);
}
