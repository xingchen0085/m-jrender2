package com.adiosy.mjrender;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.Configuration;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL42.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * 启动入口
 * @author cxh
 * @since 2022/10/17
 */
public class App {

    private long window;

    public static void main(String[] args) {
        System.out.println("hello mjrender2");

        Configuration.DEBUG.set(true);
        Configuration.DEBUG_STACK.set(true);
        Configuration.DEBUG_MEMORY_ALLOCATOR.set(true);

        new App().run();
    }

    public void run(){
        init();
        try {
            loop();
        } finally {
            glfwSetErrorCallback(null).free();
            glfwDestroyWindow(window);
            glfwTerminate();
        }
    }

    public void init(){
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

        if(!glfwInit()) throw new IllegalStateException("failed to init glfw.");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(1024, 768, "hello mrender", NULL, NULL);
        if(window == NULL) throw new IllegalStateException("failed to create glfw window.");

        glfwMakeContextCurrent(window);
        //creates the GLCapabilities instance and makes the OpenGL bindings available for use
        GL.createCapabilities();
    }

    public void loop(){
        glClearColor(0, 0, 0, 1);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            glfwPollEvents();
            glfwSwapBuffers(window);
        }
    }
}
