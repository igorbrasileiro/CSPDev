package br.ufcg.edu.csp.jetty;

import java.io.File;
import java.io.IOException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class FileServer {

	String htmlPagePath;
	String directory;

	public FileServer(String directory, String htmlPagePath) {
		this.htmlPagePath = htmlPagePath;
		this.directory = directory;
	}

	public void startServer() {
		Server server = new Server(8080);

		// Create the ResourceHandler. It is the object that will actually handle the
		// request for a given file. It is
		// a Jetty Handler object so it is suitable for chaining with other handlers as
		// you will see in other examples.
		ResourceHandler resource_handler = new ResourceHandler();

		// Configure the ResourceHandler. Setting the resource base indicates where the
		// files should be served out of.
		// In this example it is the current directory but it can be configured to
		// anything that the jvm has access to.
		resource_handler.setDirectoriesListed(true);

		resource_handler.setWelcomeFiles(new String[] { this.htmlPagePath });
		//resource_handler.setResourceBase(".");
		resource_handler.setResourceBase(this.directory);

		// Add the ResourceHandler to the server.
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
		server.setHandler(handlers.getHandlers()[0]);

		// Start things up! By using the server.join() the server thread will join with
		// the current thread.
		// See
		// "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()"
		// for more details.
		try {
				server.start();
				//server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
