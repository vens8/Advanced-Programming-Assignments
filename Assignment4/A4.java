package A4_2020149;
/*
AP Assignment 3
 Rahul Maddula
 2020149
*/
import java.util.*;

class Calculator<first, second> {
    private first f, result;
    private second s;
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

class InvalidTyeException extends RuntimeException{
    String message;
    InvalidTyeException(String message) {
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
    private String name;
    public toy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        toy t = (toy)super.clone();
        return t;
    }
}

class tile {
    private toy t;
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
    private int position, chances;
    private static ArrayList<toy> bucket;

    public Player() {
        this.position = 0;  // Initial position of player object before entering the board
        this.chances = 5;
        bucket = new ArrayList<>();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
    private final static Dice d = new Dice(22);  // 1 - 21
    private final static ArrayList<tile> carpet = new ArrayList<>();
    private final static String cardinal[] = {"first", "second", "third", "fourth", "fifth"};
    static Scanner sc = new Scanner(System.in);

    public Game(int size) {
        // Create carpet tiles
        player = new Player();
        this.size = size;  // Initial size of the board by default
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Barbie")));
        carpet.add(new tile(new toy("Flash")));
        carpet.add(new tile(new toy("Doraemon")));
        carpet.add(new tile(new toy("Nobita")));
        carpet.add(new tile(new toy("Shizuka")));
        carpet.add(new tile(new toy("Shin Chan")));
        carpet.add(new tile(new toy("Ball")));
        carpet.add(new tile(new toy("Dog")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));
        carpet.add(new tile(new toy("Teddy")));

        System.out.print("Hit enter to initialize the game");
        sc.nextLine();
        System.out.println("Game is ready");
        boolean game_over = false;
        while (!game_over) {
            play();
            System.out.println();
            if (player.getChances() == 0) {
                int i;
                game_over = true;
                System.out.println("+-----------+");
                System.out.println("| Game Over |");
                System.out.println("+-----------+");
                System.out.println("Soft toys you won are:");
                for (i = 0; i < player.getBucket().size() - 1; i++) {
                    System.out.print(player.getBucket().get(i).getName() + ", ");
                }
                System.out.print(player.getBucket().get(i).getName());
                System.out.println("\n");
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
                right = true;
                if (input.equalsIgnoreCase("integer")) {
                    Dice d1 = new Dice(100000);
                    boolean b = false;
                    int result;
                    while (!b) {
                        try {
                            //int x = sc.nextInt(), y = sc.nextInt();
                            // Generate 2 random integers.
                            d1.roll();
                            int first = d1.getValue();
                            d1.roll();
                            int second = d1.getValue();
                            // Input result
                            System.out.println("Calculate the result of " + first + " divided by " + second);
                            result = sc.nextInt();
                            b = true;
                            Calculator<Integer, Integer> calculator = new Calculator<>(first, second);
                            System.out.println(calculator.getResult());
                            return ((Integer) calculator.getResult() == result);
                        } catch (InputMismatchException e) {
                            System.out.println(e);
                            sc.nextLine();
                        }
                    }
                } else if (input.equalsIgnoreCase("string")) {
                    boolean b = false;
                    String result;
                    while (!b) {
                        try {
                            b = true;
                            int lowerbound = 65; // letter 'A'
                            int upperbound = 90; // letter 'Z'
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
                            Calculator<String, String> calculator = new Calculator<String, String>(generated1, generated2);
                            System.out.println(calculator.getResult());
                            return (((String) calculator.getResult()).equals(answer));
                        } catch (InputMismatchException e) {
                            System.out.println(e);
                            sc.nextLine();
                        }
                    }
                } else {
                    System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
                sc.nextLine();
            }
        }
        return false;
    }

    public static void play() {
        boolean play = false;
        System.out.println("Hit enter for your " + cardinal[(5 - player.getChances())] + " hop");
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
                    System.out.println("You won a " + t.getName() + " toy");
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
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
                    }
                } else {
                    System.out.println("Incorrect answer\nYou didn't win any soft toy.");
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
