/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evnit.ttpm.stdv.util;

import java.io.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.LazyInitializationException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	public static final String HMAC_KEY = "FMIS";
	private static final char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ',
			'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă',
			'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ',
			'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế',
			'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
			'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ',
			'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	private static final char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O',
			'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
			'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U',
			'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	private Util() {
		throw new UnsupportedOperationException("Cannot instantiate a Util class");
	}

	public static String generateRandomUuid() {
		return UUID.randomUUID().toString();
	}

	public static String formatDate(Date date) {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String sDate = simpleDateFormat.format(date);
		return sDate;
	}

	public static String folderName(Long idFile, String donviId, String username, String fileName) {
		// Upload file to Utils services
		String guid = UUID.randomUUID().toString();
		String path1 = "/hrms/" + donviId + "/" + username + "/" + guid;
		String pathFull = path1;
		if (fileName != null) {
			String extension = FilenameUtils.getExtension(fileName);
			pathFull = pathFull + "." + extension;
		}
		return pathFull;
	}

	public static String folderDvName(String module, String donviId, String path) {
		// Upload file to Utils services
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String strDate = simpleDateFormat.format(date);
		String path1 = "/hrms/" + module + "/" + donviId + "/" + strDate;
		String pathFull = path1;
		if (path != null) {
			pathFull = path1 + "/" + path;
		}
		return pathFull;
	}

	public static Long subTwoDate(Date date1, Date date2) {
		Long total = null;
		try {
			long getDiff = date2.getTime() - date1.getTime();
			total = getDiff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public static Long subTwoDate2(Date date1, Date date2) {
		Long total = null;
		try {
			if (date1 != null && date2 != null) {
				long getDiff = date2.getTime() - date1.getTime();
				total = getDiff / (24 * 60 * 60 * 1000) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	public static String removeAccent(String str) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToConvert);
		Date date = calendar.getTime();
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static String convertDateToString(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			return df.format(date);
		} else {
			return "";
		}
	}

	public static Date convertStringddMMyyyyToString(String strDate) throws ParseException {
		if (strDate != null) {
			// Define the date format
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			// Parse the string into a Date object
			return formatter.parse(strDate);
		} else {
			return null;
		}
	}

	public static Integer getStartYearFromYearRangeToDate(String range) {
		try {
			String[] parts = range.split(" - ");
			if (parts.length != 2) {
				throw new IllegalArgumentException("Invalid range format");
			}

			int startYear = Integer.parseInt(parts[0].trim());

			return startYear;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid year in range", e);
		}
	}

	public static Integer getEndYearFromYearRangeToDate(String range) {
		try {
			String[] parts = range.split(" - ");
			if (parts.length != 2) {
				throw new IllegalArgumentException("Invalid range format");
			}

			int endYear = Integer.parseInt(parts[1].trim());

			return endYear;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid year in range", e);
		}
	}

	/**
	 * Hàm cộng số tháng vào một ngày trả về kiểu Date
	 * 
	 * @author hoannd
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonthtoDate(Date date, Short month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * Hàm trừ số tháng vào một ngày trả về kiểu Date
	 * 
	 * @author hoannd
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date MinusMonthtoDate(Date date, short month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -month);
		return cal.getTime();
	}

	/**
	 * Hàm trừ lùi số ngày của một ngày cho trước
	 * 
	 * @author hoannd
	 * @param inputDate
	 * @param day
	 * @return
	 */
	public static Date minusDaytoDate(Date inputDate, int day) {
		// Chuyển từ Date sang LocalDate
		LocalDate localDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		// Trừ 1 ngày
		LocalDate resultDate = localDate.minusDays(day);

		// Chuyển từ LocalDate về Date (nếu cần)
		Date outputDate = Date.from(resultDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return outputDate;
	}

	/**
	 * Định dạng tiền với số chữ số phần thập phân là fraction (làm tròn lên).
	 * 
	 * @param amount
	 *            Số tiền kiểu BigDecimal.
	 * @param fraction
	 *            Số chữ số phần thập phân.
	 * 
	 * @return Chuỗi định dạng tiền.
	 */
	public static String formatCurrency(Object amount, int fraction) {
		if (amount == null)
			return "";

		NumberFormat decimalFormat = NumberFormat.getInstance(new Locale("vi", "VI", ""));
		decimalFormat.setMinimumFractionDigits(fraction);

		amount = ((BigDecimal) amount).setScale(fraction, BigDecimal.ROUND_HALF_UP);

		return decimalFormat.format(amount);
	}

	/**
	 * Định dạng tiền với số phần thập phân tùy theo số chữ số có nghĩa.
	 * 
	 * @param amount
	 *            Số tiền.
	 * 
	 * @return Chuỗi định dạng tiền.
	 */
	public static String formatCurrency(BigDecimal amount) {
		if (amount == null)
			return "";

		NumberFormat decimalFormat = NumberFormat.getInstance(new Locale("vi", "VI", ""));

		decimalFormat.setMinimumFractionDigits(amount.scale());
		String stringFormat = decimalFormat.format(amount);

		if (stringFormat.equals(","))
			return "0";

		/* Xóa ký tự '0' thừa sau phần thập phân. */
		int pos = stringFormat.indexOf(",");
		if (pos != -1)
			while (stringFormat.substring(stringFormat.length() - 1, stringFormat.length()).equals("0"))
				stringFormat = stringFormat.substring(0, stringFormat.length() - 1);

		if (stringFormat.indexOf(",") == stringFormat.length() - 1)
			stringFormat = stringFormat.replace(",", "");

		return stringFormat;
	}

	/**
	 * Hàm xóa các file trong thư mục
	 * 
	 * @author hoannd
	 * @CreateTime 2:50:57 PM, Sep 29, 2010
	 * @param file
	 */
	public static void deleteFiles(File pathDir) {
		File fileOld[] = pathDir.listFiles();
		if (fileOld != null && fileOld.length > 0) {
			for (int i = 0; i < fileOld.length; i++) {
				if (fileOld[i].isFile()) {
					fileOld[i].delete();
				}
			}
		}
	}

	/**
	 * Hàm lấy ngày đầu tiên của tháng kế tiếp của ngày truyền vào
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfNextMonth(Date date) {
		Date nextMonthFirstDay = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			nextMonthFirstDay = calendar.getTime();
		} catch (Exception e) {
		}
		return nextMonthFirstDay;
	}

	/**
	 * Hàm compare 2 ngày với nhau, chỉ tính ngày, không tính tới giờ, phút, giây
	 * 
	 * @author hoannd
	 * @CreateTime date: Jun 9, 2009; time :1:19:03 PM
	 * @param day1
	 * @param day2
	 * @return 0 nếu bằng nhau, 1 nếu day1 > day2, -1 nếu day1 < day2 @ date1 ==
	 *         null Or date2 ==null retrun -2;
	 */
	public static int compare2Date(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return -2;
		}
		if (getYear(date1) > getYear(date2)) {
			return 1;
		} else if (getYear(date1) < getYear(date2)) {
			return -1;
		}

		if (getMonth(date1) > getMonth(date2)) {
			return 1;
		} else if (getMonth(date1) < getMonth(date2)) {
			return -1;
		}

		if (getDay(date1) > getDay(date2)) {
			return 1;
		} else if (getDay(date1) < getDay(date2)) {
			return -1;
		}

		return 0;
	}

	public static int getDay(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static Short getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return ((Integer) month).shortValue();
	}

	public static Short getYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return ((Integer) year).shortValue();
	}

	private static boolean isSimpleObjectType(Object object) {
		if (object instanceof String || object instanceof Long || object instanceof Short || object instanceof Integer
				|| object instanceof Double || object instanceof Boolean || object instanceof Byte
				|| object instanceof BigDecimal || object instanceof Date)
			return true;

		return false;
	}

	private static boolean isSimpleArray(Object object) {
		if (object instanceof long[] || object instanceof short[] || object instanceof int[]
				|| object instanceof double[] || object instanceof boolean[] || object instanceof byte[])
			return true;

		return false;
	}

	private static Object createArray(Object object) {
		if (object instanceof String[])
			return new String[(((String[]) object).length)];
		else if (object instanceof Long[]) {
			return new Long[(((Long[]) object).length)];
		} else if (object instanceof Short[]) {
			return new Short[(((Short[]) object).length)];
		} else if (object instanceof Integer[]) {
			return new Integer[(((Integer[]) object).length)];
		} else if (object instanceof Double) {
			return new Double[(((Double[]) object).length)];
		} else if (object instanceof Boolean) {
			return new Boolean[(((Boolean[]) object).length)];
		} else if (object instanceof Byte) {
			return new Byte[(((Byte[]) object).length)];
		} else if (object instanceof BigDecimal) {
			return new BigDecimal[(((BigDecimal[]) object).length)];
		}

		return new Object[(((Object[]) object).length)];
	}

	private static Object copyObject(Object propertyValue) {
		Class<?> propertyClass = propertyValue.getClass();

		if (propertyValue instanceof Date) {
			return new Date(((Date) propertyValue).getTime());
		}

		try {
			Constructor<?> propertyConstructor = propertyClass.getConstructor(String.class);

			return propertyConstructor.newInstance(propertyValue.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Object copyArrayList(Stack<Object> sourceStack, Stack<Object> targetStack, Object propertyValue) {
		Object propertyValueNew = new ArrayList<Object>();

		try {
			for (Object o : (ArrayList<Object>) propertyValue)
				if (o == null) {
					((ArrayList<Object>) propertyValueNew).add(null);
				} else if (isSimpleObjectType(o)) {
					Constructor<?> propertyConstructor = o.getClass().getConstructor(String.class);

					((ArrayList<Object>) propertyValueNew).add(propertyConstructor.newInstance(o.toString()));
				} else {
					Object element = o.getClass().newInstance();
					((ArrayList<Object>) propertyValueNew).add(element);

					sourceStack.push(o);
					targetStack.push(element);
				}

			return propertyValueNew;
		} catch (LazyInitializationException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Object copyList(Stack<Object> sourceStack, Stack<Object> targetStack, Object propertyValue) {
		Object propertyValueNew = new Vector<Object>();

		try {
			for (Object o : (List<Object>) propertyValue)
				if (o == null) {
					((List<Object>) propertyValueNew).add(null);
				} else if (isSimpleObjectType(o)) {
					Constructor<?> propertyConstructor = o.getClass().getConstructor(String.class);

					((List<Object>) propertyValueNew).add(propertyConstructor.newInstance(o.toString()));
				} else {
					Object element = o.getClass().newInstance();
					((List<Object>) propertyValueNew).add(element);

					sourceStack.push(o);
					targetStack.push(element);
				}

			return propertyValueNew;
		} catch (LazyInitializationException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Object copyArray(Stack<Object> sourceStack, Stack<Object> targetStack, Object propertyValue) {
		Object propertyValueNew = createArray(propertyValue);

		int i = 0;
		try {
			for (Object o : (Object[]) propertyValue)
				if (o == null) {
					((Object[]) propertyValueNew)[i++] = null;
				} else if (isSimpleObjectType(o)) {
					Constructor<?> propertyConstructor = o.getClass().getConstructor(String.class);

					((Object[]) propertyValueNew)[i++] = propertyConstructor.newInstance(o.toString());
				} else {
					Object element = o.getClass().newInstance();
					((Object[]) propertyValueNew)[i++] = element;

					sourceStack.push(o);
					targetStack.push(element);
				}

			return propertyValueNew;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Object copySimpleArray(Object object) {
		if (object instanceof long[]) {
			return ((long[]) object).clone();
		} else if (object instanceof short[]) {
			return ((short[]) object).clone();
		} else if (object instanceof int[]) {
			return ((int[]) object).clone();
		} else if (object instanceof double[]) {
			return ((double[]) object).clone();
		} else if (object instanceof boolean[]) {
			return ((boolean[]) object).clone();
		} else if (object instanceof byte[]) {
			return ((byte[]) object).clone();
		}

		return null;
	}

	private static Object copyUserObject(Stack<Object> sourceStack, Stack<Object> targetStack, Object propertyValue,
			List<Object> sourceList, List<Object> targetList) {
		try {
			Class<?> propertyClass = propertyValue.getClass();
			Object propertyValueNew = propertyClass.newInstance();

			if (!sourceList.contains(propertyValue)) {
				sourceList.add(propertyValue);
				targetList.add(propertyValueNew);
				sourceStack.push(propertyValue);
				targetStack.push(propertyValueNew);
			} else {
				for (int i = 0; i < sourceList.size(); i++)
					if (sourceList.get(i).equals(propertyValue))
						propertyValueNew = targetList.get(i);
			}

			return propertyValueNew;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Object copy(Object source) {
		List<Object> sourceList = new Vector<Object>();
		List<Object> targetList = new Vector<Object>();
		Stack<Object> sourceStack = new Stack<Object>();
		Stack<Object> targetStack = new Stack<Object>();

		Object sourceTop = source;
		Class<?> topClass = sourceTop.getClass();

		Object target = null;
		try {
			target = topClass.newInstance();
			Object targetTop = target;

			do {
				topClass = sourceTop.getClass();

				/* Duyệt các property của đối tượng trên đỉnh stack. */
				for (Field f : topClass.getDeclaredFields()) {
					f.setAccessible(true);
					Object propertyValue = f.get(sourceTop);
					Object propertyValueNew = null;

					if (propertyValue != null) {
						if (isSimpleObjectType(propertyValue)) {
							propertyValueNew = copyObject(propertyValue);
						} else if (propertyValue instanceof ArrayList<?>) {
							propertyValueNew = copyArrayList(sourceStack, targetStack, propertyValue);
						} else if (propertyValue instanceof List<?>) {
							propertyValueNew = copyList(sourceStack, targetStack, propertyValue);
						} else if (propertyValue instanceof Object[]) {
							propertyValueNew = copyArray(sourceStack, targetStack, propertyValue);
						} else if (isSimpleArray(propertyValue)) {
							propertyValueNew = copySimpleArray(propertyValue);
						} else {
							propertyValueNew = copyUserObject(sourceStack, targetStack, propertyValue, sourceList,
									targetList);
						}

						if ((f.getModifiers() & Modifier.FINAL) != Modifier.FINAL) {
							f.set(targetTop, propertyValueNew);
						}
					}
				}

				if (sourceStack.size() == 0)
					break;

				sourceTop = sourceStack.pop();
				targetTop = targetStack.pop();
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return target;
	}

	/**
	 * Tạo danh sách năm từ yearStart đến năm yearStart + increase, từ yearStart
	 * quay trở về yearStart - decrease
	 * 
	 * @author hoannd
	 * @CreateTime 10:43:09, 23-07-2009
	 * @param yearStartu
	 *            : mốc
	 * @param increase
	 *            : biến tăng
	 * @param decrease
	 *            : biến giảm
	 * @return List<Integer> : danh sách năm
	 */
	public static final List<Integer> createYearIncreaseOrDecreaseList(int yearStart, int increase, int decrease) {
		List<Integer> resultList = new Vector<Integer>();
		for (int i = increase; i >= 0; i--) {
			resultList.add(yearStart + i);
		}
		for (int i = 1; i <= decrease; i++) {
			resultList.add(yearStart - i);
		}
		return resultList;
	}

	/**
	 * Chuyển chuỗi số định dạng xxx.xxx,xxx sang số BigDecimal.
	 * 
	 * @param text
	 *            Chuỗi số.
	 * 
	 * @return Số kiểu BigDecimal.
	 */
	public static BigDecimal convertToBigDecimal(String text) {
		if (text.equals(""))
			return BigDecimal.ZERO;

		text = text.replace(".", "");
		text = text.replace(",", ".");

		return new BigDecimal(text);
	}

	public static boolean detectSQLInjection(String query) {
		// Regular expression pattern to detect common SQL injection keywords
		String sqlInjectionPattern = "\\b(union|select|insert|update|delete|drop|truncate|alter|create|grant|exec|else|then|case|when|like|or)\\b";

		Pattern pattern = Pattern.compile(sqlInjectionPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(query);

		return matcher.find();
	}

	public static String getNoidungTralai(String lydoTralai, String nguoiThuchien, String ngayTralai) throws ParseException {
		String ndxuly = "";
		String strDate = "";
		if (ngayTralai != null && !ngayTralai.equals("")) {
			DateFormat parseJsonDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
			Date dateFormat = parseJsonDate.parse(ngayTralai);
			DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			strDate = simpleDateFormat.format(dateFormat);
		}
		if (lydoTralai == null) {
			ndxuly = nguoiThuchien + " trả lại .[" + strDate + "]";
		} else {
			ndxuly = nguoiThuchien + " trả lại: " + lydoTralai + " [" + strDate + "]";
		}
		return ndxuly;
	}

	public static byte[] autoFitRowHeight(byte[] excelData, Integer row, Integer column) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(excelData);
			Workbook workbook = new XSSFWorkbook(bais);

			Sheet sheet = workbook.getSheetAt(0);
			// cell (x,y) bắt đầu từ 0;
			Row rowexl = sheet.getRow(row);
			for (int i = 0; i<= column; i++) {
				Cell cell = rowexl.getCell(i);
				CellStyle style = cell.getCellStyle();
				style = workbook.createCellStyle();
				style.setWrapText(true);
				cell.setCellStyle(style);
			}
			rowexl.setHeight((short) -1);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);

			workbook.close();
			bais.close();

			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] autoFitRowHeight(byte[] excelData) {
		try {
			// Load the Excel file from the byte array
			ByteArrayInputStream bais = new ByteArrayInputStream(excelData);
			Workbook workbook = new XSSFWorkbook(bais);

			// Access the first sheet (you can adjust this for other sheets)
			Sheet sheet = workbook.getSheetAt(0);

			// Loop through each row
			for (Row row : sheet) {
				// Enable word wrapping for each cell in the row
				for (Cell cell : row) {
					CellStyle style = cell.getCellStyle();
					if (style == null) {
						style = workbook.createCellStyle();
					}
					style.setWrapText(true);
					cell.setCellStyle(style);
				}
				// Set the row height to auto-fit the content
				row.setHeight((short) -1);
			}

			// Write the modified workbook to a ByteArrayOutputStream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);

			// Close resources
			workbook.close();
			bais.close();

			// Return the updated file as a byte array
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
