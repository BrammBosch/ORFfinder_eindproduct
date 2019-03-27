package buttonActions;

import streamConverter.streamConverter;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class blastButtonAction {

    public static void blasten(String path, String seq, JButton blastButton){

        boolean stop = false;
        stop = false;
        // Create a new Thread to do the counting
        Thread t = new Thread() {
            @Override
            public void run() {  // override the run() for the running behaviors
                try {

                    blastButton.setEnabled(false);
                    String[] parts = path.split("/");
                    int einde = parts.length - 1;
                    String[] test = Arrays.copyOf(parts, einde);
                    StringBuilder path2 = new StringBuilder();
                    for (String item : test) {
                        path2.append(item + "/");
                    }
                    path2.append("blasten.py");
                    String[] callAndArgs = {String.valueOf(path2), seq};
                    Process p = Runtime.getRuntime().exec(callAndArgs);

                    InputStream error = p.getErrorStream();

                    String blastErrors = streamConverter.convertStreamToString(error);
                    System.out.println(blastErrors);


                    // Suspend this thread via sleep() and yield control to other threads.
                    // Also provide the necessary delay.
                    try {
                        sleep(10);  // milliseconds
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }

                    blastButton.setEnabled(true);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }


        };


        t.start();

    }

}