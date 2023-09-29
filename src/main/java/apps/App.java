package apps;

import configurations.Config;

/**
 * Represents app logic
 * @param <C> The type of config this app will use
 */
public interface App<C extends Config> {

    /**
     * Runs the app
     */
    void run();

}
