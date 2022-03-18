/*
 * Copyright 2014-2021 the original author or authors.
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

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;

import org.springframework.restdocs.generate.RestDocumentationGenerator;
import org.springframework.restdocs.snippet.SnippetException;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.restdocs.testfixtures.OperationBuilder;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

/**
 * Tests for failures when rendering {@link PathParametersSnippet} due to missing or
 * undocumented path parameters.
 *
 * @author Andy Wilkinson
 */
public class PathParametersSnippetFailureTests {

	@Rule
	public OperationBuilder operationBuilder = new OperationBuilder(TemplateFormats.asciidoctor());

	@Test
	public void undocumentedPathParameter() throws IOException {
		assertThatExceptionOfType(SnippetException.class)
				.isThrownBy(() -> new PathParametersSnippet(Collections.<ParameterDescriptor>emptyList())
						.document(this.operationBuilder
								.attribute(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, "/{a}/").build()))
				.withMessage("Path parameters with the following names were not documented: [a]");
	}

	@Test
	public void missingPathParameter() throws IOException {
		assertThatExceptionOfType(SnippetException.class)
				.isThrownBy(() -> new PathParametersSnippet(Arrays.asList(parameterWithName("a").description("one")))
						.document(this.operationBuilder
								.attribute(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, "/").build()))
				.withMessage("Path parameters with the following names were not found in the request: [a]");
	}

	@Test
	public void undocumentedAndMissingPathParameters() throws IOException {
		assertThatExceptionOfType(SnippetException.class)
				.isThrownBy(() -> new PathParametersSnippet(Arrays.asList(parameterWithName("a").description("one")))
						.document(this.operationBuilder
								.attribute(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, "/{b}").build()))
				.withMessage(
						"Path parameters with the following names were not documented: [b]. Path parameters with the"
								+ " following names were not found in the request: [a]");
	}

}
