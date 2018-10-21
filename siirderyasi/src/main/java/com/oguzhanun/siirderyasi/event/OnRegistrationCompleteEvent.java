package com.oguzhanun.siirderyasi.event;


import java.util.Locale;
import org.springframework.context.ApplicationEvent;

import com.oguzhanun.siirderyasi.entity.Uye;


public class OnRegistrationCompleteEvent extends ApplicationEvent {

	
	private static final long serialVersionUID = -7006438570020078398L;

	private String appUrl;
    
	private Locale locale;
    
    private Uye uye;
    
    
	public OnRegistrationCompleteEvent( Uye uye, Locale locale, String appUrl) {
		
		super(uye);
		
		this.appUrl = appUrl;
		this.locale = locale;
		this.uye = uye;
		System.out.println("onRegistreationComplete'de ...");
	}

	
	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public Uye getUye() {
		return uye;
	}


	public void setUye(Uye uye) {
		this.uye = uye;
	}


	@Override
	public String toString() {
		return "OnRegistrationCompleteEvent [appUrl=" + appUrl + ", locale=" + locale + ", musteri=" + uye + "]";
	}

}
