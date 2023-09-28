package controllers;

import input_validation.InputValidator;
import models.Calculator;
import models.SimpleCalculator;
import numbers_with_logs.NumberWithLogs;
import operations_with_logs.OperationWithLogs;
import views.CalculatorView;
import views.ConsolePrintCalculatorView;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class ConsolePrintCalculatorController implements CalculatorController{

    private final Map<String, BiFunction<Number, Number, NumberWithLogs>> OPERATIONS =
            Map.of(
                    "+", OperationWithLogs::addition,
                    "-", OperationWithLogs::subtraction,
                    "*", OperationWithLogs::multiplication,
                    "/", OperationWithLogs::division,
                    "^", OperationWithLogs::power
            );
    private final String END_REQUEST = "END";
    enum State {
            NEW,
            SET_UP,
            DONE
    };

    private State state;
    private final Calculator model;

    private final CalculatorView view;

    private final Scanner scan;

    public static void main(String[] args) {
        Calculator calculator = new SimpleCalculator();
        CalculatorView view = new ConsolePrintCalculatorView(calculator);
        ConsolePrintCalculatorController controller = new ConsolePrintCalculatorController(
                calculator,
                view);
        controller.run();
    }


    public ConsolePrintCalculatorController(Calculator model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.state = State.NEW;
        this.scan = new Scanner(System.in);
    }

    @Override
    public boolean isSetUp() {
        return this.state == State.SET_UP;
    }

    @Override
    public void setInitialValue() {
        NumberWithLogs initialValue = promptForNumber();
        model.setFirstNumber(initialValue);
        this.state = State.SET_UP;
    }

    public void setOperation() {
        InputValidator validator = new InputValidator(
                scan,
                "Enter one of the following operations: "
                        + "+, -, *, /, ^",
                OPERATIONS::containsKey);
        String operationString = validator.getValidInput();
        BiFunction<Number, Number, NumberWithLogs> operation = OPERATIONS.get(operationString);
        model.setOperation(operation);
    }

    @Override
    public void setOperationOrEnd() {
        InputValidator validator = new InputValidator(
                scan,
                "Enter END or one of the following operations: "
                    + "+, -, *, /, ^",
                input -> input.equals(END_REQUEST)
                        || OPERATIONS.containsKey(input));
        String operationString = validator.getValidInput();
        if (operationString.equals(END_REQUEST)) {
            this.state = State.DONE;
            return;
        }
        BiFunction<Number, Number, NumberWithLogs> operation = OPERATIONS.get(operationString);
        model.setOperation(operation);
    }

    @Override
    public void setSecondValue() {
        NumberWithLogs secondValue = promptForNumber();
        model.setSecondNumber(secondValue);
    }

    @Override
    public void calculate() {
        model.calculate();
    }

    @Override
    public boolean shouldGoAgain() {
        return this.state != State.DONE;
    }

    @Override
    public void displaySteps() {
        view.tryDisplayHistory();
    }

    @Override
    public void displayResult() {
        view.tryDisplayResultValue();
    }

    private void end() {
        this.state = State.DONE;
    }

    private NumberWithLogs promptForNumber() {
        InputValidator validator = new InputValidator(
                scan,
                "Enter number: ",
                this::canBeParsedAsDouble);
        Number validNumber = Double.parseDouble(validator.getValidInput());
        return new NumberWithLogs(validNumber);
    }

    private boolean canBeParsedAsDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private String promptForOperationOrEnd() {
        boolean isValid = false;
        String input = null;
        while (!isValid) {
            System.out.println("Enter 'END' to quit or one of the following operations: +, -, *, /, ^");
            input = scan.nextLine().strip();
            isValid =
                    input.equals(END_REQUEST)
                    || OPERATIONS.containsKey(input);
        }
        return input;
    }
}
