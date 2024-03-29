package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.ConnectionManager;

public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ConnectionManager.close();
    	
    }

    public void contextInitialized(ServletContextEvent event)  {
    	System.out.println("Initialization...");

    	ConnectionManager.open();

		System.out.println("Done!");
    }

}
