package com.hack23.cia.web.impl.ui.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.ApplicationManager;

/**
 * Utility class for handling party leader related operations.
 */
public final class PartyLeaderUtil {

    private static final String ROLE_CODE_PARTILEDARE = "Partiledare";
    private static final Object[] ACTIVE_PERSON_ID = new Object[] { Boolean.TRUE };

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
    public static boolean isPartyLeader(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container = 
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .anyMatch(r -> r.getRoleCode() != null 
                    && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()));
    }

    /**
     * Fetches the party leader role for a person.
     *
     * @param applicationManager the application manager
     * @param personId the person id
     * @return the party leader role, or null if not found
     */
    public static ViewRiksdagenPartyRoleMember getPartyLeaderRole(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container = 
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .filter(r -> r.getRoleCode() != null 
                    && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()))
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
    public static Map<String, Boolean> computePartyLeaders(ApplicationManager applicationManager, Iterable<String> personIds) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container = 
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);

        final Map<String, Boolean> result = new HashMap<>();
        for (String personId : personIds) {
            final List<ViewRiksdagenPartyRoleMember> roles =
                container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                        ViewRiksdagenPartyRoleMember_.personId,
                        ViewRiksdagenPartyRoleMember_.active);

            final boolean isLeader = roles.stream()
                    .anyMatch(r -> r.getRoleCode() != null 
                        && ROLE_CODE_PARTILEDARE.equalsIgnoreCase(r.getRoleCode().trim()));
            result.put(personId, isLeader);
        }
        return result;
    }
}
