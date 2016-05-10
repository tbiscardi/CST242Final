package handling;

/**
 * This class contains enum Events that different views's buttons use to notify
 * the observer. Here is also where the default height and width of most stages
 * are set.
 * 
 * @author Tom Biscardi
 */
public class GlobalVariables {

	public enum Events {
		LOG_IN_BUTTON, SAIN_REPORT_BUTTON, WHAT_IF_BUTTON, SEARCH_STUDENT_BUTTON, CONFIRM_CHANGES, DO_WHAT_IF, BACK_BUTTON
	}

	public static final int width = 600, height = 400;

}
