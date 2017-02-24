package shuhelper.ui;

public class CJlogintask extends NewTask{
	private String res;
	private String username = LoginFrame.username;
	private String password = LoginFrame.password;
	//ÑéÖ¤Âë
	private String CJ_identify;
	
	public CJlogintask(String username, String password, String CJ_identify)
	{
		this.username = username;
		this.password = password;
		this.CJ_identify = CJ_identify;
	}
	
	@Override
	public String GetPath() {
		return res;
	}

	@Override
	protected Object call() throws Exception {
		res = shuhelpapp.CJ.login(username, password, CJ_identify);
		return 1;
	}
	

}
