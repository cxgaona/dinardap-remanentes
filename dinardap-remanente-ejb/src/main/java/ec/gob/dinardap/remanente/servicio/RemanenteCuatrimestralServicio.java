package ec.gob.dinardap.remanente.servicio;

import javax.ejb.Local;

import ec.gob.dinardap.persistence.servicio.GenericService;
import ec.gob.dinardap.remanente.modelo.RemanenteAnual;
import ec.gob.dinardap.remanente.modelo.RemanenteCuatrimestral;
import ec.gob.dinardap.remanente.modelo.RemanenteCuatrimestralPK;
import java.util.Date;
import java.util.List;

@Local
public interface RemanenteCuatrimestralServicio extends GenericService<RemanenteCuatrimestral, RemanenteCuatrimestralPK> {

    public List<RemanenteCuatrimestral> getRemanenteCuatrimestralList();

    public List<RemanenteCuatrimestral> getRemanenteCuatrimestralListByAño(RemanenteAnual remanenteAnual);

    public RemanenteAnual getRemanenteAnual(RemanenteCuatrimestral remanenteCuatrimestral);

    public void createRemanenteCuatrimestral(Date fecha, Integer institucionId);

}