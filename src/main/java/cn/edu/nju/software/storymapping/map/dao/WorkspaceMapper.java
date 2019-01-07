package cn.edu.nju.software.storymapping.map.dao;

import java.util.List;
import cn.edu.nju.software.storymapping.map.entity.Workspace;

public interface WorkspaceMapper {
	public void insert(Workspace Workspace);

	public void delete(int id);
	
	public void update(Workspace Workspace);
	
	public Workspace getById(int id);

	public List<Workspace> listAll();

	public List<Workspace> listByUserId(int id);
}
