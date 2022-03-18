/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.restdocs.request;

import org.springframework.restdocs.snippet.IgnorableDescriptor;

/**
 * A descriptor of a request part.
 *
 * @author Andy Wilkinson
 * @since 1.1.0
 * @see RequestDocumentation#partWithName
 */
public class RequestPartDescriptor extends IgnorableDescriptor<RequestPartDescriptor> {

	private final String name;

	private boolean optional;

	/**
	 * Creates a new {@code RequestPartDescriptor} describing the request part with the
	 * given {@code name}.
	 * @param name the name of the request part
	 */
	protected RequestPartDescriptor(String name) {
		this.name = name;
	}

	/**
	 * Marks the request part as optional.
	 * @return {@code this}
	 */
	public final RequestPartDescriptor optional() {
		this.optional = true;
		return this;
	}

	/**
	 * Returns the name of the request part being described by this descriptor.
	 * @return the name of the parameter
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * Returns {@code true} if the described request part is optional, otherwise
	 * {@code false}.
	 * @return {@code true} if the described request part is optional, otherwise
	 * {@code false}
	 */
	public final boolean isOptional() {
		return this.optional;
	}

}
