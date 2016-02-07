package scripts.lib.antiban;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.types.RSObject;
import scripts.lib.condition.Not;
import scripts.lib.condition.True;

public class Antiban extends ABCUtil {
    private static final long MAX_WAIT_STEP = 10L;

    private static ABCUtil _abc;
    private static ABCProperties _props;

    static {
    	_props = new ABCProperties();
        _abc = new Antiban();
    }

    public static void afterActionSleep( int min, int max ) {
        General.sleep( min, max );
    }

    public static void afterActionSleep() {
        afterActionSleep( 200, 1000 );
    }

    public static void waitTime( long timeout ) {
        waitCondition( new Not( new True() ), timeout );
    }

    public static boolean waitCondition( Condition condition ) {
        return waitCondition( condition, 1000 );
    }

    /**
     * Implementation of TRiBots Timing.waitCondition, by doing it manually we can use runIdleTasks between breaks
     *
     * @param condition The condition to check
     * @param timeout   The max time to run for
     * @return Returns true if stopped because of condition, otherwise false
     */
    public static boolean waitCondition( Condition condition, long timeout ) {
        long waited_time = 0L;

        while( waited_time < timeout ) {
            if( condition.active() ) return true;

            long wait_time = MAX_WAIT_STEP;
            long time_left = timeout - waited_time;

            if( wait_time > time_left ) {
                wait_time = time_left;
            }

            runIdleTasks(); // TODO: Use argument to control if we runIdleTasks
            try {
                _abc.sleep( wait_time );
            } catch( InterruptedException e ) {

            }
            waited_time += wait_time;
        }

        return false;
    }

    public static boolean waitCondition( Condition condition, long timeout_min, long timeout_max ) {
        double timeout = timeout_min + ( ( timeout_max - timeout_min ) * Math.random() );
        return waitCondition( condition, ( long ) timeout );
    }

    public static void sleepABC2( int previous_waiting_time, boolean hovering, boolean menu_open, boolean under_attack ) {

        _props.setWaitingTime( previous_waiting_time );
        _props.setHovering( hovering );
        _props.setMenuOpen( menu_open );
        _props.setUnderAttack( under_attack );
        _props.setWaitingFixed( false );

        waitTime( _abc.generateReactionTime() );
    }

    public static void runIdleTasks() {
        if( _abc.shouldCheckTabs() )
            _abc.checkTabs();

        if( _abc.shouldCheckXP() )
            _abc.checkXP();

        if( _abc.shouldExamineEntity() )
            _abc.examineEntity();

        if( _abc.shouldMoveMouse() )
            _abc.moveMouse();

        if( _abc.shouldPickupMouse() )
            _abc.pickupMouse();

        if( _abc.shouldRightClick() )
            _abc.rightClick();

        if( _abc.shouldRotateCamera() )
            _abc.rotateCamera();

        if( _abc.shouldLeaveGame() )
            _abc.leaveGame();
    }

    public static Positionable selectTarget( Positionable[] objects ) {
        return _abc.selectNextTarget( objects );
    }
};