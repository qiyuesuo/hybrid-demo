package com.qiyuesuo.hybrid.sample.response;

public class CompanyAuthResult {

	private Integer status;
	private String name;
	private String registerNo;
	private String legalPerson;
	private String applicantName;
	private String applicantPhone;

	public Integer getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public String getApplicantPhone() {
		return applicantPhone;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}
}
