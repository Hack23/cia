/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hack23.cia.testfoundation.AbstractUnitTest;

/**
 * The Class RemoveDataManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public final class RemoveDataManagerTest extends AbstractUnitTest {

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private Statement statement;

	@Mock
	private PreparedStatement preparedStatement;

	/**
	 * Removes the person data test.
	 * @throws SQLException
	 */
	@Test
	public void removePersonDataTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(statement);
		new RemoveDataManagerImpl(dataSource).removePersonData();
		final ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(statement,Mockito.times(5)).execute(argCaptor.capture());

	}

	/**
	 * Removes the committee proposals test.
	 * @throws SQLException
	 */
	@Test
	public void removeCommitteeProposalsTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(statement);
		new RemoveDataManagerImpl(dataSource).removeCommitteeProposals();
		final ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(statement,Mockito.times(6)).execute(argCaptor.capture());
	}

	/**
	 * Removes the document status test.
	 * @throws SQLException
	 */
	@Test
	public void removeDocumentStatusTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(statement);
		new RemoveDataManagerImpl(dataSource).removeDocumentStatus();
		final ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(statement,Mockito.times(15)).execute(argCaptor.capture());
	}

	/**
	 * Removes the documents test.
	 * @throws SQLException
	 */
	@Test
	public void removeDocumentsTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(statement);
		new RemoveDataManagerImpl(dataSource).removeDocuments();
		final ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(statement,Mockito.times(2)).execute(argCaptor.capture());
	}

	/**
	 * Removes the application history test.
	 * @throws SQLException
	 */
	@Test
	public void removeApplicationHistoryTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		Mockito.when(connection.createStatement()).thenReturn(statement);
		new RemoveDataManagerImpl(dataSource).removeApplicationHistory();
		final ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.verify(statement,Mockito.times(2)).execute(argCaptor.capture());
	}

	/**
	 * Removes the user account application history test.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	@Test
	public void removeUserAccountApplicationHistoryTest() throws SQLException {
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		final ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
		Mockito.when(connection.prepareStatement(sqlCaptor.capture())).thenReturn(preparedStatement);
		new RemoveDataManagerImpl(dataSource).removeUserAccountApplicationHistory("userid");
		Mockito.verify(preparedStatement,Mockito.never()).execute(ArgumentCaptor.forClass(String.class).capture());
	}

}
