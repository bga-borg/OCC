package com.bg.thsb;

import java.io.IOException;

/**
 * Created by bg on 2015.05.17..
 */
public class Helpers {
    /**
     * This is used to pause between sections when measuring with VisualVM
     */
    public static void waitForEnter(){
        try {
            System.out.print("Press enter to continue...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
