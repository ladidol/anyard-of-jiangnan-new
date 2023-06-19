package org.cuit.fhzheng.leaf.feign;

import org.cuit.fhzheng.api.leaf.feign.SegmentFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.leaf.common.Result;
import org.cuit.fhzheng.leaf.common.Status;
import org.cuit.fhzheng.leaf.exception.LeafServerException;
import org.cuit.fhzheng.leaf.exception.NoKeyException;
import org.cuit.fhzheng.leaf.service.SegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


/**
 * @author  
 * @date 2020/7/15
 */
@RestController
public class SegmentFeignController implements SegmentFeignClient {

	private static final Logger logger = LoggerFactory.getLogger(SegmentFeignController.class);


	@Autowired
	private SegmentService segmentService;

	@Override
	public ServerResponseEntity<Long> getSegmentId(String key) {
		return ServerResponseEntity.success(get(key, segmentService.getId(key)));
	}


	private Long get(String key, Result id) {
		Result result;
		if (key == null || key.isEmpty()) {
			throw new NoKeyException();
		}
		result = id;
		if (Objects.equals(result.getStatus(), Status.EXCEPTION)) {
			throw new LeafServerException(result.toString());
		}
		return result.getId();
	}
}
