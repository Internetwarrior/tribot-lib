package scripts.lib.action;

/**
 * Created by mike on 1/6/2016.
 */
public class LinearGroup extends Action {
    private Action[] _actions;

    public LinearGroup() {
        this( new Action[] {} );
    }

    public LinearGroup( Action[] actions ) {
        this._actions = actions;
    }

    @Override
    public boolean run() {
        boolean quit = false;

        for( Action a : this._actions ) {
            if( !a.start() ) {
                System.out.println( "Failed" );
                quit = true;
                break;
            }
        }

        return !quit;
    }

    public void setActions( Action[] actions ) {
        this._actions = actions;
    }

    public Action[] getActions() {
        return this._actions;
    }
}
