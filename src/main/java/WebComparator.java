import java.util.Comparator;

public class WebComparator implements Comparator<WebNode> {

	public int compare(WebNode n1, WebNode n2) {
		if (n1 == null || n2 == null) throw new NullPointerException();
		return (int) (n2.nodeScore - n1.nodeScore);
	}

}
