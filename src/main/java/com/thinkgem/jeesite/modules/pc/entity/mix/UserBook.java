package com.thinkgem.jeesite.modules.pc.entity.mix;

import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.Pcuser;

public class UserBook {
	private Pcuser pcuser;
	private AddressBook book;

	public Pcuser getPcuser() {
		return pcuser;
	}

	public void setPcuser(Pcuser pcuser) {
		this.pcuser = pcuser;
	}

	public AddressBook getBook() {
		return book;
	}

	public void setBook(AddressBook book) {
		this.book = book;
	}

	
}
