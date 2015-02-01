package com.mysolutions.creditcardrecords;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.mysolutions.creditcardrecords.BankCardProviderNames;
import com.mysolutions.creditcardrecords.CompositeKeyFriendlyBankCardDetailsHolder;
import com.mysolutions.creditcardrecords.CompositeKeyImplementer;

/**
 * Test class for {@link CompositeKeyFriendlyBankCardDetailsHolder}
 * 
 * 
 * 
 * @author manna
 *
 */
public class CompositeKeyFriendlyBankCardDetailsHolderTest {

	private CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date> uut;

	DateFormat df = new SimpleDateFormat("MMM-yyyy");

	@Before
	public void setup() throws ParseException, Exception {

		uut = new CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date>();
		Map<CompositeKeyImplementer<String, BankCardProviderNames>, Date> entry = new HashMap<CompositeKeyImplementer<String, BankCardProviderNames>, Date>();

		entry.put(new CompositeKeyImplementer<String, BankCardProviderNames>(
				"5601-2345-3446-5678", BankCardProviderNames.HSBC_Canada), df
				.parse("Nov-2017"));
		entry.put(new CompositeKeyImplementer<String, BankCardProviderNames>(
				"3786-7334-8965-345", BankCardProviderNames.American_Express),
				df.parse("Dec-2018"));

		uut.setAllCardDetails(entry);

	}

	@Test
	public void Test_CompositeKeyFriendlyBankCardDetailsHolder_Adds_CardDetails_Successfully() {
		assertTrue(uut.getAllCardDetails().size() == 2);
	}

	@Test
	public void Test_CompositeKeyFriendlyBankCardDetailsHolder_Removes_CardDetails_Successfully() {
		CompositeKeyImplementer<String, BankCardProviderNames> key = new CompositeKeyImplementer<String, BankCardProviderNames>(
				"5601-2345-3446-5678", BankCardProviderNames.HSBC_Canada);
		
		Date d = uut.removeEntry(key);
		
		assertNotNull(d);
	}
}
