package shuhelper.ui;

public class XKlogintask extends NewTask{
	private String res;
	private String username = LoginFrame.username;
	private String password = LoginFrame.password;
	//ÑéÖ¤Âë
	private String XK_identify;
	
	public XKlogintask(String username, String password, String XK_identify)
	{
		this.username = username;
		this.password = password;
		this.XK_identify = XK_identify;
	}
	
	@Override
	public String GetPath() {
		return res;
	}

	@Override
	protected Object call() throws Exception {
		res = shuhelpapp.XK.login(username, password, XK_identify);
		return 1;
	}
	

}
