import java.util.Scanner;
import java.util.ArrayList;

public class BallClock{

     public static void main(String []args){
        //get input from user
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of balls [27 - 127]: ");
        int num = scan.nextInt();
        scan.close();
        System.out.println();
        
        //start computation timer
        long start = System.currentTimeMillis();
        
        //calculate and print days
        System.out.println(num + " balls return to original postion in " + clock(num) + " days");
        
        //end timer and print time
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Computation time: " + elapsedTime + " milliseconds");
     }
     
     public static int clock(int num) {
        //queue to hold balls
        ArrayList<Integer> queue = new ArrayList<Integer>();
        
        //minute, five minute, and hour tracks
        ArrayList<Integer> minutes = new ArrayList<Integer>();
        ArrayList<Integer> fiveMinutes = new ArrayList<Integer>();
        ArrayList<Integer> hours = new ArrayList<Integer>();
        
        //fill queue with inputed number of balls
        for (int i = 1; i <= num; i++) {
            queue.add(i);
        }
        
        //copy of queue to compare
        ArrayList<Integer> originalQueue = new ArrayList<Integer>(queue);
        
        //variables to track and controll loop
        int days = 0;
        boolean stop = false;
        boolean addDay = false;
        
        
        while (!stop) {
            if (minutes.size() < 4) {
                //add ball to minute track if not full
                minutes.add(queue.get(0));
                queue.remove(0);
            } else {
                if (fiveMinutes.size() < 11) {
                    //move current ball to five minute track if not full
                    fiveMinutes.add(queue.get(0));
                    queue.remove(0);
                    
                    //empty minute track in reverse order
                    for (int i = 3; i >= 0; i--) {
                        queue.add(minutes.get(i));
                        minutes.remove(i);
                    }
                } else {
                    if (hours.size() < 11) {
                        //move current ball to hour track if not full
                        hours.add(queue.get(0));
                        queue.remove(0);
                        
                        //empty minute track in reverse order
                        for (int i = 3; i >= 0; i--) {
                            queue.add(minutes.get(i));
                            minutes.remove(i);
                        }
                        
                        //empty five minute track in reverse order
                        for (int i = 10; i >= 0; i--) {
                            queue.add(fiveMinutes.get(i));
                            fiveMinutes.remove(i);
                        }
                    } else {
                        //empty all tracks in reverse order
                        for (int i = 3; i >= 0; i--) {
                            queue.add(minutes.get(i));
                            minutes.remove(i);
                        }
                        
                        for (int i = 10; i >= 0; i--) {
                            queue.add(fiveMinutes.get(i));
                            fiveMinutes.remove(i);
                        }
                        
                        for (int i = 10; i >= 0; i--) {
                            queue.add(hours.get(i));
                            hours.remove(i);
                        }
                        
                        //move current ball to end of queue
                        queue.add(queue.get(0));
                        queue.remove(0);
                        
                        //add day every other time loop hits here
                        if (addDay) {
                            days++;
                            addDay = false;
                        } else {
                            addDay = true;
                        }
                    }
                }
            }
            
            //stop loop when queue is in original state
            if (queue.equals(originalQueue)) {
                stop = true;
            }
            
        }
        
        return days;
     }
}
