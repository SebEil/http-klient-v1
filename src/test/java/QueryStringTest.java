import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryStringTest {

    @Test
    void shouldRetrieveQueryParameters(){
        QueryString queryString = new QueryString("Status=200");
        assertEquals("200", queryString.getParameter("Status"));
    }
}
