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
package annottext;

import org.javaconfig.annot.ConfKey;
import org.javaconfig.annot.ConfigLoadPolicy;
import org.javaconfig.annot.DefaultValue;
import org.javaconfig.annot.LoadPolicy;

/**
 * An example showing some basic annotations, using an interface to be proxied by the
 * configuration system.
 * Created by Anatole on 15.02.14.
 */
@ConfigLoadPolicy(LoadPolicy.INITIAL)
public interface AnnotatedConfig {

    @ConfKey("foo.bar.myprop")
    @ConfKey("mp")
    @ConfKey("common.test.myProperty")
    @DefaultValue("myValue_$[env.stage]")
    // @ConfigLoadPolicy(listener = MyListener.class)
    String myParameter();

    @ConfKey("simple_value")
    @ConfigLoadPolicy(LoadPolicy.LAZY)
    String simpleValue();

    @ConfKey
    String simplestValue();

    @ConfKey("env.host.name")
    String hostName();

}
