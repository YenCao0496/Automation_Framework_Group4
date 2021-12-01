package core.utilities;

public class EvnConfig {
	 /**
     * Get Selenium Grid Hub URL.
     */
    private static final String GRID_HUB = System.getProperty("GRID_HUB", "");

    /**
     * Get Browser type to run the test
     */
    public static String getGridHub() {
        return GRID_HUB;
    }

    private static final String BROWSER = System.getProperty("browser", "");

    /**
     * Get Browser type to run the test
     */
    public static String getBrowser() {
        return BROWSER;
    }

}
