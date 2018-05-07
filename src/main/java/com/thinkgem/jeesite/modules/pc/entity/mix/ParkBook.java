package com.thinkgem.jeesite.modules.pc.entity.mix;

import com.thinkgem.jeesite.modules.pc.entity.AddressBook;
import com.thinkgem.jeesite.modules.pc.entity.Pclogispark;

public class ParkBook {
	private Pclogispark park;
	private AddressBook book;

	public Pclogispark getPark() {
		return park;
	}

	public void setPark(Pclogispark park) {
		this.park = park;
	}

	public AddressBook getBook() {
		return book;
	}

	public void setBook(AddressBook book) {
		this.book = book;
	}
}
