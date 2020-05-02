/**
 * Copyright (c) 2000-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.spring.mock.web.portlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.portlet.ClientDataRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.servlet.http.Part;


/**
 * Mock implementation of the {@link javax.portlet.ClientDataRequest} interface.
 *
 * @author  Juergen Hoeller
 * @since   3.0
 */
public class MockClientDataRequest extends MockPortletRequest implements ClientDataRequest {

	private String characterEncoding;

	private byte[] content;

	private String contentType;

	private String method;

	/**
	 * Create a new MockClientDataRequest with a default {@link MockPortalContext} and a default {@link
	 * MockPortletContext}.
	 *
	 * @see  org.springframework.mock.web.portlet.MockPortalContext
	 * @see  org.springframework.mock.web.portlet.MockPortletContext
	 */
	public MockClientDataRequest() {
		super();
	}

	/**
	 * Create a new MockClientDataRequest with a default {@link MockPortalContext}.
	 *
	 * @param  portletContext  the PortletContext that the request runs in
	 */
	public MockClientDataRequest(PortletContext portletContext) {
		super(portletContext);
	}

	/**
	 * Create a new MockClientDataRequest.
	 *
	 * @param  portalContext   the PortalContext that the request runs in
	 * @param  portletContext  the PortletContext that the request runs in
	 */
	public MockClientDataRequest(PortalContext portalContext, PortletContext portletContext) {
		super(portalContext, portletContext);
	}

	@Override
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	@Override
	public int getContentLength() {
		return ((this.content != null) ? content.length : -1);
	}

	@Override
	public long getContentLengthLong() {
		return getContentLength();
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public Part getPart(String name) throws IOException, PortletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Part> getParts() throws IOException, PortletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getPortletInputStream() throws IOException {

		if (this.content != null) {
			return new ByteArrayInputStream(this.content);
		}
		else {
			return null;
		}
	}

	@Override
	public BufferedReader getReader() throws UnsupportedEncodingException {

		if (this.content != null) {
			InputStream sourceStream = new ByteArrayInputStream(this.content);
			Reader sourceReader = (this.characterEncoding != null)
				? new InputStreamReader(sourceStream, this.characterEncoding) : new InputStreamReader(sourceStream);

			return new BufferedReader(sourceReader);
		}
		else {
			return null;
		}
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
