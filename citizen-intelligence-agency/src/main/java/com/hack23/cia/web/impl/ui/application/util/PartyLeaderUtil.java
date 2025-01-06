package com.hack23.cia.web.impl.ui.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hack23.cia.model.internal.application.data.impl.ViewRiksdagenPartyRoleMember;
import com.hack23.cia.model.internal.application.data.impl.ViewRiksdagenPartyRoleMember_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.api.ApplicationManager;

public final class PartyLeaderUtil {

    private PartyLeaderUtil() {
        // Utility class, no instantiation
    }

    public static boolean isPartyLeader(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container = 
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .anyMatch(r -> r.getRoleCode() != null 
                    && "Partiledare".equalsIgnoreCase(r.getRoleCode().trim()));
    }

    public static ViewRiksdagenPartyRoleMember getPartyLeaderRole(ApplicationManager applicationManager, String personId) {
        final DataContainer<ViewRiksdagenPartyRoleMember, String> container = 
            applicationManager.getDataContainer(ViewRiksdagenPartyRoleMember.class);
        final List<ViewRiksdagenPartyRoleMember> roles =
            container.findListByProperty(new Object[] { personId, Boolean.TRUE },
                    ViewRiksdagenPartyRoleMember_.personId,
                    ViewRiksdagenPartyRoleMember_.active);

        return roles.stream()
                .filter(r -> r.getRoleCode() != null 
                    && "Partiledare".equalsIgnoreCase(r.getRoleCode().trim()))
                .findFirst()
                .orElse(null);
    }

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
                        && "Partiledare".equalsIgnoreCase(r.getRoleCode().trim()));
            result.put(personId, isLeader);
        }
        return result;
    }
}
