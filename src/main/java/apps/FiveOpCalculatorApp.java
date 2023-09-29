package apps;

import configurations.FiveOpCalculatorConfig;
import controllers.CalculatorController;

/**
 * Implementation of a calculator app that allows five operations: addition, subtraction, multiplication, division,
 * and exponent (power)
 */
public class FiveOpCalculatorApp implements App<FiveOpCalculatorConfig> {

    private CalculatorController controller;
    public FiveOpCalculatorApp(FiveOpCalculatorConfig config) {
        controller = config.controller;
    }

    @Override
    public void run() {
        try {
            controller.run();
        }
        catch (Exception e) {
            System.err.println("Error in calculator, shutting down...");
            e.printStackTrace();
        }
    }
}
