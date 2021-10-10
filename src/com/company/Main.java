package com.company;
import java.util.*;
import java.io.*;
import java.util.Collections;
    public class Main{
        public static void main(String[] args) throws Exception {
            Reader.init(System.in);
            int option;
            int u_id = 100001;      //avoid using random function it does not generate non repeating numbers
            Integer[] arr = new Integer[1000];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            Collections.shuffle(Arrays.asList(arr));
            ArrayList<Vaccine> vaccines = new ArrayList<Vaccine>();
            ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
            ArrayList<Citizen> citizens = new ArrayList<Citizen>();
            System.out.println("CoWin Portal initialized....");
            while (true) {
                System.out.println("---------------------------------");
                System.out.println("1. Add Vaccine");
                System.out.println("2. Register Hospital");
                System.out.println("3. Register Citizen");
                System.out.println("4. Add Slot for Vaccination");
                System.out.println("5. Book Slot for Vaccination");
                System.out.println("6. List all slots for a hospital");
                System.out.println("7. Check Vaccination Status");
                System.out.println("8. Exit");
                System.out.println("---------------------------------");
                option = Reader.nextint();
                //1. Add Vaccine
                if (option == 1) {
                    String name;
                    int gap, no_of_doses;
                    System.out.printf("Vaccine Name: ");
                    name = Reader.next();
                    System.out.printf("Number of Doses: ");
                    no_of_doses = Reader.nextint();
                    if(no_of_doses == 1)gap = 0;
                    else{
                        System.out.printf("Gap between Doses: ");
                        gap = Reader.nextint();
                    }
                    if (no_of_doses < 1) {
                        System.out.println("Invalid NUmber of doses!");
                        continue;
                    }
                    if (gap < 0) {
                        System.out.println("Gap cannot be negative");
                        continue;
                    }
                    int flag = 1;
                    for (int i = 0; i < vaccines.size(); i++) {
                        if ((vaccines.get(i)).get_name().equals(name)) {
                            System.out.println("Vaccine names cannot be same!!");
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        vaccines.add(new Vaccine(name, no_of_doses, gap));
                        System.out.printf("Vaccine Name: %s, Number of Doses: %d, Gap Between Doses: %d\n", name, no_of_doses, gap);
                    }
                    //2. Register Hospital
                } else if (option == 2) {
                    String name;
                    int pin_code, unique_id;
                    System.out.print("Hospital Name: ");
                    name = Reader.next();
                    System.out.printf("PinCode: ");
                    pin_code = Reader.nextint();
                    unique_id = u_id;
                    if (pin_code < 0) {
                        System.out.println("Error Invalid Pin Code!!");
                        continue;
                    }
                    else {
                        u_id++;
                        hospitals.add(new Hospital(name, pin_code, unique_id));
                        System.out.printf("Hospital Name: %s, PinCode: %d, Unique ID: %d\n", name, pin_code, unique_id);
                    }
                    //3. Register Citizen
                } else if (option == 3) {
                    String name, id;
                    int age;
                    System.out.printf("Citizen Name: ");
                    name = Reader.next();
                    System.out.printf("Age: ");
                    age = Reader.nextint();
                    System.out.printf("Unique ID: ");
                    id = Reader.next();
                    if (age < 18) {
                        System.out.printf("Citizen Name: %s, Age: %d, Unique ID: %s\n", name, age, id);
                        System.out.println("Only above 18 are allowed");
                    }
                    else if (id.length() != 12) {
                        System.out.println("Error Invalid ID!!");
                        continue;
                    } else {
                        int flag = 1;
                        for (int i = 0; i < citizens.size(); i++) {
                            if (id == (citizens.get(i)).get_unique_id()) {
                                System.out.println("Already Registered with this ID!!");
                                flag = 0;
                                break;
                            }
                        }
                        if (flag == 1) {
                            citizens.add(new Citizen(name, id, age));
                            System.out.printf("Citizen Name: %s, Age: %d, Unique ID: %s\n", name, age, id);
                        }
                    }
                }
                //4. Add Slot for Vaccination
                else if (option == 4) {
                    int unique_id, day_no, vac_option,flag = 0;
                    long quantity;
                    if (vaccines.size() == 0) {
                        System.out.printf("No vaccine data available. Please Try later!\n");
                        continue;
                    }
                    System.out.printf("Enter Hospital ID: ");
                    unique_id = Reader.nextint();
                    int hosp_id = -1;
                    for (int j = 0; j < hospitals.size(); j++) {
                        if (unique_id == (hospitals.get(j)).get_id()) {
                            hosp_id= j;
                            continue;
                        }
                    }
                    if(hosp_id == -1){
                        flag = 1;
                        System.out.println("Invalid Hospital ID!!");
                        continue;
                    }
                    System.out.printf("Enter number of Slots to be added: ");
                    int n = Reader.nextint();
                    for (int i = 0; i < n; i++) {
                        System.out.printf("Enter Day Number: ");
                        day_no = Reader.nextint();
                        System.out.printf("Enter Quantity: ");
                        quantity = Reader.nextlong();
                        System.out.printf("Select Vaccine\n");
                        for (int j = 0; j < vaccines.size(); j++) {
                            System.out.printf("%d %s\n", j, (vaccines.get(j)).get_name());
                        }
                        vac_option = Reader.nextint();
                        if(vac_option < 0 || vac_option > vaccines.size()){
                            System.out.printf("Invalid Vaccine\n");
                            continue;
                        }
                        (hospitals.get(hosp_id)).add_slot(day_no, quantity, vac_option, (vaccines.get(vac_option)).get_name());
                        System.out.printf("Slot added by Hospital %d for Day: %d, Available Quantity: %d of Vaccine %s\n", unique_id, day_no, (long) quantity, (vaccines.get(vac_option)).get_name());
                    }
                }
                //5. Book Slot for Vaccination
                else if (option == 5) {
                    String unique_id;
                    int option1, id, slot_no = -1, hosp_id = -1,flag = 0;
                    System.out.printf("Enter patient Unique ID: ");
                    unique_id = Reader.next();
                    if(vaccines.size() == 0){
                        System.out.printf("No Data of vaccine available \n");
                        continue;
                    }
                    int citizen_id = -1;
                    for (int k = 0; k < citizens.size(); k++) {
                        if ((citizens.get(k)).get_unique_id().equals(unique_id)) {
                            citizen_id = k;
                            break;
                        }
                    }
                    if(citizen_id == -1){
                        System.out.println("Invalid Citizen ID!");
                        continue;
                    }
                    if(citizens.get(citizen_id).get_vaccination_status().equals("FULLY VACCINATED")){      //fully vaccinated cannot recieve new dose
                        System.out.println("Citizen is already fully vacinated. ");
                        continue;
                    }
                    System.out.println("1. Search by area\n2. Search by Vaccine\n3. Exit");
                    System.out.printf("Enter option: ");
                    option1 = Reader.nextint();

                    int recieved_a_dose = (citizens.get(citizen_id)).get_doses_received();
                    if (option1 == 1) {                 //Search by pincode
                        int pin_code;
                        System.out.printf("Enter PinCode: ");
                        pin_code = Reader.nextint();
                        int count = 0;
                        for (int j = 0; j < hospitals.size(); j++) {
                            if (pin_code == (hospitals.get(j)).get_pin_code()) {
                                System.out.printf("%d %s\n", (hospitals.get(j)).get_id(), (hospitals.get(j)).get_name());
                                count++;
                            }
                        }
                        if(count == 0){
                            System.out.println("No hospitals available");
                            continue;
                        }

                        System.out.printf("Enter hospital id: ");
                        id = Reader.nextint();
                        for (int j = 0; j < hospitals.size(); j++) {
                            if (id == (hospitals.get(j)).get_id()) {
                                hosp_id= j;
                                break;
                            }
                        }
                        if(hosp_id == -1){
                            System.out.println("Invalid Hospital ID!!");
                            continue;
                        }
                        count = 0;

                        for (int k = 0; k < ((hospitals.get(hosp_id)).slots).size(); k++) {
                            if((((hospitals.get(hosp_id)).slots).get(k)).get_quantity() > 0){
                                if(recieved_a_dose > 0){
                                    if((((hospitals.get(hosp_id)).slots).get(k)).get_days() == (citizens.get(citizen_id)).get_next_due()){
                                        System.out.printf("%d-> Day: %d Available Qty:%d Vaccine:%s\n", k,
                                                (((hospitals.get(hosp_id)).slots).get(k)).get_days(), (long) (((hospitals.get(hosp_id)).slots).get(k)).get_quantity(), (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name());
                                        count++;
                                    }
                                }
                                else{
                                    System.out.printf("%d-> Day: %d Available Qty:%d Vaccine:%s\n", k,
                                            (((hospitals.get(hosp_id)).slots).get(k)).get_days(), (long) (((hospitals.get(hosp_id)).slots).get(k)).get_quantity(), (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name());
                                    count++;
                                }
                            }
                        }
                        if(count == 0){
                            System.out.println("No slots available");
                            continue;
                        }

                        System.out.printf("Choose Slot: ");
                        slot_no = Reader.nextint();
                        if(slot_no < 0 ||  slot_no > ((hospitals.get(hosp_id)).slots).size()){
                            System.out.println("Invalid Slot!!");
                            continue;
                        }
                        if((citizens.get(citizen_id)).get_doses_received() == 0){       //if no vaccine recived
                            System.out.printf("%s vaccinated with %s\n", (citizens.get(citizen_id)).get_name(), (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name());
                            int vac_id = (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id();
                            citizens.get(citizen_id).set_vaccine((((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name(), vac_id, 1, (((hospitals.get(hosp_id)).slots).get(slot_no)).get_days(), vaccines.get(vac_id).get_gap());
                            (((hospitals.get(hosp_id)).slots).get(slot_no)).update_quatity();
                        }

                        else if(citizens.get(citizen_id).get_vaccination_status().equals("FULLY VACCINATED")){      //fully vaccinated cannot recieve new dose
                            System.out.println("Citizen is already fully vacinated. ");
                        }

                        else if(citizens.get(citizen_id).get_vaccination_status().equals("PARTIALLY VACCINATED")){  //partailly vaccinated
                            if(citizens.get(citizen_id).get_vaccine_id() != (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id()){  //Both vaccines are not same
                                System.out.println("Invalid! Cannot Mix Vaccines.");
                            }
                            else if(citizens.get(citizen_id).get_next_due() > (((hospitals.get(hosp_id)).slots).get(slot_no)).get_days()){      //not due yet
                                System.out.println("Invalid! Cannot Get next dose before due.");
                            }
                            else{
                                System.out.printf("%s vaccinated with %s\n", (citizens.get(citizen_id)).get_name(), (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name());
                                int vac_id = (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id();
                                citizens.get(citizen_id).update_vaccine_status(vaccines.get(vac_id).get_no_of_doses(),  vaccines.get(vac_id).get_gap());
                                (((hospitals.get(hosp_id)).slots).get(slot_no)).update_quatity();
                            }
                        }
                    }

                    else if (option1 == 2) {        //search by vaccine name
                        String vaccine_name;
                        System.out.printf("Enter Vaccine name: ");
                        vaccine_name = Reader.next();
                        flag = 1;
                        for(int i =0; i< vaccines.size(); i++){
                            if(vaccines.get(i).get_name().equals(vaccine_name))flag = 0;
                        }
                        if(flag == 1){
                            System.out.println("Invalid Vaccine Name!");
                            continue;
                        }
                        for (int j = 0; j < hospitals.size(); j++) {
                            for (int k = 0; k < hospitals.get(j).slots.size(); k++) {
                                if((((hospitals.get(j)).slots).get(k)).get_vaccine_name().equals(vaccine_name)){
                                    System.out.printf("%d %s\n", (hospitals.get(j)).get_id(), (hospitals.get(j)).get_name());}
                            }
                        }

                        System.out.printf("Enter hospital id: ");
                        id = Reader.nextint();
                        for (int j = 0; j < hospitals.size(); j++) {
                            if (id == (hospitals.get(j)).get_id()) {
                                hosp_id= j;
                                break;
                            }
                        }
                        if(hosp_id == -1){
                            System.out.println("Invalid Hospital ID!!");
                            continue;
                        }
                        if(((hospitals.get(hosp_id)).slots).size() == 0){
                            System.out.println("No slots available");
                            continue;
                        }
                        int count = 0;

                        for (int k = 0; k < ((hospitals.get(hosp_id)).slots).size(); k++) {
                            if((((hospitals.get(hosp_id)).slots).get(k)).get_quantity() > 0 && (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name().equals(vaccine_name)){
                                if(recieved_a_dose > 0){
                                    if((((hospitals.get(hosp_id)).slots).get(k)).get_days() == (citizens.get(citizen_id)).get_next_due()){
                                        System.out.printf("%d-> Day: %d Available Qty:%d Vaccine:%s\n", k,
                                                (((hospitals.get(hosp_id)).slots).get(k)).get_days(), (long) (((hospitals.get(hosp_id)).slots).get(k)).get_quantity(), (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name());
                                        count++;
                                    }
                                }
                                else{
                                    System.out.printf("%d-> Day: %d Available Qty:%d Vaccine:%s\n", k,
                                            (((hospitals.get(hosp_id)).slots).get(k)).get_days(), (long) (((hospitals.get(hosp_id)).slots).get(k)).get_quantity(), (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name());
                                    count++;
                                }
                            }
                        }
                        if(count == 0){
                            System.out.println("No slots available");
                            continue;
                        }

                        System.out.printf("Choose Slot: ");
                        slot_no = Reader.nextint();
                        if(slot_no < 0 ||  slot_no > ((hospitals.get(hosp_id)).slots).size()){
                            System.out.println("Invalid Slot!!");
                            continue;
                        }
                        if((citizens.get(citizen_id)).get_doses_received() == 0){       //if no vaccine recived
                            System.out.printf("%s vaccinated with %s\n", (citizens.get(citizen_id)).get_name(), (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name());
                            int vac_id = (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id();
                            citizens.get(citizen_id).set_vaccine((((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name(), vac_id, 1, (((hospitals.get(hosp_id)).slots).get(slot_no)).get_days(), vaccines.get(vac_id).get_gap());
                            (((hospitals.get(hosp_id)).slots).get(slot_no)).update_quatity();
                        }

                        else if(citizens.get(citizen_id).get_vaccination_status().equals("FULLY VACCINATED")){      //fully vaccinated cannot recieve new dose
                            System.out.println("Citizen is already fully vacinated. ");
                        }

                        else if(citizens.get(citizen_id).get_vaccination_status().equals("PARTIALLY VACCINATED")){  //partailly vaccinated
                            if(citizens.get(citizen_id).get_vaccine_id() != (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id()){  //Both vaccines are not same
                                System.out.println("Invalid! Cannot Mix Vaccines.");
                            }
                            else if(citizens.get(citizen_id).get_next_due() < (((hospitals.get(hosp_id)).slots).get(slot_no)).get_days()){      //not due yet
                                System.out.println("Invalid! Cannot Get next dose before due.");
                            }
                            else{
                                System.out.printf("%s vaccinated with %s\n", (citizens.get(citizen_id)).get_name(), (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_name());
                                int vac_id = (((hospitals.get(hosp_id)).slots).get(slot_no)).get_vaccine_id();
                                citizens.get(citizen_id).update_vaccine_status(vaccines.get(vac_id).get_no_of_doses(),  vaccines.get(vac_id).get_gap());
                                (((hospitals.get(hosp_id)).slots).get(slot_no)).update_quatity();
                            }
                        }
                    }
                    else if (option1 == 3) {
                        continue;
                    }
                    else{
                        System.out.printf("Invalid option! Try again. \n");
                        continue;
                    }
                }
                // 6. List all slots for a hospital
                else if(option == 6){
                    int id,hosp_id = -1;
                    System.out.printf("Enter Hospital Id: ");
                    id = Reader.nextint();
                    for(int j =0; j<hospitals.size(); j++) {
                        if (id == (hospitals.get(j)).get_id()) {
                            hosp_id = j;
                            break;
                        }
                    }
                    if(hosp_id == -1 ){
                        System.out.println("Invalid Hospital ID!!");
                    }
                    else{
                        if(((hospitals.get(hosp_id)).slots).size() == 0){
                            System.out.println("No Slots Available ! Try again later.");
                        }
                        else{
                            for (int k = 0; k < ((hospitals.get(hosp_id)).slots).size(); k++) {
                                System.out.printf("Day: %d Vaccine: %s Available Qty: %d\n",
                                        (((hospitals.get(hosp_id)).slots).get(k)).get_days(), (((hospitals.get(hosp_id)).slots).get(k)).get_vaccine_name(), (long)(((hospitals.get(hosp_id)).slots).get(k)).get_quantity());
                            }
                        }
                    }
                }
                // 7. Check Vaccination Status
                else if(option == 7){
                    String cit_id;
                    int flag = 0;
                    System.out.printf("Enter Patient ID: ");
                    cit_id = Reader.next();
                    for(int j =0; j<citizens.size(); j++) {
                        if(cit_id.equals((citizens.get(j)).get_unique_id())){
                            if((citizens.get(j)).get_doses_received() == 0){
                                System.out.println("Citizen REGISTERED");
                            }
                            else if((citizens.get(j)).get_doses_received() == vaccines.get(citizens.get(j).get_vaccine_id()).get_no_of_doses()){
                                System.out.println("FULLY VACCINATED");
                                System.out.printf("Vaccine Given: %s\n",(citizens.get(j)).get_vaccine_name());
                                System.out.printf("Number of Doses given: %s\n",(citizens.get(j)).get_doses_received());
                            }
                            else{
                                if((citizens.get(j)).get_doses_received() < vaccines.get(citizens.get(j).get_vaccine_id()).get_no_of_doses()){
                                    System.out.println("PARTIALLY VACCINATED");
                                    System.out.printf("Vaccine Given: %s\n",(citizens.get(j)).get_vaccine_name());
                                    System.out.printf("Number of Doses given: %s\n",(citizens.get(j)).get_doses_received());
                                    System.out.printf("Next Dose due date: %d\n",(citizens.get(j)).get_next_due());
                                }
                            }
                            flag = 1;
                            break;
                        }
                    }
                    if(flag == 0){
                        System.out.println("Invalid ID!!");
                    }
                }
                // 8. Exit
                else if(option == 8){
                    break;
                }
                else{
                    System.out.printf("Invalid Input!! Try again.\n");
                }
            }
        }
    }

    class Hospital{
        private final String name;
        private final int pin_code,id;
        public ArrayList<Slot> slots = new ArrayList<Slot>();
        //Constructor
        public Hospital(String name,int pin_code,int unique_id){
            this.name = name;
            this.pin_code = pin_code;
            this.id = unique_id;
        }
        public void add_slot(int day_no,long quantity,int vac_id, String name){
            slots.add(new Slot(day_no, quantity, vac_id, name));
        }
        //getters
        public String get_name(){
            return name;
        }
        public int get_pin_code(){
            return pin_code;
        }
        public int get_id(){
            return id;
        }
    }

    class Citizen{
        private final String name;
        private String vaccine_name, vaccination_status;
        private final int age;
        private final String unique_id;
        private int vaccine_id, doses_received, next_due;

        //constructor
        public Citizen(String name,String id,int age){
            this.name = name;
            this.age = age;
            this.unique_id = id;
            this.doses_received = 0;
            this.vaccine_id = -1;
            this.vaccine_name = "";
            this.next_due = -1;
            this.vaccination_status = "REGISTERED";
        }
        //getters
        public String get_name(){
            return name;
        }
        public String get_vaccine_name(){
            return vaccine_name;
        }
        public String get_vaccination_status(){
            return vaccination_status;
        }
        public int get_age(){
            return age;
        }
        public String get_unique_id(){
            return unique_id;
        }
        public int get_vaccine_id(){
            return vaccine_id;
        }
        public int get_doses_received(){
            return doses_received;
        }
        public int get_next_due(){
            return next_due;
        }

        public void set_vaccine(String vaccine_name, int vaccine_id, int doses_received,int recieved_on, int gap){
            this.vaccine_name = vaccine_name;
            this.vaccine_id = vaccine_id;
            this.vaccination_status = "PARTIALLY VACCINATED";
            this.doses_received = doses_received;
            this.next_due = recieved_on + gap;
        }

        public void update_vaccine_status(int reqdoses, int gap){
            this.doses_received++;
            if(doses_received == reqdoses){
                this.vaccination_status = "FULLY VACCINATED";
                return;
            }
            next_due += gap;
        }
    }

    class Slot{
        private final int days;
        private long quantity;
        private final String vaccine_name;
        private final int vaccine_id;
        public Slot(int day_no, long quantity,int vaccine_id, String name){
            this.vaccine_name = name;
            this.days = day_no;
            this.quantity = quantity;
            this.vaccine_id = vaccine_id;
        }
        //getters
        public int get_days(){
            return days;
        }
        public long get_quantity(){
            return quantity;
        }
        public int get_vaccine_id(){
            return vaccine_id;
        }
        public String get_vaccine_name(){
            return vaccine_name;
        }
        public void update_quatity(){
            quantity--;
        }

    }
    class Vaccine{
        private final String name;
        private final int no_of_doses,gap;

        //constructor
        public Vaccine(String name,int no_of_doses,int gap){
            this.name = name;
            this.gap = gap;
            this.no_of_doses =no_of_doses;
        }

        //getters
        public String get_name(){
            return name;
        }
        public int get_no_of_doses(){
            return no_of_doses;
        }
        public int get_gap(){
            return gap;
        }
    }



    //reader class
    class Reader {

        static BufferedReader reader;
        static StringTokenizer tokenizer;

        static void init(InputStream input) {
            reader = new BufferedReader(new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                tokenizer = new StringTokenizer(
                        reader.readLine() );
            }
            return tokenizer.nextToken();
        }

        static int nextint() throws IOException {
            return Integer.parseInt( next() );
        }

        static long nextlong() throws IOException {
            return Long.parseLong( next() );
        }

        static double nextdouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }
