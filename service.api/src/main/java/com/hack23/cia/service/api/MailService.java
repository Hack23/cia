/*
 * Copyright 2010 James Pether Sörling
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
 *	$Id: MailService.java 6063 2015-05-09 06:08:20Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.api/src/main/java/com/hack23/cia/service/api/MailService.java $
*/
package com.hack23.cia.service.api;

/**
 * The Interface MailService.
 */
public interface MailService {

	/**
	 * Send mail.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param subject
	 *            the subject
	 * @param msg
	 *            the msg
	 */
	void sendMail(String from, String to, String subject, String msg);
}
