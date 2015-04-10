package com.instonctools.analyzer.component;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by ronn on 10.04.15.
 */
public class AnalyzerModuleComponent implements ModuleComponent, PersistentStateComponent {

    private Module module;

    public AnalyzerModuleComponent(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "AnalyzerModuleComponent";
    }

    public void projectOpened() {
        // called when project is opened
    }

    public void projectClosed() {
        // called when project is being closed
    }

    public void moduleAdded() {
        // Invoked when the module corresponding to this component instance has been completely
        // loaded and added to the project.
    }

    @Nullable
    @Override
    public Object getState() {
        return null;
    }

    @Override
    public void loadState(Object o) {

    }
}
