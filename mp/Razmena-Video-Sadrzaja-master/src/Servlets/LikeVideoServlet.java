package Servlets;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import DAO.ConnectionMenager;






public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ConnectionMenager.close();
    	
    }

    public void contextInitialized(ServletContextEvent event)  {
    	System.out.println("inicijalizacija...");

    	ConnectionMenager.open();

		System.out.println("zavrseno!");
    }

}

