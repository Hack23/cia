package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

/**
 * Constants for the user home view pages.
 */
public interface UserHomeViewConstants {

    // Common View Title
    String TITLE_PREFIX = "CitizenIntelligence Agency::UserHome::";

    // Overview Page
    String OVERVIEW_TITLE = "Overview";
    String OVERVIEW_DESC = "Manage user and security settings";

    // Security Settings Page
    String SECURITY_SETTINGS_TITLE = "Security Settings";
    String SECURITY_SETTINGS_DESC = "Manage user security settings";

    // User Events Page
    String USER_EVENTS_TITLE = "User Events";
    String USER_EVENTS_DESC = "All past user events";

    // User Visits Page
    String USER_VISITS_TITLE = "User Visits";
    String USER_VISITS_DESC = "All past user visits";

    // Button Labels
    String BUTTON_LOGOUT = "Logout";
    String BUTTON_DELETE_ACCOUNT = "Delete Account";
    String BUTTON_ENABLE_GOOGLE_AUTH = "Enable Google Authenticator";
    String BUTTON_DISABLE_GOOGLE_AUTH = "Disable Google Authenticator";
    String BUTTON_CHANGE_PASSWORD = "Change password";

    // Profile Section Labels
    String PROFILE_SECTION_TITLE = "Profile Details";
    String STATUS_SECTION_TITLE = "Status & Statistics";

    // Profile Field Labels
    String USERNAME_LABEL = "Username:";
    String EMAIL_LABEL = "Email:";
    String COUNTRY_LABEL = "Country:";
    String CREATED_DATE_LABEL = "Created Date:";
    String USER_TYPE_LABEL = "User Type:";
    String USER_ROLE_LABEL = "User Role:";
    String EMAIL_STATUS_LABEL = "Email Status:";
    String VISITS_LABEL = "Number of Visits:";

    // Profile Field Tooltips
    String USERNAME_DESC = "Your unique username";
    String EMAIL_DESC = "Your registered email address";
    String COUNTRY_DESC = "Country of residence";
    String CREATED_DATE_DESC = "Date when the account was created";
    String USER_TYPE_DESC = "Type of user account";
    String USER_ROLE_DESC = "Your assigned role in the system";
    String EMAIL_STATUS_DESC = "Status of email verification";
    String VISITS_DESC = "How many times you have visited";

    // Form Labels
    String LABEL_CHANGE_PASSWORD = "Change password";
    String LABEL_ENABLE_MFA = "Enable MFA using google authenticator";
    String LABEL_DISABLE_MFA = "Disable MFA using google authenticator";
    String LABEL_DELETE_ACCOUNT = "Delete Account";
}

