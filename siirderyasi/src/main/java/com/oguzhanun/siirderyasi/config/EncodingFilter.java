package com.oguzhanun.siirderyasi.config;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.web.filter.GenericFilterBean;


public class EncodingFilter extends GenericFilterBean implements Filter {

	//Bu filtrenin kullanılma sebebi şudur: Spring MVC ile yapılan uygulamalarda
	//kullanılan formlar (muhtemelen sadece method="post" iken- method=get için server'ın xml 
	//configuration'larında bir düzeltme gerekebilir) Spring tarafından otomatik olarak 
	//iso-8859-1 character kodlaması ile işlenmektedir. Yani ne veri tabanı ne de html sayfalarındaki
	//düzeltme çabaları bu yüzden işe yaramamaktadır. Dolayısıyla arada sayfalar çıkan formların
	//buradaki filtreler vasıtasıyla düzeltilmesine yani sprin'in default character kodlamasının 
	//ayarlanmasına ihtiyaç vardır...
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 request.setCharacterEncoding("UTF-8");
	     response.setCharacterEncoding("UTF-8");
	     chain.doFilter(request, response);
	}
}
