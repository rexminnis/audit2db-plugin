/**
 * 
 */
package org.jenkins.plugins.audit2db.test.model;

import java.util.Date;

import junit.framework.Assert;

import org.jenkins.plugins.audit2db.internal.model.BuildDetailsImpl;
import org.jenkins.plugins.audit2db.internal.model.BuildParameterImpl;
import org.jenkins.plugins.audit2db.model.BuildDetails;
import org.jenkins.plugins.audit2db.model.BuildParameter;
import org.junit.Test;

/**
 * Unit tests for the {@link BuildParameterImpl} class.
 * 
 * @author Marco Scata
 *
 */
public class BuildParameterImplTests {
	private final BuildDetails details = new BuildDetailsImpl(
			"BUILDID", "BUILD NAME", "BUILD_FULLNAME", new Date(),
			new Date(), 10L, "USERID", "USERNAME", null);

	private final BuildDetails otherDetails = new BuildDetailsImpl(
			"BUILDIDXXX", "BUILD NAME", "BUILD_FULLNAME", new Date(),
			new Date(), 10L, "USERID", "USERNAME", null);

	private final BuildParameter expected = new BuildParameterImpl(
			Long.valueOf(123), "PARAM NAME", "PARAM VALUE", details);
	
	@Test
	public void differentAttributesShouldPreserveEquality(){
		final BuildParameter actual = new BuildParameterImpl(
				expected.getId() + 100, 
				expected.getName(),
				expected.getValue() + "DIFFERENT", 
				expected.getBuildDetails());
		Assert.assertEquals("Broken equality", expected, actual);
	}
	
	@Test
	public void differentNameShouldBreakEquality(){
		final BuildParameter actual = new BuildParameterImpl(
				expected.getId(), 
				expected.getName() + "DIFFERENT",
				expected.getValue(), 
				expected.getBuildDetails());
		Assert.assertFalse("Broken inequality logic", actual.equals(expected));
	}
	
	@Test
	public void differentBuildIdShouldBreakEquality(){
		final BuildParameter actual = new BuildParameterImpl(
				expected.getId(), 
				expected.getName(),
				expected.getValue(), 
				otherDetails);
		Assert.assertFalse("Broken inequality logic", actual.equals(expected));
	}
	
	@Test
	public void equalsNullShouldBeFalse() {
		Assert.assertFalse("Broken inequality logic", expected.equals(null));
	}

	@Test
	public void equalsSomethingElseShouldBeFalse() {
		Assert.assertFalse("Broken inequality logic", expected.equals("SOMESTRING"));
	}
}
