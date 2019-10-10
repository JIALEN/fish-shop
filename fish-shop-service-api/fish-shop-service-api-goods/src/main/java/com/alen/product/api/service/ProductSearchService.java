package com.alen.product.api.service;

import com.alen.base.BaseResponse;
import com.alen.product.output.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * @description: 商品搜索服务接口
 * @author alen
 * @create 2019-10-09 13:38
 **/
public interface ProductSearchService {

	@GetMapping("/search")
	public BaseResponse<List<ProductDto>> search(String name);

}
