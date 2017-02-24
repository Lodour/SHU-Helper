package shuhelper.ui;

import javafx.concurrent.Task;

public class CJvalidate extends NewTask{
	
	private String CJ_validatePath;
	
	@Override
	protected Integer call() throws Exception {
		if (isCancelled()) 
		{  
            return 0;  
        }  
		CJ_validatePath = shuhelpapp.CJ.getCaptcha();
		return 1;
	}
	@Override
	public String GetPath() {
		return CJ_validatePath;
	}


}
