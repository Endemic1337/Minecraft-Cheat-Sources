package net.java.games.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public abstract class ControllerEnvironment {
    private static ControllerEnvironment defaultEnvironment;
    protected final ArrayList controllerListeners = new ArrayList();
    // $FF: synthetic field
    static final boolean $assertionsDisabled;

    static void logln(String msg) {
        log(msg + "\n");
    }

    static void log(String msg) {
        Logger.getLogger(ControllerEnvironment.class.getName()).info(msg);
    }

    protected ControllerEnvironment() {
    }

    public abstract Controller[] getControllers();

    public void addControllerListener(ControllerListener l) {
        if (!$assertionsDisabled && l == null) {
            throw new AssertionError();
        } else {
            this.controllerListeners.add(l);
        }
    }

    public abstract boolean isSupported();

    public void removeControllerListener(ControllerListener l) {
        if (!$assertionsDisabled && l == null) {
            throw new AssertionError();
        } else {
            this.controllerListeners.remove(l);
        }
    }

    protected void fireControllerAdded(Controller c) {
        ControllerEvent ev = new ControllerEvent(c);
        Iterator it = this.controllerListeners.iterator();

        while(it.hasNext()) {
            ((ControllerListener)it.next()).controllerAdded(ev);
        }

    }

    protected void fireControllerRemoved(Controller c) {
        ControllerEvent ev = new ControllerEvent(c);
        Iterator it = this.controllerListeners.iterator();

        while(it.hasNext()) {
            ((ControllerListener)it.next()).controllerRemoved(ev);
        }

    }

    public static ControllerEnvironment getDefaultEnvironment() {
        return defaultEnvironment;
    }

    static {
        $assertionsDisabled = !ControllerEnvironment.class.desiredAssertionStatus();
        defaultEnvironment = new DefaultControllerEnvironment();
    }
}
