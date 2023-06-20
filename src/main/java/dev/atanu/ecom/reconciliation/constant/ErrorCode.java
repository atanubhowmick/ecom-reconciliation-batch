/**
 * 
 */
package dev.atanu.ecom.reconciliation.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	RECONCILIATION_E001("No Product found"), 
	RECONCILIATION_E002("No Search result found"),
	RECONCILIATION_E003("Invalid Request. Page cannot be less than 0."),
	RECONCILIATION_E004("Invalid Request. Size cannot be less than 1."),
	RECONCILIATION_E005("Invalid Request. All fields are mandatory inside Filter."),
	RECONCILIATION_E006("Invalid Request. All fields are mandatory inside Search."),
	RECONCILIATION_E007("Invalid Json"),
	RECONCILIATION_E008("Invalid Request"),
	
	RECONCILIATION_E500("Internal Server Error. Please try again later!");
	
	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
