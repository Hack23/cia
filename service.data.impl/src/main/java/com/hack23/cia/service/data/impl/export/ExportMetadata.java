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
package com.hack23.cia.service.data.impl.export;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common metadata for JSON exports.
 * 
 * @author intelligence-operative
 * @since v1.36
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportMetadata implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("version")
	private String version = "1.0.0";

	@JsonProperty("generated")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Date generated;

	@JsonProperty("source")
	private String source = "Citizen Intelligence Agency";

	@JsonProperty("schema")
	private String schema;

	@JsonProperty("recordCount")
	private Integer recordCount;

	@JsonProperty("dataDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
	private Date dataDate;

	public ExportMetadata() {
		this.generated = new Date();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public Date getGenerated() {
		return generated != null ? new Date(generated.getTime()) : null;
	}

	public void setGenerated(final Date generated) {
		this.generated = generated != null ? new Date(generated.getTime()) : null;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(final String schema) {
		this.schema = schema;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(final Integer recordCount) {
		this.recordCount = recordCount;
	}

	public Date getDataDate() {
		return dataDate != null ? new Date(dataDate.getTime()) : null;
	}

	public void setDataDate(final Date dataDate) {
		this.dataDate = dataDate != null ? new Date(dataDate.getTime()) : null;
	}
}
