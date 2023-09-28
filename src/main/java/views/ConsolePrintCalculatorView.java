package views;

import models.Calculator;

import java.util.List;

public class ConsolePrintCalculatorView implements CalculatorView {

    private final Calculator calculator;

    public ConsolePrintCalculatorView(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void displayResultValue() {
        System.out.printf("Your result is %f\n",
                calculator.getResult().value().doubleValue());
    }

    @Override
    public void displayHistory() {
        List<String> history = calculator.getResult().logs();
        System.out.println("STEPS:");
        history.forEach(System.out::println);
    }
}
