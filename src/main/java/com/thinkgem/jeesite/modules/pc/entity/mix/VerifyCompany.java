package com.thinkgem.jeesite.modules.pc.entity.mix;

import com.thinkgem.jeesite.modules.pc.entity.Pccompany;
import com.thinkgem.jeesite.modules.pc.entity.VerifyData;

public class VerifyCompany {
	private VerifyData verify;
	private Pccompany company;

	public VerifyData getVerify() {
		return verify;
	}

	public void setVerify(VerifyData verify) {
		this.verify = verify;
	}

	public Pccompany getCompany() {
		return company;
	}

	public void setCompany(Pccompany company) {
		this.company = company;
	}

}
