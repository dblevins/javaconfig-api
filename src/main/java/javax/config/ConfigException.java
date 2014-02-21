/*
 * Copyright (c) 2014.
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 * Specification: Java Configuration ("Specification")
 * Copyright (c) 2012-2014, Credit Suisse All rights reserved.
 */
package javax.config;

/**
 * Base exception for configfuration issues.
 * Created by Anatole on 21.02.14.
 */
public class ConfigException extends RuntimeException{

    private static final long serialVersionUID = -5886094818057522680L;

    public ConfigException(String message){
        super(message);
    }

    public ConfigException(String message, Throwable t){
        super(message, t);
    }
}
