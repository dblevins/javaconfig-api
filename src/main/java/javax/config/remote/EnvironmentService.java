package javax.config.remote;

import java.util.List;

import javax.config.Environment;

public interface EnvironmentService {

	List<Environment> getEnvironment(Environment initialEnvironment, String environment);
	List<Environment> getCurrentEnvironment(Environment initialEnvironment);
	List<Environment> getChildEnvironments(Environment initialEnvironment);
	
}
