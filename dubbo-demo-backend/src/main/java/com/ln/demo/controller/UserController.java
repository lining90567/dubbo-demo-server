package com.ln.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
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

import com.ln.demo.api.system.dto.UserDTO;
import com.ln.demo.api.system.service.UserService;
import com.ln.demo.common.data.Page;
import com.ln.demo.util.JwtUtils;
import com.ln.demo.vo.UserDetailVO;
import com.ln.demo.vo.UserListVO;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("")
    public ResponseEntity<UserDetailVO> saveUser(@RequestBody UserDetailVO vo, @RequestHeader(value="X-Token") String token) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");

        UserDTO dto = new UserDTO();
        dto.setName(vo.getName());
        dto.setLoginName(vo.getLoginName());
        dto.setCreatorId(Integer.parseInt(currentUserId));
        dto.setRoleIds(vo.getRoleIds());

        // 随机生成salt
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        Md5Hash md5 = new Md5Hash(vo.getPassword(), salt, 6);
        // 设置盐
        dto.setSalt(salt);
        // 设置新密码
        String md5Password = md5.toHex();
        dto.setPassword(md5Password);

        UserDTO user = userService.saveUser(dto);
        vo.setId(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vo);
    }

    @GetMapping("")
    public ResponseEntity<Page<UserListVO>> getUserList(@RequestParam(required = false) String name, @RequestParam(required = false) Boolean locked, 
            @RequestParam(required = false) String sort, int offset, int limit) {
        Map<String, Object> paremeters = new HashMap<String, Object>();
        if (!StringUtils.isBlank(name)) {
            paremeters.put("name", name);
        }
        if (locked != null) {
            paremeters.put("locked", locked);
        }
        Page<UserDTO> result = userService.listUser(paremeters, sort, offset, limit);
        List<UserListVO> voList = new ArrayList<UserListVO>();
        for(UserDTO dto : result.getRows()) {
            UserListVO vo = new UserListVO();
            BeanUtils.copyProperties(dto, vo);
            vo.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dto.getCreatedAt()));
            voList.add(vo);
        }
        return ResponseEntity.ok(new Page<UserListVO>(result.getTotal(), voList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailVO> getUserById(@PathVariable("id") int id) {
        UserDTO user = userService.getById(id);
        if (user != null) {
            UserDetailVO vo = new UserDetailVO();
            BeanUtils.copyProperties(user, vo);
            vo.setCreatedAt(user.getCreatedAt().toInstant().toString());
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUser(@PathVariable("id") int id, @RequestBody UserDetailVO vo) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(vo, dto);
        int rows = userService.updateUser(dto); 
        if (rows == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(rows);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable("id") int id) {
        return userService.removeUser(id) > 0 ? ResponseEntity.noContent().build() :
            ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/password")
    public ResponseEntity<Integer> changePassword(@PathVariable("id") int userId, String password) {
        // 随机生成salt
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        
        // Md5密码
        Md5Hash md5 = new Md5Hash(password, salt, 6);
        String md5Password = md5.toHex();
        
        int rows = userService.changePassword(userId, salt, md5Password);
        if(rows > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rows);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/me")
    public ResponseEntity<UserDetailVO> getCurrentUser(@RequestHeader(value="X-Token") String token) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");
        UserDTO dto = this.userService.getById(Integer.parseInt(currentUserId));
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }
        UserDetailVO vo = new UserDetailVO();
        BeanUtils.copyProperties(dto, vo);
        return ResponseEntity.ok(vo);
    }
    
    @PutMapping("/me")
    public ResponseEntity<Integer> updateCurrentUser(@RequestHeader(value="X-Token") String token, @RequestBody UserDetailVO userDetail) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");
        UserDTO dto = new UserDTO();
        dto.setId(Integer.parseInt(currentUserId));
        dto.setName(userDetail.getName());
        dto.setLoginName(userDetail.getLoginName());
        if(!StringUtils.isBlank(userDetail.getPassword())) {
            // 随机生成salt
            SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
            String salt = secureRandomNumberGenerator.nextBytes().toHex();
            
            // Md5密码
            Md5Hash md5 = new Md5Hash(userDetail.getPassword(), salt, 6);
            String md5Password = md5.toHex();
            dto.setSalt(salt);
            dto.setPassword(md5Password);
        }
        int rows = this.userService.updateCurrentUser(dto);
        return rows > 0 ? ResponseEntity.status(HttpStatus.CREATED).body(rows) :
            ResponseEntity.notFound().build();
    }

}
