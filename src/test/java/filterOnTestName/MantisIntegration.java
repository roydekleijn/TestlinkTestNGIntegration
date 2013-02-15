package filterOnTestName;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.mantis.ta.MantisManager;
import org.mantis.ta.RequiredItemException;
import org.mantis.ta.beans.IssueData;
import org.mantis.ta.beans.ObjectRef;

public class MantisIntegration {
	private String url;
	private String user;
	private String password;
	private String projectName;

	public MantisIntegration(String url, String user, String password,
			String projectName) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.projectName = projectName;
	}

	public void createIssue(String summary, String description)
			throws RemoteException, ServiceException, RequiredItemException {
		MantisManager mgr = new MantisManager(
				"http://localhost/mantisbt/api/soap/mantisconnect.php",
				"administrator", "root");
		ObjectRef prjRef = new ObjectRef(null, "automation project");
		IssueData issueToCreate = new IssueData();
		issueToCreate.setSummary("issue summary");
		issueToCreate.setDescription("some description text");
		issueToCreate.setProject(prjRef);
		mgr.createIssue(issueToCreate);
	}
}