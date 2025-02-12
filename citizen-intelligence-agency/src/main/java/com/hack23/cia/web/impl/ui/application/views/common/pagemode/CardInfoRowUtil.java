package com.hack23.cia.web.impl.ui.application.views.common.pagemode;

import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class CardInfoRowUtil.
 */
public class CardInfoRowUtil {

	/** The Constant CARD_TENURE. */
	public static final String CARD_TENURE = "card-tenure";

	/** The Constant CARD_EXPERIENCE. */
	public static final String CARD_EXPERIENCE = "card-experience-section";

	/** The Constant CARD_INFO_VALUE. */
	public static final String CARD_INFO_VALUE = "card-info-value";

	/** The Constant CARD_INFO_ICON. */
	public static final String CARD_INFO_ICON = "card-info-icon";

	/** The Constant METRIC_LABEL. */
	public static final String METRIC_LABEL = "metric-label";

	/**
	 * Creates the page header.
	 *
	 * @param panel           the panel
	 * @param panelContent    the panel content
	 * @param header          the header
	 * @param pageHeader      the page header
	 * @param pageDescription the page description
	 */
	public static final void createPageHeader(final Panel panel, final VerticalLayout panelContent, final String header,
			final String pageHeader, final String pageDescription) {
		panel.setCaption(header);
		LabelFactory.createHeader2Label(panelContent, pageHeader);
		final Label descriptionLabel = new Label(pageDescription);
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		panelContent.addComponent(descriptionLabel);
		panelContent.setExpandRatio(descriptionLabel, ContentRatio.SMALL);
	}

	/**
	 * Adds an info row to the parent layout if value is not null or empty.
	 *
	 * @param parent  the parent layout
	 * @param caption the caption
	 * @param value   the value
	 * @param icon    the icon
	 */
	public static final void addInfoRowIfNotNull(final VerticalLayout parent, final String caption, final String value,
			final VaadinIcons icon) {
		if (value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value)) {
			parent.addComponent(CardInfoRowUtil.createInfoRow(caption, value, icon));
		}
	}

	/**
	 * Creates a simple info row (caption and value) with optional icon.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon
	 * @return a HorizontalLayout representing the info row
	 */
	public static final HorizontalLayout createInfoRow(final String caption, final String value,
			final VaadinIcons icon) {
		return CardInfoRowUtil.createInfoRow(caption, value, icon, null);
	}

	/**
	 * Creates a row displaying a caption and value, with optional icon and tooltip.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon for better visual cue
	 * @param tooltip optional tooltip to provide more info
	 * @return a HorizontalLayout representing the info row
	 */
	public static final HorizontalLayout createInfoRow(final String caption, final String value, final VaadinIcons icon,
			final String tooltip) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		if (icon != null) {
			final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("card-info-icon");
			if (tooltip != null && !tooltip.isEmpty()) {
				iconLabel.setDescription(tooltip);
			}
			layout.addComponent(iconLabel);
		}

		final Label captionLabel = new Label(caption);
		captionLabel.addStyleName("card-info-caption");
		if (tooltip != null && !tooltip.isEmpty()) {
			captionLabel.setDescription(tooltip);
		}

		final Label valueLabel = new Label(value != null ? value : "");
		valueLabel.addStyleName("card-info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
	}

	/**
	 * Creates a section layout with a title and consistent styling.
	 *
	 * @param title the section title
	 * @return the vertical layout configured for the section
	 */
	public static VerticalLayout createSectionLayout(final String title) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addStyleName("card-details-column");
		layout.setWidth("100%");

		final Label header = new Label(title);
		header.addStyleName("card-section-title");
		layout.addComponent(header);

		// Add some vertical padding after the header
		final Label padding = new Label();
		padding.setHeight("10px");
		layout.addComponent(padding);

		return layout;
	}

	/**
	 * Creates the metric row.
	 *
	 * @param icon          the icon
	 * @param linkComponent the link component
	 * @param description   the description
	 * @param valueText     the value text
	 * @return the horizontal layout
	 */
	public static final HorizontalLayout createMetricRow(final VaadinIcons icon,
			final com.vaadin.ui.Component linkComponent, final String description, final String valueText) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
		iconLabel.setDescription(description);

		// Value displayed outside of the link
		Label valueLabel = null;
		if (valueText != null && !valueText.isEmpty()) {
			valueLabel = new Label(valueText);
			valueLabel.addStyleName("metric-value");
		}

		layout.addComponent(iconLabel);
		layout.addComponent(linkComponent);
		if (valueLabel != null) {
			layout.addComponent(valueLabel);
		}

		return layout;
	}

	/**
	 * Creates the card header.
	 *
	 * @param cardContent the card content
	 * @param titleText   the title text
	 */
	public static final void createCardHeader(final VerticalLayout cardContent, final String titleText) {
		// Card Header
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.setWidth("100%");
		headerLayout.addStyleName("card-header-section");

		final Label titleLabel = new Label(titleText, ContentMode.HTML);
		titleLabel.addStyleName("card-title");
		titleLabel.setWidthUndefined();
		headerLayout.addComponent(titleLabel);

		cardContent.addComponent(headerLayout);

		// Divider line
		final Label divider = new Label("<hr/>", ContentMode.HTML);
		divider.addStyleName("card-divider");
		divider.setWidth("100%");
		cardContent.addComponent(divider);
	}

	/**
	 * Creates a standard stats container.
	 *
	 * @return the vertical layout configured for the stats container
	 */
	public static final VerticalLayout createStatsContainer() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(false);
		layout.addStyleName("card-stats-container");
		layout.setWidth("100%");
		return layout;
	}

	/**
	 * Creates the standard row.
	 *
	 * @return the horizontal layout
	 */
	public static HorizontalLayout createStandardRow() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidthUndefined();
		return layout;
	}

	/**
	 * Creates the icon label.
	 *
	 * @param icon    the icon
	 * @param tooltip the tooltip
	 * @return the label
	 */
	public static Label createIconLabel(final VaadinIcons icon, final String tooltip) {
		final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
		iconLabel.addStyleName(CARD_INFO_ICON);
		if (tooltip != null) {
			iconLabel.setDescription(tooltip);
		}
		return iconLabel;
	}

}
