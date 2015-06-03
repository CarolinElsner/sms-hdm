package de.hdm.sms.shared;


import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.sms.shared.bo.*;

/** Synchrone Schnittstelle, die der Verwaltung dient und alle notwenidgen Methoden f�r die St�cklistenAdministration {@link St�cklistenAdministrationImpl} bereitstellt. ( FEHLT NOCH)

 * @author Dimitriu
  */

@RemoteServiceRelativePath("st�cklistenAdministration")

public interface St�cklistenAdministration extends RemoteService{

	public User getUserByEmail(String user);
	public List<User>getAllUser();
}
