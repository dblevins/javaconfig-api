/*
 *
 *  * Copyright (c) 2014.
 *  * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 *  * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 *  * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 *  * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 *  * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 *  * BUTTON AT THE BOTTOM OF THIS PAGE.
 *  *
 *  * Specification: Java Configuration ("Specification")
 *  *
 *  * Copyright (c) 2012-2014, Credit Suisse All rights reserved.
 *
 */
package javax.config.spi;

import javax.config.ConfigurationModel;
import javax.config.ConfigurationService;
import java.util.Collection;

/**
 * SPI for backing up the ConfigurationServices singleton.<br/>
 * <h3>Implementation Specification</h3>
 * <p>Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * </ul></p>
 *
 * @author Anatole Tresch
 */
public interface ConfigurationServicesSpi{

    /**
     * Access a meta model.
     * @param metaModelName the meta model's name.
     * @return the according meta model.
     * TODO define access names for default models, ...
     */
    public ConfigurationModel getMetaModel(String metaModelName);

    /**
     * Access all defined {@link javax.config.ConfigurationModel} keys.
     *
     * @return all available ConfigurationModel keys, never{@code null}.
     */
    public Collection<String> getMetaModelNames();

    /**
     * Access a {@link javax.config.ConfigurationService} by name.
     *
     * @param name the meta model's name, not null
     * @return the coresponding {@link javax.config.ConfigurationService} corresponding to the
     * defined {@code ConfigurationModel}, or null.
     */
    public ConfigurationService getService(String name);

}
