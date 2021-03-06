package ec.gob.dinardap.remanente.dao;

import javax.ejb.Local;

import ec.gob.dinardap.persistence.dao.GenericDao;
import ec.gob.dinardap.remanente.modelo.Pregunta;

@Local
public interface PreguntaDao extends GenericDao<Pregunta, Integer> {

}
