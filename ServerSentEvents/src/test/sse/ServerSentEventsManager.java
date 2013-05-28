package test.sse;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServerSentEventsManager extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/event-stream; charset=utf-8");

			PrintWriter pw = response.getWriter();
			pw.write("event: server-time\n\n");
			pw.write("retry: 1000\n\n");
			pw.write("data: " + new Date() + "\n\n");
			pw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}