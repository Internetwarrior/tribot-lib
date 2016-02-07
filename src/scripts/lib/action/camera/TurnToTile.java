package scripts.lib.action.camera;

import java.awt.Point;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Projection;

import scripts.lib.action.Action;

/**
 * Created by mike on 2/7/2016.
 */
public class TurnToTile extends Action {
	private Positionable _tile;

	public TurnToTile(Positionable tile) {
		this._tile = tile;
	}

	@Override
	public boolean run() {
		Point tilePoint = this._tile.getPosition().getHumanHoverPoint();
		if (tilePoint != null && !Projection.isInViewport(tilePoint)) {
			Camera.turnToTile(this._tile);
			return true;
		}
		return false;
	}
}
