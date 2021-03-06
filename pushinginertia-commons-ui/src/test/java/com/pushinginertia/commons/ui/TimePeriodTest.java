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
package com.pushinginertia.commons.ui;

import org.joda.time.Period;
import org.junit.Assert;
import org.junit.Test;

public class TimePeriodTest {
	@Test
	public void getString() {
		final TimePeriod period = new TimePeriod(Period.days(1).toStandardDuration());
		Assert.assertEquals("1 Day", period.getString(new TimePeriod.TimePeriodResourceBundle() {
			public String getString(final String resourceKey) {
				Assert.assertEquals("TimePeriod.Day", resourceKey);
				return "${i} Day";
			}
		}));
	}

	@Test
	public void minute() {
		final TimePeriod period = new TimePeriod(Period.minutes(1).toStandardDuration());
		Assert.assertEquals(1, period.getQuantity());
		Assert.assertEquals("TimePeriod.Minute", period.getDescriptorResourceKey());
	}

	@Test
	public void minutes() {
		final TimePeriod period = new TimePeriod(Period.minutes(59).toStandardDuration());
		Assert.assertEquals(59, period.getQuantity());
		Assert.assertEquals("TimePeriod.Minutes", period.getDescriptorResourceKey());
	}

	@Test
	public void hour() {
		final TimePeriod period = new TimePeriod(Period.hours(1).toStandardDuration());
		Assert.assertEquals(1, period.getQuantity());
		Assert.assertEquals("TimePeriod.Hour", period.getDescriptorResourceKey());
	}

	@Test
	public void hours() {
		final TimePeriod period = new TimePeriod(Period.minutes(1439).toStandardDuration());
		Assert.assertEquals(23, period.getQuantity());
		Assert.assertEquals("TimePeriod.Hours", period.getDescriptorResourceKey());
	}

	@Test
	public void day() {
		final TimePeriod period = new TimePeriod(Period.days(1).toStandardDuration());
		Assert.assertEquals(1, period.getQuantity());
		Assert.assertEquals("TimePeriod.Day", period.getDescriptorResourceKey());
	}

	@Test
	public void days() {
		final TimePeriod period = new TimePeriod(Period.days(3).toStandardDuration());
		Assert.assertEquals(3, period.getQuantity());
		Assert.assertEquals("TimePeriod.Days", period.getDescriptorResourceKey());
	}

	@Test
	public void weeks() {
		final TimePeriod period = new TimePeriod(Period.days(32).toStandardDuration());
		Assert.assertEquals(5, period.getQuantity());
		Assert.assertEquals("TimePeriod.Weeks", period.getDescriptorResourceKey());
	}

	@Test
	public void month() {
		final TimePeriod period = new TimePeriod(Period.days(62).toStandardDuration());
		Assert.assertEquals(2, period.getQuantity());
		Assert.assertEquals("TimePeriod.Months", period.getDescriptorResourceKey());
	}
}
