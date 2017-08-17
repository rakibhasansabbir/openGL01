/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.ac.seu.opengl01;

import java.time.LocalTime;
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
    private float angle;

    public Display() {
        //angle = 0;
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
        
        GLFW.glfwSetKeyCallback(window, (window,key,scancode,action,mods) ->{
            if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE){
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });
        
        GLFW.glfwMakeContextCurrent(window);  //for drow anything
    }

    private void loop() {
        GL.createCapabilities();  //create capabilities for my computer 
        
        while(!GLFW.glfwWindowShouldClose(window)){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            drowClock();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();  //just check window occur or not
        }
    }   

    private void drawClockHand(){
        LocalTime currentTime = LocalTime.now();
        float secondsFunction = currentTime.getNano() / 1000000000.0f;
        angle = 90 - (currentTime.getSecond() + secondsFunction) * 6;
        GL11.glPushMatrix();
        GL11.glColor3f(1, 1, 1);
        GL11.glRotatef(angle, 0, 0, 1);
        GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3f(-0.0f, 0, 0);
            GL11.glVertex3f(0.4f, 0, 0);     //drow x exis line
            
            GL11.glVertex3f(0.4f, -0.05f, 0);
            GL11.glVertex3f(0.4f, 0.05f, 0);  //drow y exis line from 0.4f
            
            GL11.glVertex3f(0.4f, 0.05f, 0);
            GL11.glVertex3f(0.5f, 0, 0);     //connect x and y exis endpoint
            
            GL11.glVertex3f(0.4f, -0.05f, 0);
            GL11.glVertex3f(0.5f, 0, 0);     //connect x and -y exis endpoint
        GL11.glEnd();
        GL11.glPopMatrix();
        //angle -=1; 
    }
    private void drowClock() {
        drawDial(0.5,120);
        drawDial(0.6,120);
        drawMarker(12,0.5,1); //Hour marker
        drawMarker(60,0.56,0.5f); //Minute 
        drawClockHand();
        
    }

    private void drawDial(double radius, double sides) {
        double theta = 2 * Math.PI / sides;
        double x;
        double y;
        double z = -0.1;
        GL11.glPushMatrix();
        GL11.glColor3f(1, 0, 0);

        GL11.glBegin(GL11.GL_LINES);
        for(int i=0; i<sides; i++ ){
            if(radius == 0.5)
            GL11.glVertex3d(0, 0, 0);
            x = radius * Math.cos(theta * i);
            y = radius * Math.sin(theta * i);
            GL11.glVertex3d(x, y, z);
            
            x = radius * Math.cos(theta * (i+1));
            y = radius * Math.sin(theta * (i+1));
            GL11.glVertex3d(x, y, z);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private void drawMarker(double sides,double innerRadius,float intensity) {
        double theta = 2 * Math.PI / sides;
        double x;
        double y;
        double z = -0.1;
        GL11.glPushMatrix();
        GL11.glColor3f(intensity, intensity, 0);

        GL11.glBegin(GL11.GL_LINES);
        double outerRadius = 0.6;
        for(int i=0; i<sides; i++ ){

            x = innerRadius * Math.cos(theta * i);
            y = innerRadius * Math.sin(theta * i);
            GL11.glVertex3d(x, y, z);
            
            x = outerRadius * Math.cos(theta * i);
            y = outerRadius * Math.sin(theta * i);
            GL11.glVertex3d(x, y, z);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
}
