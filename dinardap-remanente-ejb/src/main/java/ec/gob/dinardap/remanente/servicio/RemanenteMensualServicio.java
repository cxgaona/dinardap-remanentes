package ec.gob.dinardap.remanente.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gob.dinardap.persistence.servicio.GenericService;
import ec.gob.dinardap.remanente.modelo.RemanenteMensual;

@Local
public interface RemanenteMensualServicio extends GenericService<RemanenteMensual, Integer> {

    public List<RemanenteMensual> getRemanenteMensualByInstitucion(Integer institucionId,Integer año);

}