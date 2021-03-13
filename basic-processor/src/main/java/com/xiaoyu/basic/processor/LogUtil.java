package com.xiaoyu.basic.processor;

import javax.tools.Diagnostic;

/**
 * XiaoYu
 * 2021/3/11 23:35
 */
class LogUtil {
    public static void logd(Object obj, String msg) {
        EnvUtil.getMessager().printMessage(Diagnostic.Kind.NOTE, obj.getClass().getSimpleName() + ":" + msg);
    }

    public static void loge(Object obj, String msg) {
        EnvUtil.getMessager().printMessage(Diagnostic.Kind.ERROR, obj.getClass().getSimpleName() + ":" + msg);
    }
}
