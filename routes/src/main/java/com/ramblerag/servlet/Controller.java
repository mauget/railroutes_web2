package com.ramblerag.servlet;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ramblerag.db.route.RouterService;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet  {
	
	private static final String PARAM_NODE_B = "nodeB";

	private static final String PARAM_NODE_A = "nodeA";

	private static final String MIME_APPLICATION_VND_GOOGLE_EARTH_KML_XML = "application/vnd.google-earth.kml+xml";

	private static final long serialVersionUID = 1L;
	
	private static final String ROUTER_REF = "router";

	private static Logger log = Logger.getLogger(Controller.class);
	
	private RouterService router;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		shortestPath(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		shortestPath(request, response);
	}
	
	private void shortestPath(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get endpoints from params, pass to findRount.
		String nodeAStr = request.getParameter(PARAM_NODE_A);
		String nodeBStr =request.getParameter(PARAM_NODE_B);
		
		// TODO catch nulls and parse issues, do something about them (although db just sits there harmlessly if bad input)
		long nodeA = Long.parseLong(nodeAStr);
		long nodeB = Long.parseLong(nodeBStr);

		response.setContentType(MIME_APPLICATION_VND_GOOGLE_EARTH_KML_XML);
		PrintStream ps = new PrintStream(response.getOutputStream() );
		log.info("Finding route");
		router.findShortestPath(ps, nodeA, nodeB);
	}

	@Override
	public void init() throws ServletException {
		setRouter((RouterService) Initializer.getApplicationContext().getBean(ROUTER_REF));
		super.init();
	}

	public void setRouter(RouterService router) {
		this.router = router;
	}

}
