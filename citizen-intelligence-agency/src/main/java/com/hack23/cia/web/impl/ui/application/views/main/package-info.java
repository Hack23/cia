/**
 * Main application entry views package.
 * 
 * This package contains the main entry point views for the application (MainView, DashboardView).
 * These views were moved from common to break the circular dependency between application and views.common.
 * 
 * Architecture: main views depend on common infrastructure, but common infrastructure does not depend on main views.
 */
package com.hack23.cia.web.impl.ui.application.views.main;
