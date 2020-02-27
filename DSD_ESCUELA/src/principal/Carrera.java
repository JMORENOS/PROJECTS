
package principal;


public class Carrera {
    
    private String idCarrera;
    private String nombreCarrera;
    
    //atributos extras
    private String fechainicio;
     private String fechafin;
     private String couta;
    
     //constructor
    public Carrera() {
    }

    
    //contructor completo
    public Carrera(String idCarrera, String nombreCarrera, String fechainicio, String fechafin,String couta ) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.fechainicio= fechainicio;
        this.fechafin= fechafin;
        this.couta=couta;
    }
    

    //metodo getters
    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setCouta(String couta) {
        this.couta = couta;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getCouta() {
        return couta;
    }

    
    
    
    @Override
     public String toString() {
        return "Carrera{" + "idCarrera=" + idCarrera + ", nombreCarrera=" + nombreCarrera + 
                ", fechainicio=" + fechainicio + 
                ", fechafin=" + fechafin + 
                ", couta=" + couta + '}';
    }
        
        
        
   
    
    
}
