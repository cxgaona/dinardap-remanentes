package ec.gob.dinardap.remanente.servicio.impl;

import ec.gob.dinardap.persistence.constante.CriteriaTypeEnum;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.gob.dinardap.persistence.dao.GenericDao;
import ec.gob.dinardap.persistence.servicio.impl.GenericServiceImpl;
import ec.gob.dinardap.persistence.util.Criteria;
import ec.gob.dinardap.remanente.dao.InstitucionRequeridaDao;
import ec.gob.dinardap.remanente.dao.RemanenteMensualDao;
import ec.gob.dinardap.remanente.modelo.EstadoRemanenteMensual;
import ec.gob.dinardap.remanente.modelo.RemanenteMensual;
import ec.gob.dinardap.remanente.modelo.Tramite;
import ec.gob.dinardap.remanente.modelo.Transaccion;
import ec.gob.dinardap.remanente.servicio.RemanenteMensualServicio;
import java.util.ArrayList;
import java.util.Date;

@Stateless(name = "RemanenteMensualServicio")
public class RemanenteMensualServicioImpl extends GenericServiceImpl<RemanenteMensual, Integer> implements RemanenteMensualServicio {

    @EJB
    private RemanenteMensualDao remanenteMensualDao;

    @EJB
    private InstitucionRequeridaDao institucionRequeridaDao;

    @Override
    public GenericDao<RemanenteMensual, Integer> getDao() {
        return remanenteMensualDao;
    }

    @Override
    public List<RemanenteMensual> getRemanenteMensualByInstitucion(Integer institucionID, Integer año) {        
        List<RemanenteMensual> remanenteMensualList = new ArrayList<RemanenteMensual>();
        String[] criteriaNombres = {"remanenteCuatrimestral.remanenteAnual.institucionRequerida.institucionId",
            "remanenteCuatrimestral.remanenteAnual.anio"};
        CriteriaTypeEnum[] criteriaTipos = {CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS};
        Object[] criteriaValores = {institucionID, año};
        String[] orderBy = {"mes"};
        boolean[] asc = {false};
        Criteria criteria = new Criteria(criteriaNombres, criteriaTipos, criteriaValores, orderBy, asc);
        remanenteMensualList = findByCriterias(criteria);
        for (RemanenteMensual rm : remanenteMensualList) {
            for (EstadoRemanenteMensual erm : rm.getEstadoRemanenteMensualList()) {
                erm.getEstadoRemanenteMensualId();
            }
        }
        for (RemanenteMensual remanenteMensual : remanenteMensualList) {
            for (Transaccion transaccion : remanenteMensual.getTransaccionList()) {
                transaccion.getTransaccionId();
                for (Tramite tramite : transaccion.getTramiteList()) {
                    tramite.getTramiteId();
                }
            }
        }
        return remanenteMensualList;
    }

    @Override
    public void editRemanenteMensual(RemanenteMensual remanenteMensual) {
        this.update(remanenteMensual);
    }

    @Override
    public List<RemanenteMensual> getRemanenteMensualByInstitucionAñoMes(Integer idInstitucion, Integer anio, Integer mes) {
        List<RemanenteMensual> remanenteMensualList = new ArrayList<RemanenteMensual>();
        String[] criteriaNombres = {"remanenteCuatrimestral.remanenteAnual.institucionRequerida.institucionId",
            "remanenteCuatrimestral.remanenteAnual.anio", "mes"};
        CriteriaTypeEnum[] criteriaTipos = {CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS};
        Object[] criteriaValores = {idInstitucion, anio, mes};
        String[] orderBy = {"mes"};
        boolean[] asc = {false};
        Criteria criteria = new Criteria(criteriaNombres, criteriaTipos, criteriaValores, orderBy, asc);
        remanenteMensualList = findByCriterias(criteria);
        for (RemanenteMensual rm : remanenteMensualList) {
            for (EstadoRemanenteMensual erm : rm.getEstadoRemanenteMensualList()) {
                erm.getEstadoRemanenteMensualId();
            }
        }
        return remanenteMensualList;
    }

    @Override
    public void crearVersionRemanenteMensual(RemanenteMensual remanenteMensualOrigen) {
        RemanenteMensual rm = new RemanenteMensual();
        RemanenteMensual rmo = new RemanenteMensual(remanenteMensualOrigen.getRemanenteMensualId());
        RemanenteMensual rmn = new RemanenteMensual();
        rm = remanenteMensualOrigen;
        rm.setRemanenteMensualOrigenId(rmo);
        rm.setRemanenteMensualId(null);
        rm.setComentarios(null);
        rm.setSolicitudCambioUrl(null);
        rm.setInformeAprobacionUrl(null);
        rm.setFechaRegistro(new Date());
        create(rm);        
    }

    @Override
    public RemanenteMensual obtenerVersionRemanenteMensual(Integer remanenteMensualOrigen) {
        List<RemanenteMensual> remanenteMensualList = new ArrayList<RemanenteMensual>();
        String[] criteriaNombres = {"remanenteMensualOrigenId"};
        CriteriaTypeEnum[] criteriaTipos = {CriteriaTypeEnum.INTEGER_EQUALS};
        Object[] criteriaValores = {remanenteMensualOrigen};
        String[] orderBy = {"remanenteMensualId"};
        boolean[] asc = {false};
        Criteria criteria = new Criteria(criteriaNombres, criteriaTipos, criteriaValores, orderBy, asc);
        remanenteMensualList = findByCriterias(criteria);
        return remanenteMensualList.get(remanenteMensualList.size() - 1);
    }
    

}
