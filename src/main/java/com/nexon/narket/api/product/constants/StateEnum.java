package com.nexon.narket.api.product.constants;

public enum StateEnum {

	/**
	 * 거래 중(100001)
	 */
	INPROGRESS("100001", "거래 중"),
	
	/**
	 * 예약 중(100002)
	 */
	BOOK("100002", "예약 중"),
	
	/**
	 * 거래 완료(100003)
	 */
	COMPLETE("100003", "거래 완료");
	
	private String code;
	private String message;
	
	private StateEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static StateEnum codeOf(String code) {
		StateEnum result = null;
        for(StateEnum value: StateEnum.values()) {
            if(value.getCode().equals(code)){
                result = value;
            }
        }
        return result;
    }
	
	public String getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
}
