/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 * 
 * Specification: JSR-xxx Java Configuration API ("Specification")
 * 
 * Copyright (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config.annot;


import javax.config.ConfigId;

/**
 * This annotation is used to define, which configuration should be used, to evaluate configured properties.
 * When applied on class level this determines the conmfiguration to be used for the whole class, whereas annotating
 * methods or fields allow to define it on a finer granular basis. In general method and field annotations override any
 * existing annotation og the same type on class level.
 */
public @interface Configuration {

    /**
     * The configurations to be used, hereby the first configurations override subsequent entries.
     *
     * @return a list of config entries to be used, if empty the current default configuratoin for the current
     * Environment is used.
     */
    String[] value();

}
