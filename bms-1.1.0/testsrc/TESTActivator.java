import static org.junit.Assert.*;

import org.eclipse.core.internal.content.Activator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;



public class TESTActivator {
	@SuppressWarnings("restriction")
	@Test public void TESTgetDefault() {
		Activator pn;
		pn=Activator.getDefault();
		assertNotNull(pn);
		
	}
}
