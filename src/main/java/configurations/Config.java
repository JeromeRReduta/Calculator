package configurations;

/**
 * Representation of a configurations for an app
 */
public interface Config {

    /** Factory pattern for making a config. This should be the only way a config can be created */
    interface Factory<C extends Config> {

        /**
         * Creates a configuration
         * @return a new config
         */
        C createConfig();
    }
}
