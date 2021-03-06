/*
 * Copyright 2014-2022 the original author or authors.
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

package org.springframework.restdocs.testfixtures;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * JUnit {@code @Rule} to capture output from System.out and System.err.
 * <p>
 * To use add as a {@link Rule @Rule}:
 *
 * <pre class="code">
 * public class MyTest {
 *
 *     &#064;Rule
 *     public OutputCaptureRule output = new OutputCaptureRule();
 *
 *     &#064;Test
 *     public void test() {
 *         assertThat(output).contains("ok");
 *     }
 *
 * }
 * </pre>
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
public class OutputCaptureRule implements TestRule, CapturedOutput {

	private final OutputCapture delegate = new OutputCapture();

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				OutputCaptureRule.this.delegate.push();
				try {
					base.evaluate();
				}
				finally {
					OutputCaptureRule.this.delegate.pop();
				}
			}
		};
	}

	@Override
	public String getAll() {
		return this.delegate.getAll();
	}

	@Override
	public String getOut() {
		return this.delegate.getOut();
	}

	@Override
	public String getErr() {
		return this.delegate.getErr();
	}

	@Override
	public String toString() {
		return this.delegate.toString();
	}

}
