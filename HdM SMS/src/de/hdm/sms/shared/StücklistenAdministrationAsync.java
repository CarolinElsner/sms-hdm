package de.hdm.sms.shared;

/**
 * Das asynchrone Interface {@link St�cklistenAdministration}.
 * 
 * @author Thies
 * @author Dimitriu
 */

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.sms.shared.bo.*;

public interface St�cklistenAdministrationAsync {

	void getUserByEmail(String u, AsyncCallback<User>callback);
	void getAllUser(AsyncCallback<List<User>>callback);
}
