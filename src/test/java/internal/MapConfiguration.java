package internal;

import javax.config.*;
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
        return Collections.emptySet();
    }

    @Override
    public Set<String> getTransitiveAreas(){
        return Collections.emptySet();
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
