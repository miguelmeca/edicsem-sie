package com.edicsem.pe.sie.util.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
    //~ Static fields/initializers =============================================

    private static Log log = LogFactory.getLog(DateUtil.class);
    private static String datePattern = "dd/MM/yyyy";
    private static String timePattern = "HH:mm";

    //~ Methods ================================================================

    /**
     * Return default datePattern (dd/MM/yyyy)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
      throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                      + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }
    
    /**
     * This method returns the current date time in the format:
     * mask
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getDateTimeNow(String mask, Date theTime) {
        return getDateTime(mask, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    
    /**
     * @param aMask
     * @param strDate
     * @param tipoRangoFecha
     * 		Calendar.HOUR
     *      Calendar.DATE
     * 		Calendar.MONTH
     *      Calendar.YEAR
     * @param cantidad
     * @return
     * @throws Exception
     * Ejemplo:  addToDate("dd/mm/yyyy", "01/01/2008" , Calendar.DATE, 30)
     */
    public static Date addToDate(String aMask, String strDate, int tipoRangoFecha, int cantidad) throws Exception {
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(convertStringToDate(aMask, strDate));
    	cal.add(tipoRangoFecha, cantidad);
    	Date fechaRetorno = cal.getTime();
    	return fechaRetorno;		
    }
    
    /**
     * @param dDate
     * @param tipoRangoFecha
     * @param cantidad
     * @return
     * @throws Exception
     * Ejemplo:  addToDate(new Date() , Calendar.DATE, 30)
     */
    public static Date addToDate(Date dDate, int tipoRangoFecha, int cantidad) throws Exception {
    	Calendar cal = new GregorianCalendar();
    	cal.setTime(dDate);
    	cal.add(tipoRangoFecha, cantidad);
    	Date fechaRetorno = cal.getTime();
    	return fechaRetorno;		
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aMask the date pattern the string is in
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(String aMask, Date aDate) {
        return getDateTime(aMask, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
      throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }

            aDate = convertStringToDate(datePattern, strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());
                    
        }

        return aDate;
    }
    
    /**
	 * Convierte del formato <i>yyyyMMdd</i> al formato <i>dd/MM/yyyy</i>.
	 * <br />
	 * Ej: 20061231 a 31/12/2006
	 * 
	 * @param fechaProceso
	 *            String con el formato <i>yyyyMMdd</i>
	 * @return String con el formato <i>dd/MM/yyyy</i>
	 */
	public static String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/"
				+ fecha.substring(0, 4);
		return resultado;
	}
	
    /**
     * This method converts a Date to a String using the datePattern
     * 
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * 
     * @throws ParseException
     */
	public static String formatoString(Date fecha, String formato) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(formato);
			return df.format(fecha);
		} catch (Exception e) {
			return null;
		}
	}
	
}
