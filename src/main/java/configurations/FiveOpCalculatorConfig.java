package configurations;

import controllers.CalculatorController;
import controllers.FiveOpCalculatorController;
import models.Calculator;
import views.CalculatorView;

/**
 * An implementation for a config for an app that supports 5 operations: add, subtract, multiply, division, and exponent/
 * power.
 */
public class FiveOpCalculatorConfig implements Config {

    public final Calculator model;

    public final CalculatorView view;

    public final CalculatorController controller;

    private FiveOpCalculatorConfig(
            Calculator model,
            CalculatorView view,
            CalculatorController controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    /**
     * Factory pattern for this config
     */
    public static class Factory implements Config.Factory<FiveOpCalculatorConfig> {

        public Factory() {}
        @Override
        public FiveOpCalculatorConfig createConfig() {
            Calculator model = new models.SimpleCalculator();
            CalculatorView view = new views.ConsolePrintCalculatorView(model);
            CalculatorController controller = new FiveOpCalculatorController(
                    model,
                    view
            );
            return new FiveOpCalculatorConfig(
                    model,
                    view,
                    controller);
        }
    }
}
