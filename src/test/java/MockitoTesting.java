import static org.mockito.Mockito.*;
import org.junit.Assert;
import org.junit.Test;
import ru.reksoft.lab.service.ContactManager;

/**
 * Created by mishanin on 06.05.2016.
 */
public class MockitoTesting extends Assert {
    private ContactManager cm;

    @Test
    public void testMockGetSizeOfBook(){
        cm = mock(ContactManager.class);
        when(cm.getCurrentSizeOfBook()).thenReturn(5);
        assertEquals(5,cm.getCurrentSizeOfBook());
    }

}
