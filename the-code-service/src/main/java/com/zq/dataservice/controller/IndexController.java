package com.zq.dataservice.controller;

import com.zq.dataservice.bean.Index;
import com.zq.dataservice.config.IpConfiguration;
import com.zq.dataservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
	@Autowired
	private IndexService indexService;
	@Autowired
    private IpConfiguration ipConfiguration;
	
//	http://127.0.0.1:8011/codes
	
	@GetMapping("/codes")
	@CrossOrigin
	public List<Index> codes() throws Exception {
		System.out.println("current instance's port is "+ ipConfiguration.getPort());
		return indexService.get();
	}
}

