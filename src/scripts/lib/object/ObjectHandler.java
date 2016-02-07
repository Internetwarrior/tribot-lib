package scripts.lib.object;

import java.awt.Point;

import org.tribot.api.Clicking;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSModel;
import org.tribot.api2007.types.RSObject;

public class ObjectHandler {
	// Needs to be polished. I generally use a-sync camera by Final Calibur, as
	// well as my own movement methods
	// which utilitzes the appropiate walking methods for each situation

	public static boolean interact(final RSObject object, final String action) {

		if (object == null)
			return false;

		RSModel model = object.getModel();

		if (Player.getPosition().distanceTo(object) > 2) {
			Walking.walkTo(object);
		}

		if (model != null) {
			Point modelCenter = model.getCentrePoint();
			if (modelCenter != null && !Projection.isInViewport(modelCenter)) {
				Camera.turnToTile(object);
			}
		}

		return Clicking.click(action, object);
	}

	/**
	 * Finds nearby objects based on their name.
	 *
	 * Distance is 20 for this search.
	 * 
	 * @param name
	 * 
	 * @return An array with all the object or an empty array.
	 */
	public static RSObject[] findAllNearest(String name) {
		return Objects.findNearest(20, Filters.Objects.nameEquals(name));
	}

	/**
	 * Finds nearest object
	 * 
	 * @param name
	 * @return The object or null
	 */
	public static RSObject findNearest(final String name) {
		RSObject[] res = Objects.findNearest(20, name);
		return res.length > 0 ? res[0] : null;
	}

}
