/*
 * Copyright (c) 2014.
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 *
 * Specification: Java Configuration ("Specification")
 *
 * Copyright (c) 2012-2014, Credit Suisse All rights reserved.
 */
package javax.config.queries;

import org.javaconfig.Configuration;
import java.io.InputStream;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by Anatole on 18.06.2014.
 */
public class JPAConfigQuery implements Function<Configuration, JPAConfigQuery.JPAConfig>{

    @Override
    public JPAConfig apply(Configuration config){
        return null;
    }

    public static interface JPAConfig{
        Set<String> getPersistenceUnits();
        InputStream getDeploymentDescriptor(String persistenceUnit);
    }

}
