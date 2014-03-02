package javax.config;

/**
 * This interface allows to distinguish different types or level of {@link javax.config.Environment}. An {@link javax
 * .config.Environment} hereby basically is modelled as an environment tree, where on each level
 * additional environment attributes can be defined. Since each Environment instance has an {@code EnvironmentType}
 * assigned, this allows to distinguish different areas of attributes provided. <br/>
 * <h4>Example</h4>
 * <b>Root Environment</b> (type: root):
 * <ul>
 * <li>Initialization Data</li>
 * <li>The Host name</li>
 * <li><b>System Properties Environment</b> (type: system-props), containing all system properties</li>
 * <li><b>Environment Properties Environment</b> (type: env-props), containing all environment properties</li>
 * <li><b>Argument Environment</b> (type: CLI-args), containing all command line arguments</li>
 * <li><b>Deployment Environment</b> (type: deployment), containing all deployment settings (may be derived)</li>
 * <p>
 * <li><ul><li><b>Ear Environment</b> (type: ear), containing all deployment settings,
 * related to the current ear.<br/>
 * <ul>
 * <li><b>Application Environment</b> (type: app), containing all application settings,
 * related to the current war.</li>
 * <p>
 * </ul></li></ul></li>
 * </ul>
 * <p/>
 * It is recommended to use constants to define environment types, e.g. enumeration fields. Avoid using names,
 * so the compiler can check type safety.
 * </p>
 * <h3>Implementation requirements</h3>
 * <p>Implementations of this interface must be
 * <ul>
 * <li>serializable</li>
 * <li>immutable</li>
 * <li>thread safe</li>
 * </ul></p>
 */
public interface EnvironmentType{

    /**
     * Get the environment type's name.
     *
     * @return the types name.
     */
    public String getName();

}
