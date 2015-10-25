package UI_handler;

public class UIHandler {
	public static int EVENT_PIECE_SELECTED = 1001;
	eventCallBack myCallBack;
	
	public interface eventCallBack{
		public void onUICallBack(int msg);
	}
	
	public UIHandler(eventCallBack g){
		myCallBack = g;
		}
}
