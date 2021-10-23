package A3_2020149;
/*
AP Assignment 3
 Rahul Maddula
 2020149
*/

import java.sql.Array;
import java.util.*;

class Dice {
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

class Floor {
    private final int points;
    private int position;
    private final String name = "an Empty Floor";
    public Floor() {
        this.points = 1;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}

class Ladder extends Floor {
    private final int points;
    private int position;
    private final String name = "a Ladder Floor";
    public Ladder() {
        this.points = 2;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Elevator extends Floor {
    private final int points;
    private int position;
    private final String name = "an Elevator Floor";
    public Elevator() {
        this.points = 4;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Snake {  // Normal Snake
    private final int points;
    private int position;
    private final String name = "a Snake Floor";

    public Snake() {
        points = -2;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }
}

class kingCobra extends Snake {  // Kind Cobra snake
    private final int points;
    private int position;
    private final String name = "a King Cobra Floor";

    public kingCobra() {
        points = -4;
    }

    @Override
    public void setPosition(int position) {  // Polymorphism
        this.position = position;
    }

    @Override
    public int getPoints() {  // Polymorphism
        return points;
    }

    @Override
    public int getPosition() {  // Polymorphism
        return position;
    }

    @Override
    public String getName() {
        return name;
    }
}

class Player {
    private String name;
    private int position;
    private int points;

    public Player() {
        this.position = -1;
        this.points = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class Board {
    private static int size;
    private final static Dice d = new Dice(2);
    private final static Player p = new Player();
    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, Snake> snakes = new HashMap<>();  // Store all objects of Snake class in respective positions
    static HashMap<Integer, kingCobra> kingCobras = new HashMap<>();  // Store all objects of kingCobra class in respective positions
    static HashMap<Integer, Floor> empty = new HashMap<>();  // Store all objects of Floor class in respective positions
    static HashMap<Integer, Ladder> ladders = new HashMap<>();  // Store all objects of Ladder class in respective positions
    static HashMap<Integer, Elevator> elevators = new HashMap<>(14);  // Store all objects of Elevator class in respective positions

    public Board() {
        // Create board items
        size = 14;

        Floor f0 = new Floor();
        f0.setPosition(0);
        empty.put(0, f0);

        Floor f1 = new Floor();
        f1.setPosition(1);
        empty.put(1, f1);

        Elevator e2 = new Elevator();
        e2.setPosition(2);
        elevators.put(2, e2);

        Floor f3 = new Floor();
        f3.setPosition(3);
        empty.put(3, f3);

        Floor f4 = new Floor();
        f4.setPosition(4);
        empty.put(4, f4);

        Snake s5 = new Snake();
        s5.setPosition(5);
        snakes.put(5, s5);

        Floor f6 = new Floor();
        f6.setPosition(6);
        empty.put(6, f6);

        Floor f7 = new Floor();
        f7.setPosition(7);
        empty.put(7, f7);

        Ladder l8 = new Ladder();
        l8.setPosition(8);
        ladders.put(8, l8);

        Floor f9 = new Floor();
        f9.setPosition(9);
        empty.put(9, f9);

        Floor f10 = new Floor();
        f10.setPosition(10);
        empty.put(10, f10);

        kingCobra k11 = new kingCobra();
        k11.setPosition(11);
        kingCobras.put(11, k11);

        Floor f12 = new Floor();
        f12.setPosition(12);
        empty.put(12, f12);

        Floor f13 = new Floor();
        f13.setPosition(13);
        empty.put(13, f13);

        System.out.println("Enter the player name and hit enter");
        String name = sc.nextLine();
        p.setName(name);
        System.out.println("The game setup is ready");

        while (p.getPosition() != 13) {
            play();
        }
        System.out.println("Game Over");
        System.out.println(p.getName() + " accumulated " + p.getPoints() + " points.");
    }

    public static void play() {
        if (p.getPosition() == -1) {  // Before the player makes it to the board
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            d.roll();
            System.out.println("Dice gave " + d.getValue());
            while (d.getValue() != 1) {
                System.out.println("Game cannot start until you get 1");
                System.out.print("Hit enter to roll the dice");
                sc.nextLine();
                d.roll();
                System.out.println("Dice gave " + d.getValue());
            }
        }
        else {
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            d.roll();
            System.out.println("Dice gave " + d.getValue());
        }
        if (p.getPosition() + d.getValue() <= 13) {
            p.setPosition(p.getPosition() + d.getValue());
            updatePoints(p.getPosition());
        }
        else {
            System.out.println("Player cannot move.");
        }
    }

    public static void updatePoints(int position) {
        System.out.println("Player position Floor - " + position);
        if (empty.get(position) != null) {
            System.out.println(p.getName() + " has reached " + empty.get(position).getName());
            p.setPoints(p.getPoints() + empty.get(position).getPoints());  // Update player points
        }
        else if (ladders.get(position) != null) {
            System.out.println(p.getName() + " has reached " + ladders.get(position).getName());
            p.setPoints(p.getPoints() + ladders.get(position).getPoints());  // Update player points
        }
        else if (elevators.get(position) != null) {
            System.out.println(p.getName() + " has reached " + elevators.get(position).getName());
            p.setPoints(p.getPoints() + elevators.get(position).getPoints());  // Update player points
        }
        else if (snakes.get(position) != null) {
            System.out.println(p.getName() + " has reached " + snakes.get(position).getName());
            p.setPoints(p.getPoints() + snakes.get(position).getPoints());  // Update player points
        }
        else if (kingCobras.get(position) != null) {
            System.out.println(p.getName() + " has reached " + kingCobras.get(position).getName());
            p.setPoints(p.getPoints() + kingCobras.get(position).getPoints());  // Update player points
        }
        System.out.println("Total points: " + p.getPoints());  // Common to all, put at the end of for loop
    }
}

public class A3 {

    public static void main (String[] args) {
        Board board = new Board();
    }
}
