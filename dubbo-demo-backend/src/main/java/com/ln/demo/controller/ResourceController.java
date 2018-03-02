package com.ln.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ln.demo.api.system.dto.AvailableResourceDTO;
import com.ln.demo.api.system.dto.ResourceDTO;
import com.ln.demo.api.system.dto.ResourcePermissionDTO;
import com.ln.demo.api.system.service.ResourceService;
import com.ln.demo.util.JwtUtils;
import com.ln.demo.vo.AvailableResourceListVO;
import com.ln.demo.vo.ResourceDetailVO;
import com.ln.demo.vo.ResourceListVO;
import com.ln.demo.vo.ResourcePermissionVO;

/**
 * 资源控制器
 * 
 * @author Lining
 * @date 2018/2/11
 */
@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {
    
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @GetMapping("")
    public ResponseEntity<List<ResourceListVO>> listResource(@RequestParam() int routerId) {
        List<ResourceDTO> dtoList = this.resourceService.listByRouterId(routerId);
        List<ResourceListVO> voList = new ArrayList<ResourceListVO>(dtoList.size());
        for(ResourceDTO dto : dtoList) {
            ResourceListVO vo = new ResourceListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResourceDetailVO> getById(@PathVariable("id") int id) {
        ResourceDTO dto = this.resourceService.getById(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }
        ResourceDetailVO vo = new ResourceDetailVO();
        BeanUtils.copyProperties(dto, vo);
        return ResponseEntity.ok(vo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateResource(@RequestBody ResourceDetailVO vo) {
        ResourceDTO dto = new ResourceDTO();
        BeanUtils.copyProperties(vo, dto);
        int rows = this.resourceService.updateResource(dto);
        return rows == 0 ? ResponseEntity.notFound().build() : 
            ResponseEntity.status(HttpStatus.CREATED).body(rows);
    }
    
    @PostMapping("")
    public ResponseEntity<ResourceDetailVO> saveResource(@RequestBody ResourceDetailVO vo) {
        ResourceDTO dto = new ResourceDTO();
        BeanUtils.copyProperties(vo, dto);
        int id = this.resourceService.saveResource(dto);
        vo.setId(id);
        return ResponseEntity.ok(vo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeResource(@PathVariable("id") int id) {
        int rows = this.resourceService.removeResource(id);
        return rows == 0 ? ResponseEntity.notFound().build() : 
            ResponseEntity.noContent().build();
    }
    
    @GetMapping("/permissions")
    public ResponseEntity<List<ResourcePermissionVO>> listResourcePermission(
            @RequestHeader(value="X-Token") String token, @RequestParam(required = false) int routerId) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");
        List<ResourcePermissionDTO> dtoList = resourceService.listPermission(Integer.parseInt(currentUserId), routerId);
        List<ResourcePermissionVO> voList = new ArrayList<ResourcePermissionVO>();
        for(ResourcePermissionDTO dto : dtoList) {
            ResourcePermissionVO vo = new ResourcePermissionVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<AvailableResourceListVO>> listAvailableResource() {
        List<AvailableResourceDTO> dtoList = this.resourceService.listAllAvailable();
        List<AvailableResourceListVO> voList = new ArrayList<AvailableResourceListVO>();
        for(AvailableResourceDTO dto : dtoList) {
            AvailableResourceListVO vo = new AvailableResourceListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }

}
