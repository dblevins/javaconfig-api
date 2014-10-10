/*
 * Copyright 2014 Credit Suisse and other (see authors).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.javaconfig.internal;

import org.javaconfig.ConfigChangeListener;
import org.javaconfig.ConfigException;
import org.javaconfig.Configuration;
import org.javaconfig.MetaInfoBuilder;
import org.javaconfig.spi.ConfigurationManagerSingletonSpi;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anatole on 09.09.2014.
 */
public class TestConfigServiceSingletonSpi implements ConfigurationManagerSingletonSpi{


    private Map<String, Configuration> configs = new ConcurrentHashMap<>();

    public TestConfigServiceSingletonSpi(){
        Map<String,String> config = new HashMap<>();
        config.put("a.b.c.key1", "value of a.b.c.key1");
        config.put("a.b.c.key2", "value of a.b.c.key2");
        config.put("a.b.key3", "value of a.b.key3");
        config.put("a.b.key4", "value of a.b.key4");
        config.put("a.key5", "value of a.key5");
        config.put("a.key6", "value of a.key6");
        config.put("int1", "123456");
        config.put("int2", "111222");
        config.put("booleanT", "true");
        config.put("double1", "1234.5678");
        config.put("BD", "123456789123456789123456789123456789.123456789123456789123456789123456789");
        config.put("testProperty", "value of testProperty");
        config.put("runtimeVersion", "${java.version}");
        configs.put("test", new MapConfiguration(MetaInfoBuilder.of().setName("test").build(), config));
    }

    @Override
    public boolean isConfigurationDefined(String name){
        return configs.containsKey(name);
    }

    @Override
    public boolean isConfigurationDefined(Annotation... qualifiers){
        return false;
    }

    @Override
    public Configuration getConfiguration(String name){
        Configuration config = configs.get(name);
        return Optional.ofNullable(config).orElseThrow(() -> new ConfigException("No such config: " + name));
    }

    @Override
    public Configuration getConfiguration(Annotation... qualifiers){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void configure(Object instance) {
        // TODO
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <T> T getConfiguration(Class<T> type) {
        // TODO
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String evaluateValue(Configuration config, String expression){
        // TODO improve this ugly implementation...
        for(Map.Entry<String, String> en: config.toMap().entrySet()){
            expression = expression.replaceAll("\\$\\{"+en.getKey()+"\\}", en.getValue().toString());
        }
        return expression;
    }

    @Override
    public void addConfigChangeListener(ConfigChangeListener listener) {
        // TODO
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void removeConfigChangeListener(ConfigChangeListener listener) {
        // TODO
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
