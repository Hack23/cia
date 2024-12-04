/**
 * This package provides classes and interfaces for managing page modes within document views.
 *
 * Key classes and interfaces:
 * - AbstractDocumentPageModContentFactoryImpl: Abstract base class for document page mode content factories.
 * - AbstractDocumentsPageModContentFactoryImpl: Abstract base class for documents page mode content factories.
 * - DocumentActivityPageModContentFactoryImpl: Implementation of the document activity page mode content factory.
 * - DocumentAttachementsPageModContentFactoryImpl: Implementation of the document attachments page mode content factory.
 * - DocumentDataPageModContentFactoryImpl: Implementation of the document data page mode content factory.
 * - DocumentDecisionPageModContentFactoryImpl: Implementation of the document decision page mode content factory.
 * - DocumentDetailsPageModContentFactoryImpl: Implementation of the document details page mode content factory.
 * - DocumentOverviewPageModContentFactoryImpl: Implementation of the document overview page mode content factory.
 * - DocumentPageVisitHistoryPageModContentFactoryImpl: Implementation of the document page visit history page mode content factory.
 * - DocumentPersonReferencesPageModContentFactoryImpl: Implementation of the document person references page mode content factory.
 * - DocumentReferencesPageModContentFactoryImpl: Implementation of the document references page mode content factory.
 * - DocumentsOverviewPageModContentFactoryImpl: Implementation of the documents overview page mode content factory.
 * - SearchDocumentPageModContentFactoryImpl: Implementation of the search document page mode content factory.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.pagemode for AbstractBasicPageModContentFactoryImpl.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.viewnames for UserViews and DocumentPageMode.
 * - Depends on com.hack23.cia.model.external.riksdagen.dokumentstatus.impl for DocumentData and related entities.
 * - Depends on com.hack23.cia.service.api for data container and application manager services.
 * - Depends on org.springframework.security.access.annotation for security annotations.
 * - Depends on org.springframework.stereotype for component annotations.
 * - Depends on com.vaadin.ui for UI components.
 */
package com.hack23.cia.web.impl.ui.application.views.user.document.pagemode;