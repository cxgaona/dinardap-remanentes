package ec.gob.dinardap.remanente.controller;

import ec.gob.dinardap.autorizacion.constante.SemillaEnum;
import ec.gob.dinardap.autorizacion.util.EncriptarCadenas;
import ec.gob.dinardap.remanente.modelo.Usuario;
import ec.gob.dinardap.remanente.servicio.UsuarioServicio;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "usuarioCtrl")
@ViewScoped
public class UsuarioCtrl extends BaseCtrl implements Serializable {

    private Integer usuarioId;
    private Usuario usuario;
    private String tituloPagina;

    private String contraseñaActual;
    private String contraseñaNueva1;
    private String contraseñaNueva2;

    @EJB
    private UsuarioServicio usuarioServicio;

    @PostConstruct
    protected void init() {
        System.out.println("===Inicio de página===");
        usuarioId = Integer.parseInt(this.getSessionVariable("usuarioId"));
        System.out.println("idUsuario = " + usuarioId);
        usuario = new Usuario();
        usuario = usuarioServicio.findByPk(usuarioId);
        tituloPagina = "Usuario: " + usuario.getNombre();
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Usuario: " + usuario.getContrasena());
        System.out.println("Usuario: " + usuario.getInstitucionId().getNombre());
    }

    public void actualizarContraseña() {
        System.out.println("===Actualizando contraseña===");
        String contraseñaActualEncriptada = EncriptarCadenas.encriptarCadenaSha1(SemillaEnum.SEMILLA_REMANENTE.getSemilla() + contraseñaActual);
        if (usuario.getContrasena().equals(contraseñaActualEncriptada)) {
            System.out.println("Proceso de actualizacion para la contraseña");
            if (contraseñaNueva1.equals(contraseñaNueva2)) {
                usuario.setContrasena(EncriptarCadenas.encriptarCadenaSha1(SemillaEnum.SEMILLA_REMANENTE.getSemilla() + contraseñaNueva1));
                usuarioServicio.editUsuario(usuario);                
                addInfoMessage("Contraseña Actualizada satisfactoriamente", "");
            } else {
                addErrorMessage("1","La nueva contraseña no coincide", "No sale el detalle");
            }
        } else {
            addErrorMessage("2","Contraseña actual incorrecta", "No sale el detalle");
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTituloPagina() {
        return tituloPagina;
    }

    public void setTituloPagina(String tituloPagina) {
        this.tituloPagina = tituloPagina;
    }

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getContraseñaNueva1() {
        return contraseñaNueva1;
    }

    public void setContraseñaNueva1(String contraseñaNueva1) {
        this.contraseñaNueva1 = contraseñaNueva1;
    }

    public String getContraseñaNueva2() {
        return contraseñaNueva2;
    }

    public void setContraseñaNueva2(String contraseñaNueva2) {
        this.contraseñaNueva2 = contraseñaNueva2;
    }

}
