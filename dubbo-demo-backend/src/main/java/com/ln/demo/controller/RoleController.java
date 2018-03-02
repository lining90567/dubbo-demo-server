package com.ln.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ln.demo.api.system.dto.PermissionDTO;
import com.ln.demo.api.system.dto.RoleDTO;
import com.ln.demo.api.system.service.RoleService;
import com.ln.demo.common.data.Page;
import com.ln.demo.vo.RoleDetailVO;
import com.ln.demo.vo.RoleListVO;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {
    
    @Autowired
    private RoleService roleService;
	
    @GetMapping("")
	public ResponseEntity<Page<RoleListVO>> getRoleList(@RequestParam(required = false) String name, 
	        int offset, int limit) {
        Map<String, Object> paremeters = new HashMap<String, Object>();
        if (!StringUtils.isBlank(name)) {
            paremeters.put("name", name);
        }
        Page<RoleDTO> result = this.roleService.listRole(paremeters, offset, limit);
        List<RoleListVO> voList = new ArrayList<RoleListVO>();
        for(RoleDTO dto : result.getRows()) {
            RoleListVO vo = new RoleListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(new Page<RoleListVO>(result.getTotal(), voList));
	}
    
    @PostMapping("")
    public ResponseEntity<RoleDetailVO> saveRole(@RequestBody RoleDetailVO vo) {
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(vo, dto);
        this.roleService.saveRole(dto);
        vo.setId(dto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoleDetailVO> getById(@PathVariable("id") int id) {
        RoleDTO dto = this.roleService.getById(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }
        
        RoleDetailVO vo = new RoleDetailVO();
        BeanUtils.copyProperties(dto, vo);
        return ResponseEntity.ok(vo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateRole(@PathVariable("id") int id, @RequestBody RoleDetailVO vo) {
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(vo, dto);
        int rows = this.roleService.updateRole(dto);
        return rows == 0 ? ResponseEntity.notFound().build() :
            ResponseEntity.status(HttpStatus.CREATED).body(rows);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RoleListVO>> listAllRole() {
        List<RoleDTO> dtoList = this.roleService.listAllRole();
        if(dtoList.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<RoleListVO> voList = new ArrayList<RoleListVO>(dtoList.size());
        for(RoleDTO dto : dtoList) {
            RoleListVO vo = new RoleListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeRole(@PathVariable("id") int id) {
        return this.roleService.removeRole(id) > 0 ?
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/resourcePermissions")
    public ResponseEntity<List<Integer>> listResourcePermissionByRoleId(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.roleService.listResourcePermission(id));
    }
    
    @PostMapping("/{id}/permissions")
    public ResponseEntity<?> savePermission(@PathVariable("id") int id, @RequestBody PermissionDTO permissionDTO) {
        int rows = this.roleService.savePermission(id, permissionDTO.getRouterIds(), permissionDTO.getResourceIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(rows);
    }

}