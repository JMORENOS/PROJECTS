
package principal;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static principal.VentanaPrincipal.RESULT;


public class Reportes extends javax.swing.JInternalFrame {

    
       public static final String RESULT
        = "reporte.pdf";
       
       public static final String RESULT1
        = "reportealumnos.pdf";
       
       public static final String RESULT2
        = "reportecursos.pdf";
       
 //############# VARIABLES PARA MANEJO DE BASE DE DATOS ########################
    //Guarda la consulta operacion a realizar
    String strConsultaSQL;
    //Apuntador a la conexion
    Connection conn = null; 
    //Para ejecutar operaciones SQL
    Statement estSQL1;
    //Para guardar los resultados de una operacion SELECT
    ResultSet rs;
    Color grisClaro = new Color( 230,230,230);  
    Color azulClaro = new Color( 124,195,255 );
     
    Document document;
    PdfWriter writer;
    
    
    
    
    ///reportes
    public Reportes() {
        initComponents();
    }
    
     public void abrirPDF() throws IOException{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT); 
    }
     
     public void abrirPDF1() throws IOException{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT1); 
    }
       public void abrirPDF2() throws IOException{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + RESULT2); 
    }
      
       
       
       //creacion del archivo pdf
      public void createPDF(){
        try
        {       //Hoja tamanio carta, rotarla (cambiar a horizontal)
            document = new Document( PageSize.LETTER.rotate() );
             
            writer = PdfWriter.getInstance(
            // that listens to the document
                    document,
                    // direccionar el PDF-stream a un archivo
                    new FileOutputStream(RESULT));
            document.open();
 
           // agregarMetaDatos(document);
            agregarContenido(document);
             
            document.close();
             
            JOptionPane.showMessageDialog(null,"Se ha generado el PDF");
                 
        } catch (FileNotFoundException | DocumentException | HeadlessException e) 
        {
            e.printStackTrace();
        }
    }
      
      
      //creacion del 2 pdf
       public void createPDF1(){
        try
        {       //Hoja tamanio carta, rotarla (cambiar a horizontal)
            document = new Document( PageSize.LETTER.rotate() );
             
            writer = PdfWriter.getInstance(
            // that listens to the document
                    document,
                    // direccionar el PDF-stream a un archivo
                    new FileOutputStream(RESULT1));
            document.open();
 
           // agregarMetaDatos(document);
            agregarContenido1(document);
             
            document.close();
             
            JOptionPane.showMessageDialog(null,"Se ha generado el PDF");
                 
        } catch (FileNotFoundException | DocumentException | HeadlessException e) 
        {
            e.printStackTrace();
        }
    }
      
        public void createPDF2(){
        try
        {       //Hoja tamanio carta, rotarla (cambiar a horizontal)
            document = new Document( PageSize.LETTER.rotate() );
             
            writer = PdfWriter.getInstance(
            // that listens to the document
                    document,
                    // direccionar el PDF-stream a un archivo
                    new FileOutputStream(RESULT2));
            document.open();
 
           // agregarMetaDatos(document);
            agregarContenido2(document);
             
            document.close();
             
            JOptionPane.showMessageDialog(null,"Se ha generado el PDF");
                 
        } catch (FileNotFoundException | DocumentException | HeadlessException e) 
        {
            e.printStackTrace();
        }
    }
      
      
      private void agregarContenido(Document document) throws DocumentException
    {
        Paragraph ParrafoHoja = new Paragraph();
                 
        // Agregamos una linea en blanco al principio del documento
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // Colocar un encabezado (en mayusculas)
        ParrafoHoja.add(new Paragraph("REPORTE ASIGNACION"));
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // 1.- AGREGAMOS LA TABLA
        agregarTabla(ParrafoHoja);
        //Agregar 2 lineas en blanco
        agregarLineasEnBlanco(ParrafoHoja, 2);
        // 2.- AGREGAMOS LA IMAGEN
        document.add(ParrafoHoja);
 
    }
      
       private void agregarContenido1(Document document) throws DocumentException
    {
        Paragraph ParrafoHoja = new Paragraph();
                 
        // Agregamos una linea en blanco al principio del documento
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // Colocar un encabezado (en mayusculas)
        ParrafoHoja.add(new Paragraph("REPORTE ALUMNOS"));
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // 1.- AGREGAMOS LA TABLA
        agregarTabla1(ParrafoHoja);
        //Agregar 2 lineas en blanco
        agregarLineasEnBlanco(ParrafoHoja, 2);
        // 2.- AGREGAMOS LA IMAGEN
        document.add(ParrafoHoja);
 
    }
    
       //generar el reporte de los cursos
       
        private void agregarContenido2(Document document) throws DocumentException
    {
        Paragraph ParrafoHoja = new Paragraph();
                 
        // Agregamos una linea en blanco al principio del documento
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // Colocar un encabezado (en mayusculas)
        ParrafoHoja.add(new Paragraph("REPORTE CURSOS"));
        agregarLineasEnBlanco(ParrafoHoja, 1);
        // 1.- AGREGAMOS LA TABLA
        agregarTabla2(ParrafoHoja);
        //Agregar 2 lineas en blanco
        agregarLineasEnBlanco(ParrafoHoja, 2);
        // 2.- AGREGAMOS LA IMAGEN
        document.add(ParrafoHoja);
 
    }
       
       private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) 
    {
        for (int i = 0; i < nLineas; i++) 
            parrafo.add(new Paragraph(" "));
    }
       
       
       
       //GENERAR LA TABLA DE ALUMNOS
        private void agregarTabla1(Paragraph parrafo) throws BadElementException 
    {
        //Anchos de las columnas
        float anchosFilas[] = {0.8f,1f,1f,1f,1f,0.8f,0.8f,1,0.6f};
        PdfPTable tabla = new PdfPTable(anchosFilas);
        String rotulosColumnas[] = {"idAlumno","Nombre","Paterno","Materno","Email","FechaNa","direccion","delegacion","noTelefono"};
        // Porcentaje que ocupa a lo ancho de la pagina del PDF
        tabla.setWidthPercentage(100);
        //Alineacion horizontal centrada
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        //agregar celda que ocupa las 9 columnas de los rotulos
        PdfPCell cell = new PdfPCell(new Paragraph("Tabla: Alumnos"));
        cell.setColspan(9);
        //Centrar contenido de celda
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
        //cell.setBackgroundColor (azulClaro);        
        tabla.addCell(cell);
 
        try
        {
            if ( ConectarBD() )
            {
                // Mostrar los rotulos de las columnas
                for(int i=0; i<rotulosColumnas.length; i++)
                {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //cell.setBackgroundColor (grisClaro);
                    tabla.addCell(cell);
                }
                 
                //Consultar la tabla empleados
                strConsultaSQL = "Select *from alumno;";
                //ejecutar consulta
                rs = estSQL1.executeQuery(strConsultaSQL);
 
                //Iterar Mientras haya una fila siguiente
            while (rs.next())
            {           //Agregar 9 celdas
                    cell = new PdfPCell(new Paragraph(rs.getString("idAlumno")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("nombre")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("paterno")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("materno")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("email")));
                    tabla.addCell(cell);
                    
                    cell = new PdfPCell(new Paragraph(rs.getString("fechaNac")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("direccion")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("delegacion")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("noTelefono")));
                    tabla.addCell(cell);
                    
                    
                    
            }
                //Cerrar los objetos de manejo de BD
                rs.close();
                estSQL1.close();
                conn.close();
           
            }       //Fin de if que comprueba si se pudo conectar
             
        }
        catch(Exception e) 
        {
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
        }
        //Agregar la tabla con los datos al parrafo que nos llego como entrada
        parrafo.add(tabla);
    } 
       
        
        
        //llenar tabla de asignacion
       private void agregarTabla(Paragraph parrafo) throws BadElementException 
    {
        //Anchos de las columnas
        float anchosFilas[] = {1f,1f,1f,1f};
        PdfPTable tabla = new PdfPTable(anchosFilas);
        String rotulosColumnas[] = {"tipoCurso","Horario","idCurso","idAlumno"};
        // Porcentaje que ocupa a lo ancho de la pagina del PDF
        tabla.setWidthPercentage(100);
        //Alineacion horizontal centrada
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        //agregar celda que ocupa las 9 columnas de los rotulos
        PdfPCell cell = new PdfPCell(new Paragraph("Tabla: Asignacion"));
        cell.setColspan(9);
        //Centrar contenido de celda
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
        //cell.setBackgroundColor (azulClaro);        
        tabla.addCell(cell);
 
        try
        {
            if ( ConectarBD() )
            {
                // Mostrar los rotulos de las columnas
                for(int i=0; i<rotulosColumnas.length; i++)
                {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //cell.setBackgroundColor (grisClaro);
                    tabla.addCell(cell);
                }
                 
                //Consultar la tabla empleados
                strConsultaSQL = "select * from asignacion ;";
                //ejecutar consula
                rs = estSQL1.executeQuery(strConsultaSQL);
 
                //Iterar Mientras haya una fila siguiente
            while (rs.next())
            {           //Agregar las celdas
                    cell = new PdfPCell(new Paragraph(rs.getString("tipoCurso")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("horario")));
                    tabla.addCell(cell);
                   
                    cell = new PdfPCell(new Paragraph(rs.getString(("idCurso"))));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString(("idAlumno"))));
                    tabla.addCell(cell);
                    
            }
                //Cerrar los objetos de manejo de BD
                rs.close();
                estSQL1.close();
                conn.close();
           
            }       //Fin de if que comprueba si se pudo conectar
             
        }
        catch(Exception e) 
        {
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
        }
        //Agregar la tabla con los datos al parrafo que nos llego como entrada
        parrafo.add(tabla);
    }  //Fin del metodo que crea la tabla
       
       
       
       //tabla de Carrera
          private void agregarTabla2(Paragraph parrafo) throws BadElementException 
    {
        
                
        //Anchos de las columnas
        float anchosFilas[] = {0.5f,0.5f,0.5f,0.5f,0.5f};
        PdfPTable tabla = new PdfPTable(anchosFilas);
        String rotulosColumnas[] = {"idCarrera","Nombre del Curso","fechainicio","fechafin","couta"};
        // Porcentaje que ocupa a lo ancho de la pagina del PDF
        tabla.setWidthPercentage(100);
        //Alineacion horizontal centrada
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        //agregar celda que ocupa las 9 columnas de los rotulos
        PdfPCell cell = new PdfPCell(new Paragraph("Tabla: Cursos"));
        cell.setColspan(9);
        //Centrar contenido de celda
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
        //cell.setBackgroundColor (azulClaro);        
        tabla.addCell(cell);
 
        try
        {
            if ( ConectarBD() )
            {
                // Mostrar los rotulos de las columnas
                for(int i=0; i<rotulosColumnas.length; i++)
                {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    //cell.setBackgroundColor (grisClaro);
                    tabla.addCell(cell);
                }
                 
                //Consultar la tabla cursos
                strConsultaSQL = "Select *from carrera;";
                //ejecutar consulta
                rs = estSQL1.executeQuery(strConsultaSQL);
 
                //Iterar Mientras haya una fila siguiente
            while (rs.next())
            {           //Agregar 9 celdas
                    
                    cell = new PdfPCell(new Paragraph(rs.getString("idCarrera")));
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("nombreCarrera")));
                    tabla.addCell(cell);
                    cell= new PdfPCell(new Paragraph(rs.getString("fechainicio")));
                    tabla.addCell(cell);
                    cell= new PdfPCell(new Paragraph(rs.getString("fechafin")));
                    tabla.addCell(cell);
                    cell= new PdfPCell(new Paragraph(rs.getString("couta")));
                    tabla.addCell(cell);
                    
                    
                    
            }
                //Cerrar los objetos de manejo de BD
                rs.close();
                estSQL1.close();
                conn.close();
           
            }       //Fin de if que comprueba si se pudo conectar
             
        }
        catch(Exception e) 
        {
            System.out.println("Excepcion al ejecutar CONSULTA!!!");
        }
        //Agregar la tabla con los datos al parrafo que nos llego como entrada
        parrafo.add(tabla);
    } 
       
        public boolean ConectarBD() throws Exception
    {
        try
        {       
                //Aqui se instancia a la otra clase --> CrearConexion.java               
                //y se le mandan en el constructor los datos de conexion
                Conexion con  = new Conexion();
                //Obtiene una referencia a la conexion abierta
                conn = con.getConnection();
             
            //Comprobar si hay una refencia valida
            if(conn!=null)
            {
                    //Mostrar MSG al usuario de la conexion se ha establecido
                    System.out.println("Conexion establecida");
                    //Se prepara para ejecutar sentencias en la conexion recien abierta
                estSQL1 = conn.createStatement();
                    return true;
            }
            else
                return false;
                 
         }
        catch (SQLException e)
        {
             
          // the above drop statements will throw exceptions
          // if the types and tables did not exist before. Just ingore it.
          System.out.println("Error en la conexion!!!");
          e.printStackTrace();
          return false;
        }
                 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reportes");

        jButton1.setText("Generar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Generar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Reporte de Alumnos");

        jLabel2.setText("Reporte de Cursos");

        jLabel3.setText("Reporte de Asignacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel3))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

           try {
               createPDF();
               abrirPDF();
           } catch (IOException ex) {
               Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    //generar reporte de alumnos
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
               createPDF1();
               abrirPDF1();
           } catch (IOException ex) {
               Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
           }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
               createPDF2();
               abrirPDF2();
           } catch (IOException ex) {
               Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
           }

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
