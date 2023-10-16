/**
 * 
 */
package dev.atanu.ecom.reconciliation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.atanu.ecom.reconciliation.constant.ErrorCode;
import dev.atanu.ecom.reconciliation.constant.ReconciliationConstant;
import dev.atanu.ecom.reconciliation.exception.ReconciliationException;

/**
 * @author Atanu Bhowmick
 *
 */

public class ReconciliationUtil {

	private static final ObjectMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(ReconciliationUtil.class);

	static {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Default Constructor
	 */
	private ReconciliationUtil() {
	}

	/**
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return T
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new ReconciliationException(ErrorCode.RECONCILIATION_E006.name(), ErrorCode.RECONCILIATION_E006.getErrorMsg(), e);
		}
		return t;
	}

	/**
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param typeReference
	 * @return T
	 */
	public static <T> T toObject(String json, TypeReference<T> typeReference) {
		T t = null;
		try {
			t = mapper.readValue(json, typeReference);
		} catch (JsonProcessingException e) {
			throw new ReconciliationException(ErrorCode.RECONCILIATION_E006.name(), ErrorCode.RECONCILIATION_E006.getErrorMsg(), e);
		}
		return t;
	}

	/**
	 * Convert object to string using jackson mapper
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		String str = ReconciliationConstant.EMPTY_STRING;
		try {
			str = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("Error while converting object to string", e);
		}
		return str;
	}

}
