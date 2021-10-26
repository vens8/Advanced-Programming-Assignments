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
    private int size;
    private final static Dice d = new Dice(2);
    private static ArrayList<Player> players = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    private static int total_players;

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
        
        // Added extra functionality for bonus (Single player & Multiplayer)
        System.out.print("Enter the number of players (1-4): ");
        total_players = sc.nextInt();
        while (total_players < 1 || total_players > 4) {
            System.out.println("Please select a number in the range 1-4.");
            System.out.print("Enter the number of players (1-4): ");
            total_players = sc.nextInt();
        }
        for (int i = 0; i < total_players; i++) {
            Scanner scInt = new Scanner(System.in);
            System.out.println("Enter the name of player " + (i + 1) + " and hit enter");
            Player p = new Player();
            String name = scInt.nextLine();
            p.setName(name);
            players.add(p);
        }
        sc.nextLine();
        System.out.println("The game setup is ready");
        System.out.println();
        boolean game_over = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPosition() >= 13) {
                game_over = true;
                break;
            }
        }
        while (!game_over) {
            for (int j = 0; j < total_players; j++) {
                if (total_players > 1)
                    System.out.println("--> It's " + players.get(j).getName() + "'s turn <--");
                play(j);
                System.out.println();
                if (players.get(j).getPosition() == 13) {
                    game_over = true;
                    System.out.println("+-----------+");
                    System.out.println("| Game Over |");
                    System.out.println("+-----------+");
                    if (total_players > 1)
                        System.out.println(players.get(j).getName() + " has won!");
                    System.out.println(players.get(j).getName() + " accumulated " + players.get(j).getPoints() + " points.");
                    if (total_players > 1) {
                        print_leaderboard();
                    }
                    System.exit(0);
                }
            }
        }
    }

    public static void play(int index) {
        boolean play = false;
        if (players.get(index).getPosition() == -1) {  // Before the player makes it to the board
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            d.roll();
            System.out.println("Dice gave " + d.getValue());
            if (d.getValue() != 1) {
                System.out.println("Game cannot start until you get 1");
                if (total_players == 1) {
                    while (d.getValue() != 1) {
                        System.out.print("Hit enter to roll the dice");
                        sc.nextLine();
                        d.roll();
                        System.out.println("Dice gave " + d.getValue());
                    }
                    play = true;
                }
            }
            else
                play = true;
        }
        else {
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            d.roll();
            System.out.println("Dice gave " + d.getValue());
            play = true;
        }
        if (play) {
            if (players.get(index).getPosition() + d.getValue() <= 13) {  // If the player can make a valid move
                players.get(index).setPosition(players.get(index).getPosition() + d.getValue());
                updatePoints(index, players.get(index).getPosition());
                if (!floors.get(players.get(index).getPosition()).getName().equalsIgnoreCase("an Empty Floor")) {
                    players.get(index).setPosition(players.get(index).getPosition() + floors.get(players.get(index).getPosition()).getJump());
                    updatePoints(index, players.get(index).getPosition());
                }
            } else {
                System.out.println("Player cannot move.");
            }
        }
    }

    public static void updatePoints(int index, int position) {  // Update the points with every dice roll
        System.out.println("Player position Floor - " + position);
        System.out.println(players.get(index).getName() + " has reached " + floors.get(position).getName());
        players.get(index).setPoints(players.get(index).getPoints() + floors.get(position).getPoints());  // Update player points
        System.out.println("Total points: " + players.get(index).getPoints());
    }

    public static void print_leaderboard() {
        int count = 0, index = -1;
        String player_name = null;
        ArrayList<Player> temp_players = new ArrayList<>();
        temp_players = players;
        System.out.println();
        System.out.println("                     LEADERBOARD                     ");
        System.out.println("+----------------------------------------------------+");
        System.out.println("|       Player Name       |       Player Score       |");
        System.out.println("+----------------------------------------------------+");
        while (temp_players.size() > 0) {
            int i, max = -100;
            for (i = 0; i < temp_players.size(); i++) {
                if (temp_players.get(i).getPoints() > max) {
                    max = temp_players.get(i).getPoints();
                    player_name = temp_players.get(i).getName();
                    index = i;
                }
            }
            ++count;
            System.out.print("|" + count + ". " + player_name);
            for (int j = 0; j < 22 - player_name.length(); j++)
                System.out.print(" ");
            String max_string = Integer.toString(max);
            System.out.print("|            " + max_string);
            for (int j = 0; j < 14 - max_string.length(); j++)
                System.out.print(" ");
            System.out.println("|");
            System.out.println("+----------------------------------------------------+");
            temp_players.remove(index);
        }
    }
}

public class A3 {

    public static void main (String[] args) {
        Board board = new Board(14);  // Creating a board object
    }
}
