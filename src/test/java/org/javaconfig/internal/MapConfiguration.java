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

import org.javaconfig.*;

import java.util.*;

/**
 * Configuration based on a simple Map.
 */
class MapConfiguration implements Configuration, PropertyProvider{

    private Map<String,String> data = new HashMap<>();
    private MetaInfo metaInfo;

    public MapConfiguration(MetaInfo metaInfo, Map<String,String> data){
        Objects.requireNonNull(metaInfo);
        Objects.requireNonNull(data);
        this.metaInfo = metaInfo;
        this.data.putAll(data);
        this.data = Collections.unmodifiableMap(this.data);
    }

    @Override
    public long getVersion() {
        return 0;
    }

    @Override
    public <T> T getOrDefault(String key, Class<T> type, T defaultValue){
        String value = get(key);
        if(value==null){
            return defaultValue;
        }
        return PropertyAdapters.getAdapter(type).adapt(get(key));
    }

    @Override
    public <T> T get(String key, Class<T> type){
        String value = get(key);
        if(value!=null){
            return PropertyAdapters.getAdapter(type).adapt(get(key));
        }
        throw new ConfigException("No such config value: " + key + " in " +  getMetaInfo());
    }

    @Override
    public Set<String> getAreas(){
        final Set<String> areas = new HashSet<>();
        this.keySet().forEach(s -> {
            int index = s.lastIndexOf('.');
            if(index > 0){
                areas.add(s.substring(0, index));
            }
            else{
                areas.add("<root>");
            }
        });
        return areas;
    }

    @Override
    public void addConfigChangeListener(ConfigChangeListener l){
        // TODO
    }

    @Override
    public void removeConfigChangeListener(ConfigChangeListener l){
        // TODO
    }

    @Override
    public int size(){
        return data.size();
    }

    @Override
    public boolean isEmpty(){
        return data.isEmpty();
    }

    @Override
    public boolean containsKey(String key){
        return data.containsKey(key);
    }

    @Override
    public Map<String,String> toMap(){
        return Collections.unmodifiableMap(data);
    }

    @Override
    public MetaInfo getMetaInfo(){
        return this.metaInfo;
    }

    @Override
    public String get(String key){
        return data.get(key);
    }

    @Override
    public Set<String> keySet(){
        return data.keySet();
    }


}
