package org.dwcj;

import com.basis.bbj.proxies.BBjAPI;
import com.basis.bbj.proxies.BBjSysGui;
import com.basis.startup.type.BBjException;
import org.dwcj.bridge.IDwcjBBjBridge;

import java.util.HashMap;

public final class Environment {

    private static final HashMap<Object, Environment> instanceMap = new HashMap<>();
    private final BBjAPI api;
    private final BBjSysGui sysgui;
    private final IDwcjBBjBridge helper;


    private static Boolean isUnitTest = null;

    public static boolean isUnitTest() {
        if (isUnitTest == null) {
            isUnitTest = true;
            try {
                Class.forName("org.junit.jupiter.api.Test");
            } catch (ClassNotFoundException e) {
                isUnitTest = false;
            }
        }
        return isUnitTest;
    }

    private Environment(BBjAPI api, IDwcjBBjBridge helper) throws BBjException {
        this.api = api;
        this.sysgui = api.openSysGui("X0");
        this.helper = helper;
    }

    public static void init(BBjAPI api, IDwcjBBjBridge helper) throws BBjException {
        Environment env = new Environment(api, helper);
        Environment.instanceMap.put(Thread.currentThread().getName(), env);
    }

    public static void cleanup() {
        Environment.instanceMap.remove(Thread.currentThread().getName());
    }

    public static Environment getInstance() {
        return Environment.instanceMap.get(Thread.currentThread().getName());
    }

    public BBjAPI getBBjAPI() {
        return this.api;
    }

    public BBjSysGui getSysGui() {
        return this.sysgui;
    }

    public IDwcjBBjBridge getDwcjHelper() {
        return helper;
    }

}
