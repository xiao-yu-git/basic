package com.xiaoyu.basic.processor;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;

/**
 * XiaoYu
 * 2021/3/11 22:59
 */
class EnvUtil {

    private static ProcessingEnvironment env;

    private static Filer filer;//文件相关的辅助接口
    private static Elements elements;//注解元素
    private static Messager messager;//日志接口

    private static Trees trees;
    private static TreeMaker treeMaker;
    private static Names names;
    private static Types types;

    public static void init(ProcessingEnvironment env) {
        if (EnvUtil.env != null) {
            return;
        }
        EnvUtil.env = env;
        filer = env.getFiler();
        elements = env.getElementUtils();
        messager = env.getMessager();

        trees = Trees.instance(env);
        Context context = ((JavacProcessingEnvironment) env).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
        types = Types.instance(context);
    }

    public static ProcessingEnvironment getEnv() {
        return env;
    }

    public static Filer getFiler() {
        return filer;
    }

    public static Elements getElements() {
        return elements;
    }

    public static Messager getMessager() {
        return messager;
    }

    public static Trees getTrees() {
        return trees;
    }

    public static TreeMaker getTreeMaker() {
        return treeMaker;
    }

    public static Names getNames() {
        return names;
    }

    public static Types getTypes() {
        return types;
    }
}
