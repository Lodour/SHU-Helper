package shuhelper.ui;

public class ChoseFrameTask extends NewTask
{

	private String[] termInfo;
	
	@Override
	public String[] GetPath() {
		// TODO Auto-generated method stub
		return termInfo;
	}

	@Override
	protected Object call() throws Exception {
		termInfo = shuhelpapp.XK.getTermInfo();
		return null;
	}

}
