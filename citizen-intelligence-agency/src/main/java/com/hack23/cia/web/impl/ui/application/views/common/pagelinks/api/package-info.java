/**
 * This package provides classes and interfaces for creating and managing page links within the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - PageLinkFactory: Interface for creating page link objects.
 * - PageModeMenuCommand: Command for handling page mode menu actions.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.model.external.riksdagen.person.impl for PersonData.
 * - Depends on com.hack23.cia.model.internal.application.data.committee.impl for ViewRiksdagenCommittee.
 * - Depends on com.hack23.cia.model.internal.application.data.ministry.impl for ViewRiksdagenMinistry.
 * - Depends on com.hack23.cia.model.internal.application.data.party.impl for ViewRiksdagenParty.
 * - Depends on com.hack23.cia.model.internal.application.data.politician.impl for ViewRiksdagenPolitician.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for various view names and page modes.
 * - Depends on com.vaadin.ui for UI components.
 *
 * The package is responsible for creating and managing page links, handling page mode menu actions, and interacting with other components of the application to ensure proper functionality. The key classes and interfaces within this package provide the necessary structure and implementation for these actions. The package also has dependencies on other packages for specific functionalities, such as person data, committee data, ministry data, party data, politician data, view names, and UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api;
