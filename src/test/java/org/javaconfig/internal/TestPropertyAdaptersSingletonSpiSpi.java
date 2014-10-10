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

import org.javaconfig.ConfigException;
import org.javaconfig.PropertyAdapter;
import org.javaconfig.spi.PropertyAdaptersSingletonSpi;
import org.javaconfig.Configured;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anatole on 08.09.2014.
 */
public final class TestPropertyAdaptersSingletonSpiSpi implements PropertyAdaptersSingletonSpi{

    private Map<Class, PropertyAdapter<?>> adapters = new ConcurrentHashMap<>();

    private TestPropertyAdaptersSingletonSpiSpi(){
        register(char.class, (s) -> s.charAt(0));
        register(int.class, (s) -> Integer.parseInt(s));
        register(byte.class, (s) -> Byte.parseByte(s));
        register(short.class, (s) -> Short.parseShort(s));
        register(boolean.class, (s) -> Boolean.parseBoolean(s));
        register(float.class, (s) -> Float.parseFloat(s));
        register(double.class, (s) -> Double.parseDouble(s));

        register(Character.class, (s) -> s.charAt(0));
        register(Integer.class, (s) -> Integer.parseInt(s));
        register(Byte.class, (s) -> Byte.parseByte(s));
        register(Short.class, (s) -> Short.parseShort(s));
        register(Boolean.class, (s) -> Boolean.parseBoolean(s));
        register(Float.class, (s) -> Float.parseFloat(s));
        register(Double.class, (s) -> Double.parseDouble(s));
        register(BigDecimal.class, (s) -> new BigDecimal(s));
        register(BigInteger.class, (s) -> new BigInteger(s));

        register(Currency.class, (s) -> Currency.getInstance(s));

        register(LocalDate.class, (s) -> LocalDate.parse(s));
        register(LocalTime.class, (s) -> LocalTime.parse(s));
        register(LocalDateTime.class, (s) -> LocalDateTime.parse(s));
        register(ZoneId.class, (s) -> ZoneId.of(s));
    }


    @Override
    public <T> PropertyAdapter<T> register(Class<T> targetType, PropertyAdapter<T> adapter){
        Objects.requireNonNull(targetType);
        Objects.requireNonNull(adapter);
        return (PropertyAdapter<T>)adapters.put(targetType, adapter);
    }

    @Override
    public <T> PropertyAdapter<T> getAdapter(Class<T> targetType, Configured annotation){
        if(annotation!=null){
            Class adapterType = annotation.adapter();
            if(!adapterType.equals(PropertyAdapter.class)){
                try{
                    return (PropertyAdapter<T>)adapterType.newInstance();
                }
                catch(Exception e){
                    throw new ConfigException("Failed to load PropertyAdapter: " + adapterType, e);
                }
            }
        }
        return (PropertyAdapter<T>) adapters.get(targetType);
    }

    @Override
    public boolean isTargetTypeSupported(Class<?> targetType){
        return adapters.containsKey(targetType);
    }
}
