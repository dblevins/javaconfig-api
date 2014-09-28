package javax.config;

import java.util.*;

/**
 * Created by Anatole on 28.09.2014.
 */
public final class MetaInfo{

    private final Map<String, String> metaInfo = new HashMap<>();

    MetaInfo(MetaInfoBuilder builder){
        Objects.requireNonNull(builder);
        this.metaInfo.putAll(builder.map);
    }

    public static MetaInfo of(String info){
        return MetaInfoBuilder.of(info).build();
    }

    public String get(String key){
        return this.metaInfo.get(key);

    }
    public Set<String> keySet(){
        return this.metaInfo.keySet();
    }

    public Map<? extends String,? extends String> toMap(){
        return Collections.unmodifiableMap(this.metaInfo);
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder("MetaInfo[");
        for(Map.Entry<String,String> en:metaInfo.entrySet()){
            b.append(escape(en.getKey())).append('=').append(escape(en.getValue())).append(", ");
        }
        if(!metaInfo.isEmpty()){
            b.setLength(b.length()-2);
        }
        b.append(']');
        return b.toString();
    }

    static String escape(String val){
        return val.replaceAll("=", "\\\\=").replaceAll("[", "\\\\[").replaceAll("]", "\\\\]").replaceAll(",", "\\\\,");
    }

}
