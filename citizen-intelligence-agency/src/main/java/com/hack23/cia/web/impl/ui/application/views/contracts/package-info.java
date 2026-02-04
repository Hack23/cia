/**
 * View contracts package.
 * 
 * This package contains interfaces that define contracts for views.
 * By depending on interfaces instead of concrete implementations, we break
 * circular dependencies and enable proper layered architecture.
 * 
 * Architecture principle:
 * - Common infrastructure depends on contracts (interfaces)
 * - View implementations depend on contracts
 * - No cycles: contracts → (no dependencies), infrastructure → contracts, views → contracts
 */
package com.hack23.cia.web.impl.ui.application.views.contracts;
