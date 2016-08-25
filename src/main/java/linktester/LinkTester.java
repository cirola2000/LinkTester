/**
 * 
 */
package linktester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Ciro Baron Neto
 * 
 *         Aug 25, 2016
 */
public class LinkTester {

	List<Link> links = null;

	HashMap<String, LinkTesterThread> threads = new HashMap<>(); 
	
	int pause = 1000;

	int numberOfThreads = 10;

	/**
	 * Constructor for Class LinkTester
	 */
	public LinkTester(List<Link> links) {
		this.links = links;
	}

	public List<Link> testLinks() {
		
		// separate links by NS
		NSUtils nsUtils = new NSUtils();
		for (Link link : links) {
			String ns = nsUtils.getNS0(link.getLink());
			LinkTesterThread thread = threads.get(ns);
			if (thread == null) {
				List<Link> l = new ArrayList<>();
				l.add(link);
				threads.put(ns, new LinkTesterThread(l, pause));
			} else {
				thread.getLinks().add(link);
			}
		}
		
		
		// test links!
		ExecutorService ex = Executors.newFixedThreadPool(numberOfThreads);
		
		for(String ns:threads.keySet()){
			ex.submit(threads.get(ns));
		}
		
		// wait threads to finish
		try {
			ex.shutdown();
			ex.awaitTermination(1000, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// return tested links
		List<Link> returnList = new ArrayList<>();
		
		for(String ns:threads.keySet()){
			returnList.addAll(threads.get(ns).getLinks());
		}
		
		return returnList;
			
	}
	
	/**
	 * Set a pause for request in the same server
	 * @param pause time in ms
	 */
	public void setPause(int pause){
		this.pause = pause;
	}
	
	/**
	 * @param numberOfThreads 
	 * Set the numberOfThreads for parallel requests
	 */
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	

	public static void main(String[] args) {

		ArrayList<Link> lns = new ArrayList<>();
		lns.add(new Link("http://www.google.de/?q=oi", true));
		lns.add(new Link("http://www.google.de/?q=oiasd", false));
		lns.add(new Link("http://www.yahoo.com/bund/daas", true));
		lns.add(new Link("http://www.yahoo.com/?q=oiasd", true));
		lns.add(new Link("http://www.yahoo.com", true));

		LinkTester tester = new LinkTester(lns);
		tester.setPause(50);
		tester.setNumberOfThreads(50);
		
		List<Link> links  = tester.testLinks();
		for(Link link : links){
			System.out.println(link.getResponseCode());
		}
	}

}
