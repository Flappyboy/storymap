package cn.edu.nju.software.storymapping.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.software.storymapping.map.controller.mockdto.RoleDto;
import cn.edu.nju.software.storymapping.map.entity.Role;
import cn.edu.nju.software.storymapping.map.service.RoleService;
import cn.edu.nju.software.storymapping.system.dto.Response;

@RestController
@RequestMapping(value = "/api")
public class RoleController {
	@Autowired
	RoleService roleService;

	@PostMapping(value = "/role")
	public Response addRole(RoleDto dto) {
		if (dto == null)
			return Response.createDefaultResponse().fail("dto为null");
		Role role = wrapRoleDto(dto);
		roleService.insertRole(role);
		dto.setId(new Long(role.getId()));
		return Response.createDefaultResponse().success(dto);
	}

	@PutMapping(value = "/role")
	public Response updateRole(RoleDto dto) {
		if (dto == null) {
			return Response.createDefaultResponse().fail("dto为null");
		}
		if (dto.getId() == null)
			return Response.createDefaultResponse().fail("dto的id为null");
		Role role = wrapRoleDto(dto);
		roleService.updateRole(role);
		return Response.createDefaultResponse().success(dto);

	}

	@PostMapping(value = "/role_activity")
	public Response addRole_Activity(Integer role_id, Integer activity_id) {
		if (role_id == null || activity_id == null)
			return Response.createDefaultResponse().fail("id为null");
		roleService.addRole_Activity(role_id, activity_id);
		return Response.createDefaultResponse().success(null);
	}

	@DeleteMapping("/role_activity/{id}")
	public Response deleteRole_Activity(@PathVariable Integer id) {
		roleService.deleteRole_Activity(id);
		return Response.createDefaultResponse().success(null);
	}

	@DeleteMapping("/role/{id}")
	public Response deleteRole(@PathVariable Integer id) {
		roleService.deleteRole(id);
		return Response.createDefaultResponse().success(null);
	}

	public Role wrapRoleDto(RoleDto dto) {
		Role role = new Role();
		if (dto.getId() != null)
			role.setId(dto.getId().intValue());
		role.setImageId(dto.getImageId());
		role.setName(dto.getTitle());
		role.setStoryMapId(dto.getStoryMapId());
		return role;
	}

}
