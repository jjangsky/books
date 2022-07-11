package com.bukkeubook.book.manage.model.dto.empPay;

public class sevPayDTO {

	private int sevMonthPay;	// 3개월급여총액
	private int sevAvgPay;		// 평균임금
	private int sevRealPay;		// 실지급액
	private int empNo;			// 사원번호
	
	public sevPayDTO() {
	}
	
	public sevPayDTO(int sevMonthPay, int sevAvgPay, int sevRealPay, int empNo) {
		super();
		this.sevMonthPay = sevMonthPay;
		this.sevAvgPay = sevAvgPay;
		this.sevRealPay = sevRealPay;
		this.empNo = empNo;
	}
	
	public int getSevMonthPay() {
		return sevMonthPay;
	}
	public void setSevMonthPay(int sevMonthPay) {
		this.sevMonthPay = sevMonthPay;
	}
	public int getSevAvgPay() {
		return sevAvgPay;
	}
	public void setSevAvgPay(int sevAvgPay) {
		this.sevAvgPay = sevAvgPay;
	}
	public int getSevRealPay() {
		return sevRealPay;
	}
	public void setSevRealPay(int sevRealPay) {
		this.sevRealPay = sevRealPay;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	@Override
	public String toString() {
		return "sevPayDTO [sevMonthPay=" + sevMonthPay + ", sevAvgPay=" + sevAvgPay + ", sevRealPay=" + sevRealPay
				+ ", empNo=" + empNo + "]";
	}
	
	
}