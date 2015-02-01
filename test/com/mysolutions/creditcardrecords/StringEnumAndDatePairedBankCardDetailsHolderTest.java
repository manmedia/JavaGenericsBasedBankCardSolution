package com.mysolutions.creditcardrecords;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mysolutions.creditcardrecords.BankCardProviderNames;
import com.mysolutions.creditcardrecords.CompositeKeyImplementer;
import com.mysolutions.creditcardrecords.StringEnumAndDatePairedBankCardDetailsHolder;

public class StringEnumAndDatePairedBankCardDetailsHolderTest {

	StringEnumAndDatePairedBankCardDetailsHolder uut;

	DateFormat df = new SimpleDateFormat("MMM-yyyy");

	@Before
	public void setup() throws ParseException, Exception {

		uut = new StringEnumAndDatePairedBankCardDetailsHolder();

		uut.putIndividualCardDetails("5601-2345-3446-5678",
				BankCardProviderNames.HSBC_Canada, df.parse("Nov-2017"));

		uut.putIndividualCardDetails("4519-4532-4524-2456",
				BankCardProviderNames.Royal_Bank_of_Canada,
				df.parse("Oct-2017"));

		uut.putIndividualCardDetails("3786-7334-8965-345",
				BankCardProviderNames.American_Express, df.parse("Dec-2018"));

	}

	@Test
	public void Test_AddsObjectsSuccessfully() {
		assertNotNull(uut);
		assertNotNull(uut.getCardDetails());
		assertEquals(3, uut.totalNumberOfRecordsAvailable());

		HashMap<CompositeKeyImplementer<String, BankCardProviderNames>, Date> map = (HashMap<CompositeKeyImplementer<String, BankCardProviderNames>, Date>) (uut
				.getCardDetails().getAllCardDetails());
		assertNotNull(map);
		assertNotNull(map.entrySet());

		Set<CompositeKeyImplementer<String, BankCardProviderNames>> keys = (Set<CompositeKeyImplementer<String, BankCardProviderNames>>) (map
				.keySet());

		assertNotNull(keys);

	}

	@Test
	public void Test_Cannot_WorkWith_StandAloneKeys_Required_HashedKeys_FromRecords() {

		HashMap<CompositeKeyImplementer<String, BankCardProviderNames>, Date> map = (HashMap<CompositeKeyImplementer<String, BankCardProviderNames>, Date>) (uut
				.getCardDetails().getAllCardDetails());
		{
			CompositeKeyImplementer<String, BankCardProviderNames> key = new CompositeKeyImplementer<String, BankCardProviderNames>(
					"3786-7334-8965-345",
					BankCardProviderNames.American_Express);

			assertNotNull(map.get(key));

		}

		{

			Set<CompositeKeyImplementer<String, BankCardProviderNames>> keys = (Set<CompositeKeyImplementer<String, BankCardProviderNames>>) (map
					.keySet());

			for (CompositeKeyImplementer<String, BankCardProviderNames> key : keys) {
				assertTrue(map.containsKey(key));
			}

		}

	}

	@Test
	public void Test_DisplaysDataByDescendingDate_Successfully() {
		uut.displayCardDetails_SortedByDescendingDate();
	}

}
