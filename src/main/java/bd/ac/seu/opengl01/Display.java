/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.opengl01;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author rakib
 */
public class Display {
    private long window;

    public Display() {
    }
       
    public void run(){
        init();
        loop();
        
    }

    private void init() {
        System.out.println("LWJGL version"+ Version.getVersion());
        if(!GLFW.glfwInit())
            throw new IllegalStateException("Could not initialize GLFW"); 
        window = GLFW.glfwCreateWindow(800, 600, "LWJGL Display", 0, 0);
    }

    private void loop() {
        while(!GLFW.glfwWindowShouldClose(window)){
            GLFW.glfwPollEvents();
        }
    }
    
    
}
