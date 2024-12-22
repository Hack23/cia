/**
 * This package contains implementations for handling country data from the World Bank.
 *
 * Key classes:
 * - Adminregion: Represents an administrative region.
 * - AdminRegionCategory: Enum representing different categories of administrative regions.
 * - CountriesElement: Represents a collection of countries.
 * - CountryElement: Represents a country.
 * - IncomeLevel: Represents the income level of a country.
 * - IncomeLevelCategory: Enum representing different categories of income levels.
 * - LendingType: Represents the lending type of a country.
 * - LendingTypeCategory: Enum representing different categories of lending types.
 * - Region: Represents a region.
 * - RegionCategory: Enum representing different categories of regions.
 * - ObjectFactory: Factory class for creating instances of the model classes.
 *
 * Annotations:
 * - @XmlSchema: Defines the XML namespace and element form default for the package.
 */
@javax.xml.bind.annotation.XmlSchema(namespace = "http://countries.worldbank.external.model.cia.hack23.com/impl", elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package com.hack23.cia.model.external.worldbank.countries.impl;
