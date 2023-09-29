package controllers;

import models.Calculator;
import numbers_with_logs.NumberWithLogs;
import utility.InputValidator;
import views.CalculatorView;

import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

import static numbers_with_logs.NumberWithLogs.Operation;

/**
 * An implementation for a calculator that runs five operations: addition, subtraction, multiplication, division, and
 * exponent (power)
 */
public class FiveOpCalculatorController implements CalculatorController {

    /**
     * Allowed operations
     */
    private final Map<String, BiFunction<Number, Number, NumberWithLogs>> OPERATIONS =
            Map.of(
                    "+", Operation::addition,
                    "-", Operation::subtraction,
                    "*", Operation::multiplication,
                    "/", Operation::division,
                    "^", Operation::power
            );

    private final String END_REQUEST = "END";

    /**
     * The calculator can be in one of 3 states: new (i.e. not set up), set_up, and done
     */
    enum State {
            NEW,
            SET_UP,
            DONE
    };

    private State state;
    private final Calculator model;

    private final CalculatorView view;

    private final Scanner scan;

    public FiveOpCalculatorController(Calculator model, CalculatorView view) {
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
            scan.close();
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

    /**
     * Prompts the user for a number, which is converted into a NumberWithLogs
     * @return a number with logs, holding the user's inputted number
     */
    private NumberWithLogs promptForNumber() {
        InputValidator validator = new InputValidator(
                scan,
                "Enter number: ",
                this::canBeParsedAsDouble);
        Number validNumber = Double.parseDouble(validator.getValidInput());
        return new NumberWithLogs(validNumber);
    }

    /**
     * Returns whether the input is a double in string form
     * @param input user input
     * @return whether the input is a double in string form
     */
    private boolean canBeParsedAsDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
