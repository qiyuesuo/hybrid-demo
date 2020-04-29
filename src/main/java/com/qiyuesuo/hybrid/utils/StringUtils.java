package com.qiyuesuo.hybrid.utils;

import java.util.Random;

public class StringUtils {

	public static final String SPACE = " ";
	public static final String EMPTY = "";

	public static String STRINGS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * <p>
	 * Checks if a CharSequence is empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * <p>
	 * NOTE: This method changed in Lang version 2.0.
	 * It no longer trims the CharSequence.
	 * That functionality is available in isBlank().
	 * </p>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is empty or null
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty ("") and not null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null
	 * @since 3.0 Changed signature from isNotEmpty(String) to isNotEmpty(CharSequence)
	 */
	public static boolean isNotEmpty(final CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if any one of the CharSequences are empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAnyEmpty(null)             = true
	 * StringUtils.isAnyEmpty(null, "foo")      = true
	 * StringUtils.isAnyEmpty("", "bar")        = true
	 * StringUtils.isAnyEmpty("bob", "")        = true
	 * StringUtils.isAnyEmpty("  bob  ", null)  = true
	 * StringUtils.isAnyEmpty(" ", "bar")       = false
	 * StringUtils.isAnyEmpty("foo", "bar")     = false
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isAnyEmpty(CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (CharSequence cs : css) {
			if (isEmpty(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if none of the CharSequences are empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNoneEmpty(null)             = false
	 * StringUtils.isNoneEmpty(null, "foo")      = false
	 * StringUtils.isNoneEmpty("", "bar")        = false
	 * StringUtils.isNoneEmpty("bob", "")        = false
	 * StringUtils.isNoneEmpty("  bob  ", null)  = false
	 * StringUtils.isNoneEmpty(" ", "bar")       = true
	 * StringUtils.isNoneEmpty("foo", "bar")     = true
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are empty or null
	 * @since 3.2
	 */
	public static boolean isNoneEmpty(CharSequence... css) {
		return !isAnyEmpty(css);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace only.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is
	 *         not empty and not null and not whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}

	/**
	 * <p>
	 * Checks if any one of the CharSequences are blank ("") or null and not whitespace only..
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAnyBlank(null)             = true
	 * StringUtils.isAnyBlank(null, "foo")      = true
	 * StringUtils.isAnyBlank(null, null)       = true
	 * StringUtils.isAnyBlank("", "bar")        = true
	 * StringUtils.isAnyBlank("bob", "")        = true
	 * StringUtils.isAnyBlank("  bob  ", null)  = true
	 * StringUtils.isAnyBlank(" ", "bar")       = true
	 * StringUtils.isAnyBlank("foo", "bar")     = false
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if any of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isAnyBlank(CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (CharSequence cs : css) {
			if (isBlank(cs)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks if none of the CharSequences are blank ("") or null and whitespace only..
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNoneBlank(null)             = false
	 * StringUtils.isNoneBlank(null, "foo")      = false
	 * StringUtils.isNoneBlank(null, null)       = false
	 * StringUtils.isNoneBlank("", "bar")        = false
	 * StringUtils.isNoneBlank("bob", "")        = false
	 * StringUtils.isNoneBlank("  bob  ", null)  = false
	 * StringUtils.isNoneBlank(" ", "bar")       = false
	 * StringUtils.isNoneBlank("foo", "bar")     = true
	 * </pre>
	 *
	 * @param css the CharSequences to check, may be null or empty
	 * @return {@code true} if none of the CharSequences are blank or null or whitespace only
	 * @since 3.2
	 */
	public static boolean isNoneBlank(CharSequence... css) {
		return !isAnyBlank(css);
	}
	
	/**
	 * 全部为空
	 * @param css
	 * @return
	 */
	public static boolean isAllBlank(CharSequence... css) {
		if (ArrayUtils.isEmpty(css)) {
			return true;
		}
		for (CharSequence cs : css) {
			if (isNotBlank(cs)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both
	 * ends of this String, handling {@code null} by returning
	 * {@code null}.
	 * </p>
	 * <p>
	 * The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * To strip whitespace use {@link #strip(String)}.
	 * </p>
	 * <p>
	 * To trim your choice of characters, use the
	 * {@link #strip(String, String)} methods.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed string, {@code null} if null String input
	 */
	public static String trim(final String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both
	 * ends of this String returning {@code null} if the String is
	 * empty ("") after the trim or if it is {@code null}.
	 * <p>
	 * The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * To strip whitespace use {@link #stripToNull(String)}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String,
	 *         {@code null} if only chars &lt;= 32, empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(final String str) {
		final String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both
	 * ends of this String returning an empty String ("") if the String
	 * is empty ("") after the trim or if it is {@code null}.
	 * <p>
	 * The String is trimmed using {@link String#trim()}.
	 * Trim removes start and end characters &lt;= 32.
	 * To strip whitespace use {@link #stripToEmpty(String)}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 *
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String trimToEmpty(final String str) {
		return str == null ? EMPTY : str.trim();
	}
	
	public static String trimToEmpty(Object o) {
		return o == null ? EMPTY : o.toString();
	}

	public static String random(int length) {
		if (length < 1) {
			return null;
		}
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(STRINGS.length());
			sb.append(STRINGS.charAt(number));
		}
		return sb.toString();
	}

	public static String randomString(int length) {
		if(length<1){
			return null;
		}
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
		Random random = new Random();     
		StringBuffer sb = new StringBuffer();     
		for (int i = 0; i < length; i++) {     
		    int number = random.nextInt(base.length());     
		    sb.append(base.charAt(number));     
		}     
		return sb.toString();
	}

}
