package oy.tol.tra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private Queue<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   /**
    * Execute from the command line: <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    *
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {
         // Create a queue (to the member variable!) for daily tasks, which are strings.
         dailyTaskQueue = new ArrayBlockingQueue<>(10); // Adjust the size as needed
         // Read the tasks for today
         readTasks();
         // Create Java Timer object to schedule your daily tasks.
         timer = new Timer();
         // Schedule the timer at fixed rate with a new TimerTask
         timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               // Check if there are tasks in the queue
               if (!dailyTaskQueue.isEmpty()) {
                  // If yes, print the task from the queue, dequeueing it
                  String task = dailyTaskQueue.poll();
                  System.out.println("Task: " + task);
               } else {
                  // If not, cancel the timer
                  timer.cancel();
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {
      try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("DailyTasks.txt");
           BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
         String line;
         while ((line = reader.readLine()) != null) {
            dailyTaskQueue.offer(line);
         }
         System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size());
      }
   }
}