import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryStringTest {

    @Test
    void shouldRetrieveQueryParameters(){
        QueryString queryString = new QueryString("Status=200");
        assertEquals("200", queryString.getParameter("Status"));
    }

    @Test
    void shouldRetrieveOtherQueryParameters(){
        QueryString queryString = new QueryString("Status=404");
        assertEquals("404", queryString.getParameter("Status"));
    }

    @Test
    void shouldRetrieveParameterByName(){
        QueryString queryString = new QueryString("text=hello");
        assertEquals(null, queryString.getParameter("Status"));
        assertEquals("hello", queryString.getParameter("text"));
    }

    @Test
    void shouldHandleMultipleParameters(){
        QueryString queryString = new QueryString("text=hello&status=200");
        assertEquals("200", queryString.getParameter("status"));
        assertEquals("hello", queryString.getParameter("text"));
    }





}
