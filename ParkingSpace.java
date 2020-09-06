import java.util.Scanner;
import java.util.*;


public class MyClass {
    public static class Timing {
        private int startTime;
        private int endTime;
        private Timing next;
        public Timing() {
            
        }
        public Timing(int startTime, int endTime,Timing next) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.next = next;
        }
        public Timing(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.next = null;
        }
        public int getStartTime() {
            return startTime;
        }
        public int getEndTime() {
            return endTime;
        }
        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }
        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }
        public Timing getNext() {
            return this.next;
        }
        public void setNext(Timing t) {
            this.next = t;
        }
    }
    public static class ParkingSlot {
        private Timing head;
        int size;
        public ParkingSlot() {
            head = null;
            size = 0;
        }
        public boolean add(Timing t ) {
            if(head == null) {
                head = t;
                size++;
                return true;
            }
            else {
                //10 20        1) And element to add 5 7    2) Add 5 12 3) Add 11 19  5) Add 12 25 4) Add 25 30
                //5 10  13 15  // and we trying to add 11 12
                Timing tmp = head;
                Timing previous = null;
                while(tmp != null ) {
                    
                    //Case 1
                    if (t.getEndTime() <= tmp.getStartTime()) {
                        t.setNext(head);
                        head = t;
                        size++;
                        return true;    
                    }
                    //EndTime > startTimeOfCurrentNode, possible Case 2, 3 or 4
                    //Case 4
                    if(t.getStartTime() >= tmp.getEndTime()) {
                        if(tmp.getNext() == null) {
                            tmp.setNext(t);
                            size++;
                            return true;
                        }
                        else {
                            if(t.getEndTime() <= tmp.getNext().getStartTime()) {
                                Timing remainingList = tmp.getNext();
                                t.setNext(remainingList);
                                tmp.setNext(t);
                                size++;
                                return true;
                            }
                         
                        }
                        
                        previous = tmp;
                        tmp = tmp.getNext();
                        continue;
                    } 
                    else if(t.getStartTime() <= tmp.getEndTime() || t.getEndTime() <= tmp.getEndTime()  ){
                        return false;
                    }
                    
                }
            }
            return false;
        }
        public int size() {
            return this.size;
        }
        public String toString() {
            StringBuilder  stringToBeReturned = new StringBuilder();
            Timing tmp = head;
            while(tmp != null) {
                stringToBeReturned.append("[").append( tmp.getStartTime()) .append(" ").append(tmp.getEndTime()).append("]\t");
                tmp = tmp.getNext();
            }
            return stringToBeReturned.toString();
        }
        
    }
    public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
        int[][] timings;
        int minParkingSlotsNeeded = 1;
        List<ParkingSlot> parkingSlots = new LinkedList<>();
        parkingSlots.add(new ParkingSlot());
        int n = sc.nextInt();
        timings = new int[n][2];
        for(int i = 0; i < n; i++) {
            timings[i][0] =  sc.nextInt();
            timings[i][1] = sc.nextInt();
            System.out.println("timings[" +i + "][0] = " + timings[i][0]);
            System.out.println("timings[" +i + "][0] = " + timings[i][1]);
            Timing timingForCurrentVehicle = new Timing(timings[i][0], timings[i][1]);
            boolean gotParkingSpaceInExistingSlots = false;
            Iterator<ParkingSlot> itr = parkingSlots.iterator();
            while(itr.hasNext()) {
                ParkingSlot currentSlot = itr.next();
                //Try to accomodate this Timing into current Slot. If its not possible , we will try to add it new slot.
                if(currentSlot.add(timingForCurrentVehicle)) {
                    gotParkingSpaceInExistingSlots = true;
                    break;   
                }
                
            }
            if(!gotParkingSpaceInExistingSlots) {
                ParkingSlot newParkingSlot = new ParkingSlot();
                newParkingSlot.add(timingForCurrentVehicle);
                parkingSlots.add(newParkingSlot);
            }
            itr = parkingSlots.iterator();
            while(itr.hasNext()) {
                ParkingSlot currentSlot = itr.next();
                System.out.println("----------------------------------------------------");
                System.out.println(currentSlot.toString());
                System.out.println("----------------------------------------------------");
            }
            
        }
        System.out.println("Minimum number of parking spaces needed is " + parkingSlots.size());
        sc.close();
    }
}
