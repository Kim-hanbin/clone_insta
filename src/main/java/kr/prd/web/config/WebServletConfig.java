package kr.prd.web.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;


@Configuration
public class WebServletConfig implements WebMvcConfigurer {
	
	
	//인코딩
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return encodingFilter;
	}
	
	
	//업로드 기능 설정
	@Bean
	public MultipartConfigElement   multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		
		factory.setMaxFileSize(  DataSize.of(100, DataUnit.MEGABYTES)  );
		factory.setMaxRequestSize(  DataSize.of(100, DataUnit.MEGABYTES)  );
		
		return factory.createMultipartConfig();
		
	}
	
	  //업로드를 실행할 리졸버 
	   @Bean
	    public MultipartResolver multipartResolver(){
	        return new StandardServletMultipartResolver();
	    }


}
