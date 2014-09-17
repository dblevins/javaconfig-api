package internal;

import javax.config.*;
import java.util.*;

/**
 * Created by Anatole on 09.09.2014.
 */
public class MapConfiguration implements Configuration{

    private Map<String,String> data = new HashMap<>();

    public MapConfiguration(String name, Map<String,String> data){
        Objects.requireNonNull(name);
        Objects.requireNonNull(data);
        this.data.putAll(data);
        this.data.put("_metainfo", "[name='"+name+"']");
        this.data = Collections.unmodifiableMap(this.data);
    }

    @Override
    public <T> T getOrDefault(String key, Class<T> type, T defaultValue){
        String value = get(key);
        if(value==null){
            return defaultValue;
        }
        return PropertyAdapters.getAdapter(type).apply(get(key));
    }

    @Override
    public <T> T get(String key, Class<T> type){
        String value = get(key);
        if(value!=null){
            return PropertyAdapters.getAdapter(type).apply(get(key));
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
    public boolean isMutable(){
        return false;
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
    public boolean containsKey(Object key){
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value){
        return data.containsValue(value);
    }

    @Override
    public String get(Object key){
        return data.get(key);
    }

    @Override
    public String put(String key, String value){
        throw new IllegalStateException("Config is not mutable.");
    }

    @Override
    public String remove(Object key){
        throw new IllegalStateException("Config is not mutable.");
    }

    @Override
    public void putAll(Map<? extends String,? extends String> m){
        throw new IllegalStateException("Config is not mutable.");
    }

    @Override
    public void clear(){
        throw new IllegalStateException("Config is not mutable.");
    }

    @Override
    public Set<String> keySet(){
        return data.keySet();
    }

    @Override
    public Collection<String> values(){
        return data.values();
    }

    @Override
    public Set<Entry<String,String>> entrySet(){
        return data.entrySet();
    }
}
