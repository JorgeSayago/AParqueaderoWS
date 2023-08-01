package ec.edu.ups.ppw.negocio;

import java.util.List;

import ec.edu.ups.ppw.dao.LugarParqueoDAO;
import ec.edu.ups.ppw.modelo.LugarParqueo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionLugarParqueo {
	
	@Inject
	private LugarParqueoDAO daoLugarParqueo;
	
	public void guardarLugar(LugarParqueo lugarParqueo) throws Exception{
		if(daoLugarParqueo.read(lugarParqueo.getCodigo())==null) {
			try {
				daoLugarParqueo.insert(lugarParqueo);
			}catch(Exception e) {
				throw new Exception("Error al insertar: "+e.getMessage());
			}
			}else {
				try {
					daoLugarParqueo.update(lugarParqueo);
				} catch (Exception e) {
					throw new Exception("Error al actualizar: " + e.getMessage());
				}
			}
		}
		
	public void eliminarLugar(LugarParqueo lugarParqueo) throws Exception {
		LugarParqueo lugarParqueo1=daoLugarParqueo.read(lugarParqueo.getCodigo());
		if (lugarParqueo1 == null) {
			 throw new Exception("El lugar  con el codigo " + lugarParqueo.getCodigo() + " no existe");
		}
		try {
			daoLugarParqueo.delete(lugarParqueo.getCodigo());
		} catch (Exception e) {
			 throw new Exception("Error al eliminar: " + e.getMessage());
		}
	}
	public void actualizarLugar(LugarParqueo lugarParqueo) throws Exception{
		
		LugarParqueo lugarExistente =daoLugarParqueo.read(lugarParqueo.getCodigo());
		if (lugarExistente ==null) {
			throw new Exception("El lugar  con el codigo " + lugarParqueo.getCodigo() + " no existe");
		
		}
		try {
			daoLugarParqueo.update(lugarParqueo);
		} catch (Exception e) {
			throw new Exception("Error al actualizar: " + e.getMessage());
		}
	}
	
	public List<LugarParqueo>getParqueos(){
		return daoLugarParqueo.getAll();
	}	
	}


