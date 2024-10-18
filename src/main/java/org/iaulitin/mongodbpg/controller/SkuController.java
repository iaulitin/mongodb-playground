package org.iaulitin.mongodbpg.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iaulitin.mongodbpg.common.exceptions.ServiceException;
import org.iaulitin.mongodbpg.dto.CreateSkuBulkRequest;
import org.iaulitin.mongodbpg.dto.CreateSkuRequest;
import org.iaulitin.mongodbpg.dto.SkuDto;
import org.iaulitin.mongodbpg.service.SkuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/skus")
public class SkuController extends BaseController {
    private final SkuService skuService;

    @PostMapping
    public ResponseEntity<SkuDto> create(@RequestBody CreateSkuRequest createSkuRequest) throws ServiceException {
        SkuDto skuDto = skuService.createSku(createSkuRequest);
        return ResponseEntity.ok(skuDto);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<SkuDto>> createBulk(@RequestBody CreateSkuBulkRequest createSkuBulkRequest) throws ServiceException {
        List<SkuDto> skuDto = skuService.createSkuBulk(createSkuBulkRequest);
        return ResponseEntity.ok(skuDto);
    }

}
