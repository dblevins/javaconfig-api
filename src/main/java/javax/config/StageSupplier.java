package javax.config;

/**
 * Created by Anatole on 06.09.2014.
 */
@FunctionalInterface
public interface StageSupplier{

    /**
     * Get the environment's stage.
     * @return the current stage, never null.
     */
    public Stage getStage();

}
