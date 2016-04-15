package com.occ.helper;

import java.io.IOException;

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
