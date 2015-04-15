package com.instonctools.analyzer.component;

import com.instonctools.analyzer.component.state.ApplicationComponentState;
import com.instonctools.analyzer.service.MarkerService;
import com.instonctools.analyzer.service.RuleService;
import com.instonctools.analyzer.service.StandardService;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by ronn on 10.04.15.
 */
@State(name = "AnalyzerAppComponent", storages = @Storage(id = "AnalyzerAppComponent", file = StoragePathMacros.APP_CONFIG + "/analyzer_app.xml"))
public class AnalyzerApplicationComponent implements ApplicationComponent, PersistentStateComponent<ApplicationComponentState> {

    private ApplicationComponentState componentState;

    private RuleService ruleService;

    private MarkerService markerService;

    private StandardService standardService;

    public AnalyzerApplicationComponent() {
        System.out.println(getClass().getSimpleName() + " CREATED!");
    }

    public void initComponent() {
        this.ruleService = ServiceManager.getService(RuleService.class);
        this.standardService = ServiceManager.getService(StandardService.class);
        this.markerService = ServiceManager.getService(MarkerService.class);
    }

    public RuleService getRuleService() {
        return ruleService;
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "AnalyzerApplicationComponent";
    }

    @Nullable
    @Override
    public ApplicationComponentState getState() {
        return componentState;
    }

    @Override
    public void loadState(ApplicationComponentState componentState) {
        this.componentState = componentState;
    }
}
