package A3_2020149;
/*
AP Assignment 3
 Rahul Maddula
 2020149
*/

import java.util.*;

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

abstract class Floor {  // Encapsulated Empty Floor class
    public abstract int getPoints();

    public abstract void setPoints(int points);

    public abstract int getPosition();

    public abstract void setPosition(int position);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract int getJump();
}

class Empty extends Floor {  // Encapsulated & inherited Ladder class
    private int points;
    private int position;
    private String name;
    private int jump;  // Specify which position to jump to on the board
    public Empty() {
        this.points = 1;
        this.name = "an Empty Floor";
        this.jump = 0;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getJump() {
        return jump;
    }
}

class Ladder extends Floor {  // Encapsulated & inherited Ladder class
    private int points;
    private int position;
    private String name;
    private int jump;  // Specify which position to jump to on the board

    public Ladder() {
        this.points = 2;
        this.name = "a Ladder Floor";
        this.jump = 4;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getJump() {
        return jump;
    }
}

class Elevator extends Floor {  // Encapsulated & inherited Elevator class
    private int points;
    private int position;
    private String name;
    private int jump;  // Specify which position to jump to on the board

    public Elevator() {
        this.points = 4;
        this.name = "an Elevator Floor";
        this.jump = 8;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int getJump(){
        return jump;
    }
}

class Snake extends Floor{  // Encapsulated & inherited Snake class
    private int points;
    private int position;
    private String name;
    private int jump;  // Specify which position to jump to on the board

    public Snake() {
        this.points = -2;
        this.name = "a Snake Floor";
        this.jump = -4;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getJump(){
        return jump;
    }
}

class kingCobra extends Floor {  // Encapsulated & inherited King Cobra class
    private int points;
    private int position;
    private String name;
    private int jump;  // Specify which position to jump to on the board

    public kingCobra() {
        this.points = -4;
        this.name = "a King Cobra Floor";
        this.jump = -8;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getJump(){
        return jump;
    }
}

class Player {  // Encapsulated player class
    private String name;
    private int position;
    private int points;

    public Player() {
        this.position = -1;  // Initial position of player object before entering the board
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

    static HashMap<Integer, Floor> floors = new HashMap<>();  // Store all objects of Floor class children in respective positions

    public Board(int size) {
        // Create board items
        this.size = size;  // Initial size of the board by default

        Empty f0 = new Empty();
        f0.setPosition(0);
        floors.put(0, f0);

        Empty f1 = new Empty();
        f1.setPosition(1);
        floors.put(1, f1);

        Elevator e2 = new Elevator();
        e2.setPosition(2);
        floors.put(2, e2);

        Empty f3 = new Empty();
        f3.setPosition(3);
        floors.put(3, f3);

        Empty f4 = new Empty();
        f4.setPosition(4);
        floors.put(4, f4);

        Snake s5 = new Snake();
        s5.setPosition(5);
        floors.put(5, s5);

        Empty f6 = new Empty();
        f6.setPosition(6);
        floors.put(6, f6);

        Empty f7 = new Empty();
        f7.setPosition(7);
        floors.put(7, f7);

        Ladder l8 = new Ladder();
        l8.setPosition(8);
        floors.put(8, l8);

        Empty f9 = new Empty();
        f9.setPosition(9);
        floors.put(9, f9);

        Empty f10 = new Empty();
        f10.setPosition(10);
        floors.put(10, f10);

        kingCobra k11 = new kingCobra();
        k11.setPosition(11);
        floors.put(11, k11);

        Empty f12 = new Empty();
        f12.setPosition(12);
        floors.put(12, f12);

        Empty f13 = new Empty();
        f13.setPosition(13);
        floors.put(13, f13);

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
        if (p.getPosition() + d.getValue() <= 13) {  // If the player can make a valid move
            p.setPosition(p.getPosition() + d.getValue());
            updatePoints(p.getPosition());
            if (!floors.get(p.getPosition()).getName().equalsIgnoreCase("an Empty Floor")) {
                p.setPosition(p.getPosition() + floors.get(p.getPosition()).getJump());
                updatePoints(p.getPosition());
            }
        }
        else {
            System.out.println("Player cannot move.");
        }
    }

    public static void updatePoints(int position) {  // Update the points with every dice roll
        System.out.println("Player position Floor - " + position);
        System.out.println(p.getName() + " has reached " + floors.get(position).getName());
        p.setPoints(p.getPoints() + floors.get(position).getPoints());  // Update player points
        System.out.println("Total points: " + p.getPoints());
    }
}

public class A3 {

    public static void main (String[] args) {
        Board board = new Board(14);  // Creating a board object
    }
}
