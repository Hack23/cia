/**
 * This package provides classes and interfaces for handling various actions within the Citizen Intelligence Agency web application.
 *
 * Key classes and interfaces:
 * - PageActionEventHelper: Interface for creating page events.
 * - PageActionEventHelperImpl: Implementation of the PageActionEventHelper interface.
 * - ViewAction: Enum representing different view actions.
 *
 * Dependencies and relationships:
 * - Depends on com.hack23.cia.model.internal.application.system.impl for ApplicationEventGroup and ApplicationOperationType.
 * - Depends on com.hack23.cia.service.api for ApplicationManager and CreateApplicationEventRequest.
 * - Depends on com.hack23.cia.web.impl.ui.application.views.common.util for UserContextUtil.
 *
 * The package is responsible for creating and managing page events, handling different view actions, and interacting with other components of the application to ensure proper functionality. The key classes and interfaces within this package provide the necessary structure and implementation for these actions. The package also has dependencies on other packages for specific functionalities, such as application event groups, application operation types, application management, and user context utilities.
 */
package com.hack23.cia.web.impl.ui.application.views.common.action;
