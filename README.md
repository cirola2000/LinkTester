# LinkTester
A library to test a set of links over the internet


##Simple example:
The following example creates a list of links which will be tested over the internet. 

```
	public static void main(String[] args) {

		ArrayList<Link> lns = new ArrayList<>();
		lns.add(new Link("http://www.google.de/?q=oi", true));
		lns.add(new Link("http://www.google.de/?q=oiasd", false));
		lns.add(new Link("http://www.yahoo.com/bund/daas", true));
		lns.add(new Link("http://www.yahoo.com/?q=oiasd", true));
		lns.add(new Link("http://www.yahoo.com", true));

		LinkTester tester = new LinkTester(lns);
		
		// we should set an interval for queries requested to the same server, otherwise we will be dinied
		tester.setPause(50);
		
		// number of threads to test links in parallel
		tester.setNumberOfThreads(50);
		
		List<Link> links  = tester.testLinks();
		
		// show the HTTP response code for the links 
		for(Link link : links){
			System.out.println(link.getResponseCode());
		}
	}
```
