package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.service.api.ApplicationManager;

/**
 * Utility class for handling party leader related operations.
 */
public final class PartyLeaderUtil {

    /** The Constant ROLE_CODE_PARTILEDARE. */
    private static final String ROLE_CODE_PARTILEDARE = "Partiledare";

    /**
     * Instantiates a new party leader util.
     */
    private PartyLeaderUtil() {
        // Utility class, no instantiation
    }

    /**
     * Checks if a person is a party leader.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return true, if the person is a party leader
     */
    public static boolean isPartyLeader(final ApplicationManager applicationManager, final String personId) {
        return getActiveRoles(applicationManager, personId).stream()
                .anyMatch(PartyLeaderUtil::isPartyLeaderRole);
    }

    /**
     * Fetches the party leader role for a person.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return the party leader role, or null if not found
     */
    public static ViewRiksdagenPartyRoleMember getPartyLeaderRole(final ApplicationManager applicationManager, final String personId) {
        return getActiveRoles(applicationManager, personId).stream()
                .filter(PartyLeaderUtil::isPartyLeaderRole)
                .findFirst()
                .orElse(null);
    }

    /**
     * Computes party leaders for a list of person IDs.
     *
     * @param applicationManager the application manager
     * @param personIds the person ids
     * @return a map of person ids to boolean indicating if they are party leaders
     */
    public static Map<String, Boolean> computePartyLeaders(final ApplicationManager applicationManager, final Iterable<String> personIds) {
        final Map<String, Boolean> result = new HashMap<>();
        for (final String personId : personIds) {
            result.put(personId, isPartyLeader(applicationManager, personId));
        }
        return result;
    }

    /**
     * Gets the active roles.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return the active roles
     */
    @SuppressWarnings("unchecked")
    private static List<ViewRiksdagenPartyRoleMember> getActiveRoles(final ApplicationManager applicationManager, final String personId) {
        return applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class)
                .findListByProperty(
                    new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active
                );
    }

    /**
     * Checks if is party leader role.
     *
     * @param role the role
     * @return true, if is party leader role
     */
    private static boolean isPartyLeaderRole(final ViewRiksdagenPartyRoleMember role) {
        return role.getRoleCode() != null && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(role.getRoleCode().trim());
    }
}
