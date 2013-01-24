/* Copyright (c) 2011-2013 Pushing Inertia
 * All rights reserved.  http://pushinginertia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pushinginertia.commons.lang;

/**
 * Provides some common validation logic.
 */
public class ValidateAs {
	/**
	 * Validates that a variable is not null.
	 * @param value value to check
	 * @param name name of the value that will be echoed in the exception if the value is null
	 * @param <T> type of the value to check
	 * @return the value passed in as a parameter
	 * @throws IllegalArgumentException if the value is null
	 */
	public static <T> T notNull(final T value, final String name) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException("Value for [" + name + "] cannot be null.");
		}
		return value;
	}
}