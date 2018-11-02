package com.apibucket.loginapi.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="email-service-v1")
@RibbonClient(name="email-service-v1")
public interface EmailServiceProxy {
	
	@PostMapping("/email/accountverify/{to}")
	public void verifyAccount(@RequestHeader("Authorization")String Authorization,@PathVariable(value="to")String to);
	@PostMapping("/email/retrive")
	public void retriveEmail(@RequestHeader("Authorization")String Authorization) ;
}
