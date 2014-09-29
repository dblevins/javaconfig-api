/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

/**
 * Instances of this interface can be configured to handle configuration changes. The also have control if config
 * changes are applied and propagated.
 * Created by Anatole on 29.09.2014.
 */
public interface ConfigChangeHandler{

    /**
     * Method called by the configuration system, if the underlying config has been changed.
     * @param evt the configuration change event.
     * @return true, if the event should be forwarded to registered change methods.
     */
    boolean handleChange(ConfigChangeEvent evt);
}
