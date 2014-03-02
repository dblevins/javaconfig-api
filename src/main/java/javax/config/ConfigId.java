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

package javax.config;

/**
 * This marker interface allows to identify different configurations. It is recommended to use enumerations to identify
 * configurations so the compiler can ensure type safety, when referencing configurations. As a consequence pure
 * names cannot be used directly, which helps avoiding typing errors on configuration look up.
 * </p>
 * <h3>Implementation requirements</h3>
 * <p>Implementations of this interface must be
 * <ul>
 * <li>serializable</li>
 * <li>immutable</li>
 * <li>thread safe</li>
 * </ul></p>
 */
public interface ConfigId{

}
