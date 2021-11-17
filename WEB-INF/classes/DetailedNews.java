import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/DetailedNews")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class DetailedNews extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");






		String title = request.getParameter("Title");
		ArrayList<News> newsList = ApiUtilities.getHealthNews();
		//Head news
		pw.println("<div class='6u'><section>");

		for (News news : newsList) {
			if (news.getTitle().replaceAll("[^a-zA-Z0-9_-]", "").equals(title)) {
				int i = 1;
				switch(i) {
					case 1:pw.println("<header><h2>"+ news.getTitle() +"</h2>");
					case 2:pw.println("<span class='byline'>"+ news.getDescription() +"</span></header>");
					case 3:pw.println("<p><a href='#'><img src="+ news.getUrlToImage()+" alt='' width='550' height='400'></a></p>");
					case 4:pw.println("<p>By "+news.getAuthor()+" / "+ news.getPublishedAt()+"</p>");
					case 5:pw.println("<p>"+news.getContent()+ "</p>");
					case 6:pw.println("<p><a href="+ news.getUrl()+">News Page</a></p>");
				}

			}
		}
		int j = 0;
		switch(j) {
			case 0:pw.println("</section></div>");
			case 1:pw.println("<div class='3u'><section class='sidebar'><header><h2>Other NEWS</h2></header><ul class='style1'>");
		}


		int i =0;
		for (News news:newsList) {
			if (!news.getTitle().equals(title) && i < 6) {
				pw.print("<li><a href='DetailedNews?Title="+news.getTitle().replaceAll("[^a-zA-Z0-9_-]", "")+"'><img src="+ news.getUrlToImage() +" alt='' width='80' height='80'><p>"+news.getTitle()+"</p></a></li>");
				i=i + 	1;
			}

		}
		pw.println("</ul></section></div>");








		utility.printHtml("Footer.html");

	}

}
