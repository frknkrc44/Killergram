package com.shatyuka.killergram;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Class<?> chatUIActivityClass = XposedHelpers.findClassIfExists("org.telegram.ui.ChatActivity", lpparam.classLoader);
            if (chatUIActivityClass == null) return;
            XposedBridge.hookAllMethods(chatUIActivityClass, "addSponsoredMessages", XC_MethodReplacement.returnConstant(null));
        } catch (Throwable t) {
            XposedBridge.log(t);
        }
    }
}
