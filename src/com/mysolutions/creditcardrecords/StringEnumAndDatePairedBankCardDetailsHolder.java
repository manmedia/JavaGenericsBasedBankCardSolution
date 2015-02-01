package com.mysolutions.creditcardrecords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Class that uses generic {@link CompositeKeyFriendlyBankCardDetailsHolder
 * <K,V>} with {@link CompositeKeyImplementer<K1,K2>} in order to create a
 * simple bank card details holder.
 * 
 * Uses a String (Credit card number) and Enum (Provider name) for composite key
 * to add/remove data.
 * 
 * Requirements
 * 
 * 1. Credit card number must be in XXXX-XXXX-XXXX-XXXX format (amex XXXX-XXXX-XXXX-XXX).
 * 
 * 2. Although the tests have shown that you can use <String, String> as paired key, but
 *    using Enum is preferred as it helps to keep the provider names intact. Also, this is
 *    reusable and extensible.
 * 
 * 
 * @author manna
 *
 */

public class StringEnumAndDatePairedBankCardDetailsHolder {

	/*
	 * Card details holder by Composite String key (Card number and Card
	 * provider) and Date value (ExpiryDate)
	 */
	private CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date> cardDetails;
	private static String detailsPrinterFormat = "%1$-20s| %2$-25s| %3$tb-%3$tY\n";

	/* Constructors */
	public StringEnumAndDatePairedBankCardDetailsHolder() {
		this.cardDetails = new CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date>();
	}

	public StringEnumAndDatePairedBankCardDetailsHolder(
			CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date> cardDetails)
			throws IllegalArgumentException {
		this();

		Set<CompositeKeyImplementer<String, BankCardProviderNames>> keySet = (Set<CompositeKeyImplementer<String, BankCardProviderNames>>) (cardDetails
				.getAllCardDetails().keySet());
		for (CompositeKeyImplementer<String, BankCardProviderNames> keys : keySet) {

			String cardNumber = keys.getKey1();
			creditCardNumberFormatValidator(cardNumber);

		}

		this.cardDetails = cardDetails;
	}

	/* Card number format must be in XXXX-XXXX-XXXX-XXXX */
	public StringEnumAndDatePairedBankCardDetailsHolder(String cardNumber,
			BankCardProviderNames cardProvider) {
		this();
		this.cardDetails = new CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date>(
				new CompositeKeyImplementer<String, BankCardProviderNames>(
						cardNumber, cardProvider), new Date());
	}

	/* Card number format must be in XXXX-XXXX-XXXX-XXXX */
	public StringEnumAndDatePairedBankCardDetailsHolder(String cardNumber,
			BankCardProviderNames cardProvider, Date date) {
		this();
		this.cardDetails = new CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date>(
				new CompositeKeyImplementer<String, BankCardProviderNames>(
						cardNumber, cardProvider), date);
	}

	/* methods */
	public CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date> getCardDetails() {
		return this.cardDetails;
	}

	public void setCardDetails(
			CompositeKeyFriendlyBankCardDetailsHolder<CompositeKeyImplementer<String, BankCardProviderNames>, Date> cardDetails) {
		this.cardDetails = cardDetails;
	}

	public Map<CompositeKeyImplementer<String, BankCardProviderNames>, Date> getIndividualCardData(
			CompositeKeyImplementer<String, BankCardProviderNames> key) {
		// return this.cardDetails.getCardDetails(key);
		return null;
	}

	public void putIndividualCardDetails(String cardNumber,
			BankCardProviderNames cardPrvoider, Date date) {
		this.cardDetails.putEntry(
				new CompositeKeyImplementer<String, BankCardProviderNames>(
						cardNumber, cardPrvoider), date);
	}

	public void deleteIndividualCardDetails(String cardNumber,
			BankCardProviderNames cardProvider) {

	}

	public int totalNumberOfRecordsAvailable() {
		return this.cardDetails.getAllCardDetails().size();
	}

	/**
	 * Card number validator (to check if it is in XXXX-XXXX-XXXX-XXXX format)
	 * 
	 * @param cardNumber
	 */
	private void creditCardNumberFormatValidator(String cardNumber) {
		String[] cardNumberSegments = cardNumber.split("[^0-9]+");
		if (cardNumberSegments.length != 4) {
			throw new IllegalArgumentException(
					"Card number format is not correct! Needs to be in XXXX-XXXX-XXXX-XXXX format");
		}

		for (int i = 0; i < cardNumberSegments.length; i++) {
			if (i < cardNumberSegments.length - 1
					&& cardNumberSegments[i].length() != 4) {
				throw new IllegalArgumentException(
						"First three card number segments must be in 4 digits! i.e. XXXX-XXXX-XXXX");
			}

			if (i == cardNumberSegments.length - 1
					&& (cardNumberSegments[i].length() == 3 || cardNumberSegments[i]
							.length() == 4)) {
				throw new IllegalArgumentException(
						"Last segment must be either 3 or 4 digits long! i.e. XXXX-XXXX-XXXX-XXX(X)");
			}
		}

	}

	/**
	 * Utility method to mask card data
	 * 
	 * 
	 * @param creditCardNumberString
	 * @param providerId
	 * @return
	 */

	private String maskCreditCardDigitsByProviderId(
			String creditCardNumberString, BankCardProviderNames providerId) {

		String maskerNumbers = "XXXX-XXXX-XXXX-XXXX";
		String maskedNumber = "";
		switch (providerId) {

		case HSBC_Canada:
			maskedNumber = creditCardNumberString.substring(0, 2)
					+ maskerNumbers.substring(2);
			break;

		case Royal_Bank_of_Canada:
			maskedNumber = creditCardNumberString.substring(0, 4)
					+ maskerNumbers.substring(4);
			break;

		case American_Express:
			maskedNumber = maskerNumbers.substring(0, 15)
					+ creditCardNumberString.substring(15);
			break;

		default:
			maskedNumber = maskerNumbers;
			break;
		}

		return maskedNumber;

	}

	/**
	 * Method to display data based on (descending) Expiry Date 
	 * 
	 * 
	 * 
	 * 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayCardDetails_SortedByDescendingDate() {

		System.out.printf("%-20s| %-25s| %-10s\n\n", "CARD NUMBER",
				"CARD PROVIDER", "EXPIRY DATE");

		Map<CompositeKeyImplementer<String, BankCardProviderNames>, Date> detailsToPrint = this.cardDetails
				.getAllCardDetails();

		List entries = new ArrayList(detailsToPrint.entrySet());

		Collections.sort(entries, new MapEntryBasedDescendingComparator());

		Map map = new LinkedHashMap();

		for (Iterator iter = entries.iterator(); iter.hasNext();) {
			Map.Entry item = (Map.Entry) (iter.next());
			map.put(item.getKey(), item.getValue());
		}

		for (Object o : map.keySet()) {

			String cardNumber = ((CompositeKeyImplementer<String, BankCardProviderNames>) o)
					.getKey1().toString();

			BankCardProviderNames providerId = ((CompositeKeyImplementer<String, BankCardProviderNames>) o)
					.getKey2();

			System.out
					.printf(StringEnumAndDatePairedBankCardDetailsHolder.detailsPrinterFormat,
							maskCreditCardDigitsByProviderId(cardNumber,
									providerId),
							providerId,
							map.get(new CompositeKeyImplementer<String, BankCardProviderNames>(
									cardNumber, providerId)));
		}
	}

}
