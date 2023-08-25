package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class yymm {
	private String year;
	private String month;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void MinusCurrent() {
		int intmonth = Integer.parseInt(this.month);
		int intyear = Integer.parseInt(this.year);
		intmonth = intmonth - 1;
		if (intmonth == 0) {
			intmonth = 12;
			intyear = intyear - 1;
		}

		if (intmonth < 10) {
			this.month = "0" + intmonth;
		} else {
			this.month = "" + intmonth;
		}
		this.year = "" + intyear;

	}

	public void PlusCurrent() {
		int intmonth = Integer.parseInt(this.month);
		int intyear = Integer.parseInt(this.year);
		intmonth = intmonth + 1;
		if (intmonth == 13) {
			intmonth = 1;
			intyear = intyear + 1;
		}

		if (intmonth < 10) {
			this.month = "0" + intmonth;

		} else {
			this.month = "" + intmonth;
		}
		this.year = "" + intyear;
	}

}
