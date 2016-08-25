/**
 * 
 */
package linktester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ciro Baron Neto
 * 
 *         Aug 25, 2016
 */
public class LinkTesterThread implements Runnable {

	final static Logger logger = LoggerFactory.getLogger(LinkTesterThread.class);

	private List<Link> links = null;
	
	private int pause = 100;

	/**
	 * Constructor for Class LinkTesterThread
	 */
	public LinkTesterThread(List<Link> links, int pause) {
		this.links = links;
	}

	/**
	 * Constructor for Class LinkTesterThread - **PRIVATE** Don't let users
	 * instantiate this.
	 */
	private LinkTesterThread() {
	}

	/**
	 * @return the link
	 */
	public List<Link> getLinks() {
		return links;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		for (Link l : links)
			testLink(l, l.getLink(), null);
	}

	private void testLink(Link link, String linkURL, String cookies) {

		try {
			Thread.sleep(pause);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// check if the link has already been processed
		if (link.getStatus() == LinkStatus.WORKS) {
			logger.debug("Link " + link.getLink() + " has already processed.");
			return;
		}

		// Connect and get response code
		ConnectionService connService = new ConnectionService();
		try {
			if (cookies == null)
				connService.openConnection(linkURL);
			else
				connService.openConnection(linkURL, cookies);

		} catch (IOException e) {
			link.setErrorMsg(e.getMessage());
			link.setStatus(LinkStatus.ERROR);
			logger.error(link.getStatus().toString());
			return;
		}
		int responseCode = connService.getResponseCode();
		
		// if server response is 3xx (redirect)
		if (link.isFollowRedirects())
			if (isRedirect(responseCode)) {
				testLink(link, connService.getHttpConnection().getHeaderField("Location"),
						connService.getHttpConnection().getHeaderField("Set-Cookie"));
			}
		link.setStatus(LinkStatus.WORKS);
		logger.debug("Status: " + link.getStatus().toString() + " Link: " + link.getLink());
		link.setResponseCode(responseCode);

	}

	private boolean isRedirect(int responseCode) {
		if (responseCode != HttpURLConnection.HTTP_OK) {
			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM
					|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
				return true;
		}
		return false;
	}

}
