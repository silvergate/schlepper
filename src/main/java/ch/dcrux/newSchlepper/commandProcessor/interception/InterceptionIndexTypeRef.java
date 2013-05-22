package ch.dcrux.newSchlepper.commandProcessor.interception;

import ch.dcrux.newSchlepper.commandProcessor.IModifyCommand;

/**
 * Buran.
 *
 * @author: ${USER} Date: 22.05.13 Time: 00:51
 */
public class InterceptionIndexTypeRef<TModifyCommand extends IModifyCommand<?>> {
    private final String ref;

    public InterceptionIndexTypeRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }
}
