package com.fin.bancs.constants;

public class AppConstants {

    // General constants
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String NOT_FOUND = "NOT_FOUND";

    // API URLs
    public static final String BASE_API_URL = "/api/v1";
    public static final String ACCOUNT_API_URL = "/api/v1/accounts";
    public static final String NOTIFICATION_API_URL = "/api/v1/notifications";

    // Messages
    public static final String ACCOUNT_CREATED = "Account created successfully.";
    public static final String ACCOUNT_UPDATED = "Account updated successfully.";
    public static final String ACCOUNT_DELETED = "Account deleted successfully.";

    // Numeric Constants
    public static final int MAX_RETRY_COUNT = 3;
    public static final long TIMEOUT_IN_MS = 5000L;

    public static class  FREQUENCY {
        public static final int DAY = 1;
        public static final int MONTH = 1;
        public static final int YEAR = 1;
    }

    private AppConstants() {
        // private constructor to prevent instantiation
    }
}
