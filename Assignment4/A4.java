package A4_2020149;
/*
AP Assignment 3
 Rahul Maddula
 2020149
*/

import java.util.*;

class Calculator<first, second> {
    private first result;  // Could be second too since
    int a = 10;
    public Calculator(first f, second s) {

        if ((f instanceof Integer) && (s instanceof Integer))
            result = (first) ((Integer)((Integer)f/(Integer)s));
        else if((f instanceof String) && (s instanceof String))
            result = (first) (f + (String)s);
    }
    public Object getResult() {
        return result;
    }
}

class customException extends Exception {
    String message;
    customException(String message) {
        this.message=message;
    }
    public String toString()
    {
        return message;
    }
}

class Dice {  // Encapsulated Dice class
    private final int faces;
    private int value;
    public Dice(int faces) {
        this.faces = faces;
    }
    public void roll() {
        Random random = new Random();
        value = 1 + random.nextInt(faces);
    }
    public int getValue() {
        return value;
    }
}

class toy implements Cloneable {
    private final String name;
    public toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (toy)super.clone();
    }
}

class tile {
    private final toy t;
    private static int tileNo;
    public tile (toy t) {
        this.t = t;
        ++tileNo;
    }

    public toy getT() {
        return t;
    }
}

class Player {  // Encapsulated player class
    private int chances;
    private static ArrayList<toy> bucket;

    public Player() {
        this.chances = 5;
        bucket = new ArrayList<>();
    }

    public int getChances() {
        return chances;
    }

    public void decrementChances(){
        --chances;
    }

    public void addToy (toy t) {
        bucket.add(t);
    }

    public static ArrayList<toy> getBucket() {
        return bucket;
    }
}

class Game {
    private static Player player;
    private int size;
    private final static Dice d = new Dice(23);  // 1 - 22
    private final static ArrayList<tile> carpet = new ArrayList<>();
    private final static String[] ordinal = {"first", "second", "third", "fourth", "fifth"};
    private static final Scanner sc = new Scanner(System.in);

    public Game(int size) {
        // Create carpet tiles
        System.out.print("Hit enter to initialize the game");
        sc.nextLine();

        player = new Player();
        this.size = size;  // Initial size of the board by default

        // Creating and adding all the tile objects with toys
        carpet.add(new tile(new toy("Lion")));
        carpet.add(new tile(new toy("Barbie")));
        carpet.add(new tile(new toy("Flash")));
        carpet.add(new tile(new toy("Doraemon")));
        carpet.add(new tile(new toy("Nobita")));
        carpet.add(new tile(new toy("Shizuka")));
        carpet.add(new tile(new toy("Shin Chan")));
        carpet.add(new tile(new toy("Ball")));
        carpet.add(new tile(new toy("Dog")));
        carpet.add(new tile(new toy("Batman")));
        carpet.add(new tile(new toy("Superman")));
        carpet.add(new tile(new toy("Mr. Bean")));
        carpet.add(new tile(new toy("Teddy Bear")));
        carpet.add(new tile(new toy("Lightning McQueen")));
        carpet.add(new tile(new toy("Andy")));
        carpet.add(new tile(new toy("Buzz Lightyear")));
        carpet.add(new tile(new toy("Mr. Potato")));
        carpet.add(new tile(new toy("Panda")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Rabbit")));

        System.out.println("Game is ready");
        boolean game_over = false;
        while (!game_over) {
            play();
            if (player.getChances() == 0) {
                int i;
                game_over = true;
                System.out.println("+-----------+");
                System.out.println("| Game Over |");
                System.out.println("+-----------+");
                System.out.println("Soft toys you won are:");
                try {
                    for (i = 0; i < Player.getBucket().size() - 1; i++) {
                        System.out.print(Player.getBucket().get(i).getName() + ", ");
                    }
                    System.out.print(Player.getBucket().get(i).getName());
                    System.out.println("\n");
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(e);
                }
            } else {
                play();
            }
        }
    }

    public static boolean solveQuestion() {
        System.out.println("Question answer round. Please enter 'integer' or 'string' accordingly");
        boolean right = false;
        String input;
        while (!right) {
            try {
                input = sc.nextLine();
                if (input.equalsIgnoreCase("integer")) {
                    Dice d1 = new Dice(100000);
                    boolean b = false;
                    right = true;
                    int result;
                    while (!b) {
                        try {
                            // Generate 2 random integers.
                            d1.roll();
                            int first = d1.getValue();
                            d1.roll();
                            int second = d1.getValue();
                            // Input result
                            System.out.println("Calculate the result of " + first + " divided by " + second);
                            result = sc.nextInt();
                            sc.nextLine();
                            b = true;
                            Calculator<Integer, Integer> calculator = new Calculator<>(first, second);
                            return ((Integer) calculator.getResult() == result);
                        } catch (InputMismatchException e) {
                            System.out.println(e);
                            sc.nextLine();
                        }
                    }
                } else if (input.equalsIgnoreCase("string")) {
                    boolean b = false;
                    right = true;
                    while (!b) {
                        try {
                            b = true;
                            int lowerbound = 65, upperbound = 90; // 'A' - 'Z'
                            int maxLength = 4;
                            Random random = new Random();
                            StringBuilder str = new StringBuilder(maxLength);
                            for (int i = 0; i < maxLength; i++) {
                                int rlint = lowerbound + (int)
                                        (random.nextFloat() * (upperbound - lowerbound + 1));
                                str.append((char) rlint);
                            }
                            String generated1 = str.toString();
                            str = new StringBuilder(maxLength);
                            for (int i = 0; i < maxLength; i++) {
                                int rlint = lowerbound + (int)
                                        (random.nextFloat() * (upperbound - lowerbound + 1));
                                str.append((char) rlint);
                            }
                            String generated2 = str.toString();

                            System.out.println("Calculate the concatenation of strings " + generated1 + " " + generated2);
                            String answer = sc.nextLine();
                            //sc.nextLine();
                            Calculator<String, String> calculator = new Calculator<>(generated1, generated2);
                            return (calculator.getResult().equals(answer));
                        } catch (InputMismatchException e) {
                            System.out.println(e);
                            sc.nextLine();
                        }
                    }
                } else {
                    throw new customException("Invalid choice exception\n");
                }
            } catch (customException e) {
                System.out.print(e);
                System.out.println("Question answer round. Please enter 'integer' or 'string' accordingly");
            }
        }
        return false;
    }

    public static void play() {

        System.out.print("Hit enter for your " + ordinal[(5 - player.getChances())] + " hop");
        sc.nextLine();
        d.roll();
        if (d.getValue() > 20) {
            System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
        } else {
            System.out.println("You landed on tile " + d.getValue());
            if (d.getValue() % 2 == 0) {  // Getting toy on a even tile.
                try {
                    toy t = (toy) carpet.get(d.getValue() - 1).getT().clone();
                    player.addToy(t);
                    System.out.println("You won a(n) " + t.getName() + " toy");
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    sc.nextLine();
                }
                catch (IndexOutOfBoundsException e) {  // For line 260
                    System.out.println(e);
                }
            } else {  // Question on a odd tile.
                if (solveQuestion()) {
                    System.out.println("Correct answer");
                    try {
                        toy t = (toy) carpet.get(d.getValue() - 1).getT().clone();
                        player.addToy(t);
                        System.out.println("You won a " + t.getName() + " toy");
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                        sc.nextLine();
                    }
                    catch (IndexOutOfBoundsException e) {  // For line 260
                        System.out.println(e);
                    }
                } else {
                    System.out.println("Incorrect answer.");
                    System.out.println("You didn't win any soft toy.");
                }
            }
        }
        player.decrementChances();
    }
}

public class A4 {
    public static void main(String[] args) {
        Game c = new Game(20);
    }
}
