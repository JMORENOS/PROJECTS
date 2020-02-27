
package principal;


public class Asignacion {
  
    private String tipoCurso;
    private String horario;
    
    //atributos extras
    private String idAlumno;
     private String idCarrera;
    
    
     //constructor
    public Asignacion() {
    }

    
    //contructor completo

    public Asignacion(String tipoCurso, String horario, String idAlumno, String idCarrera) {
        this.tipoCurso = tipoCurso;
        this.horario = horario;
        this.idAlumno = idAlumno;
        this.idCarrera = idCarrera;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
  
    

    //metodo getters
    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getHorario() {
        return horario;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

   

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

   

   
    
    @Override
     public String toString() {
        return "Asignacion{" + "tipoCurso=" + tipoCurso + 
                ", Horario=" + horario + 
                ", idAlumno=" + idAlumno + 
                ", idCarrera=" + idCarrera + 
                 '}';
    }
        
        
        
   
    
    
}

    
    
    
