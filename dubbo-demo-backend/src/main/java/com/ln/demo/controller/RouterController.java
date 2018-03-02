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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ln.demo.api.system.dto.RouterDTO;
import com.ln.demo.api.system.service.RouterService;
import com.ln.demo.common.data.Page;
import com.ln.demo.util.JwtUtils;
import com.ln.demo.vo.RouterDetailVO;
import com.ln.demo.vo.RouterListVO;
import com.ln.demo.vo.RouterNavVO;

@RestController
@RequestMapping("/routers")
public class RouterController extends BaseController {
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private RouterService routerService;
    
    @GetMapping("")
    public ResponseEntity<Page<RouterListVO>> listRouter(@RequestParam(required = false) String name, int offset, int limit) {
        Map<String, Object> paremeters = new HashMap<String, Object>();
        if (!StringUtils.isBlank(name)) {
            paremeters.put("name", name);
        }
        Page<RouterDTO> result = routerService.listRouter(paremeters, offset, limit);
        List<RouterListVO> voList = new ArrayList<RouterListVO>(result.getRows().size());
        for(RouterDTO dto : result.getRows()) {
            RouterListVO vo = new RouterListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(new Page<RouterListVO>(result.getTotal(), voList));
    }
    
    @GetMapping("/authorized")
    public ResponseEntity<List<RouterNavVO>> listAuthorizedRouter(@RequestHeader(value="X-Token") String token) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");
        List<RouterDTO> dtoList = routerService.listAuthorizedRouter(Integer.parseInt(currentUserId));
        List<RouterNavVO> voList = new ArrayList<RouterNavVO>(dtoList.size());
        for(RouterDTO dto : dtoList) {
            RouterNavVO vo = new RouterNavVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RouterDetailVO> getById(@PathVariable("id") int id) {
        RouterDTO dto = this.routerService.getById(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }
        RouterDetailVO vo = new RouterDetailVO();
        BeanUtils.copyProperties(dto, vo);
        return ResponseEntity.ok(vo);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<RouterListVO>> listByParentId(@RequestParam() int parentId) {
        List<RouterDTO> dtoList = this.routerService.listByParentId(parentId);
        List<RouterListVO> voList = new ArrayList<RouterListVO>(dtoList.size());
        for(RouterDTO dto : dtoList) {
            RouterListVO vo = new RouterListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateRouter(@PathVariable("id") int id, @RequestBody RouterDetailVO vo) {
        RouterDTO dto = new RouterDTO();
        BeanUtils.copyProperties(vo, dto);
        int rows = this.routerService.updateRouter(dto);
        return rows == 0 ? ResponseEntity.notFound().build() : ResponseEntity.status(HttpStatus.CREATED).body(rows);
    }
    
    @PostMapping("")
    public ResponseEntity<RouterDetailVO> saveRouter(@RequestBody RouterDetailVO vo) {
        RouterDTO dto = new RouterDTO();
        BeanUtils.copyProperties(vo, dto);
        vo.setId(this.routerService.saveRouter(dto).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> removeRouter(@PathVariable("id") int id) {
        return this.routerService.removeRouter(id) > 0 ? 
                ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RouterListVO>> listAll(@RequestParam(required = false) Boolean includeLocked) {
        List<RouterDTO> dtoList = this.routerService.listAll(includeLocked == null ? false : includeLocked);
        List<RouterListVO> voList = new ArrayList<RouterListVO>();
        for(RouterDTO dto : dtoList) {
            RouterListVO vo = new RouterListVO();
            BeanUtils.copyProperties(dto, vo);
            voList.add(vo);
        }
        return ResponseEntity.ok(voList);
    }

}
