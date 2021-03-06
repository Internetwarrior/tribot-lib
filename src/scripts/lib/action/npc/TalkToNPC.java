package scripts.lib.action.npc;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import scripts.lib.action.Action;
import scripts.lib.condition.IsInDialogue;

/**
 * Created by mike on 1/10/2016.
 */
public class TalkToNPC extends Action {
    private String _npc_name;

    public TalkToNPC( String npc_name ) {
        super();
        this._npc_name = npc_name;
    }

    @Override
    public boolean run() {
        RSNPC npcs[] = NPCs.findNearest( this._npc_name );

        if( npcs.length > 0 ) {
            System.out.println( npcs[ 0 ] );

            if( !npcs[ 0 ].isOnScreen() ) {
                Camera.turnToTile( npcs[ 0 ] );
            }

            DynamicClicking.clickRSNPC( npcs[ 0 ], "Talk-to " + this._npc_name );

            return Timing.waitCondition( new IsInDialogue(), 3000 );
        }
        return false;
    }
}
