package javax.config;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class EnvironmentType {

	/**
	 * The {@link EnvironmentType} used for the initial {@link Environment} of
	 * this JVM.
	 */
	public static final EnvironmentType INITIAL_ENVIRONMENT = EnvironmentType
			.of("Runtime");

	private String name;

	private static Map<String, EnvironmentType> environments = new ConcurrentHashMap<>();

	private EnvironmentType(String name) {
		Objects.requireNonNull(name);
		this.name = name;
	}

	public static Collection<EnvironmentType> getEnvironmentTypes() {
		return environments.values();
	}

	public static EnvironmentType of(String typeName) {
		EnvironmentType type = environments.get(typeName);
		if (type == null) {
			type = new EnvironmentType(typeName);
			environments.put(typeName, type);
		}
		return type;
	}

	public String getName() {
		return this.name;
	}

}
