
import java.util.Scanner;

enum State {
    EXIT(), BUY(), FILL(), REMAINING(), RUNNING, TAKE()
}

class CoffeeMachineAction {

    public static int mlOfWater = 400;
    public static int mlOfMilk = 540;
    public static int gramsOfCoffee = 120;
    public static int disposableCups = 9;
    public static int money = 550;

    public static void buyProduct(String num) {

        switch (num) {
            case "1":
                if (mlOfWater >= 250 && gramsOfCoffee >= 16 && disposableCups >= 1) {
                    mlOfWater -= 250;
                    gramsOfCoffee -= 16;
                    money += 4;
                    disposableCups--;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough water!");
                }
                break;
            case "2":
                if (mlOfWater >= 350 && mlOfMilk >= 75 && gramsOfCoffee >= 20 && disposableCups >= 1) {
                    mlOfWater -= 350;
                    gramsOfCoffee -= 20;
                    mlOfMilk -= 75;
                    money += 7;
                    disposableCups--;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough water!");
                }
                break;
            case "3":
                if (mlOfWater >= 200 && mlOfMilk >= 100 && gramsOfCoffee >= 12 && disposableCups >= 1) {
                    mlOfWater -= 200;
                    gramsOfCoffee -= 12;
                    mlOfMilk -= 100;
                    money += 6;
                    disposableCups--;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough water!");
                }
                break;
            case "back":
                break;
        }
    }

    public static void fillProduct(int waterAdd, int milkAdd, int gramsAdd, int cupsAdd) {
        mlOfWater += waterAdd;
        mlOfMilk += milkAdd;
        gramsOfCoffee += gramsAdd;
        disposableCups += cupsAdd;
    }

    public static void remainingProduct() {
        System.out.println("The coffee machine has:\n" +
                mlOfWater + " ml of water\n" +
                mlOfMilk + " ml of milk\n" +
                gramsOfCoffee + " g of coffee beans\n" +
                disposableCups + " disposable cups\n" +
                "$" + money + " of money");
    }

    public static void takeProduct() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public static State stateOfMachine(String choice) {
        switch (choice) {
            case "fill":
                return State.FILL;
            case "buy":
                return State.BUY;
            case "take":
                return State.TAKE;
            case "remaining":
                return State.REMAINING;
            default:
                return State.EXIT;
        }
    }

}


public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        State state = State.RUNNING;
        boolean runMachine = true;

        while (runMachine) {

            switch (state) {
                case RUNNING:
                    System.out.println("Write action (buy, fill, take, remaining, exit): ");
                    String action = scanner.next();
                    state = CoffeeMachineAction.stateOfMachine(action);
                    break;

                case BUY:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappucino, back - to main menu:");
                    String num = scanner.next();
                    if (num.equals("back")) {
                        state = State.RUNNING;
                        break;
                    } else {
                        CoffeeMachineAction.buyProduct(num);
                        ;
                    }
                    state = State.RUNNING;
                    break;

                case FILL:

                    System.out.println("Write how many ml of water you want to add: ");
                    int waterAdd = scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add: ");
                    int milkAdd = scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    int gramsAdd = scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    int cupsAdd = scanner.nextInt();

                    CoffeeMachineAction.fillProduct(waterAdd, milkAdd, gramsAdd, cupsAdd);
                    state = State.RUNNING;
                    break;

                case REMAINING:
                    CoffeeMachineAction.remainingProduct();
                    state = State.RUNNING;
                    break;
                case TAKE:
                    CoffeeMachineAction.takeProduct();
                    state = State.RUNNING;
                    break;
                case EXIT:
                    runMachine = false;
                    break;
            }
        }

    }
}

