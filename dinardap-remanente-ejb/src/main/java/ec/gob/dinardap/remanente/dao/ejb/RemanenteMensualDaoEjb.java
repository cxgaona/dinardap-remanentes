package ec.gob.dinardap.remanente.dao.ejb;

import ec.gob.dinardap.remanente.dao.RemanenteMensualDao;
import ec.gob.dinardap.remanente.modelo.RemanenteMensual;

import javax.ejb.Stateless;

@Stateless(name = "RemanenteMensualDao")
public class RemanenteMensualDaoEjb extends RemanenteGenericDao<RemanenteMensual, Integer> implements RemanenteMensualDao {

    public RemanenteMensualDaoEjb() {
        super(RemanenteMensual.class);
    }
}
