package scripts.lib.action.bank;

import org.tribot.api2007.Banking;
import scripts.lib.action.Action;

/**
 * Created by mike on 1/10/2016.
 */
public class CloseBankAction extends Action {
    @Override
    public void start() {
        Banking.close();
        super.start();
    }
}