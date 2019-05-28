import java.io.IOException;



public class runnablethread implements Runnable {
    /*

    a little class to experiment with Runnable Threads, Deamons,
    and Running bash shells from java applets.

    run can not be
     */
    private String command;

    public runnablethread(String string){
        System.out.println(string);
        command=string;
    }
    public void run(){
        try{
            //this is actually not working as intended. its grabbing the ascii value
            int number = (int)Thread.currentThread().getName().charAt(7);
            System.out.println(number);
            Thread.sleep(number*100);
              System.out.println("From Thread: "+ Thread.currentThread().getName());
              runShellScript(command);

          }
          catch(InterruptedException ex){
              ex.printStackTrace();
          }
      }


    public static void main(String [] args){

        Thread thread1 = new Thread(new runnablethread("ls"));
        Thread thread2 = new Thread(new runnablethread("ps"));
        Thread thread3 = new Thread(new runnablethread("echo 'this is pretty badass'"));

        //thread.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();

    }

    public static void runShellScript(String checked_command) {


        String command = "#!/bin/bash\ncd ~\n";
        command = command + checked_command;

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}


