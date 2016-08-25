/**
 * 
 */
package linktester;

/**
 * @author Ciro Baron Neto
 * 
 * Aug 25, 2016
 */
public class Link {
	
	private String link;
	
	private LinkStatus status;
	
	private int responseCode; 
	
	private String errorMsg;
	
	boolean followRedirects = false;
	
	/**
	 * Constructor for Class Link 
	 */
	public Link(String link) {
		setLink(link);
	}
	
	
	/**
	 * Constructor for Class Link 
	 */
	public Link(String link, Boolean followRedirects) {
		setLink(link);
		setFollowRedirects(followRedirects);
	}
	
	
	/**
	 * @param link 
	 * Set the link value.
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * @param status 
	 * Set the status value.
	 */
	public void setStatus(LinkStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the status
	 */
	public LinkStatus getStatus() {
		return status;
	}
	
	/**
	 * @param responseCode 
	 * Set the responseCode value.
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	
	/**
	 * @param errorMsg 
	 * Set the errorMsg value.
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * @param followRedirects 
	 * Set the followRedirects value.
	 */
	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}
	
	/**
	 * @return the followRedirects
	 */
	public boolean isFollowRedirects() {
		return followRedirects;
	}

}
