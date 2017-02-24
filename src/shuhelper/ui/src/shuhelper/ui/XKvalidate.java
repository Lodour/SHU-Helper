package shuhelper.ui;

import javafx.concurrent.Task;

public class XKvalidate extends NewTask{
	
	private String XK_validatePath;
	
	@Override
	protected Integer call() throws Exception {
		if (isCancelled()) 
		{  
            return 0;  
        }  
		XK_validatePath = shuhelpapp.XK.getCaptcha();
		return 1;
	}
	@Override
	public String GetPath() {
		return XK_validatePath;
	}


}
