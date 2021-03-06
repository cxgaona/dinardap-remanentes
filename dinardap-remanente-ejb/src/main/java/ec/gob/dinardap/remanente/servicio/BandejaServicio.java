package ec.gob.dinardap.remanente.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.gob.dinardap.persistence.servicio.GenericService;
import ec.gob.dinardap.remanente.dto.BandejaDTO;
import ec.gob.dinardap.remanente.modelo.Bandeja;
import ec.gob.dinardap.remanente.modelo.InstitucionRequerida;
import ec.gob.dinardap.remanente.modelo.Usuario;

@Local
public interface BandejaServicio extends GenericService<Bandeja, Integer> {   

    public List<BandejaDTO> getBandejaByUsuarioAñoMes(Integer usuarioId, Integer año, Integer mes);
    
    public void crearBandeja(Bandeja bandeja);
    
    public void editBandeja(Bandeja bandeja);
    
    public void generarNotificacion(List<Usuario> usuarioAsignadoList, Integer usuarioSolicitanteId,
            Integer remanenteCuatrimestralId, Integer remanenteAnualId, InstitucionRequerida institucion,
            Integer remanenteMensualId, String descripcion, String estado);

    public void editBandeja(BandejaDTO bandejaDTO);

}
