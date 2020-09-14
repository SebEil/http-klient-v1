public class QueryString {
    public final String value;

    public QueryString(String queryString) {
        int equalPos = queryString.indexOf('=');
        value = queryString.substring(equalPos+1);
    }

    public String getParameter(String name) {
        return value;
    }
}
