
package principal;

import java.io.Serializable;

public class Alumno implements Serializable {
    
    private String idAlumno;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;
    private String  fechaNac;
    private String direccion;
    private String delegacion;
    private String noTelefono;

    public Alumno() {
    }

    
    //contructor 

    public Alumno(String idAlumno, String nombre, String paterno, String materno, String email, String fechaNac, String direccion, String delegacion, String noTelefono) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.delegacion = delegacion;
        this.noTelefono = noTelefono;
    }
    
    
    
    //metodos geter y seter
    public String getDelegacion() {
        return delegacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public String getNoTelefono() {
        return noTelefono;
    }

    
    public String getIdAAlumno() {
        return idAlumno;
    }

    
    
    public void setIdAAlumno(String idAAlumno) {
        this.idAlumno = idAAlumno;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

   

    public void setNoTelefono(String noTelefono) {
        this.noTelefono = noTelefono;
    }

    
    
    
    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", nombre=" + nombre +
                ", paterno=" + paterno + 
                ", materno=" + materno + ", email=" + email + ", fechaNac=" + fechaNac +
                ", direccion=" + direccion +
                ", delegacion=" + delegacion +
                ", noTelefono=" + noTelefono + '}';
    }
    

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    
    
    
    

    
    
            
    
}
