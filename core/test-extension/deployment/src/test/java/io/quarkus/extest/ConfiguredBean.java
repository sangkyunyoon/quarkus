package io.quarkus.extest;

import javax.enterprise.event.Observes;

import io.quarkus.extest.runtime.IConfigConsumer;
import io.quarkus.extest.runtime.TestAnnotation;
import io.quarkus.extest.runtime.config.TestBuildAndRunTimeConfig;
import io.quarkus.extest.runtime.config.TestRunTimeConfig;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

/**
 * A sample bean
 */
@TestAnnotation
public class ConfiguredBean implements IConfigConsumer {
    TestRunTimeConfig runTimeConfig;
    TestBuildAndRunTimeConfig buildTimeConfig;

    public ConfiguredBean() {
        System.out.printf("ConfiguredBean.ctor, %s%n", super.toString());
    }

    /**
     * Called by runtime with the runtime config object
     * 
     * @param runTimeConfig
     */
    @Override
    public void loadConfig(TestBuildAndRunTimeConfig buildTimeConfig, TestRunTimeConfig runTimeConfig) {
        System.out.printf("loadConfig, buildTimeConfig=%s, runTimeConfig=%s%n", buildTimeConfig, runTimeConfig);
        this.buildTimeConfig = buildTimeConfig;
        this.runTimeConfig = runTimeConfig;
    }

    /**
     * Called when the runtime has started
     * 
     * @param event
     */
    void onStart(@Observes StartupEvent event) {
        System.out.printf("onStart, event=%s%n", event);
    }

    void onStop(@Observes ShutdownEvent event) {
        System.out.printf("onStop, event=%s%n", event);
    }

    public TestRunTimeConfig getRunTimeConfig() {
        return runTimeConfig;
    }

    public TestBuildAndRunTimeConfig getBuildTimeConfig() {
        return buildTimeConfig;
    }

    @Override
    public String toString() {
        return "ConfiguredBean{runTimeConfig=" + runTimeConfig + '}';
    }
}
