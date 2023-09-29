import apps.App;
import apps.FiveOpCalculatorApp;
import configurations.FiveOpCalculatorConfig;

/**
 * Driver class
 */
public class Driver {

    public static void main(String[] args) {
        FiveOpCalculatorConfig config = new FiveOpCalculatorConfig.Factory().createConfig();
        App<FiveOpCalculatorConfig> app = new FiveOpCalculatorApp(config);
        app.run();
    }
}
