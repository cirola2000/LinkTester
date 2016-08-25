package linktester;

public class NSUtils {

	public String getNS0(String url) {
		String[] split = url.split("/");
		if (split.length > 3)
			url = split[0] + "//" + split[2] + "/";
		else if (!url.endsWith("/"))
			url = url + "/";
		return url;
	}
}
