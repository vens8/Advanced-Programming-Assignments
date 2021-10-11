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
        String x = sc.next();
        while (true) {
            if (x.chars().allMatch( Character::isDigit )) {  // Check if the user input is a numeric value.
                if (Integer.parseInt(x) == 1) {
                    vaccine v = new vaccine();
                    vaccines.add(v);
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 2) {
                    hospital h = new hospital();
                    if (h.unique_id != null)
                        hospitals.add(h);
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 3) {
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
                    x = sc.next();
                } else if (Integer.parseInt(x) == 4) {
                    add_slot s = new add_slot();
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 5) {
                    book_slots();
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 6) {
                    list_slots();
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 7) {
                    vaccination_status();
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                } else if (Integer.parseInt(x) == 8) {
                    System.out.println("Exiting COVIN Portal...");
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid choice. Please choose an option from the displayed menu.\n");
                    menu();
                    System.out.print("Enter your choice: ");
                    x = sc.next();
                }
            }
            else {
                System.out.println("Invalid input. Please choose a number from 1-8.\n");
                menu();
                System.out.print("Enter your choice: ");
                x = sc.next();
            }
        }
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
        boolean citizen_exists = false, citizen_valid = true;
        int citizen_index = 0;
        for (int i = 0; i < citizens.size(); i++) {
            if (citizens.get(i).unique_id.equalsIgnoreCase(unique_id)) {
                citizen_index = i;
                citizen_exists = true;
                if (citizens.get(i).vaccination_status.equals("Fully Vaccinated"))
                    citizen_valid = false;
                break;
            }
        }

        if (!citizen_exists) {
            System.out.print("Invalid ID. Citizen not registered.\n");
            return;
        }
        else {
            if (!citizen_valid) {
                System.out.print("Cannot book slot. You're already fully vaccinated\n");
                return;
            }

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
                    System.out.print("\nHospital ID: " + hospital.unique_id + "\n");
                    matches.add(hospital.unique_id);
                    System.out.print("Hospital Name: " + hospital.hospital_name);
                    System.out.println();
                }
            }
            if (pincode_exists) {
                System.out.print("Enter the Hospital ID: ");
                String hospital_id = sc.next();
                if (matches.contains(hospital_id)) {
                    boolean slot_exists = false;
                    ArrayList<Integer> slot_options = new ArrayList<>();
                    for (int i = 0; i < slots.size(); i++) {
                        if (slots.get(i).hospital_id.equalsIgnoreCase(hospital_id)) {
                            if (slots.get(i).quantity > 0) {
                                slot_exists = true;
                                slot_options.add(i);
                                System.out.print("Option " + i + " -> Day " + slots.get(i).day_no + "\n");
                                System.out.print("      Quantity: " + slots.get(i).quantity + " (Available)\n");
                                System.out.print("      Vaccine: " + slots.get(i).vaccine + "\n");
                            }
                        }
                    }
                    if (slot_exists) {
                        System.out.print("Choose slot: ");
                        int slot_index = sc.nextInt(), vaccine_index = 0;
                        if (slot_options.contains(slot_index)) {
                            for (int j = 0; j < vaccines.size(); j++) {
                                if (vaccines.get(j).vaccine_name.equalsIgnoreCase(slots.get(slot_index).vaccine)) {
                                    vaccine_index = j;
                                    break;
                                }
                            }
                            if (citizens.get(citizen_index).vaccine.equals(slots.get(slot_index).vaccine) || citizens.get(citizen_index).vaccination_status.equals("Registered")) {
                                citizens.get(citizen_index).vaccine = vaccines.get(vaccine_index).vaccine_name;
                                ++citizens.get(citizen_index).doses;

                                if (vaccines.get(vaccine_index).doses == citizens.get(citizen_index).doses) {
                                    citizens.get(citizen_index).vaccination_status = "Fully Vaccinated";
                                    citizens.get(citizen_index).next_dose = 0;
                                } else {
                                    citizens.get(citizen_index).vaccination_status = "Partially Vaccinated";
                                    citizens.get(citizen_index).next_dose = vaccines.get(vaccine_index).dose_gap + slots.get(slot_index).day_no;
                                }
                                --slots.get(slot_index).quantity;
                                if (slots.get(slot_index).quantity <= 0) {
                                    slots.remove(slot_index);
                                }
                                System.out.print(citizens.get(citizen_index).citizen_name + " has been vaccinated with " + citizens.get(citizen_index).vaccine + "\n");
                            } else {
                                System.out.print("Cannot book this slot. You have previously been vaccinated with " + citizens.get(citizen_index).vaccine + "\n");
                            }
                        }
                        else
                            System.out.print("Invalid option. Please choose from the available slot options.\n");
                    }
                    else
                        System.out.print("No slots available.\n");
                }
            }
            else
                System.out.print("Invalid pincode.\n");
        }
        else if (choice == 2) {
            System.out.print("Enter vaccine name: ");
            sc.nextLine();
            String vaccine = sc.nextLine();
            boolean vaccine_exists = false;
            ArrayList<String> matches = new ArrayList<>();  // Add add hospital ids related to a pincode

            for (A1.add_slot.days slots : slots) {
                if (slots.vaccine.equalsIgnoreCase(vaccine)) {
                    vaccine_exists = true;
                    for (A1.hospital hospital : hospitals) {
                        if (slots.hospital_id.equalsIgnoreCase(hospital.unique_id)) {
                            System.out.print("Hospital ID: " + slots.hospital_id + "\n");
                            matches.add(hospital.unique_id);
                            System.out.print("Hospital Name: " + slots.hospital_name());
                            System.out.println();
                        }
                    }
                }
            }
            if (vaccine_exists) {
                System.out.print("Enter the hospital ID: ");
                String hospital_id = sc.next();
                if (matches.contains(hospital_id)) {
                    boolean slot_exists = false;
                    ArrayList<Integer> slot_options = new ArrayList<>();
                    for (int i = 0; i < slots.size(); i++) {
                        if (slots.get(i).hospital_id.equalsIgnoreCase(hospital_id) && slots.get(i).vaccine.equalsIgnoreCase(vaccine)) {
                            if (slots.get(i).quantity > 0) {
                                slot_exists = true;
                                slot_options.add(i);
                                System.out.print("Option " + i + " -> Day " + slots.get(i).day_no + "\n");
                                System.out.print("      Quantity: " + slots.get(i).quantity + " (Available)\n");
                                System.out.print("      Vaccine: " + slots.get(i).vaccine + "\n");
                            }
                        }
                    }
                    if (slot_exists) {
                        System.out.print("Choose slot: ");
                        int slot_index = sc.nextInt(), vaccine_index = 0;
                        if (slot_options.contains(slot_index)) {
                            for (int j = 0; j < vaccines.size(); j++) {
                                if (vaccines.get(j).vaccine_name.equalsIgnoreCase(slots.get(slot_index).vaccine)) {
                                    vaccine_index = j;
                                    break;
                                }
                            }
                            if (citizens.get(citizen_index).vaccine.equals(slots.get(slot_index).vaccine) || citizens.get(citizen_index).vaccination_status.equals("Registered")) {
                                citizens.get(citizen_index).vaccine = vaccines.get(vaccine_index).vaccine_name;
                                ++citizens.get(citizen_index).doses;

                                if (vaccines.get(vaccine_index).doses == citizens.get(citizen_index).doses) {
                                    citizens.get(citizen_index).vaccination_status = "Fully Vaccinated";
                                    citizens.get(citizen_index).next_dose = 0;
                                } else {
                                    citizens.get(citizen_index).vaccination_status = "Partially Vaccinated";
                                    citizens.get(citizen_index).next_dose = vaccines.get(vaccine_index).dose_gap + slots.get(slot_index).day_no;
                                }
                                --slots.get(slot_index).quantity;
                                if (slots.get(slot_index).quantity <= 0) {
                                    slots.remove(slot_index);
                                }
                                System.out.print(citizens.get(citizen_index).citizen_name + " has been vaccinated with " + citizens.get(citizen_index).vaccine + "\n");
                            } else {
                                System.out.print("Cannot book this slot. You have previously been vaccinated with " + citizens.get(citizen_index).vaccine + "\n");
                            }
                        }
                        else
                            System.out.print("Invalid option. Please choose from the available slot options.\n");
                    }
                    else
                        System.out.print("No slots available.\n");
                }
                else
                    System.out.print("Hospital ID mismatch.\n");
            }
            else
                System.out.print("Vaccine not available.\n");
        }
        else if (choice == 3)
            return;
        else {
            System.out.print("Invalid option. Please choose from the available menu options.\n");
        }
    }

    public static void list_slots() {
        System.out.print("Enter the hospital ID: ");
        String hospital_id = sc.next();
        boolean slot_exists = false, hospital_exists = false;

        for (hospital hospital : hospitals) {
            if (hospital.unique_id.equalsIgnoreCase(hospital_id)) {
                hospital_exists = true;
                break;
            }
        }
        if (hospital_exists) {
            for (add_slot.days slot : slots) {
                if (slot.hospital_id.equalsIgnoreCase(hospital_id)) {
                    slot_exists = true;
                    if (slot.quantity > 0) {
                        System.out.print("Day " + slot.day_no + "\n");
                        System.out.print("      Quantity: " + slot.quantity + " (Available)\n");
                        System.out.print("      Vaccine: " + slot.vaccine + "\n");
                    } else {
                        System.out.print("Day " + slot.day_no + "\n");
                        System.out.print("      Quantity: " + 0 + " (Not Available)\n");
                        System.out.print("      Vaccine: " + slot.vaccine + "\n");
                    }
                }
            }
            if (!slot_exists)
                System.out.print("No slots available.\n");
        }
        else
            System.out.print("Invalid ID. Hospital not registered.\n");
    }

    public static void vaccination_status() {
        System.out.print("Enter the patient ID: ");
        String patient_id = sc.next();
        boolean patient_exists = false;
        for(citizen citizen : citizens) {
            if(citizen.unique_id.equalsIgnoreCase(patient_id)) {
                patient_exists = true;
                System.out.print("\nPatient name: " + citizen.citizen_name + "\n");
                System.out.print("Vaccination status: " + citizen.vaccination_status + "\n");
                if (!citizen.vaccination_status.equalsIgnoreCase("Registered")) {
                    System.out.print("Vaccine given: " + citizen.vaccine + "\n");
                    System.out.print("Number of doses given: " + citizen.doses + "\n");
                    System.out.print("Next dose due date: " + citizen.next_dose + "\n");
                }
            }
        }
        if(!patient_exists)
            System.out.print("Invalid ID. Patient not registered.\n");
    }

    public static class vaccine {
        String vaccine_name;
        int doses = 0, dose_gap;

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
            if (Integer.toString(pincode).length() != 6)
                System.out.print("Invalid pincode. Please enter a 6 digit pincode.\n");
            else {
                unique_id = generate(++hospital_id);
                display();
            }
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
        int age, doses = 0, next_dose;

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
        static String h_id;
        static int slots;

        public static class days {
            int day_no, quantity = 0, slots;
            String vaccine, hospital_id;

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
                display();
                System.out.println();
            }

            public void display() {
                System.out.println("\nFollowing slot added!");
                System.out.println("Hospital ID: " + h_id);  // Accessing outer variable because hospital_id updates after object creation inside outer class constructor
                System.out.println("Day: " + day_no);
                System.out.println("Quantity: " + quantity);
                System.out.println("Vaccine: " + vaccine);
            }

            public String hospital_name() {
                for(A1.hospital hospital : hospitals) {
                    if (hospital.unique_id.equalsIgnoreCase(hospital_id))
                        return hospital.hospital_name;
                }
                return "NULL";  // Dummy - this return statement is never reached.
            }

        }

        public add_slot() {
            System.out.print("Hospital ID: ");
            sc.nextLine();
            h_id = sc.nextLine();
            System.out.print("Number of slots to be added: ");
            slots = sc.nextInt();
            boolean hospital_exists = false;
            for (A1.hospital hospital : hospitals) {
                if (hospital.unique_id.equalsIgnoreCase(h_id)) {
                    hospital_exists = true;
                    for (int j = 0; j < slots; j++) {
                        days d = new days();
                        d.hospital_id = h_id;
                        d.slots = slots;
                        A1.slots.add(d);
                    }
                }
            }
            if(!hospital_exists)
                System.out.print("Invalid ID. Hospital not registered.\n");
        }
    }
}
