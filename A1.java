import java.lang.reflect.Array;
import java.util.*;


public class A1 {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<vaccine> vaccines = new ArrayList<>();

    static ArrayList<hospital> hospitals = new ArrayList<>();
    static int hospital_id = 0;

    static ArrayList<citizen> citizens = new ArrayList<>();

    static ArrayList<add_slot.days> slots = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("COVIN Portal initialized...");
        menu();

        System.out.print("Enter your choice: ");
        int x = sc.nextInt();
        while (x != 8) {
            if (x == 1) {
                vaccine v = new vaccine();
                vaccines.add(v);
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 2) {
                hospital h = new hospital();
                hospitals.add(h);
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 3) {
                citizen c = new citizen();
                if (c.age >= 18 && c.unique_id.length() == 12) {
                    c.display();
                    citizens.add(c);
                } else if (c.age < 18) {
                    System.out.print("Sorry, registration is only for 18+ citizens.\n");
                } else {
                    System.out.print("Invalid Unique ID. Please enter a 16 digit ID!\n");
                }
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 4) {
                add_slot s = new add_slot();
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 5) {
                book_slots();
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 6) {
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else if (x == 7) {
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            } else {
                System.out.println("Invalid choice. Please choose an option from the displayed menu.\n");
                menu();
                System.out.print("Enter your choice: ");
                x = sc.nextInt();
            }
        }
        System.exit(0);
    }

    public static void line() {
        System.out.println("----------------------------------------");
    }

    public static void menu() {
        line();
        System.out.println("""
                1. Add Vaccine
                2. Register Hospital
                3. Register Citizen
                4. Add Slot for Vaccination
                5. Book Slot for Vaccination
                6. List all slots for a hospital
                7. Check Vaccination Status
                8. Exit""");
        line();
    }

    public static void book_slots() {
        System.out.print("Enter patient unique ID: ");
        String unique_id = sc.next();
        boolean citizen_exists = false;
        int citizen_index = 0;
        for (int i = 0; i < citizens.size(); i++) {
            if (citizens.get(i).unique_id.equals(unique_id)) {
                citizen_index = i;
                citizen_exists = true;
                break;
            }
        }

        if (!citizen_exists) {
            System.out.print("Invalid ID. Citizen not registered.\n");
            book_slots();
        }

        System.out.println("""
                1. Search by area
                2. Search by vaccine
                3. Exit""");
        System.out.print("Enter option: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("Enter pincode: ");
            int pincode = sc.nextInt();
            boolean pincode_exists = false;
            ArrayList<String> matches = new ArrayList<>();  // Add add hospital ids related to a pincode
            for (A1.hospital hospital : hospitals) {
                if (hospital.pincode == pincode) {
                    pincode_exists = true;
                    System.out.print("Hospital ID: " + hospital.unique_id + "\n");
                    matches.add(unique_id);
                    System.out.print("Hospital Name: " + hospital.hospital_name);
                    System.out.println();
                }
            }
            if (pincode_exists) {
                System.out.print("Enter the Hospital ID: ");
                String hospital_id = sc.next();
                if (matches.contains(hospital_id)) {
                    boolean slot_exists = false;
                    for (int i = 0; i < slots.size(); i++) {
                        if (slots.get(i).hospital_id().equals(hospital_id)) {
                            if (slots.get(i).quantity > 0) {
                                slot_exists = true;
                                System.out.print("Option " + i + " -> Day " + slots.get(i).day_no + "\n");
                                System.out.print("      Quantity: " + slots.get(i).quantity + " (Available)\n");
                                System.out.print("      Vaccine: " + slots.get(i).vaccine + "\n");
                            }
                        }
                    System.out.print("Choose slot: ");
                    int slot_index = sc.nextInt(), vaccine_index = 0;
                    for (int j = 0; j < vaccines.size(); j++) {
                        if (vaccines.get(j).vaccine_name.equals(slots.get(slot_index).vaccine)) {
                            vaccine_index = j;
                            break;
                        }
                    }
                    citizens.get(citizen_index).vaccine = vaccines.get(vaccine_index).vaccine_name;
                    citizens.get(citizen_index).doses = vaccines.get(vaccine_index).doses - 1;
                    citizens.get(citizen_index).next_dose = vaccines.get(vaccine_index).dose_gap;

                    if (vaccines.get(vaccine_index).doses == 1)
                        citizens.get(citizen_index).vaccination_status = "Fully Vaccinated";
                    else
                        citizens.get(citizen_index).vaccination_status = "Partially Vaccinated";
                    System.out.print(citizens.get(citizen_index).citizen_name + "has been vaccinated with " + citizens.get(citizen_index).vaccine);
                    }
                }
            }
        }
    }


    public static class vaccine {
        String vaccine_name;
        int doses, dose_gap;

        public vaccine() {
            System.out.print("Vaccine Name: ");
            sc.nextLine();
            vaccine_name = sc.nextLine();
            System.out.print("Number of Doses: ");
            doses = sc.nextInt();
            if(doses > 1) {
                System.out.print("Gap between Doses: ");
                dose_gap = sc.nextInt();
            }
            else
                dose_gap = 0;
            display();
        }
        public void display() {
            System.out.println("\nFollowing vaccine has been added!");
            System.out.println("Vaccine Name: " + vaccine_name);
            System.out.println("Number of Doses: " + doses);
            System.out.println("Gap between Doses: " + dose_gap);
        }
    }

    public static class hospital {
        String hospital_name, unique_id;
        int pincode;

        public hospital() {
            System.out.print("Hospital Name: ");
            sc.nextLine();
            hospital_name = sc.nextLine();
            System.out.print("Pin Code: ");
            pincode = sc.nextInt();
            unique_id = generate(++hospital_id);
            display();
        }
        public void display() {
            System.out.println("\nFollowing hospital has been registered!");
            System.out.println("Hospital Name: " + hospital_name);
            System.out.println("Pin Code: " + pincode);
            System.out.println("Unique ID: " + unique_id);
        }

        public String generate(int hospital_id) {  // Generate a 6 digit unique id
            String id = Integer.toString(hospital_id);
            String zeroes = "0".repeat(6 - id.length());
            return zeroes + id;
        }
    }

    public static class citizen {
        String citizen_name, unique_id, vaccination_status = "Registered", vaccine = "";
        int age, doses, next_dose;

        public citizen() {
            System.out.print("Citizen Name: ");
            sc.nextLine();
            citizen_name = sc.nextLine();
            System.out.print("Age: ");
            age = sc.nextInt();
            System.out.print("Unique ID: ");
            unique_id = sc.next();
        }
        public void display() {
            System.out.println("\nFollowing citizen has been registered!");
            System.out.println("Citizen Name: " + citizen_name);
            System.out.println("Age: " + age);
            System.out.println("Unique ID: " + unique_id);
        }

    }

    public static class add_slot {
        static String hospital_id;
        static int slots;

        public static class days {
            int day_no, quantity;
            String vaccine;
            //String vaccine_status;

            public days() {
                System.out.print("Enter day number: ");
                day_no = sc.nextInt();  // for a particular hospital it should not allow to enter same day number again.
                System.out.print("Enter quantity: ");
                quantity = sc.nextInt();
                System.out.print("Select Vaccine:\n");
                int i;
                for(i = 0; i < vaccines.size(); i++) {
                    System.out.print(i + ". " + vaccines.get(i).vaccine_name + "\n");
                }
                int index = sc.nextInt();
                while(!(index >= 0 && index < i)) {
                    System.out.print("Invalid option. Please choose from the available vaccines.\n");
                    System.out.print("Select Vaccine: ");
                    index = sc.nextInt();
                }
                vaccine = vaccines.get(index).vaccine_name;
                /* if (quantity > 0)
                    vaccine_status = "Available";
                else
                    vaccine_status = "Not Available"; */
                display();
                System.out.println();
            }

            public void display() {
                System.out.println("\nFollowing slot added!");
                System.out.println("Hospital Name: " + hospital_id);
                System.out.println("Day: " + day_no);
                System.out.println("Vaccine: " + vaccine);
            }
            public String hospital_id() {
                return hospital_id;
            }
            public int slots() {
                return slots;
            }

        }

        public add_slot() {
            System.out.print("Hospital ID: ");
            sc.nextLine();
            hospital_id = sc.nextLine();
            System.out.print("Number of slots to be added: ");
            slots = sc.nextInt();
            boolean hospital_exists = false;
            for(int i = 0; i < hospitals.size(); i++) {
                if(hospitals.get(i).unique_id.equals(hospital_id)) {
                    hospital_exists = true;
                    for(int j = 0; j < slots; j++) {
                        add_slot.days d = new add_slot.days();
                        A1.slots.add(d);
                    }
                }
            }
            if(!hospital_exists)
                System.out.print("Invalid ID. Hospital not registered.\n");
        }
    }
}
