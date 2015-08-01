/*
 * Copyright 2014 James Pether Sörling
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.pageclicklistener;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenGovermentRoleMember;
import com.hack23.cia.web.impl.ui.application.views.common.AbstractPageItemRendererClickListener;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;

/**
 * The listener interface for receiving govermentRoleMemberPageItemClick events.
 * The class that is interested in processing a govermentRoleMemberPageItemClick
 * event implements this interface, and the object created with that class is
 * registered with a component using the component's
 * <code>addGovermentRoleMemberPageItemClickListener</code> method. When
 * the govermentRoleMemberPageItemClick event occurs, that object's appropriate
 * method is invoked.
 *
 */
public final class GovermentRoleMemberPageItemClickListener extends
AbstractPageItemRendererClickListener<ViewRiksdagenGovermentRoleMember> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new goverment role member page item click listener.
	 */
	public GovermentRoleMemberPageItemClickListener() {
		super(UserViews.POLITICIAN_VIEW_NAME);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.web.impl.ui.application.views.common.
	 * AbstractPageItemRendererClickListener#getPageId(java.lang.Object)
	 */
	@Override
	protected String getPageId(final ViewRiksdagenGovermentRoleMember t) {
		return t.getPersonId();
	}

}
