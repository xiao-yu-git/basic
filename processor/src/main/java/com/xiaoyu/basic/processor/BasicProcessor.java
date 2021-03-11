package com.xiaoyu.basic.processor;

import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * XiaoYu
 * 2021/3/11 23:14
 * <p>
 * 注解处理器基础封装
 */
public abstract class BasicProcessor extends AbstractProcessor {

    private boolean mIsFirstRound = true;

    protected JCTree.JCCompilationUnit rootTree;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        EnvUtil.init(processingEnvironment);
    }

    protected abstract Class<? extends Annotation> getAnnotation();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!mIsFirstRound) {
            return false;
        }
        mIsFirstRound = false;

        logd("process begin ---> set = " + set);

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(getAnnotation());
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                TreePath path = getTrees().getPath(element);
                rootTree = (JCTree.JCCompilationUnit) path.getCompilationUnit();
                logd("process find class = " + element + ", jcTree = " + rootTree.getClass().getSimpleName());
                translate((TypeElement) element, rootTree);
                try {
                    generateJavaFile((TypeElement) element, rootTree);
                    logd("generate " + element + " success");
                } catch (IOException e) {
                    e.printStackTrace();
                    logd("generate " + element + " failed");
                }
            }
        }
        return false;
    }

    protected abstract void translate(TypeElement curElement, JCTree curTree);

    protected abstract void generateJavaFile(TypeElement curElement, JCTree curTree) throws IOException;

    protected ProcessingEnvironment getEnv() {
        return EnvUtil.getEnv();
    }

    protected Filer getFiler() {
        return EnvUtil.getFiler();
    }

    protected Elements getElements() {
        return EnvUtil.getElements();
    }

    protected Messager getMessager() {
        return EnvUtil.getMessager();
    }

    protected Trees getTrees() {
        return EnvUtil.getTrees();
    }

    protected TreeMaker getTreeMaker() {
        return EnvUtil.getTreeMaker();
    }

    protected Names getNames() {
        return EnvUtil.getNames();
    }

    protected Types getTypes() {
        return EnvUtil.getTypes();
    }

    protected void logd(String msg) {
        LogUtil.logd(this, msg);
    }

    protected void loge(String msg) {
        LogUtil.loge(this, msg);
    }
}
