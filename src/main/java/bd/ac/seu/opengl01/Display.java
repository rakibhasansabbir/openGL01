/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.opengl01;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author rakib
 */
public class Display {
    private long window;
    private long angle;

    public Display() {
        angle = 0;
    }
       
    public void run(){
        init();
        loop();
        
    }

    private void init() {
        System.out.println("LWJGL version"+ Version.getVersion());
        if(!GLFW.glfwInit())
            throw new IllegalStateException("Could not initialize GLFW"); 
        window = GLFW.glfwCreateWindow(600, 600, "LWJGL Display", 0, 0);
        GLFW.glfwMakeContextCurrent(window);  //for drow anything
    }

    private void loop() {
        GL.createCapabilities();  //create capabilities for my computer 
        
        while(!GLFW.glfwWindowShouldClose(window)){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            drow();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();  //just check window occur or not
        }
    }   

    private void drow() {
        GL11.glPushMatrix();
        GL11.glRotatef(angle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3f(-0.0f, 0, 0);
            GL11.glVertex3f(0.5f, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
        angle +=1; 
        
    }
    
}
