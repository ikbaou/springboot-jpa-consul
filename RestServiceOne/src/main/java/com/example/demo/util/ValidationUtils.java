package com.example.demo.util;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * Validation utility class;
 *
 * @author Michael Markogiannakis
 */
public final class ValidationUtils {

    private ValidationUtils() { /* */ };

    private static final String VALID_NUMERIC = "[0-9]*";

    /**
     * Checks if the given {@link String} is a number [0-9]*.
     *
     * @param string the {@link String} input
     *
     * @return true if it is a number
     */
    public static boolean isNumeric(final String string) {
        return isEmpty(string) ? false : string.matches(VALID_NUMERIC);
    }

    /**
     * Checks if the given {@link Map} is empty.
     *
     * @param arg the {@link Map} input
     *
     * @return true if the given {@link Map} is empty.
     */
    public static boolean isEmpty(final Map<?, ?> arg) {
        return arg == null || arg.isEmpty();
    }

    /**
     * Checks if the given {@link Enumeration} is empty.
     *
     * @param arg the {@link Enumeration} input
     *
     * @return true if the given {@link Enumeration} is empty.
     */
    public static boolean isEmpty(final Enumeration<?> arg) {
        return arg == null || !arg.hasMoreElements();
    }

    /**
     * Checks if the given {@link Collection} is empty.
     *
     * @param arg the {@link Collection} input
     *
     * @return true if the given {@link Collection} is empty.
     */
    public static boolean isEmpty(final Collection<?> arg) {
        return arg == null || arg.isEmpty();
    }

    /**
     * Checks if the given {@link Collection} has exactly one element.
     *
     * @param arg the {@link Collection} input
     *
     * @return true if the {@link Collection} array has exactly one element
     */
    public static boolean hasOneElement(final Collection<?> arg) {
        return arg != null && arg.size() == 1;
    }

    /**
     * Checks if the given array has exactly one element.
     *
     * @param arg the {@link Object} array input
     *
     * @return true if the given array has exactly one element
     */
    public static boolean hasOneElement(final Object[] arg) {
        return arg != null && arg.length == 1;
    }

    /**
     * Checks if the given {@link Collection} has at least a number of elements.
     *
     * @param arg the {@link Collection} input
     *
     * @return true if the {@link Collection} has at least the given length
     */
    public static boolean hasAtMostElements(final Collection<?> arg,
            final int length) {
        return arg != null && arg.size() <= length;
    }

    /**
     * Checks if the given array has at most a number of elements.
     *
     * @param arg the {@link Object} array input
     *
     * @return true if the array has at most the given length
     */
    public static boolean hasAtMostElements(final Object[] arg,
            final int length) {
        return arg != null && arg.length <= length;
    }

    /**
     * Checks if the given {@link Collection} has at least a number of elements.
     *
     * @param arg the {@link Collection} input
     *
     * @return true if the {@link Collection} has at least the given length
     */
    public static boolean hasAtLeastElements(final Collection<?> arg,
            final int length) {
        return arg != null && arg.size() >= length;
    }

    /**
     * Checks if the given array has at least a number of elements.
     *
     * @param arg the {@link Object} array input
     *
     * @return true if the array has at least the given length
     */
    public static boolean hasAtLeastElements(final Object[] arg,
            final int length) {
        return arg != null && arg.length >= length;
    }

    /**
     * Checks if the given {@link Collection} has the given length.
     *
     * @param arg the {@link Collection} input
     *
     * @return true if the array has the given length
     */
    public static boolean hasLength(final Collection<?> arg, final int length) {
        return arg != null && arg.size() == length;
    }

    /**
     * Checks if the given array has the given length.
     *
     * @param arg the {@link Object} array input
     * @param length the desired length
     *
     * @return true if the array has the given length
     */
    public static boolean hasLength(final Object[] arg, final int length) {
        return arg != null && arg.length == length;
    }

    /**
     * Checks if the given {@link String} is empty.
     *
     * @param arg the {@link String} input
     *
     * @return true if empty
     */
    public static boolean isEmpty(final String arg) {
        return arg == null || arg.trim().isEmpty();
    }

    /**
     * Checks if the given {@link String} array is empty.
     *
     * @param arg the {@link String} array input
     *
     * @return true if empty
     */
    public static boolean isEmpty(final String... args) {
        return args == null || args.length == 0;
    }

    /**
     * Checks if the given array is empty.
     *
     * @param arg the {@link Object} array input
     *
     * @return true if empty
     */
    public static boolean isEmpty(final Object[] arg) {
        return arg == null || arg.length == 0;
    }

    /**
     * Checks if the given {@link Object} is empty. The supported types are
     * arrays of {@link Object}s, {@link Collection}, {@link Map}
     * and {@link String}. Otherwise a null check is performed.
     *
     * @param arg the {@link Object} input
     *
     * @return true if argument can be characterized as empty 
     */
    public static boolean isEmpty(final Object arg) {
        boolean result = false;
        if (arg instanceof Collection<?>) {
            result = isEmpty((Collection<?>) arg);
        } else if (arg instanceof Map<?,?>) {
            result = isEmpty((Map<?,?>) arg);
        } else if (arg instanceof Object[]) {
            result = isEmpty((Object[]) arg);
        } else if (arg instanceof String) {
            result = isEmpty((String) arg);
        } else {
            result = arg == null;
        }
        return result;
    }

    /**
     * Checks if {@link Integer} is between min and max.
     *
     * @param arg the given {@link Integer}
     * @param min the minimum
     * @param max the maximum
     *
     * @return true if date is between min and max
     */
    public static boolean isBetween(final Integer arg,
        final Integer min, final Integer max) {
        boolean result = false;
        if (arg != null) {
            if (min != null && max != null) {
                result = min <= arg && arg <= max;
            } else if (min != null && max == null) {
                result = min <= arg;
            } else if (min == null && max != null) {
                result = arg <= max;
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if {@link Date} is between min and max.
     *
     * @param arg the given {@link Date}
     * @param min the minimum
     * @param max the maximum
     *
     * @return true if date is between min and max
     */
    public static boolean isBetween(final Date arg,
        final Date min, final Date max) {
        boolean result = false;
        if (arg != null) {
            if (min != null && max != null) {
                result = arg.after(min) && arg.before(max);
            } else if (min != null && max == null) {
                result = arg.after(min);
            } else if (min == null && max != null) {
                result = arg.before(max);
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if the current date is in the future
     *
     * @param date the {@link Date} input
     *
     * @return true if date is in the future
     */
    public static boolean isInFuture(final Date date) {
        return isBetween(date, new Date(), null);
    }

    /**
     * Checks if the current date is in the past
     *
     * @param date the {@link Date} input
     *
     * @return true if date is in the past
     */
    public static boolean isInPast(final Date date) {
        return isBetween(date, null, new Date());
    }

    /**
     * Checks if the current date is after the given;
     *
     * @param expiresAt the {@link Date} input
     *
     * @return true if expired
     */
    public static boolean isExpired(final Date expiresAt) {
        return isBetween(new Date(), expiresAt, null);
    }

    /**
     * Checks if the arg is a valid class name.
     *
     * @param arg the {@link String} input
     *
     * @return true if class exists
     */
    public static boolean classExists(final String arg) {
        boolean result = true;
        try {
            Class.forName(arg, false,
                Thread.currentThread().getContextClassLoader());
        } catch (final ClassNotFoundException e) {
            result = false;
        }
        return result;
    }
    
    /**
     * Checks if the given {@link String} is in range of min and max.
     *
     * @param str the {@link String} input
     * @param min the minimum length
     * @param max the maximum length
     *
     * @return true if it is in range
     */
    public static boolean isInRange(final String str,
            final int min, final int max) {
        return str == null ? false : isInRange(str.length(), min, max);
    }

    /**
     * Checks if the given integer is in range of min and max.
     * 
     * @param arg the int input
     * @param min the minimum value
     * @param max the maximum value
     *
     * @return true if it is in range
     */
    public static boolean isInRange(final int arg, final int min,
            final int max) {
        return arg >= min && arg <= max;
    }

    /**
     * NPE free equals.
     *
     * @param expected the expected {@link Object}
     * @param actual the actual {@link Object}
     *
     * @return true if the arguments are equal
     */
    public static boolean equals(final Object expected,
            final Object actual) {
        return (expected == null && actual == null)
            || (expected != null && expected.equals(actual));
    }
}
