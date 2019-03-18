package com.hack23.cia.model.external.riksdagen.dokumentlista.impl;

import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

public final class DefaultStringIdentifier implements IdentifierBridge<String> {

	@Override
	public String cast(final Object arg0) {
		return (String) arg0;
	}

	@Override
	public String fromDocumentIdentifier(final String arg0, final IdentifierBridgeFromDocumentIdentifierContext arg1) {
		return arg0;
	}

	@Override
	public String toDocumentIdentifier(final String arg0, final IdentifierBridgeToDocumentIdentifierContext arg1) {
		return arg0;
	}
	
}